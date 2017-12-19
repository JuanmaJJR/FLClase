package com.example.examen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.examen.Adapters.ListaNoticiasAdapter;
import com.example.examen.FBObjects.FBNoticia;
import com.example.milib.ListaFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListaFragment ListaFragmentNoticias;
    private Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //inicializamos el secondactivityevents y le hacemos listener del FireBaseAdmin, al igual que en el primer activity
        SecondActivityEvents events = new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);


        //boton logout, le decimos su id visual y su listener
        this.btnLogOut = (Button) this.findViewById(R.id.btnlogout);
        this.btnLogOut.setOnClickListener(events);



        ListaFragmentNoticias = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentListNoticias);

        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Noticias");
    }
}
class SecondActivityEvents implements FireBaseAdminListener, View.OnClickListener{

    SecondActivity secondActivity;

    public SecondActivityEvents(SecondActivity secondActivity){
        this.secondActivity = secondActivity;
    }

    //Por aqui filtramos nuestra rama con la condicion IF. En este caso filtramos por la de notocias, y añadimos el adapted de noticias al recyclerview del fragment de noticias.
    @Override
    public void FireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {
        if(rama.equals("Noticias")){
            GenericTypeIndicator<ArrayList<FBNoticia>> indicator = new GenericTypeIndicator<ArrayList<FBNoticia>>(){};
            ArrayList<FBNoticia> coches = dataSnapshot.getValue(indicator);
            ListaNoticiasAdapter listaCochesAdapter = new ListaNoticiasAdapter(coches,secondActivity);
            secondActivity.ListaFragmentNoticias.recyclerView.setAdapter(listaCochesAdapter);
        }

    }


//Estos metodos estan vacios porque nos vemos obligados a importarlos.
    @Override
    public void FireBaseAdmin_RegisterOk(Boolean ok) {

    }

    @Override
    public void FireBaseAdmin_LoginOk(Boolean ok) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnlogout){
            System.out.print("looogggoutt");
            DataHolder.instance.fireBaseAdmin.getmAuth().signOut();
            Intent intent = new Intent(secondActivity, MainActivity.class);
            secondActivity.startActivity(intent);
            secondActivity.finish();

        }
    }
}
