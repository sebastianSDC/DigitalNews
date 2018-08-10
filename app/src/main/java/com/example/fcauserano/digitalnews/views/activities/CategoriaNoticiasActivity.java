package com.example.fcauserano.digitalnews.views.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.utils.CargadorFragment;

import com.example.fcauserano.digitalnews.R;
import com.example.fcauserano.digitalnews.views.fragments.CategoriaNoticiasFragment;
import com.example.fcauserano.digitalnews.views.fragments.CategoriaNoticiasFragment.NotificadorNotica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CategoriaNoticiasActivity extends AppCompatActivity implements NotificadorNotica{

    private CategoriaNoticiasFragment categoriaNoticiasFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_noticias);
        Bundle bundle = getIntent().getExtras();

        categoriaNoticiasFragment = new CategoriaNoticiasFragment();
        categoriaNoticiasFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        CargadorFragment.cargarFragment(fragmentManager, R.id.container_fragment_categoria_noticia_id, categoriaNoticiasFragment);
    }

    @Override
    public void notificar(String categoria, int posicionclikeada, List<Noticia> noticias) {
        ArrayList<Noticia> listaNoticia = (ArrayList) noticias;
        Intent intent = new Intent(this, MainActivity2.class);
        Bundle bundle = new Bundle();
        bundle.putInt(MainActivity2.POSICION_KEY,posicionclikeada);
        bundle.putSerializable(MainActivity2.NOTICIA_KEY, listaNoticia);
        bundle.putString(MainActivity2.CATEGORIA_KEY,categoria);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
