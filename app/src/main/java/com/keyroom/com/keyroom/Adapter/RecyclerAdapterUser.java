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
import com.keyroom.com.keyroom.Model.User;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapterUser extends RecyclerView.Adapter<RecyclerAdapterUser.MyViewHolder> implements Filterable {
    private List<User> mUser;
    private List<User> mUserFull;
    private Context mContex;

    public RecyclerAdapterUser(List<User> mUser,Context mContex) {
        this.mUser = mUser;
        mUserFull = new ArrayList<>(mUser);
        this.mContex = mContex;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User usr = mUser.get(position);
        holder.nama.setText(usr.getNama());
        holder.nim.setText(usr.getNim());
        Picasso.get().load(ApiClient.BASE_ASSETS+usr.getImg())
                .placeholder(R.drawable.ic_profil)
 //               .error(R.drawable.ic_profil)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    @Override
    public Filter getFilter() {
        return mUserFilter;
    }

    private Filter mUserFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<User> filteredUser = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){ // jika search kosong
                filteredUser.addAll(mUserFull);
            }else {
                String textfilter = constraint.toString().toLowerCase().trim();
                for (User usr : mUserFull){
                    if (usr.getNim().toLowerCase().contains(textfilter)){
                        filteredUser.add(usr);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredUser;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mUser.clear();
            mUser.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView nama,nim;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image_profil_mhsitm);
            nama = itemView.findViewById(R.id.nama_mahasiswa_itm);
            nim = itemView.findViewById(R.id.nim_mahasiswa_itm);
        }
    }

}
