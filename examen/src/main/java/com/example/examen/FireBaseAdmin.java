package com.example.examen;

import android.support.annotation.NonNull;
import android.util.Log;
import android.app.Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by juan.jusue on 19/12/2017.
 */

public class FireBaseAdmin {
    private FirebaseAuth mAuth;
    public FireBaseAdminListener listener;
    public FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference myRefRaiz;



    public FireBaseAdmin(){

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRefRaiz = database.getReference();
    }
    public void setListener(FireBaseAdminListener listener) {
        this.listener = listener;
    }

    //metodo para registrar con email y password en firebase
    public void registerConEmailYPassword(String email, String pass, Activity activity){
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("FirebaseAdmin", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            listener.FireBaseAdmin_RegisterOk(true);
                        }
                        else{
                            listener.FireBaseAdmin_RegisterOk(false);
                        }

                        // ...
                    }
                });
    }
    //metodo para logear con email y password en firebase
    public void loginConEmailYPassword(String email, String pass, Activity activity){
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("FirebaseAdmin", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            System.out.print("yes");
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            listener.FireBaseAdmin_LoginOk(true);
                        }
                        else{
                            System.out.print("nooo");
                            listener.FireBaseAdmin_LoginOk(false);
                        }

                        // ...
                    }
                });
    }

}
