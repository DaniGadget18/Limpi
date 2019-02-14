package com.example.limpi;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class recuperarContrasena extends AppCompatActivity {

    private Typeface fuente1;
    private TextView txtrecuperar, txtrecuperatu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        String fuente = "font/trebucbd.ttf";
        this.fuente1 = Typeface.createFromAsset(getAssets(),fuente);

        txtrecuperar = findViewById(R.id.fontextorecuperar);
        txtrecuperatu = findViewById(R.id.fontrecuperatucontra);
        txtrecuperar.setTypeface(fuente1);
        txtrecuperatu.setTypeface(fuente1);


    }
}
