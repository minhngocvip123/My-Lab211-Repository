package manager;

import entity.Task;
import java.time.LocalDate;
import java.util.ArrayList;
import validation.Validation;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Manager class that handles all of the program's fundamental operation
 *
 * @author ADMIN
 */
public class Manager {

    private final ArrayList<Task> tasks = new ArrayList<>();
    private final Validation validation = new Validation();
    private int lastId;

    public Manager() {
        lastId = 5;
        tasks.add(new Task(1, Task.TaskType.Code, "Task force delta", LocalDate.of(2024, 1, 12), 8, 17.5, "Dev", "Lead"));
        tasks.add(new Task(2, Task.TaskType.Design, "Task force alpha", LocalDate.of(2024, 2, 21), 9, 12.5, "BA", "Lead"));
        tasks.add(new Task(3, Task.TaskType.Test, "Task force beta", LocalDate.of(2024, 3, 23), 8.5, 13.4, "Tester", "PM"));
        tasks.add(new Task(4, Task.TaskType.Code, "Task force omega", LocalDate.of(2024, 4, 5), 6.25, 16.75, "Dev", "Lead"));
        tasks.add(new Task(5, Task.TaskType.Review, "Task force sigma", LocalDate.of(2024, 4, 24), 8, 17.5, "All", "Tech Lead"));
    }

    //method to display menu
    public void displayMenu() {
        System.out.println("");
        System.out.println("========= Task program =========");
        System.out.println("1. Add Task");
        System.out.println("2. Delete task");
        System.out.println("3. Display Task");
        System.out.println("4. Exit");
    }

    public void addTask() {
        String name = validation.getString("Requirement Name: ");
        int type = validation.getInt("Task Type: ", 1, 4);
        Task.TaskType taskType = Task.TaskType.getTypeByInt(type);
        LocalDate date = validation.getLocalDate("Date: ");
        //plan must be from 8 - 17.5; planTo > planFrom
        double planFrom = validation.getDouble("From: ", 8, 17.5);
        double planTo = validation.getDouble("From: ", planFrom, 17.5);
        String assignee = validation.getString("Assignee: "); 
        String expert = validation.getString("Reviewer: "); 
        //automatically assign a new Id incrementally from the last id
        ++lastId;
        tasks.add(new Task(lastId, taskType, name, date, planFrom, planTo, assignee, expert));
    }

    public void deleteTask() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * method which handles displaying list of tasks
     */
    public void getDataTasks() {
        //check if list is empty
        if (tasks.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        System.out.println("----------------------------------------- Task ---------------------------------------");
        System.out.printf("%-7s%-20s%-12s%-15s%-7s%-15s%-15s\n",
                "Id", "Name", "Task Type", "Date", "Time",
                "Assignee", "Reviewer");
        for (Task task : tasks) {
            System.out.printf("%-7s%-20s%-12s%-15s%-7s%-15s%-15s\n",
                    task.getId(),
                    task.getRequirementName(),
                    task.getType(),
                    task.getDateWithFormat(),
                    task.getTime(), //planTo - planFrom
                    task.getAssignee(),
                    task.getExpert());
        }
    }

}
