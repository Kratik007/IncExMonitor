package com.workspace.incexmonitor.Services;

import java.util.Date;

public class DayBook {
	
	public String acountName;
	public Date date;
	public float amount;
	public String pay_receive;
	public char by;
	public String remark;
	
	public DayBook() {
		super();
	}
	public DayBook(String acountName, Date date, float amount, String pay_receive, char by, String remark) {
		super();
		this.acountName = acountName;
		this.date = date;
		this.amount = amount;
		this.pay_receive = pay_receive;
		this.by = by;
		this.remark = remark;
	}
	public String getAcountName() {
		return acountName;
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
	public char getBy() {
		return by;
	}
	public String getRemark() {
		return remark;
	}
	public void setAcountName(String acountName) {
		this.acountName = acountName;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void setPay_receive(String pay_receive) {
		this.pay_receive = pay_receive;
	}
	public void setBy(char by) {
		this.by = by;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
