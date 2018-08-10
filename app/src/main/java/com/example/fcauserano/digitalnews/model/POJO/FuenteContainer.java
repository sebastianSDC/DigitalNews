package com.example.fcauserano.digitalnews.model.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FuenteContainer {
    @SerializedName("sources")
    private List<Fuente> fuentes;

    public List<Fuente> getFuentes() {
        return fuentes;
    }
}
