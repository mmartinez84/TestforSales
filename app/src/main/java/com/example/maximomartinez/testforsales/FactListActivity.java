package com.example.maximomartinez.testforsales;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class FactListActivity extends ActionBarActivity {

    private DbManager manager;
    private Cursor cursor;
    private ListView listView;
    private InvoiceCustAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_list);

        manager  = new DbManager(this);

       //  String b =  "1";

        cursor = manager.loadCursorFact(1);
        if(cursor.getCount() > 1){


            listView  = (ListView)findViewById(R.id.listInvoice);

            SearchFact searchFact  = new SearchFact();
            searchFact.execute();

        }else{
            Toast.makeText(this, "Error al leer los datos", Toast.LENGTH_LONG);
        }
    }

    private  class  SearchFact extends AsyncTask<Void, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Void... params) {


            adapter = new InvoiceCustAdapter(FactListActivity.this, cursor, 0);

            return null;


        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            listView.setAdapter(adapter);
        }
    }
}
