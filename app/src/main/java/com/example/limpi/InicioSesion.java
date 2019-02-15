package com.example.limpi;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;

public class InicioSesion extends AppCompatActivity {


    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener listener;
    LoginButton loginButton;
    CallbackManager   callbackManager;
    private Typeface fuente1, fuente2;

    TextView fontemail, fontpass, fontforgotcontra, fontrecuperar, textfire;
    EditText correo, password;
    String texto;
/*
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mysqlchild = database.getReference("texto");
*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        FirebaseApp.initializeApp(this);

        /*new CountDownTimer(1000,300) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(InicioSesion.this,home.class);
                startActivity(intent);
            }
        }.start();
*/
        correo = findViewById(R.id.editemail);




/*        String fuente = "font/trebucbd.ttf";
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

*/


        //Inicio con facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(InicioSesion.this, "Se canceló", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FacebookException error) {
                Toast.makeText(InicioSesion.this, "Hubo un error", Toast.LENGTH_SHORT).show();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if ( user!=null )
                {
                    MainFacebook();
                }

            }
        };

    }

    private void MainFacebook() {
        Intent intent = new Intent(InicioSesion.this,home.class);
        startActivity(intent);
    }


    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if ( !task.isSuccessful() )
                        {
                            Toast.makeText(getApplicationContext(),R.string.firebase_error_login,Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
    public void registrarusuario(View view) {
        Intent registro = new Intent(this, RegistroUsuario.class);
        startActivity(registro);
    }

    public void recuperapass(View view) {
        Intent recuperar = new Intent(this,recuperarContrasena.class);
        startActivity(recuperar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(listener);
    }
}
