package com.hp.tipcalculator.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hp.tipcalculator.Tip;


public class TipCalculatorDBHelper extends SQLiteOpenHelper {

	
	private static final int DB_VERSION = 1;

	// Database Name
	private static final String DB_NAME = "tipManager";

	// Table Name
	private static final String TABLE_TIPS = "tips";

	//Columns names inside tasks table
	private static final String ID = "id";
	private static final String TOTAL_AMOUNT = "total_amount"; //includes Tax
	private static final String TIP_PERCENT = "tip_percent";
	private static final String PEOPLE = "people";
	private static final String BILL_PER_PERSON = "bill_per_person";
	private static final String TIP_PER_PERSON = "tip_per_person";
	private static final String TOTAL_PER_PERSON = "total_per_person";
	private static final String FINAL_TOTAL = "final_total"; //final_total includes the tip + total Amount
	private static final String TIME_STAMP = "timeStamp";
	
	
	
	public TipCalculatorDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sqlQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_TIPS + " ( "
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
				+ TOTAL_AMOUNT + " REAL, " + TIP_PERCENT + " INTEGER, "
				+ PEOPLE + " INTEGER, " + BILL_PER_PERSON + " REAL, "
				+ TIP_PER_PERSON + " REAL, " + TOTAL_PER_PERSON + " REAL, "
				+ FINAL_TOTAL + " REAL, " + TIME_STAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
				+ ")";
		db.execSQL(sqlQuery);
		 
	}
	
	public List<Tip> getAllTipsHistory(){
		List<Tip> tipsList = new ArrayList<Tip>();
		
		String query = "SELECT * FROM " + TABLE_TIPS;
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Tip myTip = new Tip();
				myTip.setId(cursor.getInt(0));
				myTip.setTotalAmount(cursor.getDouble(1));
				myTip.setPercent(cursor.getInt(2));
				myTip.setPeople(cursor.getInt(3));
				myTip.setBillPerPerson(cursor.getDouble(4));
				myTip.setTipPerPerson(cursor.getDouble(5));
				myTip.setTotalPerPerson(cursor.getDouble(6));
				myTip.setFinalTotal(cursor.getDouble(7));
				myTip.setTimeStamp(cursor.getString(8));
				
				// Adding contact to list
				tipsList.add(myTip);
			} while (cursor.moveToNext());
		}

		// return task list
		return tipsList;
	}

	
	// Adding new task
	public void addTipInfo(Tip tip) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TOTAL_AMOUNT, tip.getTotalAmount());
		values.put(TIP_PERCENT, tip.getPercent());
		values.put(PEOPLE, tip.getPeople());
		values.put(BILL_PER_PERSON, tip.getBillPerPerson());
		values.put(TIP_PER_PERSON, tip.getTipPerPerson());
		values.put(TOTAL_PER_PERSON, tip.getTotalPerPerson());
		values.put(FINAL_TOTAL, tip.getFinalTotal());
		values.put(TIME_STAMP, new Date().toString());

		// Inserting Row
		db.insert(TABLE_TIPS, null, values);
		db.close(); // Closing database connection
	}
		
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
