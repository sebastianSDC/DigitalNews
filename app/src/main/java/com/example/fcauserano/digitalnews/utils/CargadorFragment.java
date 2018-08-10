package com.example.fcauserano.digitalnews.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public final class CargadorFragment extends AppCompatActivity {

    public static void cargarFragment(FragmentManager fragmentManager, int contenedor, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(contenedor, fragment);
        fragmentTransaction.commit();
    }
}
