package com.example.fcauserano.digitalnews.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.HashMap;
import java.util.Map;

public class Constantes {
    private static Map<String, String> categorias;

    public static boolean hayInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    public static String apiKey() {
        return "533c64d3a93a4e159a1bbc36ba8028d7";
    }

    public static Map<String, String> getCategorias() {
        categorias = new HashMap<>();
        categorias.put("Ciencia", "science");
        categorias.put("Deportes", "sports");
        categorias.put("Espectaculos", "entertainment");
        categorias.put("General", "general");
        categorias.put("Negocios", "business");
        categorias.put("Salud", "health");
        categorias.put("Tecnologia", "technology");
        return categorias;
    }

    public static String lenguaje() {
        return "es";
    }

    public static String ordenadoPor() {
        return "relevancy";
    }

    public static String pais() {
        return "ar";
    }

    public static String categoriaPrincipal() {
        return "general";
    }
}
