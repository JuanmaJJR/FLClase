package com.example.milib.synctask;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by juan.jusue on 16/01/2018.
 */

public class HtppAsyncTask extends AsyncTask<String,Integer,String[]> {

    public HtppAsyncTask(){

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.v("HttpAsyncTask","DEFINIMOS VARIABLE PREVIAS");

    }

    @Override
    protected String[] doInBackground(String... urls) {
        int count;
        String pathfin[]=new String[urls.length];
        this.publishProgress(0);

        for(int i=0;i<urls.length;i++){
            try {
                String root = Environment.getExternalStorageDirectory().toString();

                Log.v("HttpAsyncTask","Downloading");
                // System.out.println("Downloading");

                URL url = new URL(urls[0]);

                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file
                this.publishProgress(20);

                pathfin[i]=root+"/downloadedfile"+i+".jpg";
                OutputStream output = new FileOutputStream(pathfin[i]);
                byte data[] = new byte[1024];

                long total = 0;


                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            double  temp = 100*((i+1)/urls.length);
            this.publishProgress(20*(i+1));



        }
        this.publishProgress(100);


        return pathfin;

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.v("HttpAsyncTask","PORCENTAJE DE PROGESO: "+values[0]);

    }

    @Override
    protected void onPostExecute(String[] in) {
        super.onPostExecute(in);
        Log.v("HttpAsyncTask","SE ACABO LA TAREA");

    }
}
