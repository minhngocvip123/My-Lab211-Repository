/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import Entity.Course;
import Entity.Student;
import Manager.Manager;
import Validation.Validation;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Student> listStudent = new ArrayList<Student>();
        //pre-add students to help with the program demo
        listStudent.add(new Student(1, "Nguyen Van A", Course.Java, 1));
        listStudent.add(new Student(1, "Nguyen Van A", Course.CCPlus, 1));
        listStudent.add(new Student(1, "Nguyen Van A", Course.DotNet, 3));
        listStudent.add(new Student(1, "Nguyen Van A", Course.Java, 2));
        listStudent.add(new Student(2, "Nguyen Van B", Course.CCPlus, 2));
        listStudent.add(new Student(2, "Nguyen Van B", Course.CCPlus, 1));
        listStudent.add(new Student(2, "Nguyen Van B", Course.CCPlus, 3));
        listStudent.add(new Student(3, "Nguyen Van C", Course.DotNet, 2));
        listStudent.add(new Student(3, "Nguyen Van C", Course.Java, 2));
        
        int count = listStudent.size();
        //print out initial list size for testing purpose
//        System.out.println(count);
        Manager manager = new Manager();
        Validation validation = new Validation();

        //display menu

        //loop until user want to exit program
        while (true) {
            //Show menu option
            manager.menuDisplay();
            int choice = validation.getInt("Enter your choice: ", 1, 5);
            switch (choice) {
                case 1:
                    manager.createStudent(count, listStudent);
                    break;
                case 2:
                    manager.findAndSort(listStudent);
                    break;
                case 3:
                    manager.updateOrDelete(count, listStudent);
                    break;
                case 4:
                    manager.report(listStudent);
                    break;
                case 5:
                    return;
            }

        }
        
    }

}
