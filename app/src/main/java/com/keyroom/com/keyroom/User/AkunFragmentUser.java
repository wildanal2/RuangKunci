package com.keyroom.com.keyroom.User;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.keyroom.com.keyroom.Adapter.RecyclerAdapterJadwal;
import com.keyroom.com.keyroom.Adapter.RecyclerAdapterRiwayatUser;
import com.keyroom.com.keyroom.Admin.AddKelas;
import com.keyroom.com.keyroom.Admin.DetailRuangManagerFragmentAdmin;
import com.keyroom.com.keyroom.Config.SessionManagement;
import com.keyroom.com.keyroom.Model.GetDetailKelas;
import com.keyroom.com.keyroom.Model.GetPeminjaman;
import com.keyroom.com.keyroom.Model.Peminjaman;
import com.keyroom.com.keyroom.Model.PostPutDellUser;
import com.keyroom.com.keyroom.Model.User;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.keyroom.com.keyroom.Rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class AkunFragmentUser extends Fragment {

    User nUser;
    View v;
    ApiInterface mApiInterface;
    //    sesion management
    SessionManagement mSesion;
    HashMap<String,String> ses_user;

    TextView nama, nim, kelas;
    String imagePath = "";
    ImageView foto,btn_editfoto,fotobaru;
    RecyclerView mRV ;
    List<Peminjaman> mlistPeminjaman;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_akun_user,container,false);

        initialize();
        initialize_Listener();
        return v;
    }

    public void initialize_Listener(){
        ImageView btn_setting = v.findViewById(R.id.btn_setting_akun);

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(new AkunFragmentUser(),"AkunFragment")
                        .addToBackStack("AkunFragment")
                        .replace(R.id.frament_container_user,new Setting())
                        .commit();
            }
        });

    }

    public void initialize(){
        mSesion = new SessionManagement(getContext());
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ses_user = mSesion.getUserInformation();

        nama = v.findViewById(R.id.txtnamaakun);
        nim = v.findViewById(R.id.txtnimakun);
        kelas = v.findViewById(R.id.txtkelasakun);
        foto = v.findViewById(R.id.images);
        mRV =  v.findViewById(R.id.recyler_riwayatby);
        btn_editfoto = v.findViewById(R.id.edit_foto);

        Call<PostPutDellUser> detailmhs = mApiInterface.detailUser(ses_user.get(SessionManagement.KEY_ID).toString());
        detailmhs.enqueue(new Callback<PostPutDellUser>() {
            @Override
            public void onResponse(Call<PostPutDellUser> call, Response<PostPutDellUser> response) {
                nUser = response.body().getmUser();

                Picasso.get().load(ApiClient.BASE_ASSETS+nUser.getImg())
                        .placeholder(R.drawable.ic_profil)
                        //               .error(R.drawable.ic_profil)
                        .into(foto);
                nama.setText("Nama : "+nUser.getNama());
                nim.setText("Nim : "+nUser.getNim());
                kelas.setText("Kelas : "+nUser.getKelas());

                Call<GetPeminjaman> riwayat = mApiInterface.riwayatbynim(nUser.getNim());
                riwayat.enqueue(new Callback<GetPeminjaman>() {
                    @Override
                    public void onResponse(Call<GetPeminjaman> call, Response<GetPeminjaman> response) {
                        mlistPeminjaman = response.body().getListPeminjaman();

                        RecyclerAdapterRiwayatUser mAdapter = new RecyclerAdapterRiwayatUser(mlistPeminjaman);
                        mRV.setLayoutManager(new LinearLayoutManager(getActivity()));
                        mRV.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetPeminjaman> call, Throwable t) {
                        Toast.makeText(getContext(),"Something Worng in "+t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onFailure(Call<PostPutDellUser> call, Throwable t) {
                Toast.makeText(getContext(),"Something Worng in "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        btn_editfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // conf menampilkan dialog
                final Dialog newfoto = new Dialog(getActivity());
                newfoto.requestWindowFeature(Window.FEATURE_NO_TITLE);
                newfoto.setContentView(R.layout.dialog_gantifoto);
                newfoto.setCancelable(false);

                Button btn_save = newfoto.findViewById(R.id.btn_simpan);
                Button btn_batal = newfoto.findViewById(R.id.btn_batal);
                fotobaru = newfoto.findViewById(R.id.fotoini);

                btn_batal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newfoto.hide();
                    }
                });
                fotobaru.setOnClickListener(new View.OnClickListener() {
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
                        final ProgressDialog process = new ProgressDialog(getActivity());
                        process.setTitle("Please Wait ..");
                        process.setMessage("Processing uploading file..");
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
                        RequestBody id = MultipartBody.create(MediaType.parse("multipart/form-data"),ses_user.get(SessionManagement.KEY_ID).toString());

                        Call<PostPutDellUser> postphoto = mApiInterface.fotobaru(body_img,id);
                        postphoto.enqueue(new Callback<PostPutDellUser>() {
                            @Override
                            public void onResponse(Call<PostPutDellUser> call, Response<PostPutDellUser> response) {
                                Toast.makeText(getContext(),"SUksess "+response.body().getStatus(),Toast.LENGTH_SHORT).show();
                                process.hide();
                                newfoto.hide();
                                initialize();
                            }

                            @Override
                            public void onFailure(Call<PostPutDellUser> call, Throwable t) {
                                Toast.makeText(getContext(),"Something Worngg "+ t.getMessage(),Toast.LENGTH_SHORT).show();
                                process.hide();
                            }
                        });
                    }
                });

                newfoto.show();

            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 10) {
            if (data == null) {
                Toast.makeText(getContext(), "Gambar Gagal Di load",Toast.LENGTH_LONG).show();
                return;
            }
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn,null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                Glide.with(getActivity()).load(new File(imagePath)).into(fotobaru);
                cursor.close();
            }else {
                Toast.makeText(getContext(), "Gambar Gagal Di load",Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        initialize();
    }
}
