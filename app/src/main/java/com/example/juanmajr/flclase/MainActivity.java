package com.example.juanmajr.flclase;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.example.juanmajr.flclase.FBObjects.Contact;
import com.example.milib.LoginFragment;
import com.example.milib.LoginFragmentListener;
import com.example.milib.RegisterFragment;
import com.example.milib.RegisterFragmentListener;
import com.facebook.AccessToken;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    LoginFragment loginFragment;
    RegisterFragment registerFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializado el fragment del Login
        loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentLogin);
        registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentRegister);

        //inicializado el events
        MainActivityEvents mainActivityEvents = new MainActivityEvents(this);

        //seteamos a los fragments y al firebaseadmin su listener, debido a que mainActivityEvents implementa los listener de los fragments
        loginFragment.setListener(mainActivityEvents);
        registerFragment.setListener(mainActivityEvents);
        DataHolder.instance.fireBaseAdmin.setListener(mainActivityEvents);

        //creamos una trasition de los fragments para mostrar el de login al inicializar la app
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.show(loginFragment);
        transition.hide(registerFragment);
        transition.commit();

        DatabaseHandler db = new DatabaseHandler(this);

        db.addContact(new Contact("Juanma", "111111111"));
        db.addContact(new Contact("Peter", "222222222"));
        db.addContact(new Contact("Tuvilla", "333333333"));
        db.addContact(new Contact("Yony", "44444444"));

        // Reading all contacts
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("TUTORIALSQLLITE ", log);
        }

        FirebaseCrash.report(new Exception("My first Android non-fatal error"));
        FirebaseCrash.log("Activity created");





    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the fragment, which will then pass the result to the login
        // button.
        Log.v("aa","sisisisi");
        if (loginFragment != null) {
            loginFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}

//Este events implementa los listener de los fragmentos, y del firebaseadmin, para que cuando se usen en el listener de milib
//se ejecuten estos metodos.
class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener, FireBaseAdminListener{
    MainActivity mainActivity;
    public MainActivityEvents(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    public void loginFragmentFacebook(AccessToken accessToken, FragmentActivity activity) {
        DataHolder.instance.fireBaseAdmin.handleFacebookAccessToken(mainActivity,accessToken);
    }

    //cuando el LoginFragmentEvents reciba que se ha pulsado el boton, llamara al metodo de la interfaz
    //debido a que esta seteado esta clase como el listener, se ejectura este metodo implementado de la interfaz
    @Override
    public void loginFragmentLoginButtonClicked(String sUser, String sPass) {
        DataHolder.instance.fireBaseAdmin.loginConEmailYPassword(sUser,sPass,mainActivity);
    }

    //cuando el LoginFragmentEvents reciba que se ha pulsado el boton, llamara al metodo de la interfaz
    //debido a que esta seteado esta clase como el listener, se ejectura este metodo implementado de la interfaz
    @Override
    public void loginFragmentRegisterButtonClicked() {
        FragmentTransaction transition = mainActivity.getSupportFragmentManager().beginTransaction();
        transition.hide(mainActivity.loginFragment);
        transition.show(mainActivity.registerFragment);
        transition.commit();

    }

    @Override
    public void cambiarPantalla() {
        FirebaseCrash.log("LOGIN RRSS");

        Intent intent = new Intent(mainActivity,SecondActivity.class);
        mainActivity.startActivity(intent);
        mainActivity.finish();
    }

    //cuando el RegisterFragmentEvents reciba que se ha pulsado el boton, llamara al metodo de la interfaz
    //debido a que esta seteado esta clase como el listener, se ejectura este metodo implementado de la interfaz
    @Override
    public void registerFragmentBtnAceptarClicked(String sUser, String sPass) {
        DataHolder.instance.fireBaseAdmin.registerConEmailYPassword(sUser,sPass,mainActivity);
    }
    //cuando el RegisterFragmentEvents reciba que se ha pulsado el boton, llamara al metodo de la interfaz
    //debido a que esta seteado esta clase como el listener, se ejectura este metodo implementado de la interfaz
    @Override
    public void registerFragmentBtnCancelarClciked() {
        FragmentTransaction transition = mainActivity.getSupportFragmentManager().beginTransaction();
        transition.show(mainActivity.loginFragment);
        transition.hide(mainActivity.registerFragment);
        transition.commit();

    }

    //Este metodo es llamado por el firebase admin al realizar el registro, si se le llama con un
    //true por parametro, haremos el intent de las activitys para mostrar la segunda.
    @Override
    public void FireBaseAdmin_RegisterOk(Boolean ok) {
        if(ok){
            Intent intent = new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        }
        else{

        }

    }

    //Este metodo es llamado por el firebase admin al realizar el login, si se le llama con un
    //true por parametro, haremos el intent de las activitys para mostrar la segunda.
    @Override
    public void FireBaseAdmin_LoginOk(Boolean ok) {
        if(ok){
            FirebaseCrash.log("Login normal");
            Intent intent = new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        }
        else{

        }
    }

    @Override
    public void FireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {

    }

    @Override
    public void handleFacebookAccessToken(AccessToken token) {

    }
    @Override
    public void loginFragmentTwitter(TwitterSession twitterSession, FragmentActivity activity){
        DataHolder.instance.fireBaseAdmin.handleTwitterSession(mainActivity,twitterSession);
    }


}
