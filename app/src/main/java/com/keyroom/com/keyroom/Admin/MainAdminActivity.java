package com.keyroom.com.keyroom.Admin;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.User.HomeFragmentUser;
import com.keyroom.com.keyroom.User.SearchFragmentUser;


public class MainAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        BottomNavigationView botNav = findViewById(R.id.bottom_navigation_admin);
        botNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedfragment = null; // fragmnet yang akan ditampilkan

                    switch (item.getItemId()){
                        case R.id.nav_home_admin:
                            selectedfragment = new HomeFragmentUser();
                            break;
                        case R.id.nav_search_admin:
                            selectedfragment = new SearchFragmentUser();
                            break;
                        case R.id.nav_kelas_admin:
                            selectedfragment = new KelasManagerFragmentAdmin();
                            break;
                        case R.id.nav_setting_admin:
                            selectedfragment = new setting_admin();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frament_container_admin,selectedfragment).commit();
                    return true;
                }
            };

}
