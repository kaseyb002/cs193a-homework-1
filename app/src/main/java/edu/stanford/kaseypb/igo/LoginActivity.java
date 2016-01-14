package edu.stanford.kaseypb.igo;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Parse.initialize(this);
    }

    public String getUsernameFromForm() {
        EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        return usernameEditText.getText().toString();
    }

    public String getPasswordFromForm() {
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        return passwordEditText.getText().toString();
    }

    public void login(View view) {
        ParseUser.logInInBackground(getUsernameFromForm(), getPasswordFromForm(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();//http://stackoverflow.com/questions/18240779/correct-context-to-use-within-callbacks
                }
            }
        });
    }

    public void createAccount(View view) {//https://parse.com/docs/android/guide#users

        ParseUser user = new ParseUser();
        user.setUsername(getUsernameFromForm());
        user.setPassword(getPasswordFromForm());
        user.setEmail(getUsernameFromForm());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {//success
                    Toast.makeText(LoginActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();//http://stackoverflow.com/questions/18240779/correct-context-to-use-within-callbacks
                } else {//error
                    Toast.makeText(LoginActivity.this, "Account Creation Failed!", Toast.LENGTH_SHORT).show();//http://stackoverflow.com/questions/18240779/correct-context-to-use-within-callbacks
                }
            }
        });
    }
}
