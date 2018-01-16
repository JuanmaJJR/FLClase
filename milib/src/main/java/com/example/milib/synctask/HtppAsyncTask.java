package com.example.milib.synctask;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by juan.jusue on 16/01/2018.
 */

public class HtppAsyncTask extends AsyncTask<String,Integer,Integer> {

    public HtppAsyncTask(){

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.v("HttpAsyncTask","DEFINIMOS VARIABLE PREVIAS");

    }

    @Override
    protected Integer doInBackground(String... strings) {

        this.publishProgress(10);
        Log.v("HttpAsyncTask","FASE 1 "+strings[0]);


        this.publishProgress(20);
        Log.v("HttpAsyncTask","FASE 2"+strings[0];

        this.publishProgress(60);
        Log.v("HttpAsyncTask","FASE 3"+strings[0]);

        this.publishProgress(100);
        Log.v("HttpAsyncTask","FASE 4"+strings[0]);


        return 0;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.v("HttpAsyncTask","PORCENTAJE DE PROGESO: "+values[0]);

    }

    @Override
    protected void onPostExecute(Integer in) {
        super.onPostExecute(in);
        Log.v("HttpAsyncTask","SE ACABO LA TAREA");

    }
}
