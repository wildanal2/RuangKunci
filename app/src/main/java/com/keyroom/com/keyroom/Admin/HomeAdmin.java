package com.keyroom.com.keyroom.Admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keyroom.com.keyroom.Adapter.RecyclerAdapterKelas;
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

public class HomeAdmin extends Fragment {
    View v;
    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterKelas mAdapter;
    private List<Kelas> mKelas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home_admin, container, false);
        mRecyclerView = v.findViewById(R.id.recyler_search_admin);

        initialize();
        initialize_listener();
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

        SearchView searchkls = v.findViewById(R.id.search_kelasadmin);
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
    }

    void initialize_listener(){
        // recycler touchlistener
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int posi) {
                Bundle bundle = new Bundle();
                bundle.putString("id",mKelas.get(posi).getId());  // mengisi string yang akan dikirim

                DetailRuang detailfragAdm = new DetailRuang();
                detailfragAdm.setArguments(bundle); // memasukkan bundle ke fragment detail ruangan

                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(new HomeAdmin(),"HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.frament_container_admin,detailfragAdm).commit();
            }
            @Override
            public void onLongClick(View view, int posi) { }
        }));
    }

}
