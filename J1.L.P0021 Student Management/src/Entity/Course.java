/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 * 
 * Enum class Course to display 3 constant values: Java, .Net and C/C++
 */
public enum Course {
    Java("Java", 1),
    DotNet(".Net", 2),
    CCPlus("C/C++", 3);

    private int id;
    private String name;

    private Course(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    
    //method to define course of a student via Integer input
    public static Course getTypeByInt(int type) {
        switch (type) {
            case 1:
                return Java;
            case 2:
                return DotNet;
            case 3:
                return CCPlus;
            //catch invalid input error
            default:
                throw new IllegalArgumentException("invalid course id: " + type);

        }
    }
}
