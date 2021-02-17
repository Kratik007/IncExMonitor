package com.workspace.incexmonitor.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workspace.incexmonitor.Dto.ExpenseDTO;
import com.workspace.incexmonitor.Services.ExpenseService;

@RestController
public class ExpenseController {
	@Autowired
	private ExpenseService expenseServ;
	@GetMapping("/expenses-id/{categoryId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getExpenseByCategory(@PathVariable Long categoryId){
		return expenseServ.getExpenseByCategoryId(categoryId);
	}
	
	@GetMapping("/expenses/{fromdate}/{todate}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getExpenseBetweenDates(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate){
		return  expenseServ.getExpenseBetweenDates(fromDate, toDate);
	}
	@GetMapping("/user-expense/{username}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getExpenseByUsername(String username){
		return expenseServ.getExpenseByUsername(username);
	}
	@PostMapping("/expense")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> createExpense(@RequestBody ExpenseDTO expenseDto){
		System.out.println(expenseDto.getAmount());
		return expenseServ.createExpense(expenseDto);
	}
	@DeleteMapping("/expense/{expenseId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> deleteIncome(@PathVariable Long expenseId){
		return expenseServ.deleteExpense(expenseId);
	}
	@GetMapping("/expense-balance-sheet/{username}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getExpenseSheet(@PathVariable String username){
		return expenseServ.getExpenseBalanceSheet(username);
	}
	@GetMapping("/expense-balance-sheet/{username}/{fromDate}/{toDate}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getIncomeSheetBetweenDates(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") String username,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate){
		return expenseServ.getExpenseBalanceSheetBetweenDates(username, fromDate, toDate);
	}
}
