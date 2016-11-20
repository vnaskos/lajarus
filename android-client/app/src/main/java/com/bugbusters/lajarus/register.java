package com.bugbusters.lajarus;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.bugbusters.lajarus.R.id.textView2;

public class register extends AppCompatActivity {

    public Button but1;
    public TextView textView1,textView2,textView3,textView4;
    public String text1,text2,text3,text4;

    public void dialog1() {
        new AlertDialog.Builder(this)
                .setTitle("Fill")
                .setMessage("Fill All Areas")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //rerereere
                    }

                }).create().show();
    }

    public void dialog2() {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Not same passwords")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //rerereere
                    }

                }).create().show();
    }

    public void action_button(){
        textView1 = (TextView)findViewById(R.id.editText8);
        text1=textView1.getText().toString();
        textView2 = (TextView)findViewById(R.id.editText9);
        text2=textView2.getText().toString();
        textView3 = (TextView)findViewById(R.id.editText10);
        text3=textView3.getText().toString();
        textView4 = (TextView)findViewById(R.id.editText11);
        text4=textView4.getText().toString();
        if(text1.isEmpty() || text2.isEmpty() || text3.isEmpty() || text4.isEmpty()){
            dialog1();
        }
        if(!text2.equals(text3)){
            dialog2();
        }
    }



    public void init_register(){
        but1 = (Button)findViewById(R.id.button);
        but1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                action_button();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init_register();
    }
}