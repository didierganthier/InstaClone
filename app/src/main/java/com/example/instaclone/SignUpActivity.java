package com.example.instaclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    EditText usernameSignup;
    EditText emailSignup;
    EditText passwordSignup;
    Button btnSignup;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        
        usernameSignup = findViewById(R.id.usernameSignUp);
        emailSignup = findViewById(R.id.emailSignup);
        passwordSignup = findViewById(R.id.passwordSignup);
        btnSignup = findViewById(R.id.btnSignUp);
        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logIntent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(logIntent);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameSignup.getText().toString().isEmpty() || passwordSignup.getText().toString().isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Username and password are required", Toast.LENGTH_SHORT).show();
                }

                String username = usernameSignup.getText().toString();
                String email = emailSignup.getText().toString();
                String password = passwordSignup.getText().toString();

                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);
                
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            usernameSignup.setText("");
                            emailSignup.setText("");
                            passwordSignup.setText("");
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(SignUpActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            ParseErrorHandler.handleParseError(e);
                        }
                    }
                });
            }
        });
    }
}
