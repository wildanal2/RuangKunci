package com.keyroom.com.keyroom.User;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.keyroom.com.keyroom.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frament_container_user,new HomeFragmentUser()).commit();
        BottomNavigationView botNav = findViewById(R.id.bottom_navigation);
        botNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedfragment = null; // fragmnet yang akan ditampilkan

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedfragment = new HomeFragmentUser();
                            break;
                        case R.id.nav_search:
                            selectedfragment = new SearchFragmentUser();
                            break;
                        case R.id.nav_akun:
                            selectedfragment = new AkunFragmentUser();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frament_container_user,selectedfragment).commit();
                    return true;
                }
            };
}
