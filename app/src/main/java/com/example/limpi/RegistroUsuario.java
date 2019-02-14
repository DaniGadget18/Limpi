package com.example.limpi;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class RegistroUsuario extends AppCompatActivity {

    private Typeface fuente;
    private TextView fontbienvenido, fontnombre, fontapellidos, fontcorreo, fontpass, fontconfpass, fontedad, fontsexo, fontdire;
    private Spinner edad;
    String[] edades = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        String treb = "font/trebucbd.ttf";
        this.fuente = Typeface.createFromAsset(getAssets(),treb);

        fontnombre = findViewById(R.id.fontnombres);
        fontapellidos = findViewById(R.id.fontapellidos);
        fontcorreo = findViewById(R.id.fontemail);
        fontpass = findViewById(R.id.fontpass);
        fontconfpass = findViewById(R.id.fontconfpass);
        fontedad = findViewById(R.id.fontedad);
        fontsexo = findViewById(R.id.fontsexo);
        fontdire = findViewById(R.id.fontdire);
        fontbienvenido = findViewById(R.id.bienvenido);
        fontnombre.setTypeface(fuente);
        fontapellidos.setTypeface(fuente);
        fontcorreo.setTypeface(fuente);
        fontpass.setTypeface(fuente);
        fontconfpass.setTypeface(fuente);
        fontedad.setTypeface(fuente);
        fontsexo.setTypeface(fuente);
        fontdire.setTypeface(fuente);
        fontbienvenido.setTypeface(fuente);



        edad = findViewById(R.id.edades);




    }



}
