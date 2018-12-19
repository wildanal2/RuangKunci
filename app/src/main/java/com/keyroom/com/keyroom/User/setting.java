package com.keyroom.com.keyroom.User;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.keyroom.com.keyroom.Admin.HomeAdmin;
import com.keyroom.com.keyroom.Config.SessionManagement;
import com.keyroom.com.keyroom.R;

public class Setting extends Fragment {
    //    sesion management
    SessionManagement mSesion;
    View v;
    RelativeLayout bt_logout,btn_password;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_setting,container,false);

        initialize();
        return v;
    }

    void initialize(){
        mSesion = new SessionManagement(getContext());

        bt_logout = v.findViewById(R.id.btn_logout);
        btn_password = v.findViewById(R.id.btn_newpassword);
        ImageView btn_back = v.findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
                //moved to home admin fragment
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frament_container_user,new AkunFragmentUser()).commit();
            }
        });
        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSesion.logoutUser();
                getActivity().finish();
            }
        });

        btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),GantiPassword.class);
                getActivity().startActivity(i);
            }
        });
    }
}
