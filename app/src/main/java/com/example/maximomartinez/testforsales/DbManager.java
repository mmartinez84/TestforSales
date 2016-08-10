package com.example.maximomartinez.testforsales;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by maximo.martinez on 7/27/2015.
 */
public class DbManager {
    //tablas
    public  static  final  String TABLE_NAME ="customers";
    public  static  final  String TABLE_CAT  ="categories";
    public  static  final  String TABLE_PROD = "products";
    public  static  final  String TABLE_FACT  ="invoices";

    //views
    public  static  final  String VIEW_CLI = "vcustumers";

    //campos
    public  static  final  String CN_ID = "_id";
    public  static  final  String CN_NAME ="_name";
    public  static  final  String CN_PHONE = "_phone";
    public  static  final  String CN_EMAIL = "_email";

    public static  final  String CC_ID  = "_id";
    public static  final  String CC_CAT = "_category";
    public static  final  String CC_DESC = "_description";

    public  static  final  String PD_ID = "_id";
    public  static  final  String PD_DESC  = "_descripcion";
    public  static  final  String PD_PRIC = "_price";
    public  static  final  String PD_TAX = "_tax";
    public  static  final  String PD_UM = "_um";

    public  static  final  String IV_ID = "_id";
    public  static  final  String IV_CUST = "_custumer_id";
    public  static  final  String IV_AMOUNT = "_amount";
    public  static  final  String IV_OAMOUNT = "_open_am";




    public  static  final  String CREATE_TABLE = " create table " + TABLE_NAME +" ("
            + CN_ID + " INTEGER primary key autoincrement, "
            + CN_NAME + " text not null,"
            + CN_PHONE + " text, "
            + CN_EMAIL + " text);";

    public  static  final  String CREATE_CATEGORY  = " create table " +  TABLE_CAT + " ("
            + CC_ID + " INTEGER primary key autoincrement, "
            + CC_CAT + " text not null, "
            + CC_DESC + " text);";

    public static  final String CREATE_PRODUCT  = " create table " + TABLE_PROD + " ( "
            + PD_ID + " INTEGER primary key autoincrement, "
            + PD_DESC + " TEXT not null, "
            + PD_PRIC + " DECIMAL (18,2),  "
            + PD_TAX + " INTEGER , "
            + PD_UM + " TEXT );";


    public static  final  String CREATE_INVOICE = " create table " + TABLE_FACT + " ("
            + IV_ID + " INTEGER primary key autoincrement , "
            + IV_CUST + " INTEGER, "
            + IV_AMOUNT + " DECIMAL (18,2), "
            + IV_OAMOUNT + " DECIMAL (18,2) );";


    public  static  final  String CREATE_VCUSTUMERS = "create view " + VIEW_CLI +
            " as SELECT  " +TABLE_NAME + "." +  CN_ID  + " as _Id, " +
            " " + TABLE_NAME + "." + CN_NAME + " , " +
            " sum(" + TABLE_FACT + "." + IV_AMOUNT + ") as _open " +
            " FROM " + TABLE_NAME + " JOIN " + TABLE_FACT +
            " ON " + TABLE_NAME + "." + CN_ID + " = "+ TABLE_FACT + "." + IV_CUST;




    private DbHelper helper;
    private SQLiteDatabase db;

    public DbManager(Context context) {

         helper = new DbHelper(context);
         db = helper.getWritableDatabase();
        Log.d("Maximo", "DbHelper Opening Version: " + this.db.getVersion());

    }

    private  void  openWriteableDB(){
        db = helper.getWritableDatabase();
    }

    //CRUD OPERATIONS FOR CUSTUMERS
    private ContentValues generateContentValues(String name, String phone,String email){
        ContentValues values = new ContentValues();
        values.put(CN_NAME, name);
        values.put(CN_PHONE, phone);
        values.put(CN_EMAIL, email);

        return values;

    }

    //Datos  Productos

    private  ContentValues contentProd (int _Id, String _desc, double _prec, int _tax, String _um){

        ContentValues values  = new ContentValues();
        values.put(PD_ID, _Id);
        values.put(PD_DESC, _desc);
        values.put(PD_PRIC, _prec);
        values.put(PD_TAX, _tax);
        values.put(PD_UM, _um);

        return  values;
    }

    public void insertProd( int _id, String _desc, double _prec, int _tax,String _um){

        db.insert(TABLE_PROD, null, contentProd(_id, _desc,_prec,_tax,_um));

    }


    //Datos Facturas
    private ContentValues contenFact( int _idCust, double _amount, double _opamount){
        ContentValues values = new  ContentValues();

        values.put( IV_CUST, _idCust);
        values.put(IV_AMOUNT, _amount);
        values.put(IV_OAMOUNT, _amount);

        return  values;
    }

    public  void  insertFact(int Idcust, double amount, double openamount){

        db.insert(TABLE_FACT, null, contenFact(Idcust, amount, openamount));
    }

    //Datos Cliente
    public  void insertCli(String name, String phone,String email ){

        db.insert(TABLE_NAME, null, generateContentValues(name, phone, email));
    }

    //insert comand sql
    public  void  insert (String name, String phone,String email){

       //db.execQSL + query
        db.execSQL("insert into ...");
    }

    public void  DeleteCli(String name){
        //db.delete(tabla,where,argumentos del where)

        db.delete(TABLE_NAME, CN_NAME + "=?", new String[]{name});

    }

    public  void  DeleteMultiple(String name, String name2){
        //borrado multiple
        db.delete(TABLE_NAME, CN_NAME + " IN (?,?) ", new String[]{name, name2});
    }

    public void  updateCli(String name, String phone,String email){

        db.update(TABLE_NAME, generateContentValues(name, phone, email), CN_NAME + "=? ", new String[]{name});
    }


  //Consultas
    public Cursor loadCursorCli(){

        String[] columns = new String[]{ CN_ID, CN_NAME,CN_PHONE, CN_EMAIL};

       Cursor cursor =  db.query(TABLE_NAME, columns, null, null, null, null, null);

        //return db.rawQuery("select _id  from " + TABLE_NAME, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        return  cursor;


    }

    public  Cursor loadCursorFact( int cod){

        openWriteableDB();
        //cFactInfo f  = new cFactInfo();

        Cursor cursor = db.rawQuery(" select * from " + TABLE_FACT + " where _custumer_id = "
                                                                             + cod, null  );

        Log.d("debug", "Facturas del cliente");

        if(cursor != null || cursor.getCount() <=0){
            cursor.moveToFirst();
            }

        return  cursor;

    }

    public cClienInfo buscaCli(String cod){

        openWriteableDB();
         cClienInfo c  = new cClienInfo();

         // String where  = CN_ID + "." + CN_ID + "= ?";
         // String[] param  = {cod};
         //  String[] param  = new String[1];
         //   param[0] = Integer.toString()

       // Cursor cursor = db.query(VIEW_CLI,null, where, param,null, null, null);
        Cursor cursor = db.rawQuery("select * from " + VIEW_CLI + " WHERE _id =  " + cod, null);

        Log.d("Errpr", " select * from  + VIEW_CLI +  WHERE _id =   cod");

        if (cursor != null || cursor.getCount() <=0){

            cursor.moveToFirst();
            c.set_id(cursor.getInt(0));
            c.setName(cursor.getString(1));
            c.setAmount(cursor.getDouble(2));

            cursor.close();

        }
        return  c;




    }

    //CRUD OPERATIONS FOR CATEGORIES

    private ContentValues generateCatContentValues(String cat, String desc){

        ContentValues values = new ContentValues();
        values.put(CC_CAT, cat);
        values.put(CC_DESC, desc);

        return  values;
    }

    public  void  insertCat(String cat , String desc){

        db.insert(TABLE_CAT,null, generateCatContentValues(cat, desc));
    }
}
