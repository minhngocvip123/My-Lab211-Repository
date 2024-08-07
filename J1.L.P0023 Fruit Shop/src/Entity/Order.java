/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class Order {
    private int id;
    private String customerName;
    private double totalPrice;
    private Map<Integer, OrderDetail> orderDetails;

    public Order() {
    }

    public Order(int id, String customerName, double totalPrice, Map<Integer, OrderDetail> orderDetails) {
        this.id = id;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.orderDetails = orderDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Integer, OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Map<Integer, OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    //print out order
    @Override
    public String toString() {
        String s = "Customer: " + customerName + "\n";
        s+= String.format("%-25s%-15s%-15s%-15s\n", "Product", "Quantity", "Price", "Amount");
        int i = 0;
        for (Map.Entry<Integer, OrderDetail> entry : orderDetails.entrySet()){
            s+= String.format(++i + "" + "%-25s%-15d%-15.1f%-15.1f\n",
                    entry.getValue().getFruitName(),
                    entry.getValue().getQuantity(),
                    entry.getValue().getPrice(),
                    entry.getValue().getQuantity() * entry.getValue().getPrice());
        }
        s += String.format("Total: %.2f$\n", totalPrice);
        return s;
    }
}