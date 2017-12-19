package com.example.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.examen.Adapters.ListaNoticiasAdapter;
import com.example.examen.FBObjects.FBNoticia;
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
        if(rama.equals("Noticias")){
            GenericTypeIndicator<ArrayList<FBNoticia>> indicator = new GenericTypeIndicator<ArrayList<FBNoticia>>(){};
            ArrayList<FBNoticia> coches = dataSnapshot.getValue(indicator);
            ListaNoticiasAdapter listaCochesAdapter = new ListaNoticiasAdapter(coches,secondActivity);
            secondActivity.ListaFragmentNoticias.recyclerView.setAdapter(listaCochesAdapter);
        }

    }



    @Override
    public void FireBaseAdmin_RegisterOk(Boolean ok) {

    }

    @Override
    public void FireBaseAdmin_LoginOk(Boolean ok) {

    }
}
