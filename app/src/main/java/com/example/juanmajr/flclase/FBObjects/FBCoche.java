package com.example.juanmajr.flclase.FBObjects;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

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

    //Fuera de firebase
    private Marker marker=null;




    public FBCoche(){

    }

    public FBCoche(int fabricado, String marca, String nombre, double lat, double lon, String imgurl) {
        this.Fabricado = fabricado;
        this.Marca = marca;

        this.Nombre = nombre;
        this.lat = lat;
        this.lon = lon;
        this.imgurl = imgurl;
    }
    public FBCoche(int fabricado, String marca, String nombre){

    }

    public void setMarker(Marker marker){
        this.marker = marker;
    }

    public Marker getMarker(){
        return this.marker;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Fabricado", Fabricado);
        result.put("Marca", Marca);
        result.put("Nombre", Nombre);
        result.put("lat", lat);
        result.put("lon", lon);
        result.put("imgurl", imgurl);

        return result;
    }

    public int getFabricado() {
        return Fabricado;
    }

    public void setFabricado(int fabricado) {
        Fabricado = fabricado;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
