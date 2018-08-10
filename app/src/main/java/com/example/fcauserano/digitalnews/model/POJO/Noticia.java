package com.example.fcauserano.digitalnews.model.POJO;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import junit.framework.Assert;

import java.io.Serializable;

public class Noticia implements Serializable {
    @SerializedName("source")
    private Fuente fuente;
    @SerializedName("author")
    private String autor;
    @SerializedName("title")
    private String titulo;
    @SerializedName("description")
    private String texto;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String imagen;
    @SerializedName("publishedAt")
    private String publicadoPor;

    //private String imagen;
    //private String texto;
    //private String titulo;
    //private String categoria;

    public Noticia() {
    }

    public Noticia(String imagen, String titulo, String texto ) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.texto = texto;
    }

    public String getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }


    public Fuente getFuente() {
        return fuente;
    }

    public void setFuente(Fuente fuente) {
        this.fuente = fuente;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicadoPor() {
        return publicadoPor;
    }

    public void setPublicadoPor(String publicadoPor) {
        this.publicadoPor = publicadoPor;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Noticia)) {
            return false;
        }
        Noticia noticiaAComparar = (Noticia) obj;
        return (noticiaAComparar.getTitulo().equals(this.titulo));
    }

    public int getDrawable(Context c) {
        String foto = getImagen();
        Assert.assertNotNull(foto);
        int fotoRes = c.getResources().getIdentifier(foto, "drawable", c.getPackageName());
        return fotoRes;
    }
}


