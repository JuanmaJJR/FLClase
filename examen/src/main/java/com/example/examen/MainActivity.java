package com.example.examen;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.milib.LoginFragment;
import com.example.milib.LoginFragmentListener;
import com.example.milib.RegisterFragment;
import com.example.milib.RegisterFragmentListener;

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
class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener{
    MainActivity mainActivity;
    public MainActivityEvents(MainActivity mainActivity){
        this.mainActivity = mainActivity;

    }

    @Override
    public void loginFragmentLoginButtonClicked(String sUser, String sPass) {

    }

    @Override
    public void loginFragmentRegisterButtonClicked() {

    }

    @Override
    public void registerFragmentBtnAceptarClicked(String sUser, String sPass) {

    }

    @Override
    public void registerFragmentBtnCancelarClciked() {

    }
}
