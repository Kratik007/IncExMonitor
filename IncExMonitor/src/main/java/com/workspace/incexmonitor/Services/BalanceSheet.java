package com.workspace.incexmonitor.Services;

public class BalanceSheet {
	public String acountName;
	public float amount;
	public String pay_receive;
	public BalanceSheet() {
		super();
	}
	
	public BalanceSheet(String acountName, float amount, String pay_receive) {
		super();
		this.acountName = acountName;
		this.amount = amount;
		this.pay_receive = pay_receive;
	}
	
	public BalanceSheet(String acountName, float amount) {
		super();
		this.acountName = acountName;
		this.amount = amount;
	}
	
	public String getPay_receive() {
		return pay_receive;
	}

	public void setPay_receive(String pay_receive) {
		this.pay_receive = pay_receive;
	}

	public String getAcountName() {
		return acountName;
	}
	public float getAmount() {
		return amount;
	}
	public void setAcountName(String acountName) {
		this.acountName = acountName;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
}
