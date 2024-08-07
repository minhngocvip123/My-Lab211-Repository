/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Entity.Fruit;
import Entity.OrderDetail;
import Validation.Validation;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author ADMIN
 */
public class Manager {

    ArrayList<Fruit> listFruit = new ArrayList<>();
    Hashtable<String, ArrayList<Fruit>> orders = new Hashtable<>();
    Validation validation = new Validation();

    //method to display menu
    public void displayMenu() {
        System.out.println("");
        System.out.println("FRUIT SHOP SYSTEM");
        System.out.println("1. Create Fruit");
        System.out.println("2. View Orders");
        System.out.println("3. Shopping(for buyers)");
        System.out.println("4. Exit");
    }

    //constructor, pre-add fruits into list for testing purposes
    public Manager() {
        listFruit.add(new Fruit(1, "Banana", 3.6, 4, "Guatemala"));
        listFruit.add(new Fruit(2, "Apple", 2, 10, "USA"));
        listFruit.add(new Fruit(3, "Watermelon", 6.5, 3, "Japan"));
        listFruit.add(new Fruit(4, "Grapefruit", 4, 12, "Vietnam"));
        listFruit.add(new Fruit(5, "Kiwi", 8, 0, "New Zealand"));
    }

    public void createFruit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void viewOrders() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void shop() {
        //list to save all orders
        ArrayList<Fruit> listOrder = new ArrayList<>();
        while (true) {
            //display fruit catalogues
            displayListOfFruit();
            Fruit selectedFruit;
            //get selection input from user. Loop until id input is valid
            while (true) {
                int id = validation.getInt("Enter your fruit choice: ");
                selectedFruit = getFruitById(id);
                if (selectedFruit == null) {
                    System.err.println("Item does not exist. Please choose another fruit on the list!");
                } else if (selectedFruit.getQuantity() < 1) {
                    System.err.println("This fruit exists but no stock left. Please choose another fruit on the list!");
                } else {
                    break;
                }
            }
            System.out.println("You selected: " + selectedFruit.getName());
            int quantity = validation.getInt("Please input quantity: ", 1, selectedFruit.getQuantity());
            //deduct the quantity of stock of the fruit
            selectedFruit.setQuantity(selectedFruit.getQuantity() - quantity);
            //check if fruit already in order list
            Fruit fruitInOrder = checkFruitInOrder(listOrder, selectedFruit.getId());
            //if fruit already in order list, increase its quantity
            if (fruitInOrder != null) {
                fruitInOrder.setQuantity(fruitInOrder.getQuantity() + quantity);
            } else {
                //if not, add fruit into order list
                listOrder.add(new Fruit(selectedFruit.getId(), selectedFruit.getName(), selectedFruit.getPrice(), quantity, selectedFruit.getOrigin()));
            }
            //check if user wants to proceed to checkout
            if (validation.checkInputYN("Do you want to order now (Y/N)? ")) {
                //exit ordering process by breaking the loop
                break;
            }
        }
        //display list order
        displayListOrder(listOrder);
        String customerName = validation.getString("Input your name: ");
        //map customer name to the order
        orders.put(customerName, listOrder);
    }

    public void displayListOfFruit() {
        if (listFruit.isEmpty()) {
            System.err.println("No fruits available!");
        } else {
            System.out.printf("%-10s%-20s%-15s%-15s\n", "Item", "Fruit Name", "Origin", "Price");
            for (Fruit fruit : listFruit) {
                //do not print out fruits with 0 quantity
                if (fruit.getQuantity() > 0) {
                    System.out.printf("%-10s%-20s%-15s%-15s\n", fruit.getId(),
                            fruit.getName(), fruit.getOrigin(), fruit.getPrice() + "$");
                }
            }
        }
    }

    public Fruit getFruitById(int id) {
        for (Fruit fruit : listFruit) {
            if (fruit.getId() == id) {
                return fruit;
            }
        }
        return null;
    }

    public Fruit checkFruitInOrder(ArrayList<Fruit> listOrder, int id) {
        for (Fruit fruit : listOrder) {
            if (fruit.getId() == id) {
                return fruit;
            }
        }
        return null;
    }

    private void displayListOrder(ArrayList<Fruit> listOrder) {
        double total = 0;
        System.out.printf("%-20s%-15s%-15s%-10s\n", "Product", "Quantity", "Price", "Amount");
        for (Fruit fruit : listOrder) {
            System.out.printf("%-20s%-15s%-15s%-10s\n", fruit.getName(),
                    fruit.getQuantity(), fruit.getPrice(),
                    fruit.getPrice() * fruit.getQuantity() + "$");
            //add total price of all orders
            total += fruit.getPrice() * fruit.getQuantity();
        }
        System.out.println("Total: " + total + "$");
    }
}
