package com.example.tudorfenesan.form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.btnOK)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //String info = ((EditText)findViewById(R.id.txtName)).getText().toString()+";"+((EditText)findViewById(R.id.txtTo)).getText().toString()+";"+((EditText)findViewById(R.id.txtPhone)).getText().toString()+";"+((EditText)findViewById(R.id.txtMessage)).getText().toString();
                //String name = ((EditText)findViewById(R.id.txtName)).getText().toString();
                String to = ((EditText)findViewById(R.id.txtTo)).getText().toString();
              //  String phone = ((EditText)findViewById(R.id.txtPhone)).getText().toString();
             //   String mess = ((EditText)findViewById(R.id.txtMessage)).getText().toString();
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
                mail.putExtra(Intent.EXTRA_SUBJECT,"This is what you completed");
               // mail.putExtra(Intent.EXTRA_TEXT, name);
               // mail.putExtra(Intent.EXTRA_TEXT,to);
              //  mail.putExtra(Intent.EXTRA_TEXT,phone);
               // mail.putExtra(Intent.EXTRA_TEXT, info);
                mail.setType("text/html");
                mail.putExtra(Intent.EXTRA_TEXT,
                        Html.fromHtml(new StringBuilder()
                        .append("<html>")
                        .append(((EditText)findViewById(R.id.txtName)).getText().toString())
                        .append("<br/>")
                        .append(((EditText)findViewById(R.id.txtTo)).getText().toString())
                        .append("<br/>")
                        .append(((EditText)findViewById(R.id.txtPhone)).getText().toString())
                        .append("<br/>")
                        .append(((EditText)findViewById(R.id.txtMessage)).getText().toString())
                        .append("<br/></html>").toString()));
               // mail.setType("message/rfc822");
                startActivity(Intent.createChooser(mail, "Send email via:"));
            }
        });

    }
}
