package com.keyroom.com.keyroom.User;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.keyroom.com.keyroom.Config.SessionManagement;
import com.keyroom.com.keyroom.Model.PostPutDellUser;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.keyroom.com.keyroom.Rest.ApiInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GantiPassword extends AppCompatActivity {
    //    sesion management
    SessionManagement mSesion;
    HashMap<String,String> ses_user;
    ApiInterface mApiInterface;

    ImageView btn_back,btn_save;
    EditText newpass,repass,currentpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_password);

        mSesion = new SessionManagement(GantiPassword.this);
        ses_user = mSesion.getUserInformation();
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btn_back = findViewById(R.id.btn_back);
        btn_save = findViewById(R.id.btn_Save);
        newpass = findViewById(R.id.etnew_pass);
        repass = findViewById(R.id.etrenew_pass);
        currentpass = findViewById(R.id.etcurr_pass);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (newpass.getText().toString().equals(repass.getText().toString()) && newpass.getText().length()!=0){
                    // progess dialog
                    final ProgressDialog process = new ProgressDialog(GantiPassword.this);
                    process.setTitle("Please Wait ..");
                    process.setMessage("Processing update..");
                    process.show();

                    Call<PostPutDellUser> newpP = mApiInterface.newpassword(ses_user.get(SessionManagement.KEY_ID),newpass.getText().toString(),currentpass.getText().toString());
                    newpP.enqueue(new Callback<PostPutDellUser>() {
                        @Override
                        public void onResponse(Call<PostPutDellUser> call, Response<PostPutDellUser> response) {
                            final AlertDialog.Builder notif = new AlertDialog.Builder(GantiPassword.this);
                            notif.setTitle("Update Password");

                            if (response.body().getStatus().equals("200")){
                                notif.setMessage(" "+response.body().getMessage());
                                notif.setCancelable(true);
                                notif.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                    }
                                });
                                notif.show();
                            }else {
                                Toast.makeText(GantiPassword.this,"oldd PAss worngg ",Toast.LENGTH_SHORT).show();
                            }
                            process.hide();
                        }

                        @Override
                        public void onFailure(Call<PostPutDellUser> call, Throwable t) {
                            Toast.makeText(GantiPassword.this,"Something Worng in "+t.getMessage(),Toast.LENGTH_SHORT).show();
                            process.hide();
                        }
                    });
                }else {
                    Toast.makeText(GantiPassword.this,"Re password Tidak benar",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
