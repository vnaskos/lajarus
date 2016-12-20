package com.bugbusters.lajarus;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bugbusters.lajarus.manager.TokenManager;
import com.bugbusters.lajarus.util.HttpRequestDispatcher;
import com.bugbusters.lajarus.util.Config;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private final static int USERNAME_FIELD = 0;
    private final static int PASSWORD_FIELD = 1;
    private final static int CONF_PASSWORD_FIELD = 2;
    private final static int EMAIL_FIELD = 3;

    public Button registerButton;
    public EditText[] fields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                registerButtonListener();
            }
        });

        fields = new EditText[4];
        fields[USERNAME_FIELD] = (EditText) findViewById(R.id.usernameField);
        fields[PASSWORD_FIELD] = (EditText) findViewById(R.id.passwordField);
        fields[CONF_PASSWORD_FIELD] = (EditText)findViewById(R.id.confirmPasswordField);
        fields[EMAIL_FIELD] = (EditText) findViewById(R.id.emailField);
    }

    public void showFillAllAreasErrorMessage() {
        new AlertDialog.Builder(this)
                .setTitle("Fill")
                .setMessage("Fill All Areas")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                }).create().show();
    }

    public void showNotSamePasswordErrorMessage() {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Not same passwords")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                }).create().show();
    }

    public void registerButtonListener(){
        //Iterate and check every field if empty
        for(EditText field : fields) {
            String fieldValue = field.getText().toString().trim();
            if(fieldValue.isEmpty()) {
                showFillAllAreasErrorMessage();
                return;
            }
        }

        //Check if password and confirm password are equal
        String password = fields[PASSWORD_FIELD].getText().toString().trim();
        String confirmPassword = fields[CONF_PASSWORD_FIELD].getText().toString().trim();
        if(!password.equals(confirmPassword)){
            showNotSamePasswordErrorMessage();
            return;
        }

        //Perform the actual register procedure
        try {
            performRegister();
        } catch (JSONException ex) {
            Log.e("Register", ex.toString());
        }
    }


    public void performRegister() throws JSONException {
        final JSONObject body = new JSONObject();

        final String username = fields[USERNAME_FIELD].getText().toString().trim();
        final String password = fields[PASSWORD_FIELD].getText().toString().trim();

        body.put("username", username);
        body.put("password", password);
        body.put("email", fields[EMAIL_FIELD].getText().toString().trim());

        final JSONObject playerBody = new JSONObject();
        playerBody.put("name", fields[USERNAME_FIELD].getText().toString().trim());

        Log.d("Register", body.toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                String registerUrl = Config.getHttpURL().concat("auth/register");

                final JSONObject response = HttpRequestDispatcher.performPOST(registerUrl, body);
                String token = "";
                try {
                    token = TokenManager.getInstance().createToken(username, password);
                } catch (Exception e) {

                }

                String playerUrl = Config.getHttpURL().concat("player/create");
                final JSONObject response2 = HttpRequestDispatcher.performPOST(playerUrl, playerBody, token);

                //Display result
                if (response2 != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        }).start();
    }

}
