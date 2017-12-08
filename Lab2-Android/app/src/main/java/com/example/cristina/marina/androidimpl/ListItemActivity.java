package com.example.cristina.marina.androidimpl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.antonio.koteles.androidimpl.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.cristina.marina.androidimpl.MainActivity.contains;
import static com.example.cristina.marina.androidimpl.MainActivity.createFile;
import static com.example.cristina.marina.androidimpl.MainActivity.requestsArray;
import static com.example.cristina.marina.androidimpl.MainActivity.show;

public class ListItemActivity extends AppCompatActivity
{
    // editable texts
    private EditText textReceivedName;
    private EditText textReceivedAddress;
    private EditText textReceivedProductName;
    private EditText textReceivedDescription;

    // button for saving changes
    private Button saveChanges;

    // button for deleteing a request
    private Button deleteRequest;

    // button for opening chart page
    private Button openChart;

    public ArrayList<String> productNamesArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_activity);

        onClickChartButton();

        //get the saveButton
        saveChanges = findViewById(R.id.saveChangesBtn);
        //get the deleteButton
        deleteRequest = findViewById(R.id.deleteBtn);

        //get the data from the intent
        String nameGet = getIntent().getStringExtra("itemName");
        String addressGet = getIntent().getStringExtra("itemAddress");
        String productNameGet = getIntent().getStringExtra("itemProductName");
        String descriptionGet = getIntent().getStringExtra("itemDescription");

        //get the item position
        final int position = getIntent().getIntExtra("itemPosition", -1);


        // get the array of names to search for position element
        productNamesArray = getIntent().getStringArrayListExtra("arrayProductsNames");


        // editable
        textReceivedName = findViewById(R.id.receivedTextName);
        textReceivedName.setText(nameGet);

        textReceivedAddress = findViewById(R.id.receivedTextAddress);
        textReceivedAddress.setText(addressGet);

        textReceivedProductName = findViewById(R.id.receivedTextProductName);
        textReceivedProductName.setText(productNameGet);

        textReceivedDescription = findViewById(R.id.receivedTextDescription);
        textReceivedDescription.setText(descriptionGet);

        // on click saves the changes
        saveChanges.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //take the new input
                String getNewName = textReceivedName.getText().toString();
                String getNewAddress = textReceivedAddress.getText().toString();
                String getNewProductName = textReceivedProductName.getText().toString();
                String getNewDescription = textReceivedDescription.getText().toString();

                //create new request
                Request request = new Request(getNewName, getNewAddress, getNewProductName, getNewDescription);

                //if duplicate -> exit
                if (contains(requestsArray, request))
                {
                    finish();
                }
                //if empty
                else if (getNewName.trim().equals("") || getNewAddress.equals("") || getNewProductName.equals("") || getNewDescription.equals(""))
                {
                    Toast.makeText(getBaseContext(), "Some input is empty", Toast.LENGTH_LONG).show();
                }
                else
                {
                    //rewrite old record
                    requestsArray.set(position, request);

                    //show message
                    Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_LONG).show();

                    //
                    show.invalidateViews();

                    // rewrite the file
                    try
                    {
                        createFile(v, ListItemActivity.this);
                    }
                    catch (IOException | JSONException e)
                    {
                        e.printStackTrace();
                    }

                    finish();
                }
            }
        });


        // on click delete record
        deleteRequest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemActivity.this);
                builder.setMessage("Are you sure you want to delete this item.");
                builder.setCancelable(true);


                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                //get record details
                                String getNewName = textReceivedName.getText().toString();
                                String getNewAddress = textReceivedAddress.getText().toString();
                                String getNewProductName = textReceivedProductName.getText().toString();
                                String getNewDescription = textReceivedDescription.getText().toString();

                                //create new request
                                Request request = new Request(getNewName, getNewAddress, getNewProductName, getNewDescription);

                                //if request exists
                                if (contains(requestsArray, request))
                                {
                                    requestsArray.remove(position);

                                    Toast.makeText(getBaseContext(), "Request deleted", Toast.LENGTH_LONG).show();

                                    show.invalidateViews();

                                    // rewrite the file
                                    try
                                    {
                                        createFile(v, ListItemActivity.this);
                                    }
                                    catch (IOException | JSONException e)
                                    {
                                        e.printStackTrace();
                                    }

                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(getBaseContext(), "There is no matching request", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {

                    }
                });

                AlertDialog alert1 = builder.create();
                alert1.show();
            }
        });
    }

    public void onClickChartButton()
    {
        //take the chart button
        openChart = findViewById(R.id.chartBtn);

        //create click event
        openChart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //create new intent
                Intent intent = new Intent(view.getContext(), ChartActivity.class);
                intent.putExtra("arrayProductsNamesFromListItemActivity", productNamesArray);
                //start new activity
                startActivity(intent);
            }
        });
    }
}
