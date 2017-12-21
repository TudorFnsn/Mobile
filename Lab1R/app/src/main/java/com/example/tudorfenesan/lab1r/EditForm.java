package com.example.tudorfenesan.lab1r;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by tudorfenesan on 20/12/2017.
 */

public class EditForm extends AppCompatActivity
{
    private TextView textViewName;
    private TextView textViewEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_form);

        textViewName = findViewById(R.id.nameListView);
        textViewEmail = findViewById(R.id.emailListView);

        textViewName.setText(getIntent().getStringExtra("name"));
        textViewEmail.setText(getIntent().getStringExtra("email"));
    }

    public void save(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("name", textViewName.getText().toString());
        intent.putExtra("email", textViewEmail.getText().toString());

        setResult(RESULT_OK, intent);
        finish();
    }
}
