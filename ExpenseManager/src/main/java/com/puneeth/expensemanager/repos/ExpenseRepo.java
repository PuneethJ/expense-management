package com.puneeth.expensemanager.repos;


import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.puneeth.expensemanager.entities.Expense;


@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long>{

    Page<Expense> findByCategoryContaining(String category,Pageable page);
    Page<Expense> findByNameContaining(String keyword,Pageable page);
    Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);

}
