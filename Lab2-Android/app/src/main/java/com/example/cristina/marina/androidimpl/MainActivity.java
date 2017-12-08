package com.example.cristina.marina.androidimpl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.antonio.koteles.androidimpl.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    // buttons
    private Button saveButton;
    private Button refreshButton;


    // inputs
    private EditText nameText;
    private EditText addressText;
    private EditText productNameText;
    private EditText descriptionText;

    // spinner
    private Spinner warranty;


    // list view
    public static ListView show;

    // the array that holds the requests
    public static ArrayList<Request> requestsArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize UI components
        nameText = findViewById(R.id.nameInput);
        addressText = findViewById(R.id.addressInput);
        productNameText = findViewById(R.id.productNameInput);
        descriptionText = findViewById(R.id.descriptionInput);
        warranty = findViewById(R.id.warrantyid);


        show = findViewById(R.id.myListView);
        saveButton = findViewById(R.id.addBtn);
        refreshButton = findViewById(R.id.refreshBtn);


        refreshButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    //call readFile
                    readFile(v, MainActivity.this);
                }
                catch (IOException | JSONException e)
                {
                    e.printStackTrace();
                }

                //if no data
                if (requestsArray.isEmpty())
                {
                    Toast.makeText(getBaseContext(), "List is empty", Toast.LENGTH_LONG).show();
                }
                //if data exists
                else
                {
                    //create new adapter
                    ArrayAdapter<Request> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, requestsArray);

                    //set the adapter
                    show.setAdapter(adapter);
                }

            }
        });


        // saves a new request in the list and shows it into the list view
        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //get the data from textBoxes
                String getName = nameText.getText().toString();
                String getAddress = addressText.getText().toString();
                String getProductName = productNameText.getText().toString();
                String getDescription = descriptionText.getText().toString();


                Request request = new Request(getName, getAddress, getProductName, getDescription);

                //check to not contain duplicates
                if (contains(requestsArray, request))
                {
                    Toast.makeText(getBaseContext(), "Item already in the list", Toast.LENGTH_LONG).show();
                }
                //if input is empty then show Toast
                else if (getName.trim().equals("") || getAddress.equals("") || getProductName.equals("") || getDescription.equals(""))
                {
                    Toast.makeText(getBaseContext(), "Some input is empty", Toast.LENGTH_LONG).show();
                }
                else
                {
                    //start saving
                    //add the request in array
                    requestsArray.add(request);

                    //create new adapter
                    ArrayAdapter<Request> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, requestsArray);

                    //set the new adapter
                    show.setAdapter(adapter);

                    //clear the textBoxes
                    ((EditText) findViewById(R.id.nameInput)).setText("");
                    ((EditText) findViewById(R.id.addressInput)).setText("");
                    ((EditText) findViewById(R.id.productNameInput)).setText("");
                    ((EditText) findViewById(R.id.descriptionInput)).setText("");

                    // rewrite the data file
                    try
                    {
                        createFile(v, MainActivity.this);
                    }
                    catch (IOException | JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });


        // event for clicking on a list view item
        // opens a new activity
        show.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // take the selected files
                Request selectedFromList = (Request) (show.getItemAtPosition(position));

                // create new intent and set parameters
                Intent intent = new Intent(view.getContext(), ListItemActivity.class);
                intent.putExtra("itemName", selectedFromList.getName());
                intent.putExtra("itemAddress", selectedFromList.getAddress());
                intent.putExtra("itemProductName", selectedFromList.getProductName());
                intent.putExtra("itemDescription", selectedFromList.getDescription());

                //create new array of products names
                ArrayList<String> productsNames = new ArrayList<>();
                for (int i = 0; i < requestsArray.size(); i++)
                {
                    //add every name of every product in the array
                    productsNames.add(requestsArray.get(i).getProductName());
                }

                //set the product names
                intent.putExtra("arrayProductsNames", productsNames);
                //set item position
                intent.putExtra("itemPosition", position);

                // start the new activity
                startActivityForResult(intent, 0);
            }
        });
    }

    // function for sending email based on the introduced requests
    public void sendMail(View view)
    {
        String reqString = "\n------------------------------------------------\n";
        for (int i = 0; i < requestsArray.size(); i++)
        {
            reqString += requestsArray.get(i).toString();
            reqString += "\n------------------------------------------------\n";
        }

        Intent intent, chooser;

        intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String[] to = {"k.cristina_16@yahoo.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Trims de Toni");
        intent.putExtra(Intent.EXTRA_TEXT, "Requests list: \n" + reqString);
        intent.setType("message/rfc822");
        chooser = Intent.createChooser(intent, "Send email");
        startActivity(chooser);
    }


    // checks if a request is contained by a list of requests comparing each field
    public static boolean contains(ArrayList<Request> list, Request r)
    {
        for (Request request : list)
        {
            if (request.getAddress().equals(r.getAddress()) && request.getDescription().equals(r.getDescription())
                    && request.getName().equals(r.getName()) && request.getProductName().equals(r.getProductName()))
            {
                return true;
            }
        }
        return false;
    }

    // persists data from the array of requests into a JSON file on disk
    public static void createFile(View v, Context context) throws IOException, JSONException
    {

        JSONArray data = new JSONArray();
        JSONObject request;

        for (int i = 0; i < requestsArray.size(); i++)
        {
            // create json object of type Request
            request = new JSONObject();

            // set all fields
            request.put("name", requestsArray.get(i).getName());
            request.put("address", requestsArray.get(i).getAddress());
            request.put("productName", requestsArray.get(i).getProductName());
            request.put("description", requestsArray.get(i).getDescription());

            //add in requests array -> then store in json
            data.put(request);
        }
        String text = data.toString();

        FileOutputStream fos = context.openFileOutput("requestsFile", MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();
    }

    // takes data from the json file
    public static void readFile(View v, Context context) throws IOException, JSONException
    {
        //open the file
        FileInputStream fileInputStream = context.openFileInput("requestsFile");

        //make file readable
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        StringBuilder builder = new StringBuilder();
        while (bufferedInputStream.available() != 0)
        {
            char c = (char) bufferedInputStream.read();
            //add every character into the builder
            builder.append(c);
        }

        bufferedInputStream.close();
        fileInputStream.close();

        //create JSONArray
        JSONArray data = new JSONArray(builder.toString());
        for (int i = 0; i < data.length(); i++)
        {
            //read data from json
            String name = data.getJSONObject(i).getString("name");
            String address = data.getJSONObject(i).getString("address");
            String productName = data.getJSONObject(i).getString("productName");
            String description = data.getJSONObject(i).getString("description");

            //create new request
            Request request = new Request(name, address, productName, description);

            //add current request to the array request
            requestsArray.add(request);
        }
    }
}
