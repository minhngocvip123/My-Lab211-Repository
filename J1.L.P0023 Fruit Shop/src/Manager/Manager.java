/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Entity.Fruit;
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
        //list to display fruits created
        ArrayList<Fruit> newFruits = new ArrayList<>();
        while (true) {
            //testing
            displayListOfFruit(listFruit);
            
            int id = validation.getInt("Enter Id: ");
            String name;
            //check id
            if (validation.checkIdExist(listFruit, id)) {
                //if id is taken, update quantity of fruit instead
                System.out.println("Fruit with this Id(" + id + ") already exist: " + getFruitById(id).getName());
                System.out.println("Update this fruit quantity instead.");
                //quantity cannot be negative number
                int quantity = validation.getInt("Enter quantity: ", 0, Integer.MAX_VALUE);
                //increase the quantity of stock of the fruit
                addQuantity(id, quantity, listFruit);
                Fruit selectedFruit = getFruitById(id);
                //add updated fruit into created fruit list
                if (!checkFruitExist(selectedFruit.getName(), newFruits)) {
                    //if fruit is not already in add list, add fruit into add list
                    newFruits.add(new Fruit(selectedFruit.getId(), selectedFruit.getName(), 
                            selectedFruit.getPrice(), quantity, selectedFruit.getOrigin()));
                } else {
                    //if already in the list, increase quantity
                    addQuantity(id, quantity, newFruits);
                }
                System.out.println("Fruit quantity added successfully!");
            //if id is not taken, create new fruit
            } else {
                //check fruit name
                while (true) {
                    name = validation.getString("Enter fruit name: ");
                    if (checkFruitExist(name, listFruit)) {
                        System.err.println("This fruit is already created. Please enter a different name.");
                    } else {
                        break;
                    }
                }
                double price = validation.getDouble("Enter price: ");
                int quantity = validation.getInt("Enter quantity: ");
                String origin = validation.getString("Enter origin: ");
                newFruits.add(new Fruit(id, name, price, quantity, origin));
                listFruit.add(new Fruit(id, name, price, quantity, origin));
                System.out.println("Fruit created successfully!");
            }

            //if user want to stop adding, break the loop and return to menu
            if (!validation.checkInputYN("Do you want to continue (Y/N)?")) {
                System.out.println("List of fruits created/restocked: ");
                displayListOfFruit(newFruits);
                break;
            }
        }
    }

    /**
     * Method to handle displaying orders
     */
    public void viewOrders() {
        //check if there are no orders
        if(orders.isEmpty()){
            System.err.println("No orders!");
            return;
        }
        //iterate through each entry in the hashtable
        //assign current key as name and iterate through each key with 
        //keySet() method to find the key-value pair
        for(String name : orders.keySet()){
            System.out.println("Customer: " + name);
            ArrayList<Fruit> listOrder = orders.get(name);
            displayListOrder(listOrder);
        }
        
    }

    /**
     * Method to handle shopping process
     */
    public void shop() {
        //list to save all orders
        ArrayList<Fruit> listOrder = new ArrayList<>();
        while (true) {
            //display fruit catalogues
            displayListOfFruit(listFruit);
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
        //map customer name to the order and add to the hashtable
        orders.put(customerName, listOrder);
    }
    
    //method to display all fruits in the list
    public void displayListOfFruit(ArrayList<Fruit> listFruit) {
        if (listFruit.isEmpty()) {
            System.err.println("No fruits available!");
        } else {
            System.out.printf("%-10s%-20s%-15s%-15s%-15s\n", "Item", "Fruit Name", "Origin", "Quantity", "Price");
            for (Fruit fruit : listFruit) {
                //do not print out fruits with 0 quantity
                if (fruit.getQuantity() > 0) {
                    System.out.printf("%-10s%-20s%-15s%-15s%-15s\n", 
                            fruit.getId(),
                            fruit.getName(), 
                            fruit.getOrigin(), 
                            fruit.getQuantity(), 
                            fruit.getPrice() + "$");
                }
            }
        }
    }
    
    //method to find fruit by id
    public Fruit getFruitById(int id) {
        for (Fruit fruit : listFruit) {
            if (fruit.getId() == id) {
                return fruit;
            }
        }
        return null;
    }
    
    //method to check if fruit is already in the order list
    public Fruit checkFruitInOrder(ArrayList<Fruit> listOrder, int id) {
        for (Fruit fruit : listOrder) {
            if (fruit.getId() == id) {
                return fruit;
            }
        }
        return null;
    }
    
    //method to display order
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

    private boolean checkFruitExist(String name, ArrayList<Fruit> listFruit) {
        for (Fruit fruit : listFruit) {
            if (fruit.getName() == name) {
                return true;
            }
        }
        return false;
    }

    //method to add quantity to a fruit found by Id
    private void addQuantity(int id, int quantity, ArrayList<Fruit> listFruit) {
        for (Fruit fruit : listFruit) {
            if (fruit.getId() == id) {
                fruit.setQuantity(fruit.getQuantity() + quantity);
            }
        }
    }

}
