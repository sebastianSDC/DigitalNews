package com.example.fcauserano.digitalnews.views.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fcauserano.digitalnews.R;
import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.views.adapters.Adapter_ViewPager_FragmentCategoriaNoticia;
import com.example.fcauserano.digitalnews.views.fragments.Fragment_ViewPager_Noticia;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    public static final String CATEGORIA_KEY = "categoria seleccionada";
    public List<Fragment_ViewPager_Noticia> listafragment_viewPager_noticia;
    public static final String POSICION_KEY = "posicion clikeada";
    public static final String NOTICIA_KEY = "Recetas Key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listafragment_viewPager_noticia = new ArrayList<>();
        Intent intent =  getIntent();
        Bundle bundle = intent.getExtras();
        List<Noticia> litaNoticia = (List<Noticia>) bundle.getSerializable(NOTICIA_KEY);
        int posicionclikeada = bundle.getInt(POSICION_KEY);
        String categoia = bundle.getString(CATEGORIA_KEY);
        cargarFragmentsHardcodeados(litaNoticia,categoia);

        //Encuentro el viewPager
        final ViewPager viewPager = findViewById(R.id.viewPager);
        final Adapter_ViewPager_FragmentCategoriaNoticia adapter_viewPager_fragmentCategoriaNoticia =
                new Adapter_ViewPager_FragmentCategoriaNoticia(getSupportFragmentManager(), listafragment_viewPager_noticia );

        viewPager.setAdapter(adapter_viewPager_fragmentCategoriaNoticia);

        viewPager.setCurrentItem(posicionclikeada);


    }


    private void cargarFragmentsHardcodeados(List<Noticia> listaNoticia,String categoria) {
        //Método que hardcodea los fragments y los agrega a la lista que tenemos como atributo
        for (Noticia unanoticia : listaNoticia) {

            listafragment_viewPager_noticia.add(Fragment_ViewPager_Noticia.DameUnFragment(categoria,unanoticia));
        }
    }
}
