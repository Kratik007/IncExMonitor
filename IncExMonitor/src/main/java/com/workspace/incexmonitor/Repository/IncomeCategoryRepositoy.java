package com.workspace.incexmonitor.Repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workspace.incexmonitor.Entities.IncomeCategory;

@Repository
public interface IncomeCategoryRepositoy extends JpaRepository<IncomeCategory, Long> {
	public Optional<IncomeCategory> getIncomeCategoryByIncomeCategoryId(Long id);
	public ArrayList<IncomeCategory> getAllIncomeCategoryByUsername(String username);
}
