package com.example.milib;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public EditText etUsername;
    public EditText etPass;
    public Button btnLogin;
    public Button btnRegister;
    public LoginFragmentEvents events;
    public LoginFragmentListener listener;
    CallbackManager callbackManager;
    LoginButton loginButton;
    TwitterLoginButton loginButtontw;


    public LoginFragment() {
        // Required empty public constructor
    }

    public void setListener(LoginFragmentListener listener) {
        this.listener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Twitter.initialize(getActivity());

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        loginButtontw = (TwitterLoginButton) v.findViewById(R.id.login_buttontw);
        loginButtontw.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                listener.cambiarPantalla();
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });


        //asignacion de elementos
        etUsername = v.findViewById(R.id.etusername);
        etPass = v.findViewById(R.id.etpass);
        btnLogin = v.findViewById(R.id.btnlogin);
        btnRegister = v.findViewById(R.id.btnregister);
        //inicializacion de events
        events = new LoginFragmentEvents(this);
        //asignacion de controlador de eventos a los botones
        btnLogin.setOnClickListener(events);
        btnRegister.setOnClickListener(events);

        callbackManager = CallbackManager.Factory.create();
        /*
        TwitterConfig config = new TwitterConfig.Builder(this.getActivity())
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("dqAQ0NFW4MX6ztbKaMbHzKlxM", "JkhdOY217Cq9scjAdp7lmNDPNOWM6AoApMa0yoJAY3qEwQUTZb"))
                .debug(true)
                .build();
        Twitter.initialize(config);*/


        loginButton = (LoginButton) v.findViewById(R.id.login_buttonfb);
        loginButton.setReadPermissions("email");
        // If using in a fragment
        loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.v("FaceBActivity", "ME HE LOGEADO EN FACEBOOK CON EL FRAGMENTO");
            }

            @Override
            public void onCancel() {
                // App code
                Log.v("FaceBActivity", "SE HA CANCELADO EL LOGIN EN FACEBOOK");

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.v("FaceBActivity", "ERROR FACEBOOK");

            }


        });
        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("FaceBActivity","es asi: "+resultCode + "request: "+ requestCode);
        if(requestCode==64206){
            callbackManager.onActivityResult(requestCode, resultCode, data);
            listener.cambiarPantalla();

        }
        else{
            super.onActivityResult(requestCode, resultCode, data);

            // Pass the activity result to the fragment, which will then pass the result to the login
            // button.

            loginButtontw.onActivityResult(requestCode, resultCode, data);
            listener.cambiarPantalla();


        }
        }
        // Pass the activity result to the fragment, which will then pass the result to the login
        // button.
    }


    class LoginFragmentEvents implements View.OnClickListener {

        private LoginFragment loginFragment;

        public LoginFragmentEvents(LoginFragment fragment) {
            this.loginFragment = fragment;

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == this.loginFragment.btnLogin.getId()) {
                this.loginFragment.listener.loginFragmentLoginButtonClicked(this.loginFragment.etUsername.getText().toString(), this.loginFragment.etPass.getText().toString());
            } else if (view.getId() == this.loginFragment.btnRegister.getId()) {
                this.loginFragment.listener.loginFragmentRegisterButtonClicked();
            }


        }
    }

