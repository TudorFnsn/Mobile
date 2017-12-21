package com.example.tudorfenesan.lab1r.adapter;

import com.example.tudorfenesan.lab1r.R;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tudorfenesan on 20/12/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private List<Map.Entry<String, String>> values = new ArrayList<>();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        //public View layout;

        public ViewHolder(View view)
        {
            super(view);
            //layout = view;

            txtHeader = view.findViewById(R.id.nameListView);
            txtFooter = view.findViewById(R.id.emailListView);
        }
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Map.Entry<String, String>> myDataset)
    {
        values = myDataset;
    }

    public void add(int position, AbstractMap.SimpleEntry<String, String> item)
    {
        values.add(position, item);
        notifyItemInserted(position);
    }
//
//    public void remove(int position)
//    {
//        values.remove(position);
//        notifyItemRemoved(position);
//    }

    /**
     * Create new views (invoked by the layout manager)
     *
     * @param parent   -
     * @param viewType -
     * @return -
     */
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);

        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    /**
     * // Replace the contents of a view (invoked by the layout manager)
     *
     * @param holder   -
     * @param position -
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {


        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String name = values.get(position).getKey();
        String email = values.get(position).getValue();

        holder.txtHeader.setText(name);
        holder.txtFooter.setText(email);


//        holder.txtHeader.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                //remove(position);
//                add(1, new AbstractMap.SimpleEntry<>("Rares", "raresabr"));
//            }
//        });

        holder.txtHeader.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
//                Toast.makeText(v.getContext(), "plm", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return values.size();
    }
}
