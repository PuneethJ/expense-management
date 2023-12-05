package com.puneeth.expensemanager.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.puneeth.expensemanager.entities.Expense;
import com.puneeth.expensemanager.exceptions.ResourceNotFoundException;
import com.puneeth.expensemanager.repos.ExpenseRepo;

@Service
public class ExpenseServices {
	
	@Autowired
	public ExpenseRepo expenseRepo;

	@Autowired
	public UserService userService;

	public List<Expense> getAllExpenses() {
		return expenseRepo.findAll();
	}

	public Expense getExpenseById(Long id) {
		Optional<Expense> expense=expenseRepo.findById(id);
		if(expense.isPresent())
		return expense.get();
		throw new ResourceNotFoundException("expense not found with id: "+id);
	}

	public String deleteById(Long id) {
		Optional<Expense> expense=expenseRepo.findById(id);
		if(expense.isPresent())
		{
			expenseRepo.deleteById(id);
			return "Expense deleted";
		}
		return "Id not found";
	}

	public Expense createExpense(Expense expense) {
		expense.setUser(userService.getLoggedInUser());
		Expense expense1= expenseRepo.save(expense);
		return expense1;
	}

	public Expense updateExpense(Expense expense, Long id) {
		Expense existingExpense=getExpenseById(id);
		existingExpense.setName(expense.getName()!=null?expense.getName():existingExpense.getName());
		existingExpense.setDescription(expense.getDescription()!=null?expense.getDescription():existingExpense.getDescription());
		existingExpense.setAmount(expense.getAmount()!=null?expense.getAmount():existingExpense.getAmount());
		existingExpense.setDate(expense.getDate()!=null?expense.getDate():existingExpense.getDate());
		Expense newRecord= expenseRepo.save(existingExpense);
		return newRecord;
	}

	public List<Expense> readByCategory(String category,Pageable page){
		return expenseRepo.findByCategoryContaining(category, page).toList();
	}

	public List<Expense> readByName(String keyword, Pageable page){
		return expenseRepo.findByNameContaining(keyword, page).toList();
	}

	public List<Expense> readByDate(Date startDate, Date endDate, Pageable page){
		if(startDate==null){
			startDate= new Date(0);
		}
		if(endDate==null){
			endDate=new Date(System.currentTimeMillis());
		}
		return expenseRepo.findByDateBetween(startDate, endDate, page).toList();
	}

}
