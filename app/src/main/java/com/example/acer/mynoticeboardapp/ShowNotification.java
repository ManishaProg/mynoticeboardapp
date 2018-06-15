package com.example.acer.mynoticeboardapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowNotification extends AppCompatActivity implements View.OnClickListener {


    private TextView from,title,notice,heading;
    private Button addnotice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shownotification);
        from=findViewById(R.id.from);
        title=findViewById(R.id.title);
        notice=findViewById(R.id.notice);
        addnotice=findViewById(R.id.addnotice);
        heading=findViewById(R.id.head);
        Intent i=getIntent();
        // from.setText( i.getStringExtra("getFrom"));
        heading.setText(i.getStringExtra("heading ") + " Notice Board");
        String text=i.getStringExtra("title").toUpperCase();
        title.setText(text);

        notice.setText( i.getStringExtra("message"));


        addnotice.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        Intent i=new Intent(getApplicationContext(),ActivitySendPushNotification.class);
        startActivity(i);

      /*  // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.send_push_notification, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        *//*final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);*//*

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                *//*result.setText(userInput.getText());*//*
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();*/

    }
}

