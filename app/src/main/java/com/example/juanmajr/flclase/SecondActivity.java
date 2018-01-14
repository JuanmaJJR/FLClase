package com.example.juanmajr.flclase;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.juanmajr.flclase.Adapters.ListaCochesAdapter;
import com.example.juanmajr.flclase.Adapters.ListaMensajesAdapter;
import com.example.juanmajr.flclase.FBObjects.FBCoche;
import com.example.juanmajr.flclase.FBObjects.Mensaje;
import com.example.milib.ListaFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListaFragment listaMensajesFragment,ListaFragmentCoches;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SecondActivityEvents events = new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);

      //  listaMensajesFragment = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentListaMensajes);
        ListaFragmentCoches = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentListCoches);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMapa);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(ListaFragmentCoches);
        transaction.show(mapFragment);
        transaction.commit();
        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Coches");



        ArrayList<String> mdatos= new ArrayList<>();


         //ListaMensajesAdapter listaMensajesAdapter = new ListaMensajesAdapter(mdatos);

       //listaMensajesFragment.recyclerView.setAdapter(listaMensajesAdapter);
    }
}

class SecondActivityEvents implements FireBaseAdminListener{

    SecondActivity secondActivity;

    public SecondActivityEvents(SecondActivity secondActivity){
        this.secondActivity = secondActivity;
    }

    @Override
    public void FireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {
        if(rama.equals("Coches")){
            GenericTypeIndicator<ArrayList<FBCoche>> indicator = new GenericTypeIndicator<ArrayList<FBCoche>>(){};
            ArrayList<FBCoche> coches = dataSnapshot.getValue(indicator);
            ListaCochesAdapter listaCochesAdapter = new ListaCochesAdapter(coches,secondActivity);
            secondActivity.ListaFragmentCoches.recyclerView.setAdapter(listaCochesAdapter);
        }
    }



    @Override
    public void FireBaseAdmin_RegisterOk(Boolean ok) {

    }

    @Override
    public void FireBaseAdmin_LoginOk(Boolean ok) {

    }
}
