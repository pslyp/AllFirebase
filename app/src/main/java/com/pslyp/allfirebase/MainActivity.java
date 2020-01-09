package com.pslyp.allfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private TextView mUid;
    private Button mSignOut;

    private String name, email, uid;
    private Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        initInstance();
    }

    private void initInstance() {
        mUid = findViewById(R.id.uid_text_view);

        mSignOut = findViewById(R.id.sign_out_button);
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();

                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null) {
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finish();
        } else {
            name = currentUser.getDisplayName();
            email = currentUser.getEmail();
            photoUri = currentUser.getPhotoUrl();
            uid = currentUser.getUid();

            mUid.setText(uid);
        }
    }
}
