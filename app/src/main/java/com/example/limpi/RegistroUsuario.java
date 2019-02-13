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
    private TextView fontbienvenido;
    private Spinner edad;
    String[] edades = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        String treb = "font/trebucbd.ttf";
        this.fuente = Typeface.createFromAsset(getAssets(),treb);

        fontbienvenido = findViewById(R.id.bienvenido);
        fontbienvenido.setTypeface(fuente);

        edad = findViewById(R.id.edades);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.activity_registro_usuario,edad);
        edad.setAdapter(adapter);



    }



}
