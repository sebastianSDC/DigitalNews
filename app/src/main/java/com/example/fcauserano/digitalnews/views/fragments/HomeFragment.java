package com.example.fcauserano.digitalnews.views.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.example.fcauserano.digitalnews.views.adapters.CategoriaNoticiasAdapter;
//import com.example.fcauserano.digitalnews.views.fragments.CategoriaNoticiasFragment;

import com.example.fcauserano.digitalnews.controller.NoticiaController;
import com.example.fcauserano.digitalnews.utils.ResultListener;
import com.example.fcauserano.digitalnews.views.adapters.NoticiaAdapter;
import com.example.fcauserano.digitalnews.R;
import com.example.fcauserano.digitalnews.model.POJO.Noticia;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements NoticiaAdapter.NotificadorNoticiaCelda {

    private RecyclerView recyclerView;
    private List<Noticia> noticias;
    private NotificadorNoticia notificadorNoticia;
    private NoticiaAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.container_fragment_home_id);
        noticias = new ArrayList<>();
        adapter = new NoticiaAdapter(noticias,this, getActivity());
        armarListadoNoticias();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return view;
    }
    public  void onAttach (Context context) {
        super.onAttach(context);
        notificadorNoticia = (NotificadorNoticia) context;
    }

    public void armarListadoNoticias(){
        final NoticiaController noticiaController = new NoticiaController(getActivity());
        noticiaController.obtenerNoticiasController(new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> result) {
                noticias = result;
                adapter.actualizarNoticias(result);
            }
        });

        /*
        noticias.add(new Noticia(R.string.titulo_de_cat_espectaculos, R.string.texto_de_cat_espectaculos, R.string.categoria_espectaculos, R.drawable.espectaculos));
        noticias.add(new Noticia(R.string.titulo_de_cat_moda, R.string.texto_de_cat_moda, R.string.categoria_moda, R.drawable.moda));
        noticias.add(new Noticia(R.string.titulo_de_cat_deportes, R.string.texto_de_cat_deportes, R.string.categoria_deportes, R.drawable.deportes));noticias.add(new Noticia(R.string.titulo_de_cat_economia, R.string.texto_de_cat_economia, R.string.categoria_economia, R.drawable.economia));
        noticias.add(new Noticia(R.string.titulo_de_cat_politica, R.string.texto_de_cat_politica, R.string.categoria_politica, R.drawable.politica));
        noticias.add(new Noticia(R.string.titulo_de_cat_internacionales, R.string.texto_de_cat_internacionales, R.string.categoria_internacionales, R.drawable.internacionales_horiz));
        noticias.add(new Noticia(R.string.titulo_de_cat_policiales, R.string.texto_de_cat_policiales, R.string.categoria_policiales, R.drawable.policiales));
        noticias.add(new Noticia(R.string.titulo_de_cat_tecnologia, R.string.texto_de_cat_tecnologia, R.string.categoria_tecnologia, R.drawable.tecnologia));
        */
    }

    @Override
    public void noticiaClikeada(Noticia noticia) {
        //esto se va a llamar cuando se clickee una celda en el adapter
        //Esto hace de pasa mano, y tiene que notificarle al activity el contacto que llegó.
        //aca estoy en el metodo que me obligo a implementar LA INTERFAZ DEL ADAPTER!
       notificadorNoticia.notificar(noticia);

    }

    /*
    @Override
    public void categoriaClikeada(int categoria) {
        notificadorNoticia.notificarCategoria(categoria);
    }
    */

    @Override
    public void categoriaSeleccionada(String categoria) {
        notificadorNoticia.notificarCategoria(categoria);
    }

    @Override
    public void fuenteSeleccionada(String fuente) {
        notificadorNoticia.notificarFuente(fuente);
    }

    //INTERFAZ QUE COMUNICA FRAGMENT CON ACTIVITY. EL ACTIVITY ES QUIEN IMPLEMENTA ESTA INTERFAZ
    public interface NotificadorNoticia {
        public void notificar(Noticia noticia);
        //public void notificarCategoria (int categoria);
        public void notificarCategoria (String categoria);
        public void notificarFuente (String fuente);
    }
}
