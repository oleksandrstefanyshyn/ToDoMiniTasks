package com.globallogic.trainee.ostefanyshyn.todominitasks.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.globallogic.trainee.ostefanyshyn.todominitasks.R;

public class FragmenLoader {

    public static void loadFragment(Fragment fragment, FragmentManager manager) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
