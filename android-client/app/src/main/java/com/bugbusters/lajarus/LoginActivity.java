package com.bugbusters.lajarus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bugbusters.lajarus.entity.Session;
import com.bugbusters.lajarus.manager.SessionManager;
import com.bugbusters.lajarus.manager.TokenManager;

public class LoginActivity extends AppCompatActivity {

    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tokenManager = TokenManager.getInstance();

        attachLoginBtnListener();
        attachRegisterBtnListener();
    }

    public void attachLoginBtnListener() {
        Button loginBtn = (Button) findViewById(R.id.loginButton);
        final EditText usernameField = (EditText) findViewById(R.id.usernameField);
        final EditText passwordField = (EditText) findViewById(R.id.passwordField);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                performLogin(
                        usernameField.getText().toString(),
                        passwordField.getText().toString());
            }
        });
    }

    public void attachRegisterBtnListener(){
        Button registerBtn = (Button)findViewById(R.id.registerButton);
        registerBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent toy = new Intent(LoginActivity.this,register.class);
                startActivity(toy);
            }
        });
    }

    private void performLogin(final String username, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
            try {
                String token = tokenManager.createToken(username, password);

                if(token == null || token.isEmpty()) {
                    throw new Exception("Invalid token");
                }

                handleSuccessfulLogin();
            } catch (Exception e) {
                e.printStackTrace();
                handleFailedLogin();
            }
            }
        }).start();
    }

    private void handleSuccessfulLogin() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),
                        "Success!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        SessionManager sessionManager = SessionManager.getInstance();
        Session s = sessionManager.createSession(tokenManager.getLastProducedToken());
        sessionManager.setActiveSession(s);

        Intent mapIntent = new Intent(LoginActivity.this, MapActivity.class);
        startActivity(mapIntent);
    }

    private void handleFailedLogin() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),
                        "Fail! Please check your username and password",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
