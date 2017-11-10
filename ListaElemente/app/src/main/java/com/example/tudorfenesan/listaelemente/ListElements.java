package com.example.tudorfenesan.listaelemente;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;



public class ListElements extends AppCompatActivity
{
    TextView textViewName, textViewEmail;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_elements);
    }

    public void editItems1(View view)
    {
        textViewName = (TextView) findViewById(R.id.name1);
        textViewEmail = (TextView) findViewById(R.id.email1);

        Intent intent = new Intent(this, EditForm.class);

        intent.putExtra("name", textViewName.getText().toString());
        intent.putExtra("email", textViewEmail.getText().toString());

        startActivity(intent);
    }

    public void editItems2(View view)
    {
        textViewName = (TextView) findViewById(R.id.name2);
        textViewEmail = (TextView) findViewById(R.id.email2);

        Intent intent = new Intent(this, EditForm.class);

        intent.putExtra("name", textViewName.getText().toString());
        intent.putExtra("email", textViewEmail.getText().toString());

        startActivity(intent);
    }
}
