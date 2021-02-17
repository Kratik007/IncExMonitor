package com.workspace.incexmonitor.Repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workspace.incexmonitor.Entities.ExpensesCategory;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpensesCategory, Long> {
	public ArrayList<ExpensesCategory> getAllExpenseCategoryByUsername(String username);
	public Optional<ExpensesCategory> getExpenseCategoryByExpenseCategoryId(Long id);
}
