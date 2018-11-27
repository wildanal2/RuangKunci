package com.keyroom.com.keyroom.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keyroom.com.keyroom.Model.Fasilitas;
import com.keyroom.com.keyroom.R;

import java.util.List;

public class RecyclerAdapterFasilitas extends RecyclerView.Adapter<RecyclerAdapterFasilitas.MyViewHolder> {

    Context mContex;
    List<Fasilitas> mFasilitas ;

    public RecyclerAdapterFasilitas(Context mContex, List<Fasilitas> mFasilitas) {
        this.mContex = mContex;
        this.mFasilitas = mFasilitas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fasilitas, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Fasilitas fasil = mFasilitas.get(position);

        holder.nama_f.setText(fasil.getNama_fasilitas());
        holder.jumlah.setText(fasil.getJumlah());
    }

    @Override
    public int getItemCount() {
        return mFasilitas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nama_f,jumlah;

        public MyViewHolder(View itemView) {
            super(itemView);
            nama_f = itemView.findViewById(R.id.txv_namafasilitas_itmfasilitas);
            jumlah = itemView.findViewById(R.id.txv_jumlahfasilitas_itmfasilitas);
        }
    }

}
