package com.example.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.milib.ListaFragment;

public class SecondActivity extends AppCompatActivity {
    ListaFragment ListaFragmentNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ListaFragmentNoticias = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentListNoticias);

        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Noticias");
    }
}
