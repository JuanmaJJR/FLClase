package com.example.examen;

/**
 * Created by juan.jusue on 19/12/2017.
 */

public class DataHolder {
        public static DataHolder instance= new DataHolder();

        //FirebaseAdmin estatico para poder usar el mismo siempre
        public FireBaseAdmin fireBaseAdmin;
        public DataHolder(){
                fireBaseAdmin = new FireBaseAdmin();
        }
}
