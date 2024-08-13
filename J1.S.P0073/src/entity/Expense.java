/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ADMIN
 */
public class Expense {
    private static int lastId = 0; // Static variable to keep track of the last used id
    private final int id;
    private String date;
    private double amount;
    private String content;

    public Expense() {
        this.id = ++lastId; // Automatically increment the id
    }

    public Expense(String date, double amount, String content) {
        this.id = ++lastId; // Automatically increment the id
        this.date = date;
        this.amount = amount;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public void printExpense(){
        System.out.printf("%-10s%-20s%-15s%-20s\n", id, date, amount, content);
    }
}
