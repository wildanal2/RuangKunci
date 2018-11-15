package com.keyroom.com.keyroom.User;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.keyroom.com.keyroom.R;

public class AkunFragmentUser extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_akun_user,container,false);
        initialize();
        return v;
    }

    public void initialize(){
        ImageView btn_setting = v.findViewById(R.id.btn_setting_akun);

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frament_container_user,new setting()).commit();
            }
        });

    }
}
