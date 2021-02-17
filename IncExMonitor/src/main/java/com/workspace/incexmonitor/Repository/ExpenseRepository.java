package com.workspace.incexmonitor.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.workspace.incexmonitor.Entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	@Query(value="SELECT * FROM Expense WHERE expenseCategoryId=:id", nativeQuery=true)
	public Optional<ArrayList<Expense>> getExpenseByExpenseCategory(@Param("id") Long id);
	
	@Query(value="SELECT * from Expense WHERE _date BETWEEN :start and :end", nativeQuery = true)
	public Optional<ArrayList<Expense>> getExpenseBetweenDates(@Param("start") Date start,@Param("end") Date end);
	
	public Optional<ArrayList<Expense>> getAllExpenseByUsername(String username);

	public Expense getExpenseByExpenseId(Long id);

	@Query(value="SELECT * from Expense WHERE (username=:username AND (_date BETWEEN :fromDate AND :toDate))",nativeQuery = true)
	public Optional<ArrayList<Expense>> getAllExpenseByUsernameBetweenDates(@Param("username") String username, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

	@Query(value="SELECT * from Expense WHERE username=:username AND payed_by='b'",nativeQuery = true)
	public Optional<ArrayList<Expense>> getExpenseByUsernameThroughBank(@Param("username") String username);

	@Query(value="SELECT * from Expense WHERE username=:username AND payed_by='c'", nativeQuery = true)
	public Optional<ArrayList<Expense>> getExpenseByUsernameThroughCash(@Param("username") String username);

	@Query(value="SELECT * from Expense WHERE (username=:username AND (_date BETWEEN :fromDate AND :toDate) AND payed_by='b')",nativeQuery = true)
	public Optional<ArrayList<Expense>> getAllExpenseByUsernameBetweenDatesThroughBank(@Param("username") String username, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

	@Query(value="SELECT * from Expense WHERE (username=:username AND (_date BETWEEN :fromDate AND :toDate) AND payed_by='c')",nativeQuery = true)
	public Optional<ArrayList<Expense>> getAllExpenseByUsernameBetweenDatesThroughCash(@Param("username") String username, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

	@Query(value="SELECT * from Expense WHERE username=:username", nativeQuery = true)
	public Optional<ArrayList<Expense>> getAccountNameAndAmountOfUser(@Param("username") String username);
	
	@Query(value="SELECT * from Expense WHERE username=:username AND (_date BETWEEN :fromDate AND :toDate)", nativeQuery = true)
	public Optional<ArrayList<Expense>> getAccountNameAndAmountOfUserBetweenDates(@Param("username") String username, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}
