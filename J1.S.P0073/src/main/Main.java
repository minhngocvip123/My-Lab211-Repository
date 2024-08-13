package main;

import manager.Manager;
import validation.Validation;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Manager manager = new Manager();
        Validation validation = new Validation();
        
        //display menu
        while (true) {
            manager.displayMenu();

            int choice = validation.getInt("Your choice: ", 1, 4);

            switch (choice) {
                case 1:
                    //add an expense
                    manager.addExpense();
                    break;
                case 2:
                    //display all expenses
                    manager.displayAllExpense();
                    break;
                case 3:
                    //delete an expense
                    manager.removeExpense();
                    break;
                //exit
                case 4:
                    return;
            }
        }
    }
    
}
