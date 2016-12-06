package com.bugbusters.lajarus;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class first extends AppCompatActivity {

    TextView t;

   public Button but1;

    public void init(){
        but1 = (Button)findViewById(R.id.button6);
        but1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent toy = new Intent(first.this,LoginActivity.class);
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

        t= (TextView) findViewById(R.id.button6);
        Typeface myCustomFont= Typeface.createFromAsset(getAssets(), "fonts/trajanus.ttf");
        t.setTypeface(myCustomFont);

        t= (TextView) findViewById(R.id.button7);
        Typeface myCustomFont1= Typeface.createFromAsset(getAssets(), "fonts/trajanus.ttf");
        t.setTypeface(myCustomFont1);

        t= (TextView) findViewById(R.id.button8);
        Typeface myCustomFont2= Typeface.createFromAsset(getAssets(), "fonts/trajanus.ttf");
        t.setTypeface(myCustomFont2);
    }


}
