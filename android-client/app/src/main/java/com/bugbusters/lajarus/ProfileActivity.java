package com.bugbusters.lajarus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bugbusters.lajarus.manager.SessionManager;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button backBtn = (Button) findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapActivity = new Intent(ProfileActivity.this, MapActivity.class);
                startActivity(mapActivity);
            }
        });
        Button logoutBtn = (Button) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapActivity = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(mapActivity);
            }
        });

        SessionManager sessionManager = SessionManager.getInstance();
        TextView playerName = (TextView) findViewById(R.id.playerNameTextView);
        playerName.setText(sessionManager.getActiveSession().getActivePlayer().getName());
    }


}
