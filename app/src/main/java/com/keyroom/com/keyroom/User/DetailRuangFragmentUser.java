package com.keyroom.com.keyroom.User;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyroom.com.keyroom.Adapter.RecyclerAdapterFasilitas;
import com.keyroom.com.keyroom.Adapter.RecyclerAdapterJadwal;
import com.keyroom.com.keyroom.Adapter.RecyclerAdapterKelas;
import com.keyroom.com.keyroom.Model.Fasilitas;
import com.keyroom.com.keyroom.Model.GetDetailKelas;
import com.keyroom.com.keyroom.Model.Jadwal;
import com.keyroom.com.keyroom.Model.Kelas;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.keyroom.com.keyroom.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRuangFragmentUser extends Fragment {

    Bundle bndl;
    View viw;
    private ApiInterface mApiInterface;
    Kelas mKelas;


    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        viw =inflater.inflate(R.layout.fragment_detailruang_user,container,false);
        bndl = getArguments();

        initialize();
        return viw;
    }


    public void initialize(){
        //init
        final ImageView img_poster = viw.findViewById(R.id.img_detailruang_user);
        final TextView txv_namaruangan = viw.findViewById(R.id.txv_namaruang_detail);
        final TextView txv_lokasi = viw.findViewById(R.id.txv_lokasi_detail);
        final RecyclerView mRecyclerView = viw.findViewById(R.id.recycler_jadwl_detail);
        final RecyclerView mRecyclerViewFasilitas = viw.findViewById(R.id.recycler_fasilitas_detail_user);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GetDetailKelas> getDetail = mApiInterface.getDetail(bndl.getString("id"));
        getDetail.enqueue(new Callback<GetDetailKelas>() {
            @Override
            public void onResponse(Call<GetDetailKelas> call, Response<GetDetailKelas> response) {
                mKelas = response.body().getDataKelas();
                List<Jadwal> mjadwal = response.body().getListDatajadwal();
                List<Fasilitas> mFasilitas = response.body().getListFasilitas();

                // set textview
                Glide.with(getContext()).load(ApiClient.BASE_ASSETS+mKelas.getImg()).into(img_poster);
                txv_namaruangan.setText(mKelas.getRuang());
                txv_lokasi.setText("Lokasi : "+mKelas.getLokasi());

                // init recycler jadwal
                RecyclerAdapterJadwal mAdapter = new RecyclerAdapterJadwal(mjadwal);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(mAdapter);

                // init recycler Fasilitas
                RecyclerAdapterFasilitas mAdapterFasilitas = new RecyclerAdapterFasilitas(getContext(),mFasilitas);
                mRecyclerViewFasilitas.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerViewFasilitas.setAdapter(mAdapterFasilitas);

            }

            @Override
            public void onFailure(Call<GetDetailKelas> call, Throwable t) {     Log.e("Retrofit Get", t.toString()); }
        });
    }

}