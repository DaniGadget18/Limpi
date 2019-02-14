package com.example.limpi;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InicioSesion extends AppCompatActivity {

    private Typeface fuente1, fuente2;

    TextView fontemail, fontpass, fontforgotcontra, fontrecuperar, textfire;
    EditText correo, password;
    String texto;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mysqlchild = database.getReference("texto");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        String fuente = "font/trebucbd.ttf";
        mysqlchild.setValue("Hellos,World!");

        this.fuente1 = Typeface.createFromAsset(getAssets(),fuente);

        textfire = findViewById(R.id.textofirebase);

        fontemail = findViewById(R.id.email);
        fontemail.setTypeface(fuente1);
        fontpass = findViewById(R.id.password);
        fontpass.setTypeface(fuente1);
        fontforgotcontra = findViewById(R.id.forgotcontra);
        fontforgotcontra.setTypeface(fuente1);
        fontrecuperar = findViewById(R.id.recuperar);
        fontrecuperar.setTypeface(fuente1);





    }
    public void registrarusuario(View view) {
         Intent registro = new Intent(this, RegistroUsuario.class);
         startActivity(registro);
    }

    public void recuperapass(View view) {
        Intent recuperar = new Intent(this,recuperarContrasena.class);
        startActivity(recuperar);
    }




}
