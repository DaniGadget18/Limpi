package com.example.limpi;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
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

import java.util.Arrays;

public class InicioSesion extends AppCompatActivity implements View.OnClickListener {

    private Typeface fuente1, fuente2;

    TextView fontemail, fontpass, fontforgotcontra, fontrecuperar, textfire;
    EditText correo, password;
    Button login;
    String texto;
    FirebaseAuth firebaseAuthf;
    LoginButton loginButton;
    CallbackManager callbackManager;
    FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inicio_sesion);
        FacebookSdk.sdkInitialize(getApplicationContext());

        AppEventsLogger.activateApp(this);
        String fuente = "font/trebucbd.ttf";




        this.fuente1 = Typeface.createFromAsset(getAssets(),fuente);

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


        FirebaseApp.initializeApp(this);
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email"));
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

        firebaseAuthf = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser(); //
                if (user != null ){
                    Log.i("Sesion","Sesion iniciada" + user.getEmail());


                }
                if ( user!=null ){
                    MainFacebook();
                }

                else
                {
                    Log.i("Sesion", "Sesion cerrada");
                }
            }
        };

    }

    private void MainFacebook() {
        Intent intent = new Intent(InicioSesion.this, home.class);
        startActivity(intent);
        finish();
    }

    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuthf.signInWithCredential(credential)
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
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btniniciar:
                    String email = correo.getText().toString();
                    String pass = password.getText().toString();
                    Log.d("email",email);
                    Log.d("pass",pass);

                    firebaseAuthf.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful())
                            {
                                Toast.makeText(InicioSesion.this, "Tuvo un error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                    break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


        firebaseAuthf.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            firebaseAuthf.removeAuthStateListener(mAuthListener);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
