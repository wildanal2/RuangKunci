package com.keyroom.com.keyroom.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keyroom.com.keyroom.Model.Jadwal;
import com.keyroom.com.keyroom.Model.Kelas;
import com.keyroom.com.keyroom.R;

import java.util.List;

public class RecyclerAdapterJadwal extends RecyclerView.Adapter<RecyclerAdapterJadwal.MyViewHolder> {

    List<Jadwal> mJadwal;

    public RecyclerAdapterJadwal(List<Jadwal> mJadwal) {
        this.mJadwal = mJadwal;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Jadwal jdwl = mJadwal.get(position);
        holder.hari.setText(jdwl.getHari());
        holder.waktu.setText(jdwl.getWaktu());
        holder.matakulah.setText(jdwl.getMatkul());
        holder.kelas.setText(jdwl.getKelas());
//        holder.pengajar.setText(jdwl.getPengajar());
    }

    @Override
    public int getItemCount() {
        return mJadwal.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView hari,waktu,matakulah,kelas,pengajar;

        public MyViewHolder(View itemView) {
            super(itemView);
            hari = itemView.findViewById(R.id.txv_hari_jadwal);
            waktu = itemView.findViewById(R.id.txv_waktu_jadwal);
            matakulah = itemView.findViewById(R.id.txv_matakuliah_jadwal);
            kelas =  itemView.findViewById(R.id.txv_kelas_jadwal);
//            pengajar = itemView.findViewById(R.id.txv_pengajar_jadwal);
        }
    }
}
