package com.example.app.yarabisahl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WaRda on 01/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME="ULTT.db";
    public static  final String Table_Name_S = "Student";
    public static  final String Table_Name_T = "Teachers";
    public static  final String Table_Name_F = "Fil";
   // Teachers
    public static  final String C_1="Idt";
    public static  final String C_2="Nomt";
    public static  final String C_3="password";
    public static  final String C_4="Email";
    // Students
    public static  final String V_1="Ids";
    public static  final String V_2="Cne";
    public static  final String V_3="Noms";
    public static  final String V_4="Prenoms";
    public static  final String V_5="Abs";
    public static  final String V_6="Fil";
    // Filieres
    public static  final String F_2="Nomf";


    //Ids";"Cne";"Noms";"Prenoms";"Abs";"Fil;img







    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table_Fil());
        db.execSQL(Table_Teachers());
        db.execSQL(Table_Std());
    }

   public String  Table_Std()
    {

        String Sql_Stm;
        Sql_Stm="create table "+Table_Name_S+" (Ids INTEGER PRIMARY KEY AUTOINCREMENT,Cne INTEGER,Noms TEXT,Prenoms TEXT,Abs INTEGER,Fil Text)";

        return Sql_Stm;
    }

    public String  Table_Teachers()
    {

        String Sql_Stm;
        Sql_Stm="create table "+Table_Name_T+" (Idt INTEGER PRIMARY KEY AUTOINCREMENT,Nomt TEXT,password TEXT,Email TEXT)";

        return Sql_Stm;
    }

    public String  Table_Fil()
    {
        String Sql_Stm;
        Sql_Stm="create table "+Table_Name_F+" (Nomf TEXT PRIMARY KEY )";
        return Sql_Stm;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS  " + Table_Name_T);
            db.execSQL("DROP TABLE IF EXISTS  " + Table_Name_F);
            db.execSQL("DROP TABLE IF EXISTS  " + Table_Name_S);
        onCreate(db);
    }

    public boolean Insertdata(String Name,String password,String Email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(C_2,Name);
        CV.put(C_3,password);
        CV.put(C_4,Email);
       Long Res = db.insert(Table_Name_T,null,CV);
     if(Res == -1)
     {

         return false;

     }
        else {

         return  true;

     }

    }
    // filiere
    public boolean InsertdataStd(String Cne ,String Noms,String Prenoms,String Abs,String Fil)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(V_2,Cne);
        CV.put(V_3,Noms);
        CV.put(V_4,Prenoms);
        CV.put(V_5,Abs);
        CV.put(V_6,Fil);
        Long Res = db.insert(Table_Name_S,null,CV);
        if(Res == -1)
        {

            return false;

        }
        else {

            return  true;

        }

    }
    public boolean updateDataStd(String Cne ,String Noms,String Prenoms,String Fil) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(V_2,Cne);
        contentValues.put(V_3,Noms);
        contentValues.put(V_4,Prenoms);
        contentValues.put(V_6,Fil);
        db.update(Table_Name_S, contentValues, "Cne = ?",new String[] { Cne });
        return true;
    }
    public Integer deleteDataStd (String Cne) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name_S, "Cne = ?",new String[] {Cne});
    }
    public Integer deleteDataFil (String Namf) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name_F, "Nomf = ?",new String[] {Namf});
    }

    public boolean InsertdataFiliere(String Nomf)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(F_2,Nomf);
        Long Res = db.insert(Table_Name_F,null,CV);
        if(Res == -1)
        {

            return false;

        }
        else {

            return  true;

        }

    }
    public boolean Mark(String Cne ,String Abs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(V_1,Cne);
        contentValues.put(V_5,Abs);
        db.update(Table_Name_S, contentValues, "Ids = ?",new String[] { Cne });
        return true;
    }
    public Cursor getFilData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name_F,null);
        return res;
    }

    public Cursor getStdData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name_S,null);
        return res;
    }

    public Cursor getTeacherData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name_T,null);
        return res;
    }
}
