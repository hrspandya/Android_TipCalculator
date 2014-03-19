package com.hp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hp.tipcalculator.R;
import com.hp.tipcalculator.Tip;

public class TipsAdapter extends ArrayAdapter<Tip> {
    
	public TipsAdapter(Context context, ArrayList<Tip> tips) {
       super(context, R.layout.item_tip, tips);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       Tip myTip = getItem(position);    
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tip, null);
       }
       // Lookup view for data population
       TextView tvDate = (TextView) convertView.findViewById(R.id.tv_date);
       TextView tvTime = (TextView) convertView.findViewById(R.id.tv_time);
       TextView tvYouPaid = (TextView) convertView.findViewById(R.id.tv_paid_amount);
       
       // Populate the data into the template view using the data object
       tvDate.setText(myTip.getTimeStamp());
       tvTime.setText("");
       tvYouPaid.setText(String.valueOf(myTip.getTotalPerPerson()));
       // Return the completed view to render on screen
       return convertView;
   }
}