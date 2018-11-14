package com.keyroom.com.keyroom.User;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import com.keyroom.com.keyroom.R;

public class DetailRuangFragmentUser extends Fragment {


    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View viw =inflater.inflate(R.layout.fragment_detailruang_user,container,false);
        return viw;
    }


}