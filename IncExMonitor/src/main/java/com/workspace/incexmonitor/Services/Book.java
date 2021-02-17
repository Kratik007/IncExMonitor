package com.workspace.incexmonitor.Services;

import java.util.Date;

public class Book {
	Date date;
	float amount;
	String pay_receive;
	public Book() {
		super();
	}
	public Book(Date date, float amount, String pay_receive) {
		super();
		this.date = date;
		this.amount=amount;
		this.pay_receive = pay_receive;
	}
	public Date getDate() {
		return date;
	}
	public float getAmount() {
		return amount;
	}
	public String getPay_receive() {
		return pay_receive;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setAmount(float amount) {
		this.amount=amount;
	}
	public void setPay_receive(String pay_receive) {
		this.pay_receive = pay_receive;
	}
	
}
