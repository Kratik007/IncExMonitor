package com.workspace.incexmonitor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.workspace.incexmonitor.Entities.IncomeCategory;
import com.workspace.incexmonitor.Services.IncomeCategoryService;

@RestController
public class IncomeCategoryController {
	@Autowired
	private IncomeCategoryService incomeCategoryService;
	
	@GetMapping("/user-income-categories/{username}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getIncomeCategoriyByUsername(@PathVariable String username){
		return incomeCategoryService.getAllIncomeCategoryOfUser(username);
	}
	
	@GetMapping("/income-categories/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getIncomeCategoryById(@PathVariable Long id){
		return new ResponseEntity(incomeCategoryService.getIncomeCategoryById(id).get(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/income-categories",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> createIncomeCategory(@RequestBody IncomeCategory incomeCateg){
		return incomeCategoryService.createIncomeCategory(incomeCateg);
	}
	
	@RequestMapping(value="/income-categories",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> updateIncomeCategory(@RequestBody IncomeCategory incomeCateg){
		return incomeCategoryService.updateIncomeCategory(incomeCateg);
	}
	
	@DeleteMapping("/income-category/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> deleteIncomeCategory(@PathVariable Long id){
		return incomeCategoryService.deleteIncomeCategory(id);
	}
}
