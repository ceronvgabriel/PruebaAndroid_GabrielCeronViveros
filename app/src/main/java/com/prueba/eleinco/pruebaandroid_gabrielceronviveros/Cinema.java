package com.prueba.eleinco.pruebaandroid_gabrielceronviveros;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel Ceron on 30/09/2016.
 */

public class Cinema extends AppCompatActivity {

    private ListView lista;
    String[] peliculas;
    String[] campos;
    private DBManager manager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cinema);

        manager2 = new DBManager(this); //Objeto para administrar la base de datos

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:80"; // Para acceder a la informacion en el servidor web "10.0.2.2"

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        respuesta(response); // Se implementa metodo respuesta() en caso de coneccion efectiva
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Cinema.this, "Error de Conexion", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);

    }

    public void respuesta(String data){ // Luego de recibir respuesta se muestra la informacion en una lista

        lista = (ListView) findViewById(R.id.listaCinema);

        peliculas = data.split("\\r?\\n"); // Segun los delimitadores se separan las peliculas por enter o nueva linea

        List<String> nombresPeliculas = new ArrayList<String>();

        for (String pelicula: peliculas) {
            campos = pelicula.split("\\|"); // Se separan los campos de cada pelicula de acuerdo al delimitador |
            nombresPeliculas.add(campos[0]); // Se toman solamente los nombres de las peliculas para mostrar en la lista
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nombresPeliculas);

        lista.setAdapter(adapter); // Se muestran los nombres de las peliculas en la lista

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  // Una vez se da click en una pelicula de la lista se guarda en la base de datos
                String[] campo;
                campo = peliculas[position].split("\\|");
                manager2.insertar(campo[0],campo[2],campo[3],campo[4],campo[5],campo[1]); // Se insertan los datos en la BD de acuerdo al orden asignado
                Toast.makeText(Cinema.this, "Agregada: "+campo[0], Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_disponibles) {
            return true;
        }

        if (id == R.id.action_vistas) {
            startActivity(new Intent(Cinema.this,AlreadySeen.class));
        }

        return super.onOptionsItemSelected(item);
    }


}