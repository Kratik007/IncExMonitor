package com.workspace.incexmonitor.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class ExpensesCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "table-generator")
	@TableGenerator(name = "table-generator", table = "expense_category_ids", pkColumnName = "seq_id", valueColumnName = "seq_value")
	private Long expenseCategoryId;
	private String expenseCategoryName;
	private String expenseCategoryDetails;
	private String username;
	public ExpensesCategory() {
		super();
	}
	public ExpensesCategory(Long expenseCategoryId, String expenseCategoryName, String expenseCategoryDetails,
			String username) {
		super();
		this.expenseCategoryId = expenseCategoryId;
		this.expenseCategoryName = expenseCategoryName;
		this.expenseCategoryDetails = expenseCategoryDetails;
		this.username = username;
	}
	public Long getExpenseCategoryId() {
		return expenseCategoryId;
	}
	public String getExpenseCategoryName() {
		return expenseCategoryName;
	}
	public String getExpenseCategoryDetails() {
		return expenseCategoryDetails;
	}
	public String getUsername() {
		return username;
	}
	public void setExpenseCategoryId(Long expenseCategoryId) {
		this.expenseCategoryId = expenseCategoryId;
	}
	public void setExpenseCategoryName(String expenseCategoryName) {
		this.expenseCategoryName = expenseCategoryName;
	}
	public void setExpenseCategoryDetails(String expenseCateogryDetails) {
		this.expenseCategoryDetails = expenseCateogryDetails;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "ExpensesCategory [expenseCategoryId=" + expenseCategoryId + ", expenseCategoryName="
				+ expenseCategoryName + ", expenseCategoryDetails=" + expenseCategoryDetails + ", username=" + username
				+ "]";
	}
	
}
