package com.workspace.incexmonitor.Dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.workspace.incexmonitor.Entities.Expense;

public class ExpenseDTO {
	private Long expenseId;
	private String accountName;
	private Long amount;
	private char payedBy;
	private String date;
	private String remark;
	private Long expenseCategoryId;
	private String username;
	public ExpenseDTO() {
		super();
	}
	public ExpenseDTO(Long expenseId, String accountName, Long amount, char payedBy, String date, String remark,
			Long expenseCategoryId) {
		super();
		this.expenseId = expenseId;
		this.accountName = accountName;
		this.amount = amount;
		this.payedBy = payedBy;
		this.date = date;
		this.remark = remark;
		this.expenseCategoryId = expenseCategoryId;
	}
	public ExpenseDTO(Long expenseId, String accountName, Long amount, char payedBy, String date, String remark,
	    	Long expenseCategoryId, String username) {
			super();
			this.expenseId = expenseId;
			this.accountName = accountName;
			this.amount = amount;
			this.payedBy = payedBy;
			this.date = date;
			this.remark = remark;
			this.expenseCategoryId = expenseCategoryId;
			this.username = username;
	}
	public Expense getExpense() {
		Date _date=null;
		try {
			_date=new SimpleDateFormat("yyyy-MM-dd").parse(this.date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Expense(this.expenseId,this.accountName,this.amount,this.payedBy,_date,this.remark,null,this.username);
		
	}
	public Long getExpenseId() {
		return expenseId;
	}
	public String getAccountName() {
		return accountName;
	}
	public Long getAmount() {
		return amount;
	}
	public char getPayedBy() {
		return payedBy;
	}
	public String getDate() {
		return date;
	}
	public String getRemark() {
		return remark;
	}
	public Long getExpenseCategoryId() {
		return expenseCategoryId;
	}
	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public void setPayedBy(char payedBy) {
		this.payedBy = payedBy;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setExpenseCategoryId(Long expenseCategoryId) {
		this.expenseCategoryId = expenseCategoryId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
