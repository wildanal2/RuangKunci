package com.keyroom.com.keyroom.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.keyroom.com.keyroom.Model.Kelas;
import com.keyroom.com.keyroom.R;

import java.util.List;

public class RecyclerAdapterKelas extends RecyclerView.Adapter<RecyclerAdapterKelas.MyViewHolder> {
    private List<Kelas> mKelas;
    private Context mCntex;

    public RecyclerAdapterKelas(List<Kelas> mKelas, Context mCntex) {
        this.mKelas = mKelas;
        this.mCntex = mCntex;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ruangkelas, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Kelas kls = mKelas.get(position);
        holder.namakelas.setText(kls.getRuang());
        holder.lokasi.setText(kls.getLokasi());
    }

    @Override
    public int getItemCount() { return mKelas.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster,img_status;
        TextView namakelas,nama_lab,lokasi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster = itemView.findViewById(R.id.img_fotoruang_item);
            img_status = itemView.findViewById(R.id.img_status_item);
            namakelas = itemView.findViewById(R.id.txv_namaruang_item);
            lokasi = itemView.findViewById(R.id.txv_namaLokasi_item);
        }
    }
}
