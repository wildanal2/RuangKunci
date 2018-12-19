package com.keyroom.com.keyroom.Admin;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;

import com.keyroom.com.keyroom.Adapter.RecyclerAdapterUser;
import com.keyroom.com.keyroom.Model.GetUser;
import com.keyroom.com.keyroom.Model.User;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.keyroom.com.keyroom.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManager extends AppCompatActivity {
    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterUser mAdapter;
    private List<User> mUser = new ArrayList<>();
    FloatingActionButton newmhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);

        initialize();
        initialize_listener();
    }

    private  void initialize(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        mRecyclerView = findViewById(R.id.recyler_user_manager);

        Call<GetUser> getUser = mApiInterface.getUser();
        getUser.enqueue(new Callback<GetUser>() {
            @Override
            public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                mUser = response.body().getAllUser();

                mAdapter = new RecyclerAdapterUser(mUser,getApplicationContext());
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });

        SearchView searchkls = findViewById(R.id.search);
        searchkls.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

        newmhs = findViewById(R.id.btn_new_mahasiswa);
    }

    private void initialize_listener(){
        newmhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(UserManager.this,NewMahasiswa.class);
                startActivity(ii);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initialize();
    }
}
