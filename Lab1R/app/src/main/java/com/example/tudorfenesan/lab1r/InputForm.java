package com.example.tudorfenesan.lab1r;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by tudorfenesan on 20/12/2017.
 */

public class InputForm extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_form);

    }

    public void sendFeedBack(View view)
    {
        final EditText nameField = findViewById(R.id.EditTextName);
        String name = nameField.getText().toString();

        final EditText emailField = findViewById(R.id.EditTextEmail);
        String email = emailField.getText().toString();

        final EditText feedbackField = findViewById(R.id.EditTextFeedbackBody);
        String feedback = feedbackField.getText().toString();

        final Spinner feedbackSpinner = findViewById(R.id.SpinnerFeedbackType);
        String feedbackType = feedbackSpinner.getSelectedItem().toString();

        final CheckBox responseCheckbox = findViewById(R.id.CheckBoxResponse);
        boolean bRequiresResponse = responseCheckbox.isChecked();

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "To: " + name + "\n" + feedback + "\n" + feedbackType + "\n" + "Requires response: " + bRequiresResponse);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }


}
