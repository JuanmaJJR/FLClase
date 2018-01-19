package com.example.juanmajr.flclase;

/**
 * Created by JuanmaJR on 10/12/2017.
 */

public class DataHolder {

    public static DataHolder instance= new DataHolder();
    public String API_KEY ="AIzaSyDdsD3DOuyZIuGCApf6p1WOrzrhfr4xnFM";
    public FireBaseAdmin fireBaseAdmin;
    public DataHolder(){
        fireBaseAdmin = new FireBaseAdmin();
    }
}
