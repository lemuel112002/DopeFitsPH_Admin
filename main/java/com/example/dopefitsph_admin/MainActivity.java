package com.example.dopefitsph_admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.HideReturnsTransformationMethod;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signInEmail;
    private TextInputEditText signInPassword; // Change here to TextInputEditText
    private TextView signInTextViewBtn, signUpDirectText;
    private TextInputLayout passwordInputLayout;
    private boolean isPasswordVisible = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        signInEmail = findViewById(R.id.signInEmail);
        signInPassword = findViewById(R.id.signInPassword); // Make sure this ID is correct
        signInTextViewBtn = findViewById(R.id.signInTextViewBtn);
        signUpDirectText = findViewById(R.id.signUpDirectText);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);

        // Set up the visibility toggle listener
        passwordInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        signInTextViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signInEmail.getText().toString();
                String pass = signInPassword.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(MainActivity.this, "Sign in Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivity.this, AdminDashboardActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(MainActivity.this, "Sign in Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else {
                        signInPassword.setError("Please fill up password");
                    }
                } else if (email.isEmpty()) {
                    signInEmail.setError("Please fill up email");
                } else {
                    signInEmail.setError("Please enter a valid email.");
                }
            }
        });

        signUpDirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            signInPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordInputLayout.setEndIconDrawable(getDrawable(R.drawable.baseline_visibility_24));
        } else {
            // Show password
            signInPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordInputLayout.setEndIconDrawable(getDrawable(R.drawable.baseline_visibility_off_24));
        }
        isPasswordVisible = !isPasswordVisible;
        // Move the cursor to the end of the text after changing visibility
        signInPassword.setSelection(signInPassword.getText().length());
    }

}
