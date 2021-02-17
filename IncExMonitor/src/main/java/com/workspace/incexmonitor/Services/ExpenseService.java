package com.workspace.incexmonitor.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.workspace.incexmonitor.Dto.ExpenseDTO;
import com.workspace.incexmonitor.Entities.Expense;
import com.workspace.incexmonitor.Entities.ExpensesCategory;
import com.workspace.incexmonitor.Repository.ExpenseRepository;
import com.workspace.incexmonitor.Services.ExpenseCategoryService;;
@Service
public class ExpenseService {
	@Autowired
	private ExpenseRepository expenseRepo;
	
	@Autowired
	private ExpenseCategoryService expenseCategService;
	
	public ResponseEntity<?> getExpenseByCategoryId(Long categoryId){
		Optional<ArrayList<Expense>> expenses;
		try{
			 expenses= expenseRepo.getExpenseByExpenseCategory(categoryId);
		}catch(Exception e) {
			return new ResponseEntity("Failed to get Expenses try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(expenses.get()==null) {
			return new ResponseEntity("No Expense found in this category",HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity(expenses.get(),HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> getExpenseBetweenDates(Date fromDate,Date toDate){
		Optional<ArrayList<Expense>> expenses;
		try {
			expenses=expenseRepo.getExpenseBetweenDates(fromDate, toDate);
		}catch(Exception e) {
			return new ResponseEntity("Failed to get Expenses",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(expenses.get()==null) {
			return new ResponseEntity("No Expense found in this between these dates",HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity(expenses.get(),HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> getExpenseByUsername(@PathVariable String username){
		Optional<ArrayList<Expense>> expenses;
		try {
			expenses=expenseRepo.getAllExpenseByUsername(username);
		}catch(Exception e) {
			return new ResponseEntity("Failed to get Expense from username",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(expenses.get()==null) {
			return new ResponseEntity("NO Expense found for your id",HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity(expenses.get(),HttpStatus.OK);
		}
	}
	
	public Expense getExpenseFromExpenseId(Long id) {
		Expense expense;
		expense=expenseRepo.getExpenseByExpenseId(id);
		return expense;	
	}
	
	public ResponseEntity<?> createExpense(ExpenseDTO expenseDto){
		Expense expense;
		try {
		ExpensesCategory expenseCateg=expenseCategService.getExpenseCategoryById(expenseDto.getExpenseCategoryId()).get();
		expense=expenseDto.getExpense();
		expense.setExpensesCategory(expenseCateg);
		this.expenseRepo.save(expense);
		}catch(Exception e) {
			return new ResponseEntity(e.getMessage()+" Error in creating Expense try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(expense,HttpStatus.OK);
	}
	
	public ResponseEntity<?> deleteExpense(Long id){
		try{
			expenseRepo.delete(getExpenseFromExpenseId(id));
		}catch(Exception e) {
			return new ResponseEntity("Error in deleting expense try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity("Expense Deleted successfully",HttpStatus.OK);
	}

	public ResponseEntity<?> getExpenseBalanceSheet(String username){
		ArrayList<?> expenseSheet=null;
		try {
			if(expenseRepo.getAccountNameAndAmountOfUser(username).isPresent())
			expenseSheet=expenseRepo.getAccountNameAndAmountOfUser(username).get();
		}catch(Exception e) {
			return new ResponseEntity("Error in getting details",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(expenseSheet,HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> getExpenseBalanceSheetBetweenDates(String username, Date fromDate, Date toDate){
		ArrayList<?> expenseSheet=null;
		try {
			if(expenseRepo.getAccountNameAndAmountOfUserBetweenDates(username, fromDate, toDate).isPresent()) {
				expenseSheet=expenseRepo.getAccountNameAndAmountOfUserBetweenDates(username, fromDate, toDate).get();
			}
		}catch(Exception e) {
			return new ResponseEntity("Error in getting details",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(expenseSheet,HttpStatus.OK);
	}
}
