package com.bugbusters.lajarus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class items extends AppCompatActivity {

    public Button but1;

    public void init(){
        but1 = (Button)findViewById(R.id.button2);
        but1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent toy = new Intent(items.this,MapActivity.class);
                startActivity(toy);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        init();
    }
}
