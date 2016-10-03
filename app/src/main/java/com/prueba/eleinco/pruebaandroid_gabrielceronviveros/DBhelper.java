package com.prueba.eleinco.pruebaandroid_gabrielceronviveros;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gabriel Ceron on 30/09/2016.
 */

public class DBhelper extends SQLiteOpenHelper { // DBhelper usada para configurar base de datos y crear tablas iniciales

    private static final String DB_NAME = "contactos.sqlite";
    private static final int DB_SCHEME_VERSION = 2;

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}