package com.prueba.eleinco.pruebaandroid_gabrielceronviveros;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Gabriel Ceron on 30/09/2016.
 */

public class DBManager {

    public static final String TABLE_NAME = "PeliculasVIstas";

    public static final String CN_ID = "_id";
    public static final String NOMBRE = "nombre";
    public static final String GENERO = "genero";
    public static final String EDAD = "edad";
    public static final String HORARIOS = "horarios";
    public static final String URL = "url";
    public static final String DURACION = "telefono";

    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("  // Se crea el comando en SQL para la creacion de la tabla
            + CN_ID + " integer primary key autoincrement,"
            + NOMBRE + " text not null,"
            + GENERO + " text not null,"
            + EDAD + " text not null,"
            + HORARIOS + " text not null,"
            + URL + " text not null,"
            + DURACION + " text);";



    private DBhelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBhelper(context);
        db = helper.getWritableDatabase();
    }

    // Con el siguiente metodo se organizan los campos con los respectivos valores
     public ContentValues generarContentValues(String nombre, String genero, String edad, String horarios, String url, String duracion){
        ContentValues valores = new ContentValues();
        valores.put(NOMBRE, nombre);
        valores.put(GENERO, genero);
        valores.put(EDAD, edad);
        valores.put(HORARIOS, horarios);
        valores.put(URL, url);
        valores.put(DURACION, duracion);
        return valores;
    }

    // Se insertan valores en la BD
    public void insertar(String nombre, String genero, String edad, String horarios, String url, String duracion){
        db.insert(TABLE_NAME,null,generarContentValues(nombre,genero,edad,horarios,url,duracion));
    }

    public Cursor cargarCursor(){ // Usado para cargar el cursor de la BD y mostrar en la lista

        String[] columnas = new String[]{CN_ID, NOMBRE, DURACION};
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }

}
