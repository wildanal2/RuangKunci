package com.keyroom.com.keyroom.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keyroom.com.keyroom.Model.Jadwal;
import com.keyroom.com.keyroom.Model.Peminjaman;
import com.keyroom.com.keyroom.R;

import java.util.List;

public class RecyclerAdapterRiwayatUser extends RecyclerView.Adapter<RecyclerAdapterRiwayatUser.MyViewHolder> {

    List<Peminjaman> mriwayat;

    public RecyclerAdapterRiwayatUser(List<Peminjaman> mriwayat) {
        this.mriwayat = mriwayat;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayatpeminjaman, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Peminjaman pmjmn = mriwayat.get(position);

        holder.kelas.setText(pmjmn.getRuang());
        holder.nmlab.setText(pmjmn.getNama_lab());
        holder.tgl.setText(pmjmn.getMulai());
        holder.status.setText(pmjmn.getStatus());
    }

    @Override
    public int getItemCount() {
        return mriwayat.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView kelas,nmlab,tgl,status;

        public MyViewHolder(View itemView) {
            super(itemView);
            kelas = itemView.findViewById(R.id.tv_kelas);
            nmlab = itemView.findViewById(R.id.tv_nmlab);
            tgl = itemView.findViewById(R.id.tv_tgl);
            status = itemView.findViewById(R.id.tv_status);
        }
    }
}
