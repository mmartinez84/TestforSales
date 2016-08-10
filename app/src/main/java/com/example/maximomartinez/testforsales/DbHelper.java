package com.example.maximomartinez.testforsales;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by maximo.martinez on 7/27/2015.
 */
public class DbHelper extends SQLiteOpenHelper {
    private  static  final String DB_NAME = "dbsalesnew";
    private  static  final int DB_SCHEME_VERSION = 3;
   

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbManager.CREATE_TABLE);
        db.execSQL(DbManager.CREATE_CATEGORY);
        db.execSQL(DbManager.CREATE_PRODUCT);
        db.execSQL(DbManager.CREATE_INVOICE);
        db.execSQL(DbManager.CREATE_VCUSTUMERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DbManager.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbManager.TABLE_CAT);
        db.execSQL("DROP TABLE IF EXISTS " + DbManager.TABLE_FACT);

        Log.d("mensaje", "Tablas Eliminadas");

        db.execSQL(DbManager.CREATE_TABLE);
        db.execSQL(DbManager.CREATE_CATEGORY);
        db.execSQL(DbManager.CREATE_PRODUCT);
        db.execSQL(DbManager.CREATE_INVOICE);
        db.execSQL(DbManager.CREATE_VCUSTUMERS);

    }
}
