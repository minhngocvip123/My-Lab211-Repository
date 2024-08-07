
import Manager.Manager;
import Validation.Validation;

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
        // TODO code application logic here
        
        Manager manager = new Manager();
        Validation validation = new Validation();
        //display menu
        while (true) {
            manager.displayMenu();

            int choice = validation.getInt("Enter your choice: ", 1, 4);

            switch (choice) {
                case 1:
                    manager.createFruit();
                    break;
                case 2:
                    manager.viewOrders();
                    break;
                case 3:
                    manager.shop();
                    break;
                case 4:
                    return;
            }
        }
    }
    
}
