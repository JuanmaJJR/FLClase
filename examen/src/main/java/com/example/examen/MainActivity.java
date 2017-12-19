package com.example.examen;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.milib.LoginFragment;
import com.example.milib.LoginFragmentListener;
import com.example.milib.RegisterFragment;
import com.example.milib.RegisterFragmentListener;
import com.google.firebase.database.DataSnapshot;

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
       // DataHolder.instance.fireBaseAdmin.setListener(mainActivityEvents);

        //creamos una trasition de los fragments para mostrar el de login al inicializar la app
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.show(loginFragment);
        transition.hide(registerFragment);
        transition.commit();

    }
}

//Este events implementa los listener de los fragmentos, y del firebaseadmin, para que cuando se usen en el listener de milib
//se ejecuten estos metodos.
class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener,FireBaseAdminListener{
    MainActivity mainActivity;
    public MainActivityEvents(MainActivity mainActivity){
        this.mainActivity=mainActivity;
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

}
