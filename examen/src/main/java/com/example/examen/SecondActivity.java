package com.example.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.milib.ListaFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListaFragment ListaFragmentNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //inicializamos el secondactivityevents y le hacemos listener del FireBaseAdmin, al igual que en el primer activity
        SecondActivityEvents events = new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);

        ListaFragmentNoticias = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentListNoticias);

        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Noticias");
    }
}
class SecondActivityEvents implements FireBaseAdminListener{

    SecondActivity secondActivity;

    public SecondActivityEvents(SecondActivity secondActivity){
        this.secondActivity = secondActivity;
    }

    @Override
    public void FireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {

    }



    @Override
    public void FireBaseAdmin_RegisterOk(Boolean ok) {

    }

    @Override
    public void FireBaseAdmin_LoginOk(Boolean ok) {

    }
}
