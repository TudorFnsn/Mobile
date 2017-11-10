package com.example.tudorfenesan.listaelemente;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class EditForm extends AppCompatActivity
{
    TextView textViewName, textViewEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_form);

        textViewName = (TextView) findViewById(R.id.name);
        textViewEmail = (TextView) findViewById(R.id.email);

        textViewName.setText(getIntent().getStringExtra("name"));
        textViewEmail.setText(getIntent().getStringExtra("email"));
    }
}
