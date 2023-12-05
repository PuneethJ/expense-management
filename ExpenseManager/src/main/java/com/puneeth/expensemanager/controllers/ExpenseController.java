package com.puneeth.expensemanager.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.puneeth.expensemanager.entities.Expense;
import com.puneeth.expensemanager.services.ExpenseServices;

import jakarta.validation.Valid;




@RestController
public class ExpenseController {
	
	@Autowired
	public ExpenseServices expenseServices;

	@PostMapping("/expenses")
	@ResponseStatus(HttpStatus.CREATED)
	public Expense createExpense(@Valid @RequestBody Expense expense){
		return expenseServices.createExpense(expense);
	}

	@GetMapping("/expenses")
	public List<Expense> getAllExpenses()
	{
		return expenseServices.getAllExpenses();
	}

	@GetMapping("/expenses/{id}")
	public Expense getExpenseById(@PathVariable("id") Long id){
		return expenseServices.getExpenseById(id);
	}

	@DeleteMapping("/expenses/{id}")
	public String deleteById(@PathVariable("id") Long id){
		return expenseServices.deleteById(id);
	}

	@PutMapping("/expenses/{id}")
	public Expense updateExpense(@RequestBody Expense expense, @PathVariable Long id){
			return expenseServices.updateExpense(expense,id);
		}

	@GetMapping("/expenses/category")
	public List<Expense> getByCategory(@RequestParam String category,Pageable page){
		return expenseServices.readByCategory(category,page);
	}

	@GetMapping("expenses/name")
	public List<Expense> getByName(@RequestParam String keyword, Pageable page){
		return expenseServices.readByName(keyword, page);
	}

	@GetMapping("expenses/date")
	public List<Expense> getByDate(@RequestParam(name="startDate",required = false) Date startDate,
		@RequestParam(name="endDate",required = false) Date endDate ,Pageable page){
			return expenseServices.readByDate(startDate, endDate, page);
		}
	
	
	

	
}
