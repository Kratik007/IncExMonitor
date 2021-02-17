package com.workspace.incexmonitor.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "table-generator")
	@TableGenerator(name = "table-generator", table = "expense_ids", pkColumnName = "seq_id", valueColumnName = "seq_value", allocationSize = 1)
	private Long expenseId;
	private String accountName;
	private Long amount;
	private char payedBy;
	@Column(name="_date")
	private Date date;
	private String remark;	
	@ManyToOne
	@JoinColumn(name="expense_category_id",referencedColumnName = "expenseCategoryId")
	private ExpensesCategory expensesCategory;
	private String username;
	public Expense() {
		super();
	}
	public Expense(Long expenseId, String accountName, Long amount, char payedBy, Date date, String remark,
			ExpensesCategory expensesCategory) {
		super();
		this.expenseId = expenseId;
		this.accountName = accountName;
		this.amount = amount;
		this.payedBy = payedBy;
		this.date = date;
		this.remark = remark;
		this.expensesCategory = expensesCategory;
	}
	
	public Expense(Long expenseId, String accountName, Long amount, char payedBy, Date date, String remark,
			ExpensesCategory expensesCategory, String username) {
		super();
		this.expenseId = expenseId;
		this.accountName = accountName;
		this.amount = amount;
		this.payedBy = payedBy;
		this.date = date;
		this.remark = remark;
		this.expensesCategory = expensesCategory;
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Date getDate() {
		return date;
	}
	public String getRemark() {
		return remark;
	}
	public ExpensesCategory getExpensesCategory() {
		return expensesCategory;
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
	public void setDate(Date date) {
		this.date = date;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setExpensesCategory(ExpensesCategory expensesCategory) {
		this.expensesCategory = expensesCategory;
	}
	
}
