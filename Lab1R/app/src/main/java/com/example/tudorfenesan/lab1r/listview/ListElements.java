package com.example.tudorfenesan.lab1r.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.tudorfenesan.lab1r.R;
import com.example.tudorfenesan.lab1r.EditForm;

/**
 * Created by tudorfenesan on 20/12/2017.
 */

public class ListElements extends AppCompatActivity
{
    private TextView textViewName, textViewEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_elements);
    }

    public void editItems1(View view)
    {
        textViewName = findViewById(R.id.name1);
        textViewEmail = findViewById(R.id.email1);

        Intent intent = new Intent(this, EditForm.class);

        intent.putExtra("name", textViewName.getText().toString());
        intent.putExtra("email", textViewEmail.getText().toString());

        startActivityForResult(intent, 1996);
    }

    public void editItems2(View view)
    {
        textViewName = findViewById(R.id.name2);
        textViewEmail = findViewById(R.id.email2);

        Intent intent = new Intent(this, EditForm.class);

        intent.putExtra("name", textViewName.getText().toString());
        intent.putExtra("email", textViewEmail.getText().toString());

        startActivityForResult(intent, 1971);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK && requestCode == 1996)
        {
            textViewName = findViewById(R.id.name1);
            textViewEmail = findViewById(R.id.email1);

            textViewName.setText(data.getStringExtra("name"));
            textViewEmail.setText(data.getStringExtra("email"));
        }

        else if (resultCode == RESULT_OK && requestCode == 1971)
        {
            textViewName = findViewById(R.id.name2);
            textViewEmail = findViewById(R.id.email2);

            textViewName.setText(data.getStringExtra("name"));
            textViewEmail.setText(data.getStringExtra("email"));
        }
    }
}
