package com.camarografia.elektra.caguayo.camarografia;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

public class UploadTask extends AsyncTask<String,Void,Boolean> {
    private ProgressDialog progressDialog;
    AlertDialog.Builder builder;
    private Context context;


    public UploadTask(Context context) {
        this.context = context;
        builder = new AlertDialog.Builder(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Por favor espere", "Consultando...");
    }


    @Override
    protected Boolean doInBackground(String... params) {
        Boolean r = false;
        ApiRest apiRest = new ApiRest();
        try {
            r = apiRest.uploadPhoto(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }


    @Override
    protected void onPostExecute(Boolean resul) {
        progressDialog.dismiss();
        if( resul )
        {
            builder.setMessage("Imagen enviada al servidor")
                    .setTitle("Information")
                    .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            dialog.cancel();
                        }
                    }).create().show();
        }
        else
        {
            builder.setMessage("No se pudo subir la imagen")
                    .setTitle("Information")
                    .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            dialog.cancel();
                        }
                    }).create().show();
        }
    }
}

