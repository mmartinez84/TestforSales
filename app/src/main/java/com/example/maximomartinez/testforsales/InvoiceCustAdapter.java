package com.example.maximomartinez.testforsales;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by maximo.martinez on 7/8/2016.
 */
public class InvoiceCustAdapter extends CursorAdapter {

    public InvoiceCustAdapter(FactListActivity context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater  = LayoutInflater.from(parent.getContext());
        View retView  = inflater.from(context).inflate(R.layout.invoice_row, parent, false);

        String s  = (String) retView.getTag();

        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        //fill

        TextView tex_Id = (TextView) view.findViewById(R.id.texIdfat);
        tex_Id.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));

        TextView tex_info1 = (TextView)view.findViewById(R.id.texInfo1);
        tex_info1.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));

        TextView tex_info2 = (TextView)view.findViewById(R.id.texInfo2);
        tex_info2.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));


        TextView tex_info3  = (TextView)view.findViewById(R.id.texInfo3);
        tex_info3.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));






    }
}
