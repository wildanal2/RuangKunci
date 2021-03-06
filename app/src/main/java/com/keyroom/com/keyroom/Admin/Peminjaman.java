package com.keyroom.com.keyroom.Admin;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.keyroom.com.keyroom.Adapter.RecyclerAdapterKelas;
import com.keyroom.com.keyroom.Adapter.RecyclerAdapterPeminjam;
import com.keyroom.com.keyroom.Listener.ClickListener;
import com.keyroom.com.keyroom.Listener.RecyclerTouchListener;
import com.keyroom.com.keyroom.Model.GetKelas;
import com.keyroom.com.keyroom.Model.GetPeminjaman;
import com.keyroom.com.keyroom.Model.PostPutDellPeminjaman;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.keyroom.com.keyroom.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Peminjaman extends Fragment {
    View v;
    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterPeminjam mAdapter;
    private List<com.keyroom.com.keyroom.Model.Peminjaman> mPeminjaman = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_peminjaman, container, false);
        mRecyclerView = v.findViewById(R.id.recyler_peminjaman_admin);

        initialize();
        initializeListener();
        return v;
    }

    public void initialize(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GetPeminjaman> getPenjam = mApiInterface.getpeminjaman();
        getPenjam.enqueue(new Callback<GetPeminjaman>() {
            @Override
            public void onResponse(Call<GetPeminjaman> call, Response<GetPeminjaman> response) {
                mPeminjaman = response.body().getListPeminjaman();

                mAdapter = new RecyclerAdapterPeminjam(mPeminjaman,getContext());
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetPeminjaman> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toast.makeText(getContext(),"error get "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

//        searchhhh
        SearchView searchkls = v.findViewById(R.id.search);
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

    public void initializeListener(){
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int posi) {

            }

            @Override
            public void onLongClick(View view, int posi) {
                final com.keyroom.com.keyroom.Model.Peminjaman p = mPeminjaman.get(posi);
                if (p.getStatus().equals("selesai")){
                    Toast.makeText(getContext(), "ruang kelas tersedia", Toast.LENGTH_SHORT).show();
                }else{
                    final AlertDialog.Builder notif = new AlertDialog.Builder(getContext());
                    notif.setTitle("Kembalikan Kunci");
                    notif.setMessage("Ruang : "+p.getRuang()+" oleh "+p.getNama()+ " yakin dikembalikan ?" );
                    notif.setCancelable(false);
                    notif.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Call<PostPutDellPeminjaman> kembalikan = mApiInterface.pengembalian(p.getId(), p.getId_kelas());
                            kembalikan.enqueue(new Callback<PostPutDellPeminjaman>() {
                                @Override
                                public void onResponse(Call<PostPutDellPeminjaman> call, Response<PostPutDellPeminjaman> response) {
                                    Toast.makeText(getContext(), "Sukses", Toast.LENGTH_SHORT).show();
                                    initialize();
                                }

                                @Override
                                public void onFailure(Call<PostPutDellPeminjaman> call, Throwable t) {
                                    Toast.makeText(getContext(), "Something Wrong "+t.getMessage(), Toast.LENGTH_SHORT).show();
                                    initialize();
                                }
                            });

                        }
                    });

                    notif.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            notif.create().hide();
                        }
                    });

                    notif.create().show();
                }
            }
        }));

    }
}
