package com.example.fcauserano.digitalnews.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.fcauserano.digitalnews.R;
import com.example.fcauserano.digitalnews.controller.NoticiaController;
import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.utils.ResultListener;
import com.example.fcauserano.digitalnews.views.adapters.NoticiaAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements NoticiaAdapter.NotificadorNoticiaCelda {

    public static final String KET_SEARCH_WORD = "search_word";
    private static final int ELEMENTOS_NUEVO_PEDIDO = 3;
    private NoticiaAdapter adapterSearch;
    private Boolean cargando = false;
    private ProgressBar progressBar;
    private LinearLayoutManager linearLayoutManager;
    private String searchWord;
    private NoticiaController noticiaController;
    private NoticiaController noticiaControllerBusqueda;
    private List<Noticia> noticias;

    public SearchFragment() {
        // Required empty public constructor
    }

    public void actualizarBusqueda(String busqueda) {
        searchWord = busqueda;
        this.noticias.clear();
        searchNoticias(searchWord);
        adapterSearch.actualizarNoticiasBusqueda(noticias);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView recyclerViewSearch = view.findViewById(R.id.recycler_search_noticias);
        progressBar = view.findViewById(R.id.progress_bar_search);
        noticias = new ArrayList<>();
        noticiaController = new NoticiaController(getActivity());
        adapterSearch = new NoticiaAdapter(noticias, this, getActivity());
        Bundle bundle = getArguments();
        if (bundle != null) {
            searchWord = bundle.getString(KET_SEARCH_WORD);
            searchNoticias(searchWord);
            //Toast.makeText(getActivity(), searchWord, Toast.LENGTH_SHORT).show();
        }
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewSearch.setLayoutManager(linearLayoutManager);
        recyclerViewSearch.setHasFixedSize(true);
        recyclerViewSearch.setAdapter(adapterSearch);

        recyclerViewSearch.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (cargando) {
                    return;
                }

                int posicionFinal = linearLayoutManager.getItemCount();
                int posicionActual = linearLayoutManager.findLastVisibleItemPosition();

                if (posicionFinal - posicionActual <= ELEMENTOS_NUEVO_PEDIDO) {
                    searchNoticias(searchWord);
                }
            }
        });

        return view;
    }

    private void searchNoticias(String searchWord) {
        if (noticiaController.isHayPaginas(searchWord)) {
            cargando = true;
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setIndeterminate(true);
            noticiaController.obtenerSearchNoticias(searchWord, new ResultListener<List<Noticia>>() {
                @Override
                public void finish(List<Noticia> result) {
                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.INVISIBLE);
                    cargando = false;
                    adapterSearch.actualizarNoticias(result);
                }
            });
        }
    }

    @Override
    public void noticiaClikeada(Noticia noticia) {

    }

    @Override
    public void categoriaSeleccionada(String categoria) {

    }

    @Override
    public void fuenteSeleccionada(String fuente) {

    }
}
