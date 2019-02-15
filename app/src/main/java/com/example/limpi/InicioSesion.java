package com.example.limpi;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InicioSesion extends AppCompatActivity implements View.OnClickListener {

    private Typeface fuente1, fuente2;

    TextView fontemail, fontpass, fontforgotcontra, fontrecuperar, textfire;
    EditText correo, password;
    Button login;
    String texto;

    FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        String fuente = "font/trebucbd.ttf";

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
        correo = findViewById(R.id.editemail);
        password = findViewById(R.id.editTextpassword);
        login = findViewById(R.id.btniniciar);

        login.setOnClickListener(this);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser(); //
                if (user != null){
                    Log.i("Sesion","Sesion iniciada" + user.getEmail());

                }
                else
                {
                    Log.i("Sesion", "Sesion cerrada");
                }
            }
        };


    }
    public void registrarusuario(View view) {
         Intent registro = new Intent(this, RegistroUsuario.class);
         startActivity(registro);
    }

    public void recuperapass(View view) {
        Intent recuperar = new Intent(this,recuperarContrasena.class);
        startActivity(recuperar);
    }

    private void iniciarsesion(String email, String pass){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btniniciar:
                    String email = correo.getText().toString();
                    String pass = password.getText().toString();
                    iniciarsesion(email, pass);break;
        }
    }
}
