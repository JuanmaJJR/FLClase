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
    public double Lat;
    public double Lon;




    public FBCoche(){

    }

    public FBCoche(int fabricado, String marca, String nombre, double lat, double lon) {
        this.Fabricado = fabricado;
        this.Marca = marca;
        this.Nombre = nombre;
        this.Lat = lat;
        this.Lon = lon;
    }
}
