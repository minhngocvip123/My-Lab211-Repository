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

            int choice = validation.getInt("Enter your choice: ", 1, 4);

            switch (choice) {
                case 1:
                    //add task
                    manager.addTask();
                    break;
                case 2:
                    //delete task
                    manager.deleteTask();
                    break;
                case 3:
                    //display tasks
                    manager.getDataTasks();
                    break;
                //exit
                case 4:
                    return;
            }
        }
    }
    
}
