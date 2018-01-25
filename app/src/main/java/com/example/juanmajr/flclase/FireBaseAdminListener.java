package com.example.juanmajr.flclase;

import com.facebook.AccessToken;
import com.google.firebase.database.DataSnapshot;

/**
 * Created by JuanmaJR on 10/12/2017.
 */

//interfaz con los metodos que implementara el events de la activity donde descarguemos los datos o hagamos el registro o el login
//para saber si se ha realizado correctamente, o para devolver el dataSnapshot de la rama descargada en el caso de la descarga.
public interface FireBaseAdminListener {

    public void FireBaseAdmin_RegisterOk(Boolean ok);
    public void FireBaseAdmin_LoginOk(Boolean ok);
    public void FireBaseAdmin_RamaDescargada(String rama,DataSnapshot dataSnapshot);
    public void handleFacebookAccessToken(AccessToken token);
}
