package com.example.limpi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroUsuario extends AppCompatActivity {

    private Typeface fuente;
    private TextView fontbienvenido, fontnombre, fontapellidos, fontcorreo, fontpass, fontconfpass, fontedad, fontsexo, fontdire;
    private Spinner edad;
    String[] edades = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener stateListener;
    EditText correo, contrasena, verificar;
    Button registro;

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


        FirebaseAuth.getInstance().signOut();
        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.editemail);
        contrasena = findViewById(R.id.editpass);
        verificar = findViewById(R.id.editconfpass);


        edad = findViewById(R.id.edades);




    }


    public void registrar(View view) {
        final String
                Correo = String.valueOf(correo.getText()),
                Contra = String.valueOf(contrasena.getText());
        if (!Correo.isEmpty() && !Contra.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(Correo,Contra).addOnCompleteListener(RegistroUsuario.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.sendEmailVerification();
                        Intent regi = new Intent(RegistroUsuario.this,home.class);
                        startActivity(regi);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegistroUsuario.this, "" + e, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
