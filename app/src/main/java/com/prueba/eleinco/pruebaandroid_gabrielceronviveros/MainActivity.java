package com.prueba.eleinco.pruebaandroid_gabrielceronviveros;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Gabriel Ceron on 30/09/2016.
 */

public class MainActivity extends AppCompatActivity { // MainActivity implementada para el Login del usuario

    private Button bLogin;
    private String user;
    private String email;
    private EditText edad;
    private Integer anos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bLogin = (Button) findViewById(R.id.buttonlogin); //Se configura boton de Login
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = ((EditText)findViewById(R.id.editTextName)).getText().toString();
                email = ((EditText)findViewById(R.id.emailT)).getText().toString();
                edad = (EditText)findViewById(R.id.edadT);

                try { // Se verifica que lo ingresado sea un entero
                    anos = Integer.parseInt(edad.getText().toString());
                } catch (NumberFormatException e) {
                    anos =0;
                }

                if (!user.isEmpty()&&!email.isEmpty()&&(anos!=0)){  // Se verifican los datos
                    startActivity(new Intent(MainActivity.this,Cinema.class));
                    SharedPreferences.Editor editor = getSharedPreferences("Credenciales", MODE_PRIVATE).edit(); // Guardan Credenciales
                    editor.putString("NOMBRE",user);
                    editor.putString("CORREO",email);
                    editor.putInt("EDAD",anos);
                    editor.commit();
                    finish(); // Se cierra actividad de login
                }else{
                    Toast.makeText(MainActivity.this, "Por favor ingrese sus datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
