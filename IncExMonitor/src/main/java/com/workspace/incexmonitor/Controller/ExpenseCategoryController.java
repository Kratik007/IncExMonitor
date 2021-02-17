package com.workspace.incexmonitor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.workspace.incexmonitor.Entities.ExpensesCategory;
import com.workspace.incexmonitor.Services.ExpenseCategoryService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ExpenseCategoryController {
	@Autowired
	private ExpenseCategoryService expenseCategoryService;
	
	@GetMapping("/user-expense-categories/{username}")
	public ResponseEntity<?> getExpenseCategoriyByUsername(@PathVariable String username){
		return expenseCategoryService.getAllExpenseCategoryOfUser(username);
	}
	
	@GetMapping("/expense-categories/{id}")
	public ResponseEntity<?> getExpenseCategoryById(@PathVariable Long id){
		return new ResponseEntity(expenseCategoryService.getExpenseCategoryById(id).get(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/expense-categories",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> createExpenseCategory(@RequestBody ExpensesCategory expenseCateg){
		System.out.println(expenseCateg);
		return expenseCategoryService.createExpenseCategory(expenseCateg);
	}
	
	@RequestMapping(value="/expense-categories",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<?> updateExpenseCategory(@RequestBody ExpensesCategory expenseCateg){
		return expenseCategoryService.updateExpenseCategory(expenseCateg);
	}
	
	@DeleteMapping("/expense-category/{id}")
	public ResponseEntity<?> deleteExpenseCategory(@PathVariable Long id){
		return expenseCategoryService.deleteExpenseCategory(id);
	}

}
