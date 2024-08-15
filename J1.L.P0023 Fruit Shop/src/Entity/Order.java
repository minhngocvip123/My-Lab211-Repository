/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Order {
    private String customerName;
    private ArrayList<Fruit> listOrder;

    public Order() {
    }

    public Order(String customerName, ArrayList<Fruit> listOrder) {
        this.customerName = customerName;
        this.listOrder = listOrder;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<Fruit> getListOrder() {
        return listOrder;
    }

    public void setListOrder(ArrayList<Fruit> listOrder) {
        this.listOrder = listOrder;
    }

}
