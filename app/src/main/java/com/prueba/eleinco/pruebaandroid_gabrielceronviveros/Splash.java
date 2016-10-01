package com.prueba.eleinco.pruebaandroid_gabrielceronviveros;

/**
 * Created by Gabriel Ceron on 01/10/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Gabriel Ceron on 30/09/2016.
 */

public class Splash extends Activity {


    private final int SPLASH_DISPLAY_LENGTH = 1500; // Tiempo Splash

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_layout);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(Splash.this,MainActivity.class); // Luego de Splash se va a MainActivity (Login)
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
