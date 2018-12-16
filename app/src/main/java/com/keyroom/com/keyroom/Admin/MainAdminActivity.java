package com.keyroom.com.keyroom.Admin;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.keyroom.com.keyroom.Adapter.BottomNavigationViewHelper;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.User.HomeFragmentUser;
import com.keyroom.com.keyroom.User.SearchFragmentUser;


public class MainAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        getSupportFragmentManager().beginTransaction().replace(R.id.frament_container_admin,new HomeAdmin()).commit();
        BottomNavigationView botNav = findViewById(R.id.bottom_navigation_admin);
        botNav.setOnNavigationItemSelectedListener(navListener);
        BottomNavigationViewHelper.removeShiftMode(botNav);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedfragment = null; // fragmnet yang akan ditampilkan

                    //clear stack
                    getSupportFragmentManager().popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    switch (item.getItemId()){
                        case R.id.nav_home_admin:
                            selectedfragment = new HomeAdmin();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.frament_container_admin,selectedfragment)
                                    .commit();
                            break;
                        case R.id.nav_search_admin:
                            selectedfragment = new Peminjaman();
                            getSupportFragmentManager().beginTransaction()
                                    .add(new HomeAdmin(),"HomeFragment")
                                    .addToBackStack("HomeFragment")
                                    .replace(R.id.frament_container_admin,selectedfragment)
                                    .commit();
                            break;
                        case R.id.nav_kelas_admin:
                            selectedfragment = new KelasManager();
                            getSupportFragmentManager().beginTransaction()
                                    .add(new HomeAdmin(),"HomeFragment")
                                    .addToBackStack("HomeFragment")
                                    .replace(R.id.frament_container_admin,selectedfragment)
                                    .commit();
                            break;
                        case R.id.nav_setting_admin:
                            selectedfragment = new Setting();
                            getSupportFragmentManager().beginTransaction()
                                    .add(new HomeAdmin(),"HomeFragment")
                                    .addToBackStack("HomeFragment")
                                    .replace(R.id.frament_container_admin,selectedfragment)
                                    .commit();
                            break;
                    }
                    return true;
                }
            };

}
