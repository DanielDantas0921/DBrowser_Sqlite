package com.example.dbbrowserecriaodedbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static int versao = 1;
    private static String nomeDB = "ExemploDB.dbs";


    String[] sql = {
            "CREATE TABLE Utilizador(username TEXT PRIMARY KEY,password TEXT);",
            "INSERT INTO Utilizador VALUES('user1','pass1');",
            "INSERT INTO Utilizador VALUES('user2','pass2');"



    };


    public DBHelper(@Nullable Context context) {
        super(context, nomeDB, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i = 0; i < sql.length; i++) {

            db.execSQL(sql[i]);

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        versao++;
        db.execSQL("DROP TABLE IF EXISTS Utilizador");
        onCreate(db);

    }


    //-------------------------------INSERT--------------------------------------

    public long Insert_Utilizador(String username, String password) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        return db.insert("Utilizador", null, values);

    }

    //-------------------------------UPDATE--------------------------------------

    public long Update_Utilizador(String username, String password) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", password);
        return db.update("Utilizador", values, "username=?", new String[]{username});
    }

    //-------------------------------DELETE--------------------------------------

    public long Delete_Utilizador(String username) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("Utilizador","username=?",new String[]{username});

    }


    //--------------------------------SELECT-------------------------------------

    public Cursor SelectAll_Utilizador() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Utilizador", null);

    }

    public Cursor SelectByUsername_Utilizador(String username){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM  Utilizador WHERE username=? ", new String[] {username});

    }


}
