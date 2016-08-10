package com.example.maximomartinez.testforsales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



/**
 * Created by maximo.martinez on 7/31/2015.
 */
 class CustomAdapter extends ArrayAdapter<String> {
    public CustomAdapter(Context context, String[] from) {
        super(context,R.layout.custom_row, from);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater cliInflater = LayoutInflater.from(getContext());
        View customView = cliInflater.inflate(R.layout.custom_row, parent, false);

        String singleitem = getItem(position);
        TextView textView1 =(TextView) customView.findViewById(R.id.textView1);

        textView1.setText(singleitem);

        return  customView;
    }
}
