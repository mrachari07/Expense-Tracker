package com.Tracker.daoImplementation;

import com.Tracker.dao.ExpenseDao;
import com.Tracker.model.Expense;

public class ExpenseDaoImpl implements ExpenseDao {

	    private Expense[] expenses = new Expense[100];
	    private int count = 0;

	    @Override
	    public boolean addExpense(Expense expense) {
	        if (expense == null || count >= expenses.length) {
	            return false;
	        }

	        expenses[count] = expense;
	        count++;
	        return true;
	    }

	    @Override
	    public boolean deleteExpenseById(int expenseId) {
	        for (int index = 0; index < count; index++) {
	            if (expenses[index].getExpenseId() == expenseId) {

	                for (int nextIndex = index; nextIndex < count - 1; nextIndex++) {
	                    expenses[nextIndex] = expenses[nextIndex + 1];
	                }

	                expenses[count - 1] = null;
	                count--;
	                return true;
	            }
	        }

	        return false;
	    }

	    @Override
	    public Expense getExpenseById(int expenseId) {
	        for (int index = 0; index < count; index++) {
	            if (expenses[index].getExpenseId() == expenseId) {
	                return expenses[index];
	            }
	        }

	        return null;
	    }

	    @Override
	    public Expense[] getAllExpenses() {
	        Expense[] savedExpenses = new Expense[count];

	        for (int index = 0; index < count; index++) {
	            savedExpenses[index] = expenses[index];
	        }

	        return savedExpenses;
	    }
	}
