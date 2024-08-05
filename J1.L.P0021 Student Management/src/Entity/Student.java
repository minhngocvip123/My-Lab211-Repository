package Entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */

/*  Student entity class
*   implements Comparable interface to use Collections.sort
*/
public class Student implements Comparable<Student>{
    private int id;
    private String name;
    private Course course;
    private int semester;

    public Student() {
    }

    public Student(int id, String name, Course course, int semester) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
    
    //method to print out row of student
    public void print(){
        System.out.printf("%-15s%-15s%-15s\n", name, course.getName(), semester);
    }
    
    //override compareTo() method to specify the sorting logic. Here we sort by name
    @Override
    public int compareTo(Student student) {
        return this.getName().compareTo(student.name);
    }
}
