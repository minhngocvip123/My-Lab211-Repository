/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import entity.Expense;
import java.util.ArrayList;
import validation.Validation;
import static validation.Validation.DATE_PATTERN_IN;

/**
 *
 * @author ADMIN
 */
public class Manager {

    private final ArrayList<Expense> expenses = new ArrayList<>();
    private final Validation validation = new Validation();

    public Manager() {
        expenses.add(new Expense("12-Jan-2024", 300, "Uni Tuition"));
        expenses.add(new Expense("12-Jan-2024", 200, "Groceries"));
        expenses.add(new Expense("12-Jan-2024", 100, "Pay Debt"));
        expenses.add(new Expense("12-Jan-2024", 1000, "Donate to Streamer"));
    }

    public void displayMenu() {
        System.out.println("");
        System.out.println("=======Handy Expense program======");
        System.out.println("1. Add an expense");
        System.out.println("2. Display all expenses");
        System.out.println("3. Delete an expense");
        System.out.println("4. Quit");
    }

    public void addExpense() {
        System.out.println("-------------- Add an expense --------------");
        String date = validation.getDateString(
                "Enter Date: ",
                "Invalid input, input must be date with format dd-MM-yyyy!",
                DATE_PATTERN_IN,
                "Invalid date format, input must be date with format dd-MM-yyyy!");

        double amount = validation.getDouble("Enter Amount: ");
        String content = validation.getString("Enter Content: ");
        //add new expense in list
        expenses.add(new Expense(date, amount, content));
        System.out.println("Expense added successfully!");
    }

    public void displayAllExpense() {
        //check if list is empty
        if (expenses.isEmpty()) {
            System.err.println("No expenses available!");
            return;
        }
        double total = 0;
        //print put table header
        System.out.println("-------------- Display all expenses --------------");
        System.out.printf("%-10s%-20s%-15s%-20s\n", "ID", "Date", "Amount", "Content");
        //iterate through each element and print out details of each expense
        //calculate total with each iteration
        for (Expense expense : expenses) {
            total += expense.getAmount();
            expense.printExpense();
        }
        System.out.println("Total: " + total);
    }

    public void removeExpense() {
        System.out.println("-------------- Delete an expense --------------");
        int id;
        Expense expenseToDelete;
        id = validation.getInt("Enter ID: ");
        expenseToDelete = getExpenseById(id);
        if (expenseToDelete == null) {
            System.out.println("Delete an expense fail");
        }else{
            expenses.remove(expenseToDelete);
            System.out.println("Delete an expense successful!");
        }

    }

    private Expense getExpenseById(int id) {
        Expense expenseToFind = null;
        for (Expense expense : expenses) {
            if(expense.getId() == id){
                expenseToFind = expense;
            }
        }
        return expenseToFind;
    }

}
