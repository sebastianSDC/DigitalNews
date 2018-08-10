package com.example.fcauserano.digitalnews.model.POJO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Fuente implements Serializable{
    private String id;
    @SerializedName("name")
    private String nombre;
    @SerializedName("description")
    private String descripcion;
    private String url;
    @SerializedName("category")
    private String categoria;
    @SerializedName("language")
    private String idioma;
    @SerializedName("country")
    private String pais;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
