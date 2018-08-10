package com.example.fcauserano.digitalnews.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.views.fragments.Fragment_ViewPager_Noticia;

import java.util.List;

public class Adapter_ViewPager_FragmentCategoriaNoticia extends FragmentStatePagerAdapter {
    private List<Fragment_ViewPager_Noticia> listaFragments;

    public Adapter_ViewPager_FragmentCategoriaNoticia(FragmentManager fm,List<Fragment_ViewPager_Noticia> listaFragments) {
        super(fm);
        this.listaFragments = listaFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return listaFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaFragments.size();
    }
}
