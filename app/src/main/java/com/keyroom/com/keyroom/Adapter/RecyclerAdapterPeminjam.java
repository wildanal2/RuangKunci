package com.keyroom.com.keyroom.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyroom.com.keyroom.Model.Kelas;
import com.keyroom.com.keyroom.Model.Peminjaman;
import com.keyroom.com.keyroom.Model.User;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterPeminjam extends RecyclerView.Adapter<RecyclerAdapterPeminjam.MyViewHolder> implements Filterable{
    private List<Peminjaman> mPeminjam;
    private List<Peminjaman> mPeminjamFull;
    private Context mCntex;

    public RecyclerAdapterPeminjam(List<Peminjaman> mPeminjam, Context mCntex) {
        this.mPeminjam = mPeminjam;
        mPeminjamFull = new ArrayList<>(mPeminjam);
        this.mCntex = mCntex;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_peminjaman, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Peminjaman pmjmn = mPeminjam.get(position);

        holder.ruangan.setText(pmjmn.getRuang());
        holder.nama.setText(pmjmn.getNama());
        if (pmjmn.getSelesai().equals("00:00:00")){
            holder.tanggal.setText(pmjmn.getMulai()+" -> ~");
        }else {
            holder.tanggal.setText(pmjmn.getMulai()+" -> "+pmjmn.getSelesai());
        }
        holder.status.setText(pmjmn.getStatus());
    }

    @Override
    public int getItemCount() { return mPeminjam.size(); }

    @Override
    public Filter getFilter() {
        return mPeminjamanFilter;
    }

    private Filter mPeminjamanFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Peminjaman> filteredUser = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){ // jika search kosong
                filteredUser.addAll(mPeminjamFull);
            }else {
                String textfilter = constraint.toString().toLowerCase().trim();
                for (Peminjaman p : mPeminjamFull){
                    if (p.getRuang().toLowerCase().contains(textfilter)){
                        filteredUser.add(p);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredUser;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mPeminjam.clear();
            mPeminjam.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ruangan,nama,tanggal,status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ruangan = itemView.findViewById(R.id.tv_kelas);
            nama = itemView.findViewById(R.id.tv_nama);
            tanggal = itemView.findViewById(R.id.tv_waktu);
            status = itemView.findViewById(R.id.tv_status);
        }
    }


}