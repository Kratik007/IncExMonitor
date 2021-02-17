package com.workspace.incexmonitor.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.workspace.incexmonitor.Entities.IncomeCategory;
import com.workspace.incexmonitor.Repository.IncomeCategoryRepositoy;

@Service
public class IncomeCategoryService {
	@Autowired
	private IncomeCategoryRepositoy incomeCategRepo;
	
	public ResponseEntity<?> createIncomeCategory(IncomeCategory incomeCateg){
		try{
			incomeCategRepo.save(incomeCateg);
		}catch(Exception e) {
			return new ResponseEntity(e.getMessage()+"Error in creating income category",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity("Income Category Saved Successfully",HttpStatus.OK);
	}
	
	public Optional<IncomeCategory> getIncomeCategoryById(Long id){
		Optional<IncomeCategory> incomeCateg=incomeCategRepo.getIncomeCategoryByIncomeCategoryId(id);
		return incomeCateg;
	}
	
	public ResponseEntity<?> getAllIncomeCategoryOfUser(String username){
		ArrayList<IncomeCategory> incomeCategories;
		try {
			incomeCategories=incomeCategRepo.getAllIncomeCategoryByUsername(username);
		}catch(Exception e) {
			return new ResponseEntity("Error in fetching details",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(incomeCategories,HttpStatus.OK);
	}
	
	public ResponseEntity<?> updateIncomeCategory(IncomeCategory incomeCateg){
		try {
			incomeCategRepo.save(incomeCateg);
		}catch(Exception e) {
			return new ResponseEntity("Error in updating category",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity("Category updated successfully",HttpStatus.OK);
	}
	
	public ResponseEntity<?> deleteIncomeCategory(Long id){
		try {
		IncomeCategory incomeCateg=incomeCategRepo.getIncomeCategoryByIncomeCategoryId(id).get();
		incomeCategRepo.delete(incomeCateg);
		}catch(Exception e) {
			return new ResponseEntity(e.getMessage()+"Error in deleting Category try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity("Income Category Deleted successfully",HttpStatus.OK);		
	}
	
}
