package com.keyroom.com.keyroom.Admin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.keyroom.com.keyroom.Adapter.RecyclerAdapterFasilitas;
import com.keyroom.com.keyroom.Adapter.RecyclerAdapterJadwal;
import com.keyroom.com.keyroom.Adapter.RecyclerAdapterRiwayatPeminjam;
import com.keyroom.com.keyroom.Model.Fasilitas;
import com.keyroom.com.keyroom.Model.GetDetailKelas;
import com.keyroom.com.keyroom.Model.Jadwal;
import com.keyroom.com.keyroom.Model.Kelas;
import com.keyroom.com.keyroom.Model.Peminjaman;
import com.keyroom.com.keyroom.Model.PostPutDellFasilitas;
import com.keyroom.com.keyroom.Model.PostPutDellJadwal;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.keyroom.com.keyroom.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRuang extends Fragment {
    View viw;
    Bundle bndl;
    Button btn_pinjam;
    private ApiInterface mApiInterface;
    Kelas mKelas;

    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        viw =inflater.inflate(R.layout.fragment_detailruang_admin,container,false);
        bndl = getArguments();

        initialize();
        init_listener();
        return viw;
    }


    public void initialize(){
        //init from layout
        final ImageView img_poster = viw.findViewById(R.id.img_detailruang_admin);
        final ImageView img_status = viw.findViewById(R.id.ic_status);
        final TextView txv_status = viw.findViewById(R.id.tv_status);
        final TextView txv_namaruangan = viw.findViewById(R.id.txv_namaruang_detail_admin);
        final TextView txv_lokasi = viw.findViewById(R.id.txv_lokasi_detail_admin);
        final RecyclerView mRecyclerView = viw.findViewById(R.id.recycler_jadwl_detail_admin);
        final RecyclerView mRecyclerViewFasilitas = viw.findViewById(R.id.recycler_fasilitas_detail_admin);
        final RecyclerView mRecyclerViewRiwayat = viw.findViewById(R.id.recycler_riwayat);
        btn_pinjam = viw.findViewById(R.id.btn_pinjam);

        //init apiInterface
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Toolbar toolbar = (Toolbar) viw.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear 1 stack
                getActivity().getSupportFragmentManager().popBackStack();
                //moved to home admin fragment
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frament_container_admin,new HomeAdmin()).commit();
            }
        });



        Call<GetDetailKelas> getDetail = mApiInterface.getDetail(bndl.getString("id"));
        getDetail.enqueue(new Callback<GetDetailKelas>() {
            @Override
            public void onResponse(Call<GetDetailKelas> call, Response<GetDetailKelas> response) {
                mKelas = response.body().getDataKelas();
                List<Jadwal> mjadwal = response.body().getListDatajadwal();
                List<Fasilitas> mFasilitas = response.body().getListFasilitas();
                List<Peminjaman> mPeminjaman = response.body().getListPeminjaman();

                // set textview
                Glide.with(getContext()).load(ApiClient.BASE_ASSETS+mKelas.getImg()).into(img_poster);
                if (mKelas.getStatus().equals("tersedia")){
                    img_status.setImageResource(R.drawable.ic_available);
                    txv_status.setText("Tersedia");
                    btn_pinjam.setBackgroundResource(R.drawable.btn_shapepinjam);
                }else {
                    img_status.setImageResource(R.drawable.ic_notavailable);
                    txv_status.setText("Tidak Tersedia");
                    btn_pinjam.setBackgroundResource(R.drawable.btn_shapenotavail);
                }
                txv_namaruangan.setText(mKelas.getRuang());
                txv_lokasi.setText("Lokasi : "+mKelas.getLokasi());

//                // init recycler Riwayat
                RecyclerAdapterRiwayatPeminjam mAdapterriwayat = new RecyclerAdapterRiwayatPeminjam(getContext(),mPeminjaman);
                mRecyclerViewRiwayat.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerViewRiwayat.setAdapter(mAdapterriwayat);

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

    private void init_listener(){
        btn_pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mKelas.getStatus().equals("tersedia")) {
                    Intent i = new Intent(getActivity().getApplicationContext(), MeminjamKelas.class);
                    i.putExtra("id_kelas", mKelas.getId());
                    getActivity().startActivity(i);
                }else {
                    AlertDialog.Builder notif = new AlertDialog.Builder(getContext());
                    notif.setMessage("Kelas Tidak Tersedia");
                    notif.setCancelable(true);
                    notif.show();
                }
            }
        });
    }


}