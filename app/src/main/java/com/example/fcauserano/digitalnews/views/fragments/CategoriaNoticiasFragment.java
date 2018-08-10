package com.example.fcauserano.digitalnews.views.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fcauserano.digitalnews.controller.NoticiaController;
import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.R;
import com.example.fcauserano.digitalnews.utils.ResultListener;
import com.example.fcauserano.digitalnews.views.adapters.CategoriaNoticiasAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriaNoticiasFragment extends Fragment implements CategoriaNoticiasAdapter.NotificadorNoticiaCelda {
    private List<Noticia> noticias;
    private RecyclerView recyclerView;
    private TextView textCategoria;
    private NotificadorNotica notificadorNotica;
    private EditText editText;
    private CategoriaNoticiasAdapter detalleNoticiasAdaptar;

    public static final String KEY_CATEGORIA = "key_Categoria";
    public static final String KEY_FUENTE = "key_Fuente";


    public CategoriaNoticiasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categoria_noticias, container, false);
        textCategoria = view.findViewById(R.id.textView8);
        editText = view.findViewById(R.id.fragment_noticia_edittext);
        noticias = new ArrayList<>();
        Bundle bundle = getArguments();
        String categoriaRecibida = bundle.getString(KEY_CATEGORIA);
        String fuenteRecibida = bundle.getString(KEY_FUENTE);

        detalleNoticiasAdaptar = new CategoriaNoticiasAdapter(noticias, getActivity(),this);

        if (categoriaRecibida != null) {
            textCategoria.setText(categoriaRecibida);
            listaNoticiasCategoria(categoriaRecibida);
        } else {
            textCategoria.setText(fuenteRecibida);
            listaNoticiasFuente(fuenteRecibida);
        }

        recyclerView = view.findViewById(R.id.recycler_detallenoticias);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(detalleNoticiasAdaptar);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                detalleNoticiasAdaptar.getFilter().filter(s);
                //filtro(s.toString());

            }
        });
        return view;
    }
    public void filtro(String text){
        ArrayList<Noticia> listaFiltrada = new ArrayList<>();
        for (Noticia unanoticia:noticias) {
            String texto = unanoticia.getTitulo();

            if (texto.toLowerCase().contains(text.toLowerCase())){
                listaFiltrada.add(unanoticia);
            }
        }
        detalleNoticiasAdaptar.actualizarNoticias(listaFiltrada);
    }



    private void listaNoticiasCategoria(String categoria) {
         NoticiaController noticiaController = new NoticiaController(getActivity());
        noticiaController.obtenerNoticiasCategoriaController(categoria, new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> result) {
                noticias = result;
                detalleNoticiasAdaptar.actualizarNoticias(result);
            }
        });
    }

    private void listaNoticiasFuente(String fuente) {
        final NoticiaController noticiaController = new NoticiaController(getActivity());
        noticiaController.obtenerNoticiasFuenteController(fuente, new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> result) {
                detalleNoticiasAdaptar.actualizarNoticias(result);
            }
        });
    }

    public void onAttach (Context context) {
        super.onAttach(context);
        notificadorNotica = (NotificadorNotica) context;
    }


    @Override
    public void notificarCeldaClickeado(int posicionclikeada, List<Noticia> noticias) {
        notificadorNotica.notificar(textCategoria.getText().toString(), posicionclikeada,noticias);
    }
    public interface NotificadorNotica {
        public void notificar(String categoria, int posicionclikeada,List <Noticia> noticias);
    }

}