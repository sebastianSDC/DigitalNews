package com.example.fcauserano.digitalnews.views.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fcauserano.digitalnews.utils.CargadorFragment;
import com.example.fcauserano.digitalnews.views.fragments.DetalleNoticiaFragment;
import com.example.fcauserano.digitalnews.R;

public class DetalleNoticiaActivity extends AppCompatActivity {

    private DetalleNoticiaFragment detalleNoticiaFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_noticia);
        Bundle bundle = getIntent().getExtras();

        detalleNoticiaFragment = new DetalleNoticiaFragment();
        detalleNoticiaFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        CargadorFragment.cargarFragment(fragmentManager, R.id.container_fragment_detalle_noticia_id, detalleNoticiaFragment);
    }

}
