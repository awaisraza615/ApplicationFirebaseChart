package com.example.applicationfirebasechart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DataList extends ArrayAdapter<InsertData> {

    private Activity context;
    private List<InsertData> mdatalist;

    public DataList(Activity context,List<InsertData> mdatalist)
    {
        super(context,R.layout.list_layout,mdatalist);
        this.context = context;
        this.mdatalist = mdatalist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewId = (TextView) listViewItem.findViewById(R.id.txteid);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.txtename);
        TextView textViewAddress = (TextView) listViewItem.findViewById(R.id.txteaddress);
        TextView textViewAge = (TextView) listViewItem.findViewById(R.id.txteage);

        InsertData insertData = mdatalist.get(position);

        textViewId.setText(insertData.getmID());
        textViewName.setText(insertData.getmName());
        textViewAddress.setText(insertData.getmAddress());
        textViewAge.setText(insertData.getmAge());
        return listViewItem;
    }
}
