package com.example.fcauserano.digitalnews.model.DAO;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.model.POJO.NoticiaContainer;
import com.example.fcauserano.digitalnews.utils.ResultListener;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NoticiaDAOArchivo  {

    public void obtenerListaNoticiasJSON(Context context, ResultListener<List<Noticia>> resultListener){
        List<Noticia> noticiaList = null;

        AssetManager assetManager = context.getAssets();
        try {
            InputStream archivoJsonNoticias = assetManager.open("noticias.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(archivoJsonNoticias));

            Gson gson = new Gson();

            NoticiaContainer noticiaContainer = gson.fromJson(bufferedReader, NoticiaContainer.class);
            noticiaList = noticiaContainer.getNoticias();
            resultListener.finish(noticiaList);

        } catch (IOException e) {
            resultListener.finish(new ArrayList<Noticia>());
            e.printStackTrace();
        }
    }
}
