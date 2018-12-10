package com.keyroom.com.keyroom.Admin;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

public class KelasManager extends Fragment {

    View v;
    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterKelasManager mAdapter;
    private List<Kelas> mKelas = new ArrayList<>();;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_admin_kelasmanger,container,false);

        init_recycler(); // initialize isi recycler
        init_listener(); // initialize touchListener
        return v;
    }

    public void init_listener(){

        // recycler touchlistener
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int posi) {
                Bundle bundle = new Bundle();
                bundle.putString("id",mKelas.get(posi).getId());  // mengisi string yang akan dikirim

                DetailRuangManagerFragmentAdmin detailfragAdm = new DetailRuangManagerFragmentAdmin();
                detailfragAdm.setArguments(bundle); // memasukkan bundle ke fragment detail ruangan

                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.frament_container_admin,detailfragAdm).addToBackStack(null).commit();
            }
            @Override
            public void onLongClick(View view, int posi) { }
        }));

        // tambah data touchlistener
        FloatingActionButton btn_addkelas = v.findViewById(R.id.btn_newkelas_admin);
        btn_addkelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.frament_container_admin,new AddKelas()).addToBackStack(null).commit();
            }
        });

    }



    public void init_recycler(){
        // mengambil dari layout
        mRecyclerView = v.findViewById(R.id.rcycler_kelasmanager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GetKelas> getKelas = mApiInterface.getKelas();
        getKelas.enqueue(new Callback<GetKelas>() {
            @Override
            public void onResponse(Call<GetKelas> call, Response<GetKelas> response) {
                mKelas = response.body().getListDataKelas();

                mAdapter = new RecyclerAdapterKelasManager(mKelas,getContext());
                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetKelas> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }


}
