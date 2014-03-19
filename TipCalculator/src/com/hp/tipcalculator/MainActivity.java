package com.hp.tipcalculator;

import java.text.DecimalFormat;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.hp.tipcalculator.database.TipCalculatorDBHelper;

public class MainActivity extends TabActivity {
	
	protected TipCalculatorDBHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// initialize the DB, as soon as APP starts
		db = new TipCalculatorDBHelper(this);
		
		TabHost tabHost=(TabHost)findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		TabSpec spec1=tabHost.newTabSpec("TIP");
		spec1.setContent(R.id.calculator);
		spec1.setIndicator("TIP");
		
		
		ImageButton save_btn = (ImageButton) findViewById(R.id.btn_save);
		
		
		//Getting Bill Amount from USER 
		final EditText et_bill_amount = (EditText)findViewById(R.id.et_bill_amount);
		et_bill_amount.setRawInputType(Configuration.KEYBOARD_12KEY);
		
		final TextView tv_percent_amount = (TextView)findViewById(R.id.tv_percent_amount);
		final TextView tv_people_count = (TextView)findViewById(R.id.tv_people_count);
		
		final TextView tv_bill_per_person_amount = (TextView)findViewById(R.id.tv_bill_per_person_amount);
		final TextView tv_tip_per_person_amount = (TextView)findViewById(R.id.tv_tip_per_person_amount1);
		final TextView tv_total_bill_per_person_amount = (TextView)findViewById(R.id.tv_total_bill_per_person_amount1);
		final TextView tv_total_amuont_value = (TextView)findViewById(R.id.tv_total_amount_value);		
		
		
		final Context that = this;
		
		et_bill_amount.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				calculate(tv_percent_amount, tv_people_count,
						tv_bill_per_person_amount, tv_tip_per_person_amount,
						tv_total_bill_per_person_amount, tv_total_amuont_value,
						that, s.toString());  
				
			}
			
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
			
		
		SeekBar percentSeekbar = (SeekBar)findViewById(R.id.sb_percent_seekbar);
		
		percentSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				tv_percent_amount.setText("" + progress + "%");
				calculate(tv_percent_amount, tv_people_count,
						tv_bill_per_person_amount, tv_tip_per_person_amount,
						tv_total_bill_per_person_amount, tv_total_amuont_value,
						that, et_bill_amount.getText().toString());  
			}
		});
		
		
				
		SeekBar peopleSeekbar = (SeekBar)findViewById(R.id.sb_people_seekbar);
		
		peopleSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if(progress == 0){
					tv_people_count.setText("" + "Me");
				}else{
					tv_people_count.setText("" + progress);	
				}
				
				calculate(tv_percent_amount, tv_people_count,
						tv_bill_per_person_amount, tv_tip_per_person_amount,
						tv_total_bill_per_person_amount, tv_total_amuont_value,
						that, et_bill_amount.getText().toString());
				
			}
		});
		
		
		//Save Button 
		save_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try  
				  {  
					double total = Double.parseDouble(et_bill_amount.getText().toString());
					double final_total = Double.parseDouble(tv_total_amuont_value.getText().toString().replace("$", ""));
					int percent = Integer.valueOf(tv_percent_amount.getText().toString().replace("%", ""));
					int people = 1;
					if(tv_people_count.getText().toString().equalsIgnoreCase("Me")){
						people = 1;
					}else{
						people = Integer.valueOf(tv_people_count.getText().toString());
					}
					
					double bill_per_person = Double.parseDouble(tv_bill_per_person_amount.getText().toString().replace("$", ""));
					double tip_per_person = Double.parseDouble(tv_tip_per_person_amount.getText().toString().replace("$", ""));
					double total_bill_per_person = Double.parseDouble(tv_total_bill_per_person_amount.getText().toString().replace("$", ""));
					
					if(total > 0 && final_total > 0){
						
						//ImageButton btn = (ImageButton) v;
						//Tip myTip = (Tip) btn.getTag();
						Tip myTip = new Tip();
						myTip.setTotalAmount(total);
						myTip.setPercent(percent);
						myTip.setPeople(people);
						myTip.setBillPerPerson(bill_per_person);
						myTip.setTipPerPerson(tip_per_person);
						myTip.setTotalPerPerson(total_bill_per_person);
						myTip.setFinalTotal(final_total);
						
						db.addTipInfo(myTip);
						Toast.makeText(
								getApplicationContext(),
								"Saved :) ", Toast.LENGTH_LONG)
								.show();
						
					}else{
						Toast.makeText(
								getApplicationContext(),
								"Please Enter Bill Amount :) ", Toast.LENGTH_LONG)
								.show();
					}
					
				  }catch(NumberFormatException e){
					  Toast.makeText(
								getApplicationContext(),
								"Enter correct Bill Amount :) ", Toast.LENGTH_LONG)
								.show();
				  }
			    
				
			}
		});
		//Reset BUtton
		
		//Share Button
		
		
		

		TabSpec spec2=tabHost.newTabSpec("HISTORY");
		spec2.setIndicator("HISTORY");
		Intent historyIntent = new Intent(this, HistoryActivity.class);
		spec2.setContent(historyIntent);
		//spec2.setContent(R.id.history);
		
		TabSpec spec3=tabHost.newTabSpec("GUIDE");
		spec3.setIndicator("GUIDE");
		spec3.setContent(R.id.guide);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void calculate(final TextView tv_percent_amount,
			final TextView tv_people_count,
			final TextView tv_bill_per_person_amount,
			final TextView tv_tip_per_person_amount,
			final TextView tv_total_bill_per_person_amount,
			final TextView tv_total_amuont_value, final Context that,
			CharSequence s) {
		try  
		  {  
		    double total = Double.parseDouble(s.toString());
			int percent = Integer.valueOf(tv_percent_amount.getText().toString().replace("%", ""));
			int people = 0;
			
			if(tv_people_count.getText().toString().equalsIgnoreCase("Me")){
				people = 1;
			}else{
				people = Integer.valueOf(tv_people_count.getText().toString());
			}
			
			DecimalFormat df = new DecimalFormat("0.00");
			
			double total_tip_amount = 0;
			if(percent > 0){
				total_tip_amount = (total * percent)/100;
			}
			
									
			double bill_per_person_amount = total/people;
			double tip_per_person_amount = (total_tip_amount/people);
			double total_bill_per_person_amount = ((total/people) +  (total_tip_amount/people));
			double total_amuont_value = (total + total_tip_amount);
			
			
			//Total Value
			tv_bill_per_person_amount.setText("$ " + df.format(bill_per_person_amount));
			tv_tip_per_person_amount.setText("$ " + df.format(tip_per_person_amount));
			tv_total_bill_per_person_amount.setText("$ " + df.format(total_bill_per_person_amount));
			tv_total_amuont_value.setText("$ "+ df.format(total_amuont_value));
		  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
			  Toast.makeText(that, "Bill amount is not number!!",
						Toast.LENGTH_LONG);  
		  }
	}

}
