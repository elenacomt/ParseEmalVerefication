package com.example.parseemalverefication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSingUpLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log in");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtPassword);
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSingUpLoginActivity =  findViewById(R.id.btnSingUpLoginActivity);

        btnLoginActivity.setOnClickListener(this);
       // edtLoginPassword.setOnClickListener(this);
        btnSingUpLoginActivity.setOnClickListener(this);

        if(ParseUser.getCurrentUser() !=null) {

            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())  {

           case  R.id.btnLoginActivity:

               ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                       edtLoginPassword.getText().toString(),
                       new LogInCallback() {
                           @Override
                           public void done(ParseUser user, ParseException e) {
                               if (user !=null && e == null) {
                                   FancyToast.makeText(LoginActivity.this,
                                           user.getUsername() + "is Login in successfully",
                                           Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                                           true).show();
                                   transitionToTwitterUsers();
                               }
                           }
                       });
            break;

            case R.id.btnSingUpLoginActivity:

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);


                break;
        }

    }

    private void transitionToTwitterUsers() {
        Intent intent = new Intent(LoginActivity.this, TwitterUsers.class);
        startActivity(intent);
    }
}
