package com.example.maximomartinez.testforsales;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by maximo.martinez on 8/1/2015.
 */
public class CustomCursorAdapter extends CursorAdapter {

    public CustomCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

       LayoutInflater inflater  = LayoutInflater.from(parent.getContext());
       View retView = inflater.inflate(R.layout.custom_row, parent, false);
       // return  LayoutInflater.from(context).inflate(R.layout.custom_row, parent,false);

        String s = (String) retView.getTag();


        return retView;


    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // View item = LayoutInflater.from(context).inflate(R.layout.custom_row, null);


        TextView texName =(TextView) view.findViewById(R.id.textView1);
        texName.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));

        TextView textCode =(TextView)view.findViewById(R.id.textView2);
        textCode.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
        //texName.setText("Hola");

      //  view.setTag(texName.getText());
       // Log.d("***************", textCode.getText().toString());

       // item.setTag("prueba");


    }




}
