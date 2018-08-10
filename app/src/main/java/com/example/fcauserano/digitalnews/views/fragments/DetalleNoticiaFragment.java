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

import com.bumptech.glide.Glide;
import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.R;
import com.example.fcauserano.digitalnews.utils.GlideApp;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleNoticiaFragment extends Fragment {
    public static final String KEY_NOTICIA = "key_Noticia" ;

    private  TextView textoCategoria;
    private ImageView imagenNoticia;
    private TextView textoTitulo;
    private TextView textoDetalle;
    private TextView textoLink;


    public DetalleNoticiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_noticia, container, false);
        textoCategoria = view.findViewById(R.id.textView6);
        imagenNoticia = view.findViewById(R.id.imageView);
        textoTitulo = view.findViewById(R.id.textView);
        textoDetalle = view.findViewById(R.id.textView7);
        textoLink = view.findViewById(R.id.textViewLink);

        Bundle bundle = getArguments();
        Noticia noticia = (Noticia) bundle.getSerializable(KEY_NOTICIA);
        textoCategoria.setText(noticia.getFuente().getNombre());
        GlideApp.with(getActivity())
                .load(noticia.getImagen())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.iso_para_arranque)
                .into(imagenNoticia);
        //imagenNoticia.setImageResource(noticia.getDrawable(getActivity()));
        textoTitulo.setText(noticia.getTitulo());
        textoDetalle.setText(noticia.getTexto());
        String source = "<a href=\'" + noticia.getUrl() + "'>Nota completa</a> ";
        textoLink.setText(Html.fromHtml(source));
        textoLink.setMovementMethod(LinkMovementMethod.getInstance());
        //textoLink.setText(noticia.getUrl());
        return  view;
    }

}
