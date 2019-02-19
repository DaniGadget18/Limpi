package com.example.limpi;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class home extends AppCompatActivity {

    TextView usuario;
    ImageView foto;
    ActionBarDrawerToggle img1;
    DrawerLayout DL;
    FirebaseAuth.AuthStateListener listener;
    private GoogleApiClient googleApiCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        usuario = findViewById(R.id.user);

        DL = findViewById(R.id.drawer);
        img1 = new ActionBarDrawerToggle(this, DL, R.string.open, R.string.close);
        DL.addDrawerListener(img1);
        img1.syncState();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ( img1.onOptionsItemSelected(item) ){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goBack() {
        Intent intent = new Intent(home.this,InicioSesion.class);
        startActivity(intent);
        finish();
    }
    public void logOut(){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goBack();
    }

}

