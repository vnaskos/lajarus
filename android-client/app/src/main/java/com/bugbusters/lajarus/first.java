package com.bugbusters.lajarus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bugbusters.lajarus.util.HttpRequestDispatcher;

import org.json.JSONException;
import org.json.JSONObject;

public class first extends AppCompatActivity {

   public Button but1;

    public void init(){
        but1 = (Button)findViewById(R.id.button6);
        but1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent toy = new Intent(first.this,second.class);
                startActivity(toy);
            }
        });
    }

    public void init2(){
        but1 = (Button)findViewById(R.id.button7);
        but1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent toy2 = new Intent(first.this,third.class);
                startActivity(toy2);
            }
        });
    }

    public void init3(){
        but1 = (Button)findViewById(R.id.button8);
        but1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent toy3 = new Intent(first.this,fourth.class);
                startActivity(toy3);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        init();
        init2();
        init3();

        //Perform login
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    performLogin();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void performLogin() throws JSONException {
        HttpRequestDispatcher requestDispatcher = new HttpRequestDispatcher();

        JSONObject body = new JSONObject();
        body.put("username", "admin");
        body.put("password", "admin");
        String url = "http://46.103.23.5:8080/auth/login";
        JSONObject response = requestDispatcher.performPOST(url, body);

        //Display result
        if (response != null) {
            final String token = response.getString("token");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), token, Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}
