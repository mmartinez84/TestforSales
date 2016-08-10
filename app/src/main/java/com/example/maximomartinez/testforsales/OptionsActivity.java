package com.example.maximomartinez.testforsales;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import  android.util.Log;

public class OptionsActivity extends ActionBarActivity {


   // private DbManager manager;
    private Button btnPedido;
    private  Button btnCobro;
    private TextView textCode;
    private TextView textName;
    private  TextView texAmount;
    private DbManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);


        manager = new DbManager(this);
        String cod = getIntent().getExtras().getString("codigo").toString();

        //getDatos(cod);

        Scliente tarea = new  Scliente();
        tarea.execute();


        texAmount = (TextView)findViewById(R.id.texMonto);
        btnPedido = (Button)findViewById(R.id.btnPedido);
        btnCobro = (Button)findViewById(R.id.btnCobro);
        textCode = (TextView)findViewById(R.id.textCode);
        textName = (TextView)findViewById(R.id.textName);
        textCode.setText(cod);



btnPedido.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



    }
});

        btnCobro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent( OptionsActivity.this,FactListActivity.class);
                startActivity(i);


            }
        });

    }


    private  void  getDatos(){

        cClienInfo c;

        //c= manager.buscaCli(cod);
        Log.d("Errpr", " select * from  + VIEW_CLI +  WHERE _id = " + textCode.getText().toString());

        c= manager.buscaCli(textCode.getText().toString());
        textName.setText(c.getName());
        texAmount.setText(String.valueOf(c.getAmount()));


    }

    private class Scliente extends AsyncTask<Void, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Void... params) {

         getDatos();


            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

}
