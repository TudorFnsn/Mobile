package com.example.cristina.marina.androidimpl;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.antonio.koteles.androidimpl.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartActivity extends AppCompatActivity
{
    private static String TAG = "Chartactivity";

    public ArrayList<String> productNamesArray = new ArrayList<>();

    private List<Integer> yDataList = new ArrayList<>(Arrays.asList(0, 0, 0));
    private List<String> xDataList = new ArrayList<>(Arrays.asList("", "", ""));

    private PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        // take array of product names
        productNamesArray = getIntent().getStringArrayListExtra("arrayProductsNamesFromListItemActivity");

        HashMap<String, Integer> dictionary = new HashMap<>();

        for (int i = 0; i < productNamesArray.size(); i++)
        {
            if (!dictionary.containsKey(productNamesArray.get(i)))
            {
                //set value 0 for every name name in array
                dictionary.put(productNamesArray.get(i), 0);
            }
        }

        // increment values
        for (int i = 0; i < productNamesArray.size(); i++)
        {
            dictionary.put(productNamesArray.get(i), dictionary.get(productNamesArray.get(i)) + 1);
        }

        for (String key : dictionary.keySet())
        {
            dictionary.put(key, dictionary.get(key));
        }

        //if dictionary not empty
        if (dictionary.size() > 0)
        {
            Map.Entry<String, Integer> maxEntry = null;

            for (Map.Entry<String, Integer> entry : dictionary.entrySet())
            {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                {
                    //if value > maxEntry
                    maxEntry = entry;
                }
            }

            xDataList.set(0, maxEntry.getKey());
            yDataList.set(0, maxEntry.getValue());

            //delete maxEntry from dictionary
            dictionary.remove(maxEntry.getKey());
        }


        if (dictionary.size() > 0)
        {
            Map.Entry<String, Integer> maxEntry = null;

            for (Map.Entry<String, Integer> entry : dictionary.entrySet())
            {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                {
                    maxEntry = entry;
                }
            }
            xDataList.set(1, maxEntry.getKey());
            yDataList.set(1, maxEntry.getValue());

            //delete maxEntry from dictionary
            dictionary.remove(maxEntry.getKey());
        }


        if (dictionary.size() > 0)
        {
            Map.Entry<String, Integer> maxEntry = null;

            for (Map.Entry<String, Integer> entry : dictionary.entrySet())
            {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                {
                    maxEntry = entry;
                }
            }
            xDataList.set(2, maxEntry.getKey());
            yDataList.set(2, maxEntry.getValue());

            //remove maxEntry
            dictionary.remove(maxEntry.getKey());
        }

        //get the chart from layout
        pieChart = findViewById(R.id.idPieCHart);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Rares' chart");
        pieChart.setCenterTextSize(10);

        Description description = new Description();
        description.setText("Each slice is a product type. Click to show product name");
        description.setTextColor(Color.RED);
        description.setTextSize(10);
        pieChart.setDescription(description);
        //pieChart.setDrawEntryLabels(true);

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight)
            {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + entry.toString());
                Log.d(TAG, "onValueSelected: " + highlight.toString());

                //tke the position
                int position = entry.toString().indexOf("y: ");
                String nrProducts = entry.toString().substring(position + 3);

                for (int i = 0; i < yDataList.size(); i++)
                {
                    //if product found
                    if (yDataList.get(i) == Float.parseFloat(nrProducts))
                    {
                        //save the position
                        position = i;
                        break;
                    }
                    //Log.i(TAG, yDataList.get(i) + "::: " + xDataList.get(i) + " " + position);
                }

                //get the brand name
                String brand = xDataList.get(position);
                //display it
                Toast.makeText(ChartActivity.this, brand, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected()
            {

            }
        });
    }

    private void addDataSet()
    {
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        ArrayList<String> xEntries = new ArrayList<>();


        for (int i = 0; i < yDataList.size(); i++)
        {
            yEntries.add(new PieEntry(yDataList.get(i), i));
        }

        for (int i = 0; i < xDataList.size(); i++)
        {
            xEntries.add(xDataList.get(i));
        }

        // create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntries, "Products");
        pieDataSet.setSliceSpace(10);
        pieDataSet.setValueTextSize(20);
        pieDataSet.setSelectionShift(5);


        // add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.GRAY);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();

        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        legend.setXEntrySpace(20);
        legend.setYEntrySpace(5);
        legend.setTextSize(14);
        legend.setWordWrapEnabled(true);


        // create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        // update pieChart
        pieChart.invalidate();
    }
}
