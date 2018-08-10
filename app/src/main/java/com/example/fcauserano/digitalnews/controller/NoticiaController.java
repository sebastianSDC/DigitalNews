package com.example.fcauserano.digitalnews.controller;

import android.content.Context;

import com.example.fcauserano.digitalnews.model.DAO.NoticiaDAOArchivo;
import com.example.fcauserano.digitalnews.model.DAO.NoticiaDAORetrofit;
import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.utils.Constantes;
import com.example.fcauserano.digitalnews.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class NoticiaController {

    private Context context;
    private static final Integer PAGE_SIZE = 10;
    private Integer page = 1;
    private boolean hayPaginas;
    private String palabraBusqueda;

    public NoticiaController(Context context) {
        this.context = context;
        hayPaginas = true;
    }

    public void obtenerNoticiasController(final ResultListener<List<Noticia>> viewListener) {
        List<Noticia> noticias = null;


        if (Constantes.hayInternet(context)) {
            NoticiaDAORetrofit noticiaDAORetrofit = new NoticiaDAORetrofit();
            noticiaDAORetrofit.obtenerListaNoticiasInternet(page, PAGE_SIZE, new ResultListener<List<Noticia>>() {
                @Override
                public void finish(List<Noticia> result) {
                    if (result.size() < PAGE_SIZE) {
                        hayPaginas = false;
                    }
                    page++;
                    viewListener.finish(result);
                }
            });

        } else {
            NoticiaDAOArchivo noticiaDAOArchivo = new NoticiaDAOArchivo();
            noticiaDAOArchivo.obtenerListaNoticiasJSON(context, new ResultListener<List<Noticia>>() {
                @Override
                public void finish(List<Noticia> result) {
                    viewListener.finish(result);
                }
            });
        }
    }

    public void obtenerSearchNoticias(final String searchWord, final ResultListener<List<Noticia>> listener) {
        List<Noticia> noticias = null;
        if (!searchWord.equals(palabraBusqueda)) {
            palabraBusqueda = searchWord;
            hayPaginas = true;
            page = 1;
        }
        if (Constantes.hayInternet(context)) {
            NoticiaDAORetrofit daoRetrofit = new NoticiaDAORetrofit();
            daoRetrofit.obtenerSearchNoticias(page, PAGE_SIZE, searchWord, new ResultListener<List<Noticia>>() {
                @Override
                public void finish(List<Noticia> result) {
                    if (result.size() < PAGE_SIZE) {
                        hayPaginas = false;
                    }
                    page += 1;
                    listener.finish(result);
                }
            });
        } else {
            NoticiaDAOArchivo noticiaDAOArchivo = new NoticiaDAOArchivo();
            noticiaDAOArchivo.obtenerListaNoticiasJSON(context, new ResultListener<List<Noticia>>() {
                @Override
                public void finish(List<Noticia> result) {
                    listener.finish(result);
                }
            });
        }
    }

    public void obtenerNoticiasCategoriaController(final String categoria, final ResultListener<List<Noticia>> viewListener) {
        List<Noticia> noticias = null;

        if (Constantes.hayInternet(context)) {
            String claveCategoria = Constantes.getCategorias().get(categoria);
            NoticiaDAORetrofit noticiaDAORetrofit = new NoticiaDAORetrofit();
            noticiaDAORetrofit.obtenerListaNoticiasCategoriaInternet(claveCategoria, new ResultListener<List<Noticia>>() {
                @Override
                public void finish(List<Noticia> result) {
                    viewListener.finish(result);
                }
            });

        } else {
            NoticiaDAOArchivo noticiaDAOArchivo = new NoticiaDAOArchivo();
            noticiaDAOArchivo.obtenerListaNoticiasJSON(context, new ResultListener<List<Noticia>>() {
                @Override
                public void finish(List<Noticia> result) {
                    List<Noticia> noticias = new ArrayList<>();
                    for (Noticia noticia : result) {
                        if (noticia.getFuente().getCategoria().contains(categoria)) {
                            noticias.add(noticia);
                        }
                    }
                    viewListener.finish(noticias);
                }
            });
        }
    }

    public void obtenerNoticiasFuenteController(final String fuente, final ResultListener<List<Noticia>> viewListener) {
        List<Noticia> noticias = null;

        if (Constantes.hayInternet(context)) {
            NoticiaDAORetrofit noticiaDAORetrofit = new NoticiaDAORetrofit();
            noticiaDAORetrofit.obtenerListaNoticiasFuenteInternet(new ResultListener<List<Noticia>>() {
                @Override
                public void finish(List<Noticia> result) {
                    List<Noticia> noticiasFiltradas = new ArrayList<>();
                    for (Noticia unaNoticia : result) {
                        if (unaNoticia.getFuente().getNombre().contains(fuente)) {
                            noticiasFiltradas.add(unaNoticia);
                        }

                    }
                    viewListener.finish(noticiasFiltradas);
                }
            });

        } else {
            NoticiaDAOArchivo noticiaDAOArchivo = new NoticiaDAOArchivo();
            noticiaDAOArchivo.obtenerListaNoticiasJSON(context, new ResultListener<List<Noticia>>() {
                @Override
                public void finish(List<Noticia> result) {
                    List<Noticia> noticias = new ArrayList<>();
                    for (Noticia noticia : result) {
                        if (noticia.getFuente().getCategoria().contains(fuente)) {
                            noticias.add(noticia);
                        }
                    }
                    viewListener.finish(noticias);
                }
            });
        }
    }

    public boolean isHayPaginas(String searchWord) {
        if (!searchWord.equals(palabraBusqueda)) {
            hayPaginas = true;
        }
        return hayPaginas;
    }
}
