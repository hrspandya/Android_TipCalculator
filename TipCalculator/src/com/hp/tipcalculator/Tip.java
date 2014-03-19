package com.hp.tipcalculator;

public class Tip {
				
	public int id;
	public double totalAmount;  //input amount by user
	public int percent;
	public int people;
	public double billPerPerson;
	public double tipPerPerson;
	public double totalPerPerson;
	public double finalTotal; 	//final_total includes the tip + total Amount
	public String timeStamp;
	
	
	public Tip(int id, double totalAmount, int percent, int people,
			double billPerPerson, double tipPerPerson, double totalPerPerson,
			double finalTotal, String timeStamp) {
		super();
		this.id = id;
		this.totalAmount = totalAmount;
		this.percent = percent;
		this.people = people;
		this.billPerPerson = billPerPerson;
		this.tipPerPerson = tipPerPerson;
		this.totalPerPerson = totalPerPerson;
		this.finalTotal = finalTotal;
		this.timeStamp = timeStamp;
	}
	
	public Tip() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public double getBillPerPerson() {
		return billPerPerson;
	}
	public void setBillPerPerson(double billPerPerson) {
		this.billPerPerson = billPerPerson;
	}
	public double getTipPerPerson() {
		return tipPerPerson;
	}
	public void setTipPerPerson(double tipPerPerson) {
		this.tipPerPerson = tipPerPerson;
	}
	public double getTotalPerPerson() {
		return totalPerPerson;
	}
	public void setTotalPerPerson(double totalPerPerson) {
		this.totalPerPerson = totalPerPerson;
	}
	public double getFinalTotal() {
		return finalTotal;
	}
	public void setFinalTotal(double finalTotal) {
		this.finalTotal = finalTotal;
	}
	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}
