package com.keyroom.com.keyroom.Admin;


import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.keyroom.com.keyroom.Model.PostPutDellKelas;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.keyroom.com.keyroom.Rest.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class AddKelas extends AppCompatActivity {

    ApiInterface mApi;
    FloatingActionButton btnphoto;
    Button btn_save;
    String imagePath = "";
    ImageView mimage;
    EditText nama_ruang,lokasi_ruang,nama_Lab;
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_admin_addkelas);

        tb = findViewById(R.id.toolbarpeminjaman);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");

        init_clicklistener();
    }

    private void init_clicklistener(){
        mApi = ApiClient.getClient().create(ApiInterface.class);
        // mengambil data dari layout
        mimage = findViewById(R.id.image_addkelas);
        nama_ruang = findViewById(R.id.et_namaruangnew_admin);
        nama_Lab = findViewById(R.id.et_namalabruangnew_admin);
        lokasi_ruang = findViewById(R.id.et_lokasiruangnew_admin);

        btnphoto = findViewById(R.id.btn_addphotokelas_admin);
        btn_save = findViewById(R.id.btn_savekelas_admin);

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(galleryIntent, "Pilih Gambar Untuk Di upload");
                startActivityForResult(intentChoose, 10);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // progess dialog
                final ProgressDialog process = new ProgressDialog(AddKelas.this);
                process.setTitle("Please Wait ..");
                process.setMessage("uploading file..");
                process.show();

                MultipartBody.Part body_img = null;
                // jika file upload tidak kosong
                if (!imagePath.isEmpty()){
                    //File creating from selected URL
                    File file = new File(imagePath);
                    // create RequestBody instance from file
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    // MultipartBody.Part is used to send also the actual file name
                    body_img = MultipartBody.Part.createFormData("img", file.getName(), requestFile);
                }
                RequestBody reqRuang = MultipartBody.create(MediaType.parse("multipart/form-data"),nama_ruang.getText().toString());
                RequestBody reqnama_lab = MultipartBody.create(MediaType.parse("multipart/form-data"),nama_Lab.getText().toString());
                RequestBody reqlokasi = MultipartBody.create(MediaType.parse("multipart/form-data"),lokasi_ruang.getText().toString());

                Call<PostPutDellKelas> newKelas = mApi.postKelas(body_img,reqRuang,reqnama_lab,reqlokasi);
                newKelas.enqueue(new Callback<PostPutDellKelas>() {
                    @Override
                    public void onResponse(Call<PostPutDellKelas> call, Response<PostPutDellKelas> response) {
                        process.hide();
                        Toast.makeText(AddKelas.this, "Sukses :"+response.body().getStatus()+" mssage : "+response.body().getMessage(),Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDellKelas> call, Throwable t) {
                        process.hide();
                        Toast.makeText(AddKelas.this, "Something Worng  "+t.getMessage(),Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 10) {
            if (data == null) {
                Toast.makeText(AddKelas.this, "Gambar Gagal Di load",Toast.LENGTH_LONG).show();
                return;
            }
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn,null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                Glide.with(AddKelas.this).load(new File(imagePath)).into(mimage);
                cursor.close();
            }else {
                Toast.makeText(AddKelas.this, "Gambar Gagal Di load",Toast.LENGTH_LONG).show();
            }
        }
    }

}
