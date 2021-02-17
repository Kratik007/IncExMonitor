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

import com.workspace.incexmonitor.Dto.IncomeDTO;
import com.workspace.incexmonitor.Services.IncomeService;

@RestController
public class IncomeController {
	@Autowired
	private IncomeService incomeServ;
	
	@GetMapping("/incomes-id/{categoryId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getIncomeByCategory(@PathVariable Long categoryId){
		return incomeServ.getIncomeByCategoryId(categoryId);
	}
	@GetMapping("/incomes/{fromdate}/{todate}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getIncomeBetweenDates(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate){
		return  incomeServ.getIncomeBetweenDates(fromDate, toDate);
	}
	@GetMapping("/user-income/{username}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getIncomeByUsername(String username){
		return incomeServ.getIncomeByUsername(username);
	}
	@PostMapping("/income")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> createIncome(@RequestBody IncomeDTO incomeDto){
		return incomeServ.createIncome(incomeDto);
	}
	@DeleteMapping("/income/{incomeId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> deleteIncome(@PathVariable Long incomeId){
		return incomeServ.deleteIncome(incomeId);
	}
	@GetMapping("/income-balance-sheet/{username}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getIncomeSheet(@PathVariable String username){
		return incomeServ.getIncomeBalanceSheet(username);
	}
	@GetMapping("/income-balance-sheet/{username}/{fromDate}/{toDate}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getIncomeSheetBetweenDates(@PathVariable String username,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate){
		return incomeServ.getIncomeBalanceSheetBetweenDates(username, fromDate, toDate);
	}
}
