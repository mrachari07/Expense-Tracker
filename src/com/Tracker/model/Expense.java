package com.Tracker.model;

import java.time.LocalDate;

public class Expense {
	
	    private int expenseId;
	    private String title;
	    private double amount;
	    private ExpenseCategory category;
	    private LocalDate expenseDate;
	    private String notes;

	    public Expense(int expenseId, String title, double amount,
	                   ExpenseCategory category, LocalDate expenseDate, String notes) {
	        this.expenseId = expenseId;
	        this.title = title;
	        this.amount = amount;
	        this.category = category;
	        this.expenseDate = expenseDate;
	        this.notes = notes;
	    }

	    public int getExpenseId() {
	        return expenseId;
	    }

	    public void setExpenseId(int expenseId) {
	        this.expenseId = expenseId;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public void setAmount(double amount) {
	        this.amount = amount;
	    }

	    public ExpenseCategory getCategory() {
	        return category;
	    }

	    public void setCategory(ExpenseCategory category) {
	        this.category = category;
	    }

	    public LocalDate getExpenseDate() {
	        return expenseDate;
	    }

	    public void setExpenseDate(LocalDate expenseDate) {
	        this.expenseDate = expenseDate;
	    }

	    public String getNotes() {
	        return notes;
	    }

	    public void setNotes(String notes) {
	        this.notes = notes;
	    }

	    @Override
	    public String toString() {
	        return "Expense{" +
	                "expenseId=" + expenseId +
	                ", title='" + title + '\'' +
	                ", amount=" + amount +
	                ", category=" + category +
	                ", expenseDate=" + expenseDate +
	                ", notes='" + notes + '\'' +
	                '}';
	    }
	}
