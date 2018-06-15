package com.example.acer.mynoticeboardapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Reg  extends AppCompatActivity implements View.OnClickListener {
    private EditText email_id;
    private Button register;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regactivity);
        email_id=(EditText)findViewById(R.id.edit_email);
        register=findViewById(R.id.student);
        email_id.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}
