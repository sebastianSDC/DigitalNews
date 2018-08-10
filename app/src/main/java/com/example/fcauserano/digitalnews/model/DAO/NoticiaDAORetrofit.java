package com.example.fcauserano.digitalnews.model.DAO;

import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.model.POJO.NoticiaContainer;
import com.example.fcauserano.digitalnews.model.POJO.NoticiaService;
import com.example.fcauserano.digitalnews.utils.Constantes;
import com.example.fcauserano.digitalnews.utils.ResultListener;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticiaDAORetrofit {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private Retrofit retrofit;
    private NoticiaService serviceNoticia;
    private Date fecha;

    public NoticiaDAORetrofit() {
        OkHttpClient cliente = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + Constantes.apiKey())
                        .build();
                return chain.proceed(request);
            }
        }).build();
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(cliente)
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
        serviceNoticia = retrofit.create(NoticiaService.class);
    }

    public void obtenerListaNoticiasInternet(Integer pagina, Integer tamañoPagina, final ResultListener<List<Noticia>> resultListener) {
        serviceNoticia.getNoticiasTop(null, Constantes.pais(), Constantes.categoriaPrincipal(), tamañoPagina, pagina, null).enqueue(new Callback<NoticiaContainer>() {
            @Override
            public void onResponse(Call<NoticiaContainer> call, Response<NoticiaContainer> response) {
                NoticiaContainer noticiaContainer = response.body();
                if (noticiaContainer != null && noticiaContainer.getNoticias() != null) {
                    List<Noticia> resultado = noticiaContainer.getNoticias();
                    resultListener.finish(resultado);
                }
            }

            @Override
            public void onFailure(Call<NoticiaContainer> call, Throwable t) {
                resultListener.finish(new ArrayList<Noticia>());
            }
        });
    }

    public void obtenerListaNoticiasCategoriaInternet(String categoria, final ResultListener<List<Noticia>> resultListener) {
        serviceNoticia.getNoticiasPorCategoria(categoria, "ar").enqueue(new Callback<NoticiaContainer>() {
            @Override
            public void onResponse(Call<NoticiaContainer> call, Response<NoticiaContainer> response) {
                NoticiaContainer noticiaContainer = response.body();
                if (noticiaContainer != null && noticiaContainer.getNoticias() != null) {
                    List<Noticia> resultado = noticiaContainer.getNoticias();
                    resultListener.finish(resultado);
                }
            }

            @Override
            public void onFailure(Call<NoticiaContainer> call, Throwable t) {
                resultListener.finish(new ArrayList<Noticia>());
            }
        });
    }

    public void obtenerListaNoticiasFuenteInternet(final ResultListener<List<Noticia>> resultListener) {
        //serviceNoticia.getNoticiaseEerything(null, null, null, null, null, null, "relevancy", 100, 1).enqueue(new Callback<NoticiaContainer>() {
        serviceNoticia.getNoticiasTop(null, "ar", "general", 20, 1, null).enqueue(new Callback<NoticiaContainer>() {
            @Override
            public void onResponse(Call<NoticiaContainer> call, Response<NoticiaContainer> response) {
                NoticiaContainer noticiaContainer = response.body();
                if (noticiaContainer != null && noticiaContainer.getNoticias() != null) {
                    List<Noticia> resultado = noticiaContainer.getNoticias();
                    resultListener.finish(resultado);
                }
            }

            @Override
            public void onFailure(Call<NoticiaContainer> call, Throwable t) {
                resultListener.finish(new ArrayList<Noticia>());
            }
        });
    }

    public void obtenerSearchNoticias(Integer pagina, Integer tamañoPagina, String q, final ResultListener<List<Noticia>> resultListener) {
        fecha = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_WEEK, -5);
        fecha = calendar.getTime();
        String fechaTexto = dateFormat.format(fecha);

        serviceNoticia.getNoticiaseEerything(q, null, null, fechaTexto, null, Constantes.lenguaje(), Constantes.ordenadoPor(), tamañoPagina, pagina).enqueue(new Callback<NoticiaContainer>() {
            @Override
            public void onResponse(Call<NoticiaContainer> call, Response<NoticiaContainer> response) {
                NoticiaContainer noticiaContainer = response.body();
                if (noticiaContainer != null && noticiaContainer.getNoticias() != null) {
                    List<Noticia> resultado = noticiaContainer.getNoticias();
                    resultListener.finish(resultado);
                }
            }

            @Override
            public void onFailure(Call<NoticiaContainer> call, Throwable t) {
                resultListener.finish(new ArrayList<Noticia>());

            }
        });
    }
}