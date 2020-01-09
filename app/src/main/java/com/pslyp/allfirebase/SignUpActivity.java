package com.pslyp.allfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText mEmail, mPass;
    private Button mSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initInstance();
    }

    private void initInstance() {
        mAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.email_edit_text);
        mPass = findViewById(R.id.password_edit_text);
        mSignUp = findViewById(R.id.sign_up_button);
        mSignUp.setOnClickListener(signUp);
    }

    View.OnClickListener signUp = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String email = mEmail.getText().toString();
            String pass = mPass.getText().toString();

            if(!email.isEmpty() && !pass.isEmpty()) {
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Sign up success!", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(SignUpActivity.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    };
}
