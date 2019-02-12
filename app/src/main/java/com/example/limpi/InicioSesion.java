package com.example.limpi;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class InicioSesion extends AppCompatActivity {

    private Typeface fuente1, fuente2;

    TextView fontemail, fontpass, fontforgotcontra, fontrecuperar;
    EditText correo, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        String fuente = "font/trebucbd.ttf";
        String fuentetwo = "font/trebuchetms.ttf";
        this.fuente1 = Typeface.createFromAsset(getAssets(),fuente);
        this.fuente2 = Typeface.createFromAsset(getAssets(),fuentetwo);

        fontemail = findViewById(R.id.email);
        fontemail.setTypeface(fuente1);
        fontpass = findViewById(R.id.password);
        fontpass.setTypeface(fuente1);
        fontforgotcontra = findViewById(R.id.forgotcontra);
        fontforgotcontra.setTypeface(fuente1);
        fontrecuperar = findViewById(R.id.recuperar);
        fontrecuperar.setTypeface(fuente1);


    }



}
