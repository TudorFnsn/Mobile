package com.example.tudorfenesan.lab1r.recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.tudorfenesan.lab1r.R;
import com.example.tudorfenesan.lab1r.EditForm;
import com.example.tudorfenesan.lab1r.adapter.MyAdapter;

/**
 * Created by tudorfenesan on 20/12/2017.
 */

public class RecycleViewActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter<MyAdapter.ViewHolder> mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    TextView textViewName;
    TextView textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);

        recyclerView = findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final List<Map.Entry<String, String>> input = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            input.add(new AbstractMap.SimpleEntry<>("Client: " + i, "Client" + i + "@gmail.com"));
        }


        // define an adapter*
        mAdapter = new MyAdapter(input);
        recyclerView.setAdapter(mAdapter);

    }

    /**
     * @param view -
     */
    public void startNewActivity(View view)
    {
        textViewName = view.findViewById(R.id.nameListView);
        textViewEmail = view.findViewById(R.id.emailListView);


        Intent intent = new Intent(this, EditForm.class);

        intent.putExtra("name", textViewName.getText().toString());
        intent.putExtra("email", textViewEmail.getText().toString());


        startActivityForResult(intent, 0);
    }

    /**
     * @param requestCode -
     * @param resultCode  -
     * @param data        -
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 0)
        {
            textViewName = findViewById(R.id.nameListView);
            textViewEmail = findViewById(R.id.emailListView);

            textViewName.setText(data.getStringExtra("name"));
            textViewEmail.setText(data.getStringExtra("email"));
        }
    }

}
