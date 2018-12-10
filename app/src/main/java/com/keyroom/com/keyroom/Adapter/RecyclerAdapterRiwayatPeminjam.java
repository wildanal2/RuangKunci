package com.keyroom.com.keyroom.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keyroom.com.keyroom.Model.Fasilitas;
import com.keyroom.com.keyroom.Model.Peminjaman;
import com.keyroom.com.keyroom.R;

import java.util.List;

public class RecyclerAdapterRiwayatPeminjam extends RecyclerView.Adapter<RecyclerAdapterRiwayatPeminjam.MyViewHolder> {

    Context mContex;
    List<Peminjaman> mPeminjaman ;

    public RecyclerAdapterRiwayatPeminjam(Context mContex, List<Peminjaman> mPeminjaman) {
        this.mContex = mContex;
        this.mPeminjaman = mPeminjaman;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Peminjaman pmjm = mPeminjaman.get(position);

        holder.nim.setText(pmjm.getNim());
        holder.nama.setText(pmjm.getNama());
        holder.kelas.setText(pmjm.getKelas());
        holder.status.setText(pmjm.getStatus());
    }

    @Override
    public int getItemCount() {
        return mPeminjaman.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nim,nama,kelas,status;

        public MyViewHolder(View itemView) {
            super(itemView);
            nim = itemView.findViewById(R.id.tv_nim);
            nama = itemView.findViewById(R.id.tv_nama);
            kelas = itemView.findViewById(R.id.tv_kelas);
            status = itemView.findViewById(R.id.tv_status);
        }
    }

}
