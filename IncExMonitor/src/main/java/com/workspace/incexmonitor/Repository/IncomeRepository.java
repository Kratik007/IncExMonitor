package com.workspace.incexmonitor.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.workspace.incexmonitor.Entities.Income;
@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
	
	@Query(value="SELECT * FROM Income WHERE incomeCategoryId=:id", nativeQuery=true)
	public Optional<ArrayList<Income>> getIncomeByIncomeCategory(@Param("id") Long id);
	
	@Query(value="SELECT * from Income WHERE _date BETWEEN :start and :end", nativeQuery = true)
	public Optional<ArrayList<Income>> getIncomeBetweenDates(@Param("start") Date start,@Param("end") Date end);
	
	public Optional<ArrayList<Income>> getAllIncomeByUsername(String username);

	public Income getIncomeByIncomeId(Long id);
	
	@Query(value="SELECT * from Income WHERE (username=:username AND (_date BETWEEN :fromDate AND :toDate))",nativeQuery = true)
	public Optional<ArrayList<Income>> getAllIncomeByUsernameBetweenDates(@Param("username") String username, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

	@Query(value="SELECT * from Income WHERE username=:username AND receive_by='b'",nativeQuery = true)
	public Optional<ArrayList<Income>> getIncomeByUsernameThroughBank(@Param("username") String username);

	@Query(value="SELECT * from Income WHERE income.username=:username AND income.receive_by='c'",nativeQuery=true)
	public Optional<ArrayList<Income>> getIncomeByUsernameThroughCash(@Param("username") String username);
	
	@Query(value="SELECT * from Income WHERE (username=:username AND (_date BETWEEN :fromDate AND :toDate) AND receive_by='b')",nativeQuery = true)
	public Optional<ArrayList<Income>> getAllIncomeByUsernameBetweenDatesThroughBank(@Param("username") String username, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

	@Query(value="SELECT * from Income WHERE (username=:username AND (_date BETWEEN :fromDate AND :toDate) AND receive_by='c')",nativeQuery = true)
	public Optional<ArrayList<Income>> getAllIncomeByUsernameBetweenDatesThroughCash(@Param("username") String username, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
	
	@Query(value="SELECT * from Income WHERE username=:username",nativeQuery = true)
	public Optional<ArrayList<Income>> getAccountNameAndAmountOfUser(@Param("username") String username);
	
	@Query(value="SELECT * from Income WHERE username=:username AND (_date BETWEEN :fromDate AND :toDate)", nativeQuery = true)
	public Optional<ArrayList<Income>> getAccountNameAndAmountOfUserBetweenDates(@Param("username") String username, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
