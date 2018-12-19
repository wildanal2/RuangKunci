package com.keyroom.com.keyroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.keyroom.com.keyroom.Admin.MainAdminActivity;
import com.keyroom.com.keyroom.Config.SessionManagement;
import com.keyroom.com.keyroom.Model.PostPutDellUser;
import com.keyroom.com.keyroom.Model.User;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.keyroom.com.keyroom.Rest.ApiInterface;
import com.keyroom.com.keyroom.User.MainActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    User muser;
    EditText password,username;
    private ApiInterface mApiInterface;
    Button btn_login;
    //    sesion management
    SessionManagement mSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        mSesion = new SessionManagement(getApplicationContext());
        username=findViewById(R.id.editTextusername);
        password=findViewById(R.id.editTextpasswod);
        btn_login=findViewById(R.id.buttonLogin);

        if (mSesion.isLoggedIn()){
            HashMap<String,String> user = mSesion.getUserInformation();

            if (user.get(SessionManagement.KEY_LEVEL).equals("1")){
//                masuk ke admin activity
                Intent i = new Intent(getApplicationContext(),MainAdminActivity.class);
                startActivity(i);
                finish();
            }else if (user.get(SessionManagement.KEY_LEVEL).equals("2")) {
//                .. masuk ke User activity
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDellUser> login=mApiInterface.getLogin(username.getText().toString(),password.getText().toString());
                login.enqueue(new Callback<PostPutDellUser>() {
                    @Override
                    public void onResponse(Call<PostPutDellUser> call, Response<PostPutDellUser> response) {

                        String login=response.body().getStatus();
                        if (login.equals("okee")) {
                            muser=response.body().getmUser();
                            mSesion.createLoginSession(muser.getId(),muser.getLevel());

                            if (muser.getLevel().equals("2")){
                                Intent i= new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            }if (muser.getLevel().equals("1")){
                                Intent i= new Intent(getApplicationContext(), MainAdminActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Username atau Paassworrd yang anda masukan salah",Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<PostPutDellUser> call, Throwable t) {

                    }
                });

            }
        });

    }
}
