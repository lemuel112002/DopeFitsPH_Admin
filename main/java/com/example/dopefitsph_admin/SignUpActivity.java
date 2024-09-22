package com.example.dopefitsph_admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextInputEditText signupEmail, signupPassword;
    private TextView signUpTxtViewBtn, signInDirectText;
    private TextInputLayout passwordInputLayout;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.signupEmail);
        signupPassword = findViewById(R.id.signupPassword);
        signUpTxtViewBtn = findViewById(R.id.signUpTxtViewBtn);
        signInDirectText = findViewById(R.id.signInDirectText);
        passwordInputLayout = findViewById(R.id.signupPasswordInputLayout);

        // Set up the visibility toggle listener for password field only
        passwordInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        signUpTxtViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();

                if (user.isEmpty()) {
                    signupEmail.setError("Please fill the email");
                }
                if (pass.isEmpty()) {
                    signupPassword.setError("Please fill the password");
                } else {
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Sign up Successful.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, "Sign up Failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        signInDirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            signupPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordInputLayout.setEndIconDrawable(getDrawable(R.drawable.baseline_visibility_24));
        } else {
            // Show password
            signupPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordInputLayout.setEndIconDrawable(getDrawable(R.drawable.baseline_visibility_off_24));
        }
        isPasswordVisible = !isPasswordVisible;
        // Move the cursor to the end of the text after changing visibility
        signupPassword.setSelection(signupPassword.getText().length());
    }

    public void goToSignInPage(View view) {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
