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

import com.keyroom.com.keyroom.R;

public class Setting extends Fragment {

    View v;
    CardView btnAkun;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.setting_admin_fragment, container, false);
        init_listener();
        return v;
    }

    void init_listener(){
        btnAkun = v.findViewById(R.id.btnAkunManajer);
        btnAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),UserManager.class);
                getActivity().startActivity(i);
            }
        });
    }
}
