package com.pslyp.allfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText mEmail, mPass;
    private Button mSignIn, mSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initInstance();
    }

    private void initInstance() {
        mAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.email_edit_text);
        mPass = findViewById(R.id.password_edit_text);
        mSignIn = findViewById(R.id.sign_in_button);
        mSignIn.setOnClickListener(this);
        mSignUp = findViewById(R.id.sign_up_button);
        mSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                String email = mEmail.getText().toString();
                String pass = mPass.getText().toString();

                if(!email.isEmpty() && !pass.isEmpty())
                    signIn(email, pass);
                break;
            case R.id.sign_up_button:
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                break;
        }
    }

    private void signIn(final String email, final String pass) {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(SignInActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
