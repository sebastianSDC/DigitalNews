package com.example.fcauserano.digitalnews.model.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NoticiaContainer {
    @SerializedName("articles")
    private List<Noticia> noticias;

    public List<Noticia> getNoticias() {
        return noticias;
    }
}
