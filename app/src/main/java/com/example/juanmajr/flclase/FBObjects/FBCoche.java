package com.example.juanmajr.flclase.FBObjects;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by JuanmaJR on 11/12/2017.
 */

@IgnoreExtraProperties
public class FBCoche {

    public int Fabricado;
    public String Marca;
    public String Nombre;
    public double lat;
    public double lon;
    public String imgurl;




    public FBCoche(){

    }

    public FBCoche(int fabricado, String marca, String nombre, double lat, double lon,String imgurl) {
        this.Fabricado = fabricado;
        this.Marca = marca;
        this.Nombre = nombre;
        this.lat = lat;
        this.lon = lon;
        this.imgurl = imgurl;
    }
}
