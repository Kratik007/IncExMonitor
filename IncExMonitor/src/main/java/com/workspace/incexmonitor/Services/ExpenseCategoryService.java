package com.workspace.incexmonitor.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.workspace.incexmonitor.Entities.ExpensesCategory;
import com.workspace.incexmonitor.Repository.ExpenseCategoryRepository;

@Service
public class ExpenseCategoryService {
	@Autowired
	private ExpenseCategoryRepository expenseCategRepo;
	
	public ResponseEntity<?> createExpenseCategory(ExpensesCategory expenseCateg){
		try{
			expenseCategRepo.save(expenseCateg);
		}catch(Exception e) {
			return new ResponseEntity("Error in creating expense category",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity("Expense Category Saved Successfully",HttpStatus.OK);
	}
	
	public Optional<ExpensesCategory> getExpenseCategoryById(Long id){
		Optional<ExpensesCategory> expenseCateg=expenseCategRepo.getExpenseCategoryByExpenseCategoryId(id);
		return expenseCateg;
	}
	
	public ResponseEntity<?> getAllExpenseCategoryOfUser(String username){
		ArrayList<ExpensesCategory> expenseCategories;
		try {
			expenseCategories=expenseCategRepo.getAllExpenseCategoryByUsername(username);
		}catch(Exception e) {
			return new ResponseEntity("Error in fetching details",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(expenseCategories,HttpStatus.OK);
	}
	
	public ResponseEntity<?> updateExpenseCategory(ExpensesCategory expenseCateg){
		try {
			expenseCategRepo.save(expenseCateg);
		}catch(Exception e) {
			return new ResponseEntity("Error in updating category",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity("Category updated successfully",HttpStatus.OK);
	}
	
	public ResponseEntity<?> deleteExpenseCategory(Long id){
		try {
		ExpensesCategory expenseCateg=expenseCategRepo.getExpenseCategoryByExpenseCategoryId(id).get();
		expenseCategRepo.delete(expenseCateg);
		}catch(Exception e) {
			return new ResponseEntity("Error in deleting Category try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity("Income Category Deleted successfully",HttpStatus.OK);		
	}	

}
