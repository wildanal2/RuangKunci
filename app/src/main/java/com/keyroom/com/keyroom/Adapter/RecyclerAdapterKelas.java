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
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterKelas extends RecyclerView.Adapter<RecyclerAdapterKelas.MyViewHolder> implements Filterable {
    private List<Kelas> mKelas;
    private List<Kelas> mKelasFull;
    private Context mCntex;

    public RecyclerAdapterKelas(List<Kelas> mKelas, Context mCntex) {
        this.mKelas = mKelas;
        this.mCntex = mCntex;
        mKelasFull = new ArrayList<>(mKelas);
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

        Glide.with(mCntex).load(ApiClient.BASE_ASSETS+kls.getImg()).into(holder.img_poster);
        if (kls.getStatus().equals("tersedia")){
            holder.img_status.setImageResource(R.drawable.ic_available);
        }else {
            holder.img_status.setImageResource(R.drawable.ic_notavailable);
        }

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


    @Override
    public Filter getFilter() {
        return mKelasFilter;
    }

    // proses filtered
    private  Filter mKelasFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charinput) {
            List<Kelas> filteredKelas  = new ArrayList<>();

            if (charinput == null || charinput.length() == 0){ // jika search kosong
                filteredKelas.addAll(mKelasFull);
            }else {
                String textfilter = charinput.toString().toLowerCase().trim();
                for (Kelas kls : mKelasFull){
                    if (kls.getRuang().toLowerCase().contains(textfilter)){
                        filteredKelas.add(kls);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredKelas;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mKelas.clear();
            mKelas.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

}
