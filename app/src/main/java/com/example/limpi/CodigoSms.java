package com.example.limpi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class CodigoSms extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText etxtphone, etxtcodephone;
    Button solicode, vericode;
    String mVerificationId;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener (mAuthListener);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_sms);

        etxtphone = findViewById(R.id.editphone);
        etxtcodephone = findViewById(R.id.editcode);
        solicode = findViewById(R.id.btnsolicitarcodigo);
        vericode = findViewById(R.id.btncode);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){
                    Toast.makeText(CodigoSms.this, "I need login" + firebaseAuth.getCurrentUser().getProviderId(), Toast.LENGTH_SHORT).show();
                    Intent iniciosesion = new Intent(CodigoSms.this, InicioSesion.class);
                    startActivity(iniciosesion);
                    finish();
                }
            }
        };
    }

    public void requestCode (View view){
        String PhoneNumber = etxtphone.getText().toString();
        Log.d("telefono", PhoneNumber);
        if (TextUtils.isEmpty(PhoneNumber)){
            return;
        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                PhoneNumber, 5, TimeUnit.SECONDS, CodigoSms.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        signInWhitCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(CodigoSms.this, "onVerificationFailed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(String verificationid, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verificationid, forceResendingToken);
                        mVerificationId = verificationid;
                    }

                    @Override
                    public void onCodeAutoRetrievalTimeOut(String verificationid) {
                        super.onCodeAutoRetrievalTimeOut(verificationid);
                        Toast.makeText(CodigoSms.this, "onCodeAutoRetrievalTimeOut :" + verificationid, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void signInWhitCredential(PhoneAuthCredential phoneAuthCredential){
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CodigoSms.this, "Successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CodigoSms.this, "Credencial fail" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signIn(View v){
        String code = etxtcodephone.getText().toString();
        if (TextUtils.isEmpty(code)){
            return;
        }
        else {
            signInWhitCredential(PhoneAuthProvider.getCredential(mVerificationId,code));
        }
    }




}
