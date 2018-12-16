package com.keyroom.com.keyroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.keyroom.com.keyroom.Admin.MainAdminActivity;
import com.keyroom.com.keyroom.User.MainActivity;

public class MainDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo);

        Button usr = findViewById(R.id.buttonusr);
        usr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);

            }
        });

        Button adm = findViewById(R.id.buttonadm);
        adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainAdminActivity.class);
                startActivity(i);

            }
        });
    }
}
