package com.keyroom.com.keyroom.Admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keyroom.com.keyroom.R;
import com.keyroom.com.keyroom.SettingAdminViewModel;

public class setting_admin extends Fragment {

    private SettingAdminViewModel mViewModel;

    public static setting_admin newInstance() {
        return new setting_admin();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.setting_admin_fragment, container, false);
    }

}
