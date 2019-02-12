package com.example.limpi;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RegistroUsuario extends AppCompatActivity {

    private Typeface fuente;
    private TextView fontbienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        String treb = "font/trebucbd.ttf";
        this.fuente = Typeface.createFromAsset(getAssets(),treb);

        fontbienvenido = findViewById(R.id.bienvenido);
        fontbienvenido.setTypeface(fuente);


    }
}
