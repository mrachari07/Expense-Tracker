package com.Tracker.dao;

import com.Tracker.model.Expense;

public interface ExpenseDao {

	    boolean addExpense(Expense expense);

	    boolean deleteExpenseById(int expenseId);

	    Expense getExpenseById(int expenseId);

	    Expense[] getAllExpenses();
	}
