package com.keyroom.com.keyroom.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.keyroom.com.keyroom.Model.PostPutDellUser;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.keyroom.com.keyroom.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class NewMahasiswa extends AppCompatActivity {

    EditText nim, nama, email, password;
    Button btn_save;
    private ApiInterface mApiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mahasiswa);
        init_layout();
        init_listener();
    }

    void init_layout(){
        nim = findViewById(R.id.editTextNim);
        nama = findViewById(R.id.editTextNama);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    void init_listener(){
        btn_save = findViewById(R.id.buttonSave);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDellUser> NewUser = mApiInterface.postUser(nim.getText().toString(),
                                                                    nama.getText().toString(),
                                                                    email.getText().toString(),
                                                                    password.getText().toString(),
                                                                    "","2");

                NewUser.enqueue(new Callback<PostPutDellUser>() {
                    @Override
                    public void onResponse(Call<PostPutDellUser> call, Response<PostPutDellUser> response) {
                        Toast.makeText(getApplicationContext(),"save sukses",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDellUser> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"save gagal",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}
