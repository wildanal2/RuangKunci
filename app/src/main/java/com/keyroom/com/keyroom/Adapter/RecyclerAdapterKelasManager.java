package com.keyroom.com.keyroom.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyroom.com.keyroom.Model.Kelas;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;

import java.io.File;
import java.util.List;

public class RecyclerAdapterKelasManager extends RecyclerView.Adapter<RecyclerAdapterKelasManager.MyViewHolder> {
    private List<Kelas> mKelas;
    private Context mCntex;

    public RecyclerAdapterKelasManager(List<Kelas> mKelas, Context mCntex) {
        this.mKelas = mKelas;
        this.mCntex = mCntex;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ruangkelas_admin, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Kelas kls = mKelas.get(position);
        holder.namakelas.setText(kls.getRuang());
        holder.lokasi.setText(kls.getLokasi());
        Glide.with(mCntex).load(ApiClient.BASE_ASSETS+kls.getImg()).into(holder.img_poster);
    }

    @Override
    public int getItemCount() { return mKelas.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView namakelas,lokasi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster = itemView.findViewById(R.id.img_fotoruang_item_admin);
            namakelas = itemView.findViewById(R.id.txv_namaruang_item_admin);
            lokasi = itemView.findViewById(R.id.txv_namaLokasi_item_admin);
        }
    }
}
