package com.keyroom.com.keyroom.User;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.keyroom.com.keyroom.Model.GetDetailKelas;
import com.keyroom.com.keyroom.Model.PostPutDellUser;
import com.keyroom.com.keyroom.Model.User;
import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.Rest.ApiClient;
import com.keyroom.com.keyroom.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AkunFragmentUser extends Fragment {

    TextView nama, nim, kelas;
    ImageView foto;
    View v;
    ApiInterface mApiInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_akun_user,container,false);
        initialize();
        return v;
    }

    public void initialize_Listener(){
        ImageView btn_setting = v.findViewById(R.id.btn_setting_akun);

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frament_container_user,new setting()).commit();
            }
        });

    }

    public void initialize(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        nama = v.findViewById(R.id.txtnamaakun);
        nim = v.findViewById(R.id.txtnimakun);
        kelas = v.findViewById(R.id.txtkelasakun);
        foto = v.findViewById(R.id.images);

        Call<PostPutDellUser> getUser = mApiInterface.detailUser("2");
        getUser.enqueue(new Callback<PostPutDellUser>() {
            @Override
            public void onResponse(Call<PostPutDellUser> call, Response<PostPutDellUser> response) {
                User nUser = response.body().getmUser();

                nama.setText(nUser.getNama());
               nim.setText(nUser.getNim());
               kelas.setText(nUser.getKelas());
            }

            @Override
            public void onFailure(Call<PostPutDellUser> call, Throwable t) {
                Toast.makeText(getContext(), "Something Wrong", Toast.LENGTH_LONG).show();
            }
        });
    }
}
