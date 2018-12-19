package com.keyroom.com.keyroom.Admin;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.keyroom.com.keyroom.Config.SessionManagement;
import com.keyroom.com.keyroom.R;

public class Setting extends Fragment {

    View v;
    RelativeLayout btnAkun,btn_logout,bt_klsmgr;
    //    sesion management
    SessionManagement mSesion;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.setting_admin_fragment, container, false);
        init_listener();
        return v;
    }

    void init_listener(){
        mSesion = new SessionManagement(getContext());
        btnAkun = v.findViewById(R.id.btnAkunManajer);
        btn_logout = v.findViewById(R.id.btn_logout);
        bt_klsmgr = v.findViewById(R.id.bt_kelas_manager);


        btnAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),UserManager.class);
                getActivity().startActivity(i);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSesion.logoutUser();
                getActivity().finish();
            }
        });

        bt_klsmgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),KelasManager.class);
                getActivity().startActivity(i);
            }
        });
    }
}
