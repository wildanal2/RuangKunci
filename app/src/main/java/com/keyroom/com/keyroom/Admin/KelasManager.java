package com.keyroom.com.keyroom.Admin;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keyroom.com.keyroom.Adapter.RecyclerAdapterKelasManager;
import com.keyroom.com.keyroom.Listener.ClickListener;
import com.keyroom.com.keyroom.Listener.RecyclerTouchListener;
import com.keyroom.com.keyroom.Model.GetKelas;
import com.keyroom.com.keyroom.Model.Kelas;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.keyroom.com.keyroom.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KelasManager extends AppCompatActivity {

    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterKelasManager mAdapter;
    private List<Kelas> mKelas = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_admin_kelasmanger);

        init_recycler(); // initialize isi recycler
        init_listener(); // initialize touchListener
    }

    public void init_listener(){

        // recycler touchlistener
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(KelasManager.this, mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int posi) {

                Intent i = new Intent(KelasManager.this,DetailRuangManagerFragmentAdmin.class);
                i.putExtra("id",mKelas.get(posi).getId());// mengisi string yang akan dikirim
                startActivity(i);
            }
            @Override
            public void onLongClick(View view, int posi) { }
        }));

        // tambah data touchlistener
        FloatingActionButton btn_addkelas = findViewById(R.id.btn_newkelas_admin);
        btn_addkelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(KelasManager.this,AddKelas.class);
                startActivity(i);
            }
        });

    }



    public void init_recycler(){
        // mengambil dari layout
        mRecyclerView = findViewById(R.id.rcycler_kelasmanager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GetKelas> getKelas = mApiInterface.getKelas();
        getKelas.enqueue(new Callback<GetKelas>() {
            @Override
            public void onResponse(Call<GetKelas> call, Response<GetKelas> response) {
                mKelas = response.body().getListDataKelas();

                mAdapter = new RecyclerAdapterKelasManager(mKelas,getApplicationContext());
                mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetKelas> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }


}
