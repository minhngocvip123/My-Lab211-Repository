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
    //by default, lastId == 0
    private int lastId = 0;

    public Manager() {
        tasks.add(new Task(++lastId, Task.TaskType.Code, "Task force delta", LocalDate.of(2024, 1, 12), 8, 17.5, "Dev", "Lead"));
        tasks.add(new Task(++lastId, Task.TaskType.Design, "Task force alpha", LocalDate.of(2024, 2, 21), 9, 12.5, "BA", "Lead"));
        tasks.add(new Task(++lastId, Task.TaskType.Test, "Task force beta", LocalDate.of(2024, 3, 23), 8.5, 13.4, "Tester", "PM"));
        tasks.add(new Task(++lastId, Task.TaskType.Code, "Task force omega", LocalDate.of(2024, 4, 5), 6.25, 16.75, "Dev", "Lead"));
        tasks.add(new Task(++lastId, Task.TaskType.Review, "Task force sigma", LocalDate.of(2024, 4, 24), 8, 17.5, "All", "Tech Lead"));
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
        //plan must be from 8 - 17.5; planTo > planFrom; planFrom < 16.5
        double planFrom = validation.getDouble("From: ", 8, 16.5);
        double planTo = validation.getDouble("To: ", planFrom, 17.5);
        String assignee = validation.getString("Assignee: ");
        if (checkAssigneeAvailable(assignee, date, planFrom, planTo)) {
            System.err.println("Assignee is busy. Add task failed!");
        } else {
            String expert = validation.getString("Reviewer: ");
            //automatically assign a new Id incrementally from the last id
            tasks.add(new Task(++lastId, taskType, name, date, planFrom, planTo, assignee, expert));
            System.out.println("Task added successfully!");
        }

    }

    public void deleteTask() {
        //check if list is empty
        if (tasks.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        Task taskToDelete;
        while (true) {
            int id = validation.getInt("Enter Id of task: ");
            taskToDelete = getTaskById(id);
            if (taskToDelete == null) {
                System.err.println("Task with Id(" + id + ") does not exist. Please try again!");
            } else {
                break;
            }
        }
        //display task details (for testing)
        System.out.printf("%-7s%-20s%-12s%-15s%-7s%-15s%-15s\n",
                "Id", "Name", "Task Type", "Date", "Time",
                "Assignee", "Reviewer");
        taskToDelete.printTask();
        //check confirmation (y=true, n=false)
        if (validation.checkInputYN("Are you sure you want to delete this task (Y/N)? ")) {
            tasks.remove(taskToDelete);
            System.out.println("Task deleted succesfully!");
        } else {
            System.out.println("Task deletion cancelled!");
        }

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
        //print table header
        System.out.println("----------------------------------------- Task ---------------------------------------");
        System.out.printf("%-7s%-20s%-12s%-15s%-7s%-15s%-15s\n",
                "Id", "Name", "Task Type", "Date", "Time",
                "Assignee", "Reviewer");
        //iterate through each task and print them
        for (Task task : tasks) {
            task.printTask();
        }
    }

    /**
     * method to return a task by Id
     *
     * @param id
     * @return
     */
    public Task getTaskById(int id) {
        Task taskToFind = null;
        for (Task task : tasks) {
            if (task.getId() == id) {
                taskToFind = task;
            }
        }
        return taskToFind;
    }

    /**
     * Method to check if assignee is available at a certain date and time frame
     *
     * @param assignee
     * @param date
     * @param planFrom
     * @param planTo
     * @return true if busy, false if not
     */
    private boolean checkAssigneeAvailable(String assignee, LocalDate date, double planFrom, double planTo) {
        boolean isBusy = false;
        for (Task task : tasks) {
            if (task.getAssignee().equals(assignee) && task.getDate().isEqual(date)) {
                // Check if the time frames overlap:
                // Make sure the new task starts before the existing task ends
                // Make sure the new task ends after the existing task starts.
                if ((planFrom < task.getPlanTo() && planTo > task.getPlanFrom())) {
                    isBusy = true; // Assignee is busy
                }
            }

        }
        return isBusy; // Assignee is available
    }

}
