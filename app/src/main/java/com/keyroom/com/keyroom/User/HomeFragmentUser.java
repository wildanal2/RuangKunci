package com.keyroom.com.keyroom.User;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.keyroom.com.keyroom.Adapter.RecyclerAdapterKelas;
import com.keyroom.com.keyroom.Listener.ClickListener;
import com.keyroom.com.keyroom.Listener.RecyclerTouchListener;
import com.keyroom.com.keyroom.Model.GetKelas;
import com.keyroom.com.keyroom.Model.Kelas;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.keyroom.com.keyroom.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentUser extends Fragment {

    View v;
    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterKelas mAdapter;
    private List<Kelas> mKelas = new ArrayList<>();;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home_user,container,false);
        mRecyclerView = v.findViewById(R.id.recyler_home_user);

        initialize(); // initialize isi recycler

        // recycler touchlistener
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int posi) {
                Bundle bundle = new Bundle();
                bundle.putString("id",mKelas.get(posi).getId());  // mengisi string yang akan dikirim

                DetailRuangFragmentUser detailfrag = new DetailRuangFragmentUser();
                detailfrag.setArguments(bundle); // memasukkan bundle ke fragment detail ruangan

                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(new HomeFragmentUser(),"HomeFragment")
                        .addToBackStack("HomeFragment")
                        .replace(R.id.frament_container_user,detailfrag).commit();
                        //replace(R.id.frament_container_user,new DetailRuangFragmentUser()).commit();
            }
            @Override
            public void onLongClick(View view, int posi) { }
        }));
        return v;
    }

    public interface FragmentHomeListener{
        void onInputSent(CharSequence input);
    }

    public void initialize(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetKelas> getKelas = mApiInterface.getKelas();
        getKelas.enqueue(new Callback<GetKelas>() {
            @Override
            public void onResponse(Call<GetKelas> call, Response<GetKelas> response) {
                mKelas = response.body().getListDataKelas();

                mAdapter = new RecyclerAdapterKelas(mKelas,getContext());
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetKelas> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
