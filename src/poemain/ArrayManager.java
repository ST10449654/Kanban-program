/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poemain;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author RC_Student_lab
 */
class ArrayManager {
    
     
    // List to store tasks
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            String[] options = {
                    "Add Task",
                    "Display Tasks with Status 'Done'",
                    "Task with Longest Duration",
                    "Search Task by Name",
                    "Search Tasks by Developer",
                    "Delete Task by Name",
                    "Display Full Report",
                    "Exit"
            };

            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Select an action",
                    "Task Manager",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0 -> addTask();
                case 1 -> displayTasksWithStatusDone();
                case 2 -> displayTaskWithLongestDuration();
                case 3 -> searchTaskByName();
                case 4 -> searchTasksByDeveloper();
                case 5 -> deleteTaskByName();
                case 6 -> displayFullReport();
                case 7 -> System.exit(0);
                default -> JOptionPane.showMessageDialog(null, "Invalid selection");
            }
        }
    }

    private static void addTask() {
        try {
            String developer = JOptionPane.showInputDialog("Enter Developer Name:");
            String taskName = JOptionPane.showInputDialog("Enter Task Name:");
            String taskID = UUID.randomUUID().toString();
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (in hours):"));
            String taskStatus = JOptionPane.showInputDialog("Enter Task Status (e.g., 'To Do', 'In Progress', 'Done'):");

            // Input validation
            if (developer.isEmpty() || taskName.isEmpty() || taskStatus.isEmpty() || taskDuration <= 0) {
                JOptionPane.showMessageDialog(null, "All fields must be valid and filled in correctly.");
                return;
            }

            tasks.add(new Task(developer, taskName, taskID, taskDuration, taskStatus));
            JOptionPane.showMessageDialog(null, "Task added successfully! Task ID: " + taskID);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding task: " + e.getMessage());
        }
    }

    private static void displayTasksWithStatusDone() {
        StringBuilder result = new StringBuilder("Tasks with Status 'Done':\n");

        for (Task task : tasks) {
            if ("Done".equalsIgnoreCase(task.getStatus())) {
                result.append("Developer: ").append(task.getDeveloper()).append(", ")
                        .append("Task Name: ").append(task.getTaskName()).append(", ")
                        .append("Duration: ").append(task.getDuration()).append(" hours\n");
            }
        }

        JOptionPane.showMessageDialog(null, result.length() > 0 ? result.toString() : "No tasks with status 'Done' found.");
    }

    private static void displayTaskWithLongestDuration() {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.");
            return;
        }

        Task longestTask = Collections.max(tasks, Comparator.comparingInt(Task::getDuration));
        String result = "Task with Longest Duration:\n" +
                "Developer: " + longestTask.getDeveloper() + "\n" +
                "Duration: " + longestTask.getDuration() + " hours";

        JOptionPane.showMessageDialog(null, result);
    }

    private static void searchTaskByName() {
        String searchName = JOptionPane.showInputDialog("Enter Task Name to Search:");
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(searchName)) {
                String result = "Task Found:\n" +
                        "Task Name: " + task.getTaskName() + "\n" +
                        "Developer: " + task.getDeveloper() + "\n" +
                        "Status: " + task.getStatus();
                JOptionPane.showMessageDialog(null, result);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    private static void searchTasksByDeveloper() {
        String searchDeveloper = JOptionPane.showInputDialog("Enter Developer Name to Search:");
        StringBuilder result = new StringBuilder("Tasks assigned to " + searchDeveloper + ":\n");

        for (Task task : tasks) {
            if (task.getDeveloper().equalsIgnoreCase(searchDeveloper)) {
                result.append("Task Name: ").append(task.getTaskName()).append(", ")
                        .append("Status: ").append(task.getStatus()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, result.length() > 0 ? result.toString() : "No tasks found for developer: " + searchDeveloper);
    }

    private static void deleteTaskByName() {
        String deleteName = JOptionPane.showInputDialog("Enter Task Name to Delete:");
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getTaskName().equalsIgnoreCase(deleteName)) {
                iterator.remove();
                JOptionPane.showMessageDialog(null, "Task deleted successfully!");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    private static void displayFullReport() {
        StringBuilder result = new StringBuilder("Full Task Report:\n");

        for (Task task : tasks) {
            result.append("Task Name: ").append(task.getTaskName()).append(", ")
                    .append("Developer: ").append(task.getDeveloper()).append(", ")
                    .append("Task ID: ").append(task.getTaskID()).append(", ")
                    .append("Duration: ").append(task.getDuration()).append(" hours, ")
                    .append("Status: ").append(task.getStatus()).append("\n");
        }

        JOptionPane.showMessageDialog(null, result.length() > 0 ? result.toString() : "No tasks available.");
    }

    // Task class encapsulates task details
    private static class Task {
        private final String developer;
        private final String taskName;
        private final String taskID;
        private final int duration;
        private final String status;

        public Task(String developer, String taskName, String taskID, int duration, String status) {
            this.developer = developer;
            this.taskName = taskName;
            this.taskID = taskID;
            this.duration = duration;
            this.status = status;
        }

        public String getDeveloper() { return developer; }

        public String getTaskName() { return taskName; }

        public String getTaskID() { return taskID; }

        public int getDuration() { return duration; }

        public String getStatus() { return status; }
    }
}


    

