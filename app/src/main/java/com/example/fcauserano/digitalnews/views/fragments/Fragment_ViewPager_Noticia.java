package com.example.fcauserano.digitalnews.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.utils.GlideApp;

import com.example.fcauserano.digitalnews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ViewPager_Noticia extends Fragment {


    private static final String CLAVE_CODIGO_NOTICIA = "codigo noticia";
    private static final String CLAVE_CODIGO_CATEGORIA = "categoria noticia";

    public Fragment_ViewPager_Noticia() {
        // Required empty public constructor
    }

    public static Fragment_ViewPager_Noticia  DameUnFragment  (String categoria,Noticia unaNoticia) {
        //Esta es la fábrica de fragments. Se invocará sin instanciar la clase por ser static

        //Genero un nuevo fragment concreto. Que este método retornará.
        Fragment_ViewPager_Noticia fragment_viewPager_noticia = new Fragment_ViewPager_Noticia();

        //Genero un bundle para poblar el fragment concreto con sus datos
        Bundle bundle = new Bundle();
        bundle.putSerializable(CLAVE_CODIGO_NOTICIA, unaNoticia);
        bundle.putString(CLAVE_CODIGO_CATEGORIA,categoria);


        //Le pongo título que vino por parámetro al fragment concreto
        //fragmentColor.setTitulo(titulo);//
        //Le pongo al fragment el bundle
        fragment_viewPager_noticia.setArguments(bundle);
        //Devuelvo el fragment ya con título y con su bundle
        return fragment_viewPager_noticia;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_noticia, container, false);
        TextView categoria = view.findViewById(R.id.textView6);
        ImageView imagen = view.findViewById(R.id.imageView);
        TextView titulo = view.findViewById(R.id.textView);
        TextView detalle = view.findViewById(R.id.textView7);
        TextView url = view.findViewById(R.id.textViewLink);
        //Obtenemos el bundle y le sacamos los argumentos que pasamos
        Bundle bundle = getArguments();
        Noticia unaNoticia = (Noticia) bundle.getSerializable(CLAVE_CODIGO_NOTICIA);
        categoria.setText(bundle.getString(CLAVE_CODIGO_CATEGORIA));
        titulo.setText(unaNoticia.getTitulo());
        detalle.setText(unaNoticia.getTexto());
        String source = "<a href=\'" + unaNoticia.getUrl() + "'>Nota Completa</a>";
        url.setText(Html.fromHtml(source));
        url.setMovementMethod(LinkMovementMethod.getInstance());

        //imagen.setImageResource(bundle.getString(CLAVE_CODIGO_IMAGEN));
        GlideApp.with(container)
                .load(unaNoticia.getImagen())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.iso_para_arranque)
                .into(imagen);


        return view;
    }

}
