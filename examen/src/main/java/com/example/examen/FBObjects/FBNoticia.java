package com.example.examen.FBObjects;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by juan.jusue on 19/12/2017.
 */
@IgnoreExtraProperties
public class FBNoticia {
    public String titulo;
    public String periodico;
    public String imgurl;

    public FBNoticia(){

    }
    public FBNoticia(String titulo, String periodico, String imgurl) {
        this.titulo = titulo;
        this.periodico = periodico;
        this.imgurl = imgurl;
    }
}
