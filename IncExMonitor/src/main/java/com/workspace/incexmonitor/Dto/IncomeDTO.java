package com.workspace.incexmonitor.Dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.workspace.incexmonitor.Entities.Income;

public class IncomeDTO {
	private Long incomeId;
	private String accountName;
	private float amount;
	private char receiveBy;
	private String date;
	private String remark;
	private Long incomeCategoryId;
	private String username;
	public IncomeDTO() {
		super();
	}
	public IncomeDTO(Long incomeId, String accountName, float amount, char receiveBy, String date, String remark,
			Long incomeCategoryId, String username) {
		super();
		this.incomeId = incomeId;
		this.accountName = accountName;
		this.amount = amount;
		this.receiveBy = receiveBy;
		this.date = date;
		this.remark = remark;
		this.incomeCategoryId = incomeCategoryId;
		this.username = username;
	}
	public Income getIncome() {
		Date _date=null;
		try {
			_date=new SimpleDateFormat("yyyy-MM-dd").parse(this.date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Income(this.incomeId,this.accountName,this.amount,this.receiveBy,_date,this.remark,null,this.username);
	}
	public Long getIncomeId() {
		return incomeId;
	}
	public String getAccountName() {
		return accountName;
	}
	public float getAmount() {
		return amount;
	}
	public char getReceiveBy() {
		return receiveBy;
	}
	public String getDate() {
		return date;
	}
	public String getRemark() {
		return remark;
	}
	public Long getIncomeCategoryId() {
		return incomeCategoryId;
	}
	public String getUsername() {
		return username;
	}
	public void setIncomeId(Long incomeId) {
		this.incomeId = incomeId;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void setReceiveBy(char receiveBy) {
		this.receiveBy = receiveBy;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setIncomeCategoryId(Long incomeCategoryId) {
		this.incomeCategoryId = incomeCategoryId;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
