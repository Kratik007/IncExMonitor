package com.workspace.incexmonitor.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

@Entity
public class IncomeCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "table-generator")
	@TableGenerator(name = "table-generator", table = "income_category_ids", pkColumnName = "seq_id", valueColumnName = "seq_value")
	private Long incomeCategoryId;
	private String incomeCategoryName;
	private String incomeCategoryDetails;
	private String username;
	public IncomeCategory() {
		super();
	}
	public IncomeCategory(Long incomeCategoryId, String incomeCategoryName, String incomeCategoryDetails,
			String username) {
		super();
		this.incomeCategoryId = incomeCategoryId;
		this.incomeCategoryName = incomeCategoryName;
		this.incomeCategoryDetails = incomeCategoryDetails;
		this.username = username;
	}
	public Long getIncomeCategoryId() {
		return incomeCategoryId;
	}
	public String getIncomeCategoryName() {
		return incomeCategoryName;
	}
	public String getIncomeCategoryDetails() {
		return incomeCategoryDetails;
	}
	public String getUsername() {
		return username;
	}
	public void setIncomeCategoryId(Long incomeCategoryId) {
		this.incomeCategoryId = incomeCategoryId;
	}
	public void setIncomeCategoryName(String incomeCategoryName) {
		this.incomeCategoryName = incomeCategoryName;
	}
	public void setIncomeCategoryDetails(String incomeCategoryDetails) {
		this.incomeCategoryDetails = incomeCategoryDetails;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
