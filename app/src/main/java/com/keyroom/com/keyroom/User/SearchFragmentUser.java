package com.keyroom.com.keyroom.User;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keyroom.com.keyroom.Adapter.RecyclerAdapterKelas;
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


public class SearchFragmentUser extends Fragment {

    View v;
    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterKelas mAdapter;
    private List<Kelas> mKelas = new ArrayList<>();;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_search_user,container,false);
        mRecyclerView = v.findViewById(R.id.recyler_search_user);

        initialize();
        return v;
    }

    public void initialize(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetKelas> getKelas = mApiInterface.getKelas();
        getKelas.enqueue(new Callback<GetKelas>() {
            @Override
            public void onResponse(Call<GetKelas> call, Response<GetKelas> response) {
                mKelas = response.body().getListDataKelas();

                mAdapter = new RecyclerAdapterKelas(mKelas,getContext());
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetKelas> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
