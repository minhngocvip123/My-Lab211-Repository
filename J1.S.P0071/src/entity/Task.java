/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * task entity class
 * @author ADMIN
 */
public class Task {

    public enum TaskType {
        Code(1),
        Test(2),
        Design(3),
        Review(4);

        private final int value;

        private TaskType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static TaskType getTypeByInt(int type) {
            switch (type) {
                case 1:
                    return Code;
                case 2:
                    return Test;
                case 3:
                    return Design;
                case 4:
                    return Review;
                default:
                    throw new IllegalArgumentException("Invalid task type value: " + type);
            }
        }
    }

    private int id;
    private TaskType type;
    private String requirementName;
    private LocalDate date;
    private double planFrom;
    private double planTo;
    private String assignee;
    private String expert;

    public Task() {
    }

    public Task(int id, TaskType type, String requirementName, LocalDate date, double planFrom, double planTo, String assignee, String expert) {
        this.id = id;
        this.type = type;
        this.requirementName = requirementName;
        this.date = date;
        this.planFrom = planFrom;
        this.planTo = planTo;
        this.assignee = assignee;
        this.expert = expert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public LocalDate getDate() {
        //raw date
        //localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        return date;
    }

    public String getDateWithFormat() {
        //return date with format
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public double getTime() {
        return planTo - planFrom;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPlanFrom() {
        return planFrom;
    }

    public void setPlanFrom(double planFrom) {
        this.planFrom = planFrom;
    }

    public double getPlanTo() {
        return planTo;
    }

    public void setPlanTo(double planTo) {
        this.planTo = planTo;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }
}
