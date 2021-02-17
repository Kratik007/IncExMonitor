package com.workspace.incexmonitor.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.workspace.incexmonitor.Services.BooksService;

@RestController
public class BooksController {
	@Autowired
	private BooksService bkService;
	
	@GetMapping("/bank-book/{username}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getBankBook(@PathVariable String username){
		return 	bkService.getAllBankBookDetails(username);
	}
	
	@GetMapping("cash-book/{username}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getCashBook(@PathVariable String username){
		return bkService.getAllCashBookDetails(username);
	}
	
	@GetMapping("/bank-book/{username}/{fromDate}/{toDate}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getBankBookBetweenDates(@PathVariable String username,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate){
		return bkService.getAllBankBookDetailsBetweenDates(username,fromDate,toDate);
	}
	
	@GetMapping("/cash-book/{username}/{fromDate}/{toDate}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getCashBookBetweenDates(@PathVariable String username,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate){
		return bkService.getAllCashBookDetailsBetweenDates(username, fromDate, toDate);
	}
	
	@GetMapping("/day-book/{username}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getDayBookDetails(@PathVariable String username){
		return bkService.getAllDayBookByUsername(username);
	}
	
	@GetMapping("/day-book/{username}/{fromDate}/{toDate}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getDayBookBetweenDates(@PathVariable String username,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate){
		return bkService.getDayBookBetweenDates(username, fromDate, toDate);
	}
	
	@GetMapping("/balance-sheet/{username}")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<?> getBalanceSheetByUsername(@PathVariable String username){
		return bkService.getAllBalanceSheet(username);
	}
	
	@GetMapping("/balance-sheet/{username}/{fromDate}/{toDate}")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<?> getBalanceSheetByUsernameBetweenDates(@PathVariable String username,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate){
		return bkService.getBalanceSheetBetweenDates(username, fromDate, toDate);
	}
}
