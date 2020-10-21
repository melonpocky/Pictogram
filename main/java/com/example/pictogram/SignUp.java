package com.example.pictogram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity
{
    public static final String TAG = "SignUpActivity";
    private EditText etSignUser;
    private EditText etSignPassword;
    private EditText etSignEmail;
    private EditText etPhone;
    private Button btnSignSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etSignUser = findViewById(R.id.etSignUser);
        etSignPassword = findViewById(R.id.etSignPassword);
        etSignEmail = findViewById(R.id.etSignEmail);
        etPhone = findViewById(R.id.etPhone);
        btnSignSignUp = findViewById(R.id.btnSignSignUp);

        btnSignSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Create the ParseUser
                ParseUser user = new ParseUser();
                // Set core properties
                user.setUsername(etSignUser.getText().toString());
                user.setPassword(etSignPassword.getText().toString());
                user.setEmail(etSignEmail.getText().toString());
                // Set custom properties
                user.put("phone", etPhone.getText().toString());
                // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Toast.makeText(SignUp.this, "Created New User!", Toast.LENGTH_SHORT).show();
                            goLoginActivity();
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                        }
                    }
                });
            }
        });
    }

    private void goLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

}

