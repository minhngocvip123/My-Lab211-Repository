/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Entity.Course;
import Entity.Report;
import Entity.Student;
import Validation.Validation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */

/*
 * Manager class to handle all the main operations
 */
public class Manager {

    Scanner sc = new Scanner(System.in);
    Validation validation = new Validation();

    public void menuDisplay() {
        System.out.println("");
        System.out.println("WELCOME TO STUDENT MANAGEMENT");
        System.out.println(" 1. Create");
        System.out.println(" 2. Find and Sort");
        System.out.println(" 3. Update/Delete");
        System.out.println(" 4. Report");
        System.out.println(" 5. Exit");
    }

    public void createStudent(int count, ArrayList<Student> listStudent) {
        //loop until user input successfull
        while (true) {
            //if number of students greater than 10 ask user continue or not
            if (count > 10) {
                //if user entered N, end operation
                if (!validation.checkInputYN("Do you want to continue (Y/N): ")) {
                    return;
                }
            }

            int id = validation.getInt("Enter ID: ");
            //initialize name variable
            String name = "";
            //if Id already exists then automatically keep student name of Id
            if (validation.checkIdExist(id, listStudent)) {
                name = findStudentById(id, listStudent).getName();
            } else {
                name = validation.checkInputString("Enter Student Name: ");
            }

            int semester = validation.getInt("Enter semester: ", 1, Integer.MAX_VALUE);

            int courseType = validation.getInt("Choose course: 1 - Java; 2 - .Net; 3 - C/C++: ", 1, 3);
            //create a course object through courseType
            Course course = Course.getTypeByInt(courseType);
            //check if student is a duplicate (4 properties are the same)
            if (!validation.checkDuplicate(id, name, semester, course, listStudent)) {
                listStudent.add(new Student(id, name, course, semester));
                count++;
                System.out.println("Add student success.");
            } else {
                System.err.println("Student is a duplicate. Please re-input");
            }

        }
    }

    public void findAndSort(ArrayList<Student> listStudent) {
        if (listStudent.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }

        ArrayList<Student> listStudentByName = findStudentByName(listStudent);

        if (listStudentByName.isEmpty()) {
            System.err.println("Search result empty.");
        } else {
            Collections.sort(listStudentByName);
            System.out.printf("%-15s%-15s%-15s\n", "Student name", "Course Name", "Semester");
            for (Student student : listStudentByName) {
                student.print();
            }
        }
    }

    public ArrayList<Student> findStudentByName(ArrayList<Student> listStudent) {
        ArrayList<Student> listStudentByName = new ArrayList<Student>();
        String name = validation.checkInputString("Enter name to search: ");

        for (Student student : listStudent) {
            if (student.getName().contains(name)) {
                listStudentByName.add(student);
            }
        }
        return listStudentByName;
    }

    //method to handle updating or deleting a student by ID
    public void updateOrDelete(int count, ArrayList<Student> listStudent) {
        //check if list is empty
        if (listStudent.isEmpty()) {
            System.err.println("List is empty.");
            return;
        }

        int id = validation.getInt("Enter ID: ", 0, Integer.MAX_VALUE);
        Student studentById = findStudentById(id, listStudent);

        //check if student with input Id exist
        if (studentById == null) {
            System.err.println("Student not found!");
            System.err.println("Please enter student Id that exists!");
            //rerun the operation by invoking the method itself
            updateOrDelete(count, listStudent);
        } else {
            System.out.printf("%-15s%-15s%-15s\n", "Student name", "Course Name", "Semester");
            studentById.print();

            boolean updateOrDelete = validation.checkInputUpdateDelete("Do you want to update (U) or delete (D) this student? ");
            //if user enter U/u, validation returns true and let user update student at input Id
            if (updateOrDelete) {
                String updatedName = validation.checkInputString("Enter Student Name: ");
                int updatedSemester = validation.getInt("Enter semester: ", 1, Integer.MAX_VALUE);
                int updatedCourseType = validation.getInt("Choose course: 1 - Java; 2 - .Net; 3 - C/C++: ", 1, 3);
                Course updatedCourse = Course.getTypeByInt(updatedCourseType);
                //check if input info is any different from current info
                //if not, then do not update and exit operation
                if (validation.checkInformationChanged(listStudent, updatedName, updatedSemester, updatedCourse)) {
                    System.err.println("Nothing changed!");
                } else {
                    //set new updated information
                    studentById.setName(updatedName);
                    studentById.setSemester(updatedSemester);
                    studentById.setCourse(updatedCourse);
                    System.out.println("Update successfully!");
                }
                return;
            } else {
                //delete student
                listStudent.remove(studentById);
                count--;
                System.out.println("Delete successfully!");
                return;
            }
        }

    }

    //method to retrieve the student by ID
    private Student findStudentById(int id, ArrayList<Student> listStudent) {
        for (Student student : listStudent) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    /*method to handle displaying student reports
    * the report will display the number of times the same student takes the same 
    * course for all students
     */
    public void report(ArrayList<Student> listStudent) {
        //check if list is empty
        if (listStudent.isEmpty()) {
            System.err.println("List is empty.");
            return;
        }
        //create new report list
        ArrayList<Report> listReport = new ArrayList<>();
        
        //iterate through the student list for the first time to gather unique
        //sets of student Id, name and course except for semester
        for (Student student : listStudent) {
            //initialize course's total counter variable
            int total = 0;
            //get all unique sets of id, names and courses of students
            int id = student.getId();
            String studentName = student.getName();
            String courseName = student.getCourse().getName();
            //get the count for the number of time the same student study 
            //the same course
            for (Student studentTotalOfCourse : listStudent) {
                if (id == studentTotalOfCourse.getId()
                        && courseName.equalsIgnoreCase(studentTotalOfCourse.getCourse().getName())
                        && studentName.equalsIgnoreCase(studentTotalOfCourse.getName())) {
                    total++;
                }
            }
            //check if report exists. If not, add new report of student into list report
            if (!validation.checkReportExist(listReport, studentName, courseName, total)) {
                listReport.add(new Report(student.getName(), courseName, total));
            }
        }
        //print list report
        System.out.println("List Report");
        System.out.printf("%-20s | %-10s | %-10s\n", "Student Name", "Course Name", "Total of Course");
        for (Report report : listReport) {
            report.printReport();
        }
    }

}
