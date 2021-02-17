package com.workspace.incexmonitor.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.workspace.incexmonitor.Entities.Expense;
import com.workspace.incexmonitor.Entities.Income;
import com.workspace.incexmonitor.Repository.ExpenseRepository;
import com.workspace.incexmonitor.Repository.IncomeRepository;

@Service
public class BooksService {

	@Autowired
	private IncomeRepository incomeRepo;
	@Autowired
	private ExpenseRepository expenseRepo;

	public ResponseEntity<?> getAllBankBookDetails(String username){
		ArrayList book=new ArrayList<>();
		try {
			ArrayList<Expense> expBook=null;
			ArrayList<Income> incBook=null;
			if(incomeRepo.getIncomeByUsernameThroughBank(username).isPresent()){
				incBook=incomeRepo.getIncomeByUsernameThroughBank(username).get();
				incBook.forEach((Income item)->{
					Book _book =new Book();
					_book.setDate(item.getDate());
					_book.setAmount(item.getAmount());
					_book.setPay_receive("PAY");
					book.add(_book);		
				});
			}
			if(expenseRepo.getExpenseByUsernameThroughBank(username).isPresent()) {
				expBook=expenseRepo.getExpenseByUsernameThroughBank(username).get();
				expBook.forEach((Expense item)->{
					Book _book =new Book();
					_book.setDate(item.getDate());
					_book.setAmount(item.getAmount());
					_book.setPay_receive("RECEIVE");
					book.add(_book);
				});
			}
		}catch(Exception e) {
			return new ResponseEntity("Error in fetching details try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(book,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getAllCashBookDetails(String username){
		ArrayList book=new ArrayList<>();
		try {
			ArrayList<Expense> expBook=null;
			ArrayList<Income> incBook=null;
			if(incomeRepo.getIncomeByUsernameThroughCash(username).isPresent()) {
				incBook=incomeRepo.getIncomeByUsernameThroughCash(username).get();
				incBook.forEach((Income item)->{
					Book _book =new Book();
					_book.setDate(item.getDate());
					_book.setAmount(item.getAmount());
					_book.setPay_receive("PAY");
					book.add(_book);		
				});
			}
			if(expenseRepo.getExpenseByUsernameThroughCash(username).isPresent())
			{
				expBook=expenseRepo.getExpenseByUsernameThroughCash(username).get();
				expBook.forEach((Expense item)->{
					Book _book =new Book();
					_book.setDate(item.getDate());
					_book.setAmount(item.getAmount());
					_book.setPay_receive("RECEIVE");
					book.add(_book);
				});
			}
		}catch(Exception e) {
			return new ResponseEntity(e.getMessage()+" Error in fetching details",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(book,HttpStatus.OK);
	}	

	public ResponseEntity<?> getAllCashBookDetailsBetweenDates(String username, Date fromDate, Date toDate){
		ArrayList book=new ArrayList<>();
		try {
			ArrayList<Expense> expBook=null;
			ArrayList<Income> incBook=null;
			if(incomeRepo.getAllIncomeByUsernameBetweenDatesThroughCash(username,fromDate,toDate).isPresent()) {
				incBook=incomeRepo.getAllIncomeByUsernameBetweenDatesThroughCash(username,fromDate,toDate).get();
				incBook.forEach((Income item)->{
					Book _book =new Book();
					_book.setDate(item.getDate());
					_book.setAmount(item.getAmount());
					_book.setPay_receive("PAY");
					book.add(_book);		
				});
			}
			if(expenseRepo.getAllExpenseByUsernameBetweenDatesThroughCash(username,fromDate,toDate).isPresent()) {
				expBook=expenseRepo.getAllExpenseByUsernameBetweenDatesThroughCash(username,fromDate,toDate).get();
				expBook.forEach((Expense item)->{
					Book _book =new Book();
					_book.setDate(item.getDate());
					_book.setAmount(item.getAmount());
					_book.setPay_receive("RECEIVE");
					book.add(_book);
				});
			}
		}catch(Exception e) {
			return new ResponseEntity("Error in fetching details try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(book,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getAllBankBookDetailsBetweenDates(String username,Date fromDate, Date toDate){
		ArrayList book=new ArrayList<>();
		try {
			ArrayList<Expense> expBook=null;
			ArrayList<Income> incBook=null;
			if(incomeRepo.getAllIncomeByUsernameBetweenDatesThroughBank(username,fromDate,toDate).isPresent()) {
				incBook=incomeRepo.getAllIncomeByUsernameBetweenDatesThroughBank(username,fromDate,toDate).get();
				incBook.forEach((Income item)->{
					Book _book =new Book();
					_book.setDate(item.getDate());
					_book.setAmount(item.getAmount());
					_book.setPay_receive("PAY");
					book.add(_book);		
				});
			}
			if(expenseRepo.getAllExpenseByUsernameBetweenDatesThroughBank(username,fromDate,toDate).isPresent()) {
				expBook=expenseRepo.getAllExpenseByUsernameBetweenDatesThroughBank(username,fromDate,toDate).get();
				expBook.forEach((Expense item)->{
					Book _book =new Book();
					_book.setDate(item.getDate());
					_book.setAmount(item.getAmount());
					_book.setPay_receive("RECEIVE");
					book.add(_book);
				});
			}
		}catch(Exception e) {
			return new ResponseEntity("Error in fetching details try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(book,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getAllDayBookByUsername(String username){
		ArrayList<DayBook> dayBook=new ArrayList<>();
		try {
			ArrayList<Income> incomeBook=null;
			ArrayList<Expense> expenseBook=null;
			if(incomeRepo.getAllIncomeByUsername(username).isPresent()) {
				incomeBook=incomeRepo.getAllIncomeByUsername(username).get();
				incomeBook.forEach((Income item)->{
					DayBook incBook=new DayBook();
					incBook.setAcountName(item.getAccountName());
					incBook.setDate(item.getDate());
					incBook.setAmount(item.getAmount());
					incBook.setBy(item.getReceiveBy());
					incBook.setPay_receive("RECEIVE");
					incBook.setRemark(item.getRemark());
					dayBook.add(incBook);
				});
			}
			if(expenseRepo.getAllExpenseByUsername(username).isPresent()) {
				expenseBook=expenseRepo.getAllExpenseByUsername(username).get();
				expenseBook.forEach((Expense item)->{
					DayBook expBook=new DayBook();
					expBook.setAcountName(item.getAccountName());
					expBook.setDate(item.getDate());
					expBook.setAmount(item.getAmount());
					expBook.setPay_receive("PAYED");
					expBook.setBy(item.getPayedBy());
					expBook.setRemark(item.getRemark());
					dayBook.add(expBook);
				});
			}
		}catch(Exception e) {
			return new ResponseEntity("Error in fetching details try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(dayBook,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getDayBookBetweenDates(String username,Date fromDate,Date toDate){
		ArrayList<DayBook> dayBook=new ArrayList<>();
		try {
			ArrayList<Income> incomeBook=null;
			ArrayList<Expense> expenseBook=null;
			if(incomeRepo.getAllIncomeByUsernameBetweenDates(username, fromDate, toDate).isPresent()) {
				incomeBook=incomeRepo.getAllIncomeByUsernameBetweenDates(username, fromDate, toDate).get();
				incomeBook.forEach((Income item)->{
					DayBook incBook=new DayBook();
					incBook.setAcountName(item.getAccountName());
					incBook.setDate(item.getDate());
					incBook.setAmount(item.getAmount());
					incBook.setBy(item.getReceiveBy());
					incBook.setPay_receive("RECEIVE");
					incBook.setRemark(item.getRemark());
					dayBook.add(incBook);
				});
			}
			if(expenseRepo.getAllExpenseByUsernameBetweenDates(username, fromDate, toDate).isPresent()) {
				expenseBook=expenseRepo.getAllExpenseByUsernameBetweenDates(username, fromDate, toDate).get();
				expenseBook.forEach((Expense item)->{
					DayBook expBook=new DayBook();
					expBook.setAcountName(item.getAccountName());
					expBook.setDate(item.getDate());
					expBook.setAmount(item.getAmount());
					expBook.setPay_receive("PAYED");
					expBook.setBy(item.getPayedBy());
					expBook.setRemark(item.getRemark());
					dayBook.add(expBook);
				});
			}
		}catch(Exception e) {
			return new ResponseEntity("Error in fetching details try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(dayBook,HttpStatus.OK);
	}

	public ResponseEntity<?> getAllBalanceSheet(String username){
		ArrayList<BalanceSheet> balanceSheet=new ArrayList<>();
		ArrayList<Income> incomeBook=null;
		ArrayList<Expense> expenseBook=null;
		try{
			if(incomeRepo.getAllIncomeByUsername(username).isPresent()) {
				incomeBook=incomeRepo.getAllIncomeByUsername(username).get();
				incomeBook.forEach((Income item)->{
					BalanceSheet balSheet=new BalanceSheet();
					balSheet.setAcountName(item.getAccountName());
					balSheet.setAmount(item.getAmount());
					balSheet.setPay_receive("RECEIVE");
					balanceSheet.add(balSheet);
				});
			}
			if(expenseRepo.getAllExpenseByUsername(username).isPresent()) {
				expenseBook=expenseRepo.getAllExpenseByUsername(username).get();
				expenseBook.forEach((Expense item)->{
					BalanceSheet balSheet=new BalanceSheet();
					balSheet.setPay_receive("PAY");
					balSheet.setAcountName(item.getAccountName());
					balSheet.setAmount(item.getAmount());
					balanceSheet.add(balSheet);
				});
			}
		}catch(Exception e) {
			return new ResponseEntity("Error in fetching details try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(balanceSheet,HttpStatus.OK);
	}
	
	public ResponseEntity<?> getBalanceSheetBetweenDates(String username,Date fromDate,Date dateTo){
		ArrayList<BalanceSheet> balanceSheet=new ArrayList<>();
		ArrayList<Income> incomeBook=null;
		ArrayList<Expense> expenseBook=null;
		try{
			if(incomeRepo.getAllIncomeByUsernameBetweenDates(username, fromDate, dateTo).isPresent()) {
				incomeBook=incomeRepo.getAllIncomeByUsernameBetweenDates(username, fromDate, dateTo).get();
				incomeBook.forEach((Income item)->{
					BalanceSheet balSheet=new BalanceSheet();
					balSheet.setPay_receive("RECEIVE");		
					balSheet.setAcountName(item.getAccountName());
					balSheet.setAmount(item.getAmount());
					balanceSheet.add(balSheet);
				});
			}
			if(expenseRepo.getAllExpenseByUsernameBetweenDates(username,fromDate,dateTo).isPresent()) {
				expenseBook=expenseRepo.getAllExpenseByUsernameBetweenDates(username, fromDate, dateTo).get();
				expenseBook.forEach((Expense item)->{
					BalanceSheet balSheet=new BalanceSheet();
					balSheet.setPay_receive("PAY");
					balSheet.setAcountName(item.getAccountName());
					balSheet.setAmount(item.getAmount());
					balanceSheet.add(balSheet);
				});
			}
		}catch(Exception e) {
			return new ResponseEntity("Error in fetching details try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(balanceSheet,HttpStatus.OK);
	
	}
	
}
