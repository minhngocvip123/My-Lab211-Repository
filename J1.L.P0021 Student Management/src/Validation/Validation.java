/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validation;

import Entity.Course;
import Entity.Report;
import Entity.Student;
import exception.NumberNotInRangeException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Validation {

    Scanner scanner = new Scanner(System.in);

    //check user input number limit
    public int getInt(String msg, String errorMsg, int min, int max) {
        // Loop until get right format number
        while (true) {
            try {
                System.out.print(msg);
                int result = Integer.parseInt(scanner.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberNotInRangeException("Invalid input, number must be in range [" + min + ", " + max + "]");
                }
                return result;

            } catch (NumberNotInRangeException numberNotInRangeException) {
                System.out.println(numberNotInRangeException.getMessage());
            } catch (Exception ex) {
                System.out.println(errorMsg);
            }
        }
    }

    // Check user input and catch exception with default error message and input in range
    public int getInt(String msg, int min, int max) {
        return getInt(msg, "Invalid input, input must be an integer", min, max);
    }

    //default Integer input check
    public int getInt(String msg) {
        return getInt(msg, "Invalid input, input must be an integer", Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    //check user input string
    public String checkInputString(String msg) {
        //loop until user input correct
        while (true) {
            System.out.print(msg);
            String result = scanner.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Input cannot be empty!");
                System.out.printf("Enter again: ");
            } else {
                return result;
            }
        }
    }

    //check user input yes/no
    public boolean checkInputYN(String msg) {
        //loop until user input correct
        while (true) {
            String result = checkInputString(msg);
            //return true if user input y/Y
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            //return false if user input n/N
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

    //check if student Id already taken
    public boolean checkIdExist(int inputId, ArrayList<Student> listStudent) {
        for (Student student : listStudent) {
            if (student.getId() == inputId) {
                System.err.println("A student with this ID already exists. Automatically naming this student with the same name.");
                System.out.println("Student name: " + student.getName());
                return true;
            }
        }
        return false;
    }
    
    public boolean checkDuplicate(int id, String name ,int semester, Course course, ArrayList<Student> listStudent) {
        for (Student student : listStudent) {
            if (student.getId() == id
                    && student.getSemester() == semester
                    && student.getCourse() == course
                    && student.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;

    }
    
    //method to validate U/D input
    public boolean checkInputUpdateDelete(String msg) {
        while (true) {
            String result = checkInputString(msg);
            //return true if user input u/U (update)
            if (result.equalsIgnoreCase("U")) {
                return true;
            }
            //return false if user input d/D (delete)
            if (result.equalsIgnoreCase("D")) {
                return false;
            }
            System.err.println("Please enter u/U to Update or d/D to Delete. Enter again!");
        }
    }

    //method to check if updated info is any different from current info
    public boolean checkInformationChanged(ArrayList<Student> listStudent, String updatedName, int updatedSemester, Course updatedCourse) {
        for (Student student : listStudent) {
            //if all matched, there is no change => return true
            if(student.getName().equals(updatedName)
                    && student.getSemester() == updatedSemester
                    && student.getCourse() == updatedCourse){
                return true;
            }
        }
        //if not return false
        return false;
    }

    public boolean checkReportExist(ArrayList<Report> listReport, String studentName, String courseName, int total) {
        for (Report report : listReport) {
            if(studentName.equalsIgnoreCase(report.getStudentName()) 
                    && courseName.equalsIgnoreCase(report.getCourseName())
                    && total == report.getTotalCourse()){
                return true;
            }
        }
        return false;
    }

}
