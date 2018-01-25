package com.example.juanmajr.flclase;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.juanmajr.flclase.Adapters.ListaCochesAdapter;
import com.example.juanmajr.flclase.Adapters.ListaMensajesAdapter;
import com.example.juanmajr.flclase.FBObjects.FBCoche;
import com.example.juanmajr.flclase.FBObjects.Mensaje;
import com.example.milib.DetallesFragment;
import com.example.milib.GPSadmin.GPSTracker;
import com.example.milib.ListaFragment;
import com.example.milib.MapDetailFragment;
import com.example.milib.synctask.HtppAsyncTask;
import com.example.milib.synctask.HttpJsonAsyncTask;
import com.facebook.AccessToken;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListaFragment listaMensajesFragment,ListaFragmentCoches;
    SupportMapFragment mapFragment;
    DetallesFragment mapDetallesFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SecondActivityEvents events = new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);

      //  listaMensajesFragment = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentListaMensajes);
        ListaFragmentCoches = (ListaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentListCoches);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMapa);
        mapFragment.getMapAsync(events);
        mapDetallesFragments =(DetallesFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMapDetalles);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(ListaFragmentCoches);
        transaction.hide(mapDetallesFragments);
        transaction.show(mapFragment);
        transaction.commit();

        GPSTracker gpsTracker=new GPSTracker(this);
        if(gpsTracker.canGetLocation()){
            Log.v("SecondActivity",gpsTracker.getLatitude()+"  "+gpsTracker.getLongitude());
            FBCoche fbcoche = new FBCoche(2017,"Cochecito","ferrari",gpsTracker.getLatitude(),gpsTracker.getLongitude(),"");
            DataHolder.instance.fireBaseAdmin.insertarenrama("/Coches/4",fbcoche.toMap());
        }
        else{
            gpsTracker.showSettingsAlert();
        }

       // HtppAsyncTask htppAsyncTask=new HtppAsyncTask();
        //htppAsyncTask.execute("http://www.worldbestlearningcenter.com/tips/img-files/android_stop_asynctask.jpg");

        //DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Coches");



        ArrayList<String> mdatos= new ArrayList<>();


         //ListaMensajesAdapter listaMensajesAdapter = new ListaMensajesAdapter(mdatos);

       //listaMensajesFragment.recyclerView.setAdapter(listaMensajesAdapter);
        HttpJsonAsyncTask httpJSONAsyncsTask=new HttpJsonAsyncTask();
        String url= String.format("http://api.openweathermap.org/data/2.5/weather?id=%s&appid=%s",
                "3117732",DataHolder.instance.API_KEY);
        httpJSONAsyncsTask.execute(url);;

        HttpJsonAsyncTask httpJSONAsyncsTask1=new HttpJsonAsyncTask();
        String url1= String.format("http://10.0.2.2/JugadoresJSONServer/leeJugadores.php");
        httpJSONAsyncsTask1.execute(url1);
    }
}

class SecondActivityEvents implements FireBaseAdminListener,OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    SecondActivity secondActivity;
    ArrayList<FBCoche> coches;
    GoogleMap mMap;


    public SecondActivityEvents(SecondActivity secondActivity){
        this.secondActivity = secondActivity;
    }

    @Override
    public void FireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {
        if(rama.equals("Coches")){
            quitarViejosPines();
            GenericTypeIndicator<ArrayList<FBCoche>> indicator = new GenericTypeIndicator<ArrayList<FBCoche>>(){};
            coches = dataSnapshot.getValue(indicator);
            ListaCochesAdapter listaCochesAdapter = new ListaCochesAdapter(coches,secondActivity);
            secondActivity.ListaFragmentCoches.recyclerView.setAdapter(listaCochesAdapter);
            agregarPinesCoches();
        }
    }

    @Override
    public void handleFacebookAccessToken(AccessToken token) {

    }

    public void quitarViejosPines(){
    if(coches!=null){
        for (int i=0; i < coches.size();i++){
            FBCoche cochetemp = coches.get(i);
            if(cochetemp.getMarker()!=null){
                cochetemp.getMarker().remove();
            }
        }
    }
    }

    public void agregarPinesCoches(){
        for (int i=0; i < coches.size();i++){
            FBCoche cochetemp = coches.get(i);
            LatLng cochePos = new LatLng(cochetemp.lat, cochetemp.lon);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(cochePos);
            markerOptions.title(cochetemp.Nombre);
            if(mMap!=null){
                Marker marker=mMap.addMarker(markerOptions);
                marker.setTag(cochetemp);
                cochetemp.setMarker(marker);
                if(i==0){
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cochePos,10));
                }
            }


        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);

        // Add a marker in Sydney and move the camera
     //   LatLng sydney = new LatLng(-34, 151);
      //  mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Coches");


    }



    @Override
    public void FireBaseAdmin_RegisterOk(Boolean ok) {

    }

    @Override
    public void FireBaseAdmin_LoginOk(Boolean ok) {

    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        FBCoche coche= (FBCoche)marker.getTag();

        secondActivity.mapDetallesFragments.txtNombre.setText(coche.Nombre);
        secondActivity.mapDetallesFragments.txtFabricado.setText(coche.Fabricado+"");
        secondActivity.mapDetallesFragments.txtMarca.setText(coche.Marca);

        FragmentTransaction transaction = secondActivity.getSupportFragmentManager().beginTransaction();
        transaction.show(secondActivity.mapDetallesFragments);
        transaction.commit();
        return false;
    }
}
