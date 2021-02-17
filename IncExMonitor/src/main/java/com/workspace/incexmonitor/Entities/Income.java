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
public class Income {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "table-generator")
	@TableGenerator(name = "table-generator", table = "income_ids", pkColumnName = "seq_id", valueColumnName = "seq_value", allocationSize = 1)
	private Long incomeId;
	private String accountName;
	private float amount;
	private char receiveBy;
	@Column(name="_date")
	private Date date;
	private String remark;
	@ManyToOne
	@JoinColumn(name="income_category_id", referencedColumnName = "incomeCategoryId")
	private IncomeCategory incomeCategory;
	private String username;
	public Income() {
		super();
	}
	public Income(Long incomeId, String accountName, float amount, char receiveBy, Date date, String remark,
			IncomeCategory incomeCategory) {
		super();
		this.incomeId = incomeId;
		this.accountName = accountName;
		this.amount = amount;
		this.receiveBy = receiveBy;
		this.date = date;
		this.remark = remark;
		this.incomeCategory = incomeCategory;
	}
	public Income(Long incomeId, String accountName, float amount, char receiveBy, Date date, String remark,
			IncomeCategory incomeCategory, String username) {
		super();
		this.incomeId = incomeId;
		this.accountName = accountName;
		this.amount = amount;
		this.receiveBy = receiveBy;
		this.date = date;
		this.remark = remark;
		this.incomeCategory = incomeCategory;
		this.username = username;
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
	public Date getDate() {
		return date;
	}
	public String getRemark() {
		return remark;
	}
	public IncomeCategory getIncomeCategory() {
		return incomeCategory;
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
	public void setDate(Date date) {
		this.date = date;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setIncomeCategory(IncomeCategory incomeCategory) {
		this.incomeCategory = incomeCategory;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
