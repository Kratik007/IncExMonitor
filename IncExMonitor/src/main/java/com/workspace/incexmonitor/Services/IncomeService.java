package com.workspace.incexmonitor.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.workspace.incexmonitor.Dto.IncomeDTO;
import com.workspace.incexmonitor.Entities.Income;
import com.workspace.incexmonitor.Entities.IncomeCategory;
import com.workspace.incexmonitor.Repository.IncomeRepository;

@Service
public class IncomeService {
	@Autowired
	private IncomeRepository incomeRepo;
	
	@Autowired
	private IncomeCategoryService incomeCategService;
	
	public ResponseEntity<?> getIncomeByCategoryId(Long categoryId){
		Optional<ArrayList<Income>> incomes;
		try{
			 incomes= incomeRepo.getIncomeByIncomeCategory(categoryId);
		}catch(Exception e) {
			return new ResponseEntity("Failed to get Incomes try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(incomes.get()==null) {
			return new ResponseEntity("No income found in this category",HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity(incomes.get(),HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> getIncomeBetweenDates(Date fromDate,Date toDate){
		Optional<ArrayList<Income>> incomes;
		try {
			incomes=incomeRepo.getIncomeBetweenDates(fromDate, toDate);
		}catch(Exception e) {
			return new ResponseEntity("Failed to get Incomes",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(incomes.get()==null) {
			return new ResponseEntity("No income found in this between these dates",HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity(incomes.get(),HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> getIncomeByUsername(@PathVariable String username){
		Optional<ArrayList<Income>> incomes;
		try {
			incomes=incomeRepo.getAllIncomeByUsername(username);
		}catch(Exception e) {
			return new ResponseEntity("Failed to get Incomes from username",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(incomes.get()==null) {
			return new ResponseEntity("NO income found for your id",HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity(incomes.get(),HttpStatus.OK);
		}
	}
	
	public Income getIncomeFromIncomeId(Long id) {
		Income income;
		income=incomeRepo.getIncomeByIncomeId(id);
		return income;	
	}
	
	public ResponseEntity<?> createIncome(IncomeDTO incomeDto){
		Income income;
		try {
		IncomeCategory incomeCateg=incomeCategService.getIncomeCategoryById(incomeDto.getIncomeCategoryId()).get();
		income=incomeDto.getIncome();
		income.setIncomeCategory(incomeCateg);
		incomeRepo.save(income);
		}catch(Exception e) {
			return new ResponseEntity(e.getMessage()+"Error in creating Income try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(income,HttpStatus.OK);
	}
	
	public ResponseEntity<?> deleteIncome(Long id){
		try{
			incomeRepo.delete(getIncomeFromIncomeId(id));
		}catch(Exception e) {
			return new ResponseEntity("Error in deleting income try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity("Income Deleted successfully",HttpStatus.OK);
	}
	
	public ResponseEntity<?> getIncomeBalanceSheet(String username){
		ArrayList<?> incomeSheet=null;
		try {
			if(incomeRepo.getAccountNameAndAmountOfUser(username).isPresent())
			incomeSheet=incomeRepo.getAccountNameAndAmountOfUser(username).get();
		}catch(Exception e) {
			return new ResponseEntity("Error in getting details",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(incomeSheet,HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> getIncomeBalanceSheetBetweenDates(String username, Date fromDate, Date toDate){
		ArrayList<?> incomeSheet=null;
		try {
			if(incomeRepo.getAccountNameAndAmountOfUserBetweenDates(username, fromDate, toDate).isPresent()) {
				incomeSheet=incomeRepo.getAccountNameAndAmountOfUserBetweenDates(username, fromDate, toDate).get();
			}
		}catch(Exception e) {
			return new ResponseEntity("Error in getting details",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(incomeSheet,HttpStatus.OK);
	}
}
