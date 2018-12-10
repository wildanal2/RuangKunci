package com.keyroom.com.keyroom.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.keyroom.com.keyroom.Model.PostPutDellPeminjaman;
import com.keyroom.com.keyroom.Model.PostPutDellUser;
import com.keyroom.com.keyroom.Model.User;
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

public class MeminjamKelas extends AppCompatActivity {

    String nim_ok="",id_user,imagePath = "";
    EditText nim,nama,kelas;
    Button btn_find; ImageView img;
    FloatingActionButton btn_camera;
    ApiInterface mApi;
    User mUser;
    private Intent intnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meminjamkelas);

        intnt = getIntent();
        init_layout();
        init_listener();
    }

    void init_layout(){
        // data dari layout
        img = findViewById(R.id.photo_peminjam);
        nim = findViewById(R.id.editTextcarinim);
        nama = findViewById(R.id.editTextNama);
        kelas = findViewById(R.id.editTextKelas);
        btn_find = findViewById(R.id.buttoncari);
        btn_camera = findViewById(R.id.btn_cameraopen);

        mApi = ApiClient.getClient().create(ApiInterface.class);


    }

    void init_listener(){

        //eksekusi button cari
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nim_ok.equals("") && btn_find.getText().equals("Pinjamkan")){
                    meminjam_kelas();
                }else{
                    pencariannim();
                }
            }
        });

        //event jika nim diedit
        nim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.equals(nim_ok) && !nim_ok.equals("")){
                    btn_find.setBackgroundResource(R.drawable.btn_shapepinjam);
                    btn_find.setText("Pinjamkan");
                }else {
                    btn_find.setBackgroundResource(R.drawable.btn_shapecari);
                    btn_find.setText("Cari");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // jika kamera ditekan
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checking camera availability
                if (!isDeviceSupportCamera()) {
                    Toast.makeText(getApplicationContext(), "Camera di device anda tidak tersedia", Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    captureImage();
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 100 && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            img.setImageBitmap(imageBitmap);
//            //Picasso.get().load(String.valueOf(imageBitmap)).into(mImageView);
//        }
        if (resultCode == RESULT_OK && requestCode == 200) {
            if (data == null) {
                Toast.makeText(getApplicationContext(), "Gambar Gagal Di load",Toast.LENGTH_LONG).show();
                return;
            }
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn,null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                Glide.with(getApplicationContext()).load(new File(imagePath)).into(img);
                cursor.close();
            }else {
                Toast.makeText(getApplicationContext(), "Gambar Gagal Di load",Toast.LENGTH_LONG).show();
            }
        }

    }

    private void captureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(takePictureIntent, 200);
        }
    }

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    private void pencariannim(){
        //progess dialog
        final ProgressDialog process = new ProgressDialog(MeminjamKelas.this);
        process.setTitle("Please Wait ..");
        process.setMessage("finding user..");
        process.setCancelable(false);
        process.show();

        Call<PostPutDellUser> cari = mApi.cariUser(nim.getText().toString());
        cari.enqueue(new Callback<PostPutDellUser>() {
            @Override
            public void onResponse(Call<PostPutDellUser> call, Response<PostPutDellUser> response) {
                // jika sudah ketemu
                if (response.body().getStatus().equals("200")){
                    mUser = response.body().getmUser();
                    nim_ok=mUser.getNim();
                    nama.setText(mUser.getNama());
                    kelas.setText(mUser.getKelas());
                    id_user= mUser.getNim();
                    // setting tombol layout
                    btn_find.setBackgroundResource(R.drawable.btn_shapepinjam);
                    btn_find.setText("Pinjamkan");
                }else {
                    nama.setText("");
                    kelas.setText("");
                    nim_ok="";
                    btn_find.setBackgroundResource(R.drawable.btn_shapecari);
                    btn_find.setText("Cari");
                    AlertDialog.Builder alertDlg = new AlertDialog.Builder(MeminjamKelas.this);
                    alertDlg.setMessage("'nim' not found");
                    alertDlg.show();
                }
                process.hide();
            }

            @Override
            public void onFailure(Call<PostPutDellUser> call, Throwable t) {
                process.hide();
                Toast.makeText(getApplicationContext(),"Something Worng "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void meminjam_kelas(){
        //progess dialog
        final ProgressDialog process = new ProgressDialog(MeminjamKelas.this);
        process.setTitle("Please Wait ..");
        process.setMessage("Processing ..");
        process.setCancelable(false);
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

        RequestBody reqid_kelas = MultipartBody.create(MediaType.parse("multipart/form-data"),intnt.getStringExtra("id_kelas"));
        RequestBody reqid_user = MultipartBody.create(MediaType.parse("multipart/form-data"),id_user);
        RequestBody reqstatus = MultipartBody.create(MediaType.parse("multipart/form-data"),"dipinjam");
        RequestBody reqid_admin = MultipartBody.create(MediaType.parse("multipart/form-data"),"3");


        Call<PostPutDellPeminjaman> cari = mApi.meminjamBaru(body_img,reqid_kelas,reqid_user,reqstatus,reqid_admin);
        cari.enqueue(new Callback<PostPutDellPeminjaman>() {
            @Override
            public void onResponse(Call<PostPutDellPeminjaman> call, Response<PostPutDellPeminjaman> response) {
                process.hide();
                Toast.makeText(getApplicationContext(),"Sukses",Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<PostPutDellPeminjaman> call, Throwable t) {
                process.hide();
                Toast.makeText(getApplicationContext(),"Something Worng  "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

}