package com.example.maximomartinez.testforsales;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Handler;


public class Clients_Reg extends ActionBarActivity {
   private DbManager manager;
   private Cursor cursor;

    private ListView lv;
   // SimpleCursorAdapter adapter;
    private  CustomCursorAdapter cr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients__reg);

        //DbHelper helper = new DbHelper(this);
        //SQLiteDatabase db = helper.getWritableDatabase();
         manager = new DbManager(this);

       //  manager.insertCli("Comercial Chalas", "809-590-1037", "chalasco@hotmail.com");
       //  manager.insertCli("Casa Paco", "809-555-1037", "casapaco@hotmail.com");
       // manager.insertCli("Super Cumbre", "809-598-1037", "casapaco@hotmail.com");
       // manager.insertCli("Supermercado Loli", "809-590-4037", "casapaco@hotmail.com");


       // manager.insertFact(1, 700, 350);
        //manager.insertFact(1, 1000, 550);

        manager.insertProd(100,"punta de eje",125.00, 1, "PZ");
        manager.insertProd(101, "Mobil 1 20/50", 250.00, 1, "UN");



        cursor = manager.loadCursorCli();
        if (cursor.getCount() > 1) {
            lv = (ListView) findViewById(R.id.listView);

            SearchAsync tarea = new SearchAsync();
            tarea.execute();



            // String[] from = new String[]{manager.CN_NAME,manager.CN_PHONE};

            // ListAdapter adap = new CustomAdapter(this, from);
            //cr = new CustomCursorAdapter(Clients_Reg.this,cursor,0);


            //  String[] to = new String[] {android.R.id.text1, android.R.id.text2};
            //  adapter = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to,0);
            // lv.setAdapter(adapter);

          //  lv.setAdapter(cr);
        }else{
         Toast.makeText(this, "Errot en la lectura de datos!!", Toast.LENGTH_SHORT).show();
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               view.setSelected(true);


                Cursor c  = (Cursor)lv.getItemAtPosition(position);
                String x = c.getString(c.getColumnIndexOrThrow("_id"));


                if(!x.equals(null)  ) {

                        Intent newActivity = new Intent(Clients_Reg.this, OptionsActivity.class);
                        newActivity.putExtra("codigo", x);
                        startActivity(newActivity);

                }

               // Toast.makeText(getApplicationContext(), x , Toast.LENGTH_LONG).show();




            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clients__reg, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private  class SearchAsync extends AsyncTask<Void, Integer, Boolean>{
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            lv.setAdapter(cr);
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            cr = new CustomCursorAdapter(Clients_Reg.this,cursor,0);

            return null;
        }
    }




}
