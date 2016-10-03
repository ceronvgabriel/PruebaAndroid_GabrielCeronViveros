package com.prueba.eleinco.pruebaandroid_gabrielceronviveros;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Gabriel Ceron on 30/09/2016.
 */

public class AlreadySeen extends AppCompatActivity { // AlreadySeen usada para la actividad de las peliculas que ya se han visto

    private ListView listaYaVistas;
    public DBManager manager;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_already_seen);

        manager = new DBManager(this); // Se crea el objeto para administrar la vase de datos
        listaYaVistas = (ListView) findViewById(R.id.listAlreadySeen);
        cursor = manager.cargarCursor(); // Se cargan los valores de la base de datos para mostrar en la lista
        String[] from = new String[]{manager.NOMBRE,manager.GENERO};
        int[] to = new int[] {android.R.id.text1,android.R.id.text2};
        adapter = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to); // adaptador de la lista
        listaYaVistas.setAdapter(adapter); // Se muestran los datos de la BD en la lista

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {  // Usado para cambio de interfaces desde el menu

        int id = item.getItemId();

        if (id == R.id.action_disponibles) {
            startActivity(new Intent(AlreadySeen.this,Cinema.class));
            finish();
        }

        if (id == R.id.action_vistas) {
            return true;
        }

        if (id == R.id.action_logout) {
            startActivity(new Intent(AlreadySeen.this,MainActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
