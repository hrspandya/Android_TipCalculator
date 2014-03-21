package com.hp.tipcalculator;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hp.adapter.TipsAdapter;
import com.hp.tipcalculator.database.TipCalculatorDBHelper;

public class HistoryActivity extends Activity {
	
	protected TipCalculatorDBHelper db;
	private ArrayAdapter<Tip> tipsAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		
		db = new TipCalculatorDBHelper(this);
		
		// Construct the data source
		ArrayList<Tip> arrayOfTips = new ArrayList<Tip>();
		// Create the adapter to convert the array to views
		tipsAdapter = new TipsAdapter(this, arrayOfTips);
		
		//FETCH DATA AND SET ADAPTER WITH DATA
//		Tip newTip1 = new Tip(0, 100, 10, 1, 100, 10, 110, 110, new Date().toString());
//		adapter.add(newTip1);
//		
//		Tip newTip2 = new Tip(0, 100, 10, 1, 100, 10, 110, 110, new Date().toString());
//		adapter.add(newTip2);
//		
//		
//		Tip newTip3 = new Tip(0, 100, 10, 1, 100, 10, 110, 110, new Date().toString());
//		adapter.add(newTip3);
		
		arrayOfTips.addAll(db.getAllTipsHistory());
		
		// Attach the adapter to a ListView
		ListView listView = (ListView) findViewById(R.id.lv_tips);
		listView.setAdapter(tipsAdapter);
		tipsAdapter.notifyDataSetChanged();
	}
}
