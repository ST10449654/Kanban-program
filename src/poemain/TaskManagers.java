/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poemain;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
/**
 *
 * @author RC_Student_lab
 */
public class TaskManagers {
    // Instance variables
    private String taskName;
    private String description;
    private String developerDetails;
    private int duration; // in hours
    private String status;
    private String taskId;

    // Static counters and collections
    private static int taskCounter = 0;
    private static final List<TaskManagers> tasks = new ArrayList<>();

    // Method to add tasks
    public void addTasks() {
        int taskNumber;

        try {
            // Prompt user to enter the number of tasks
            taskNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks you wish to create:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            return;
        }

        for (int i = 0; i < taskNumber; i++) {
            // Create a new task
            TaskManagers newTask = new TaskManagers();

            // Gather task details
            newTask.taskName = JOptionPane.showInputDialog("Enter task name:");
            if (newTask.taskName == null && newTask.taskName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Task name cannot be empty.");
                i--; // Retry this task
                continue;
            }

            newTask.description = JOptionPane.showInputDialog("Enter task description (Max 50 characters):");
            if (newTask.description == null && newTask.description.length() > 50) {
                JOptionPane.showMessageDialog(null, "Task description must be 50 characters or less.");
                i--; // Retry this task if the requirements are not met
                continue;
            }

            newTask.developerDetails = JOptionPane.showInputDialog("Enter developer details (name and surname):");
            if (!newTask.developerDetails.isEmpty() && newTask.developerDetails != null) {
            } else {
                JOptionPane.showMessageDialog(null, "Developer details cannot be empty.");
                i--; // Retry this task
                continue;
            }

            try {
                newTask.duration = Integer.parseInt(JOptionPane.showInputDialog("Enter the task duration in hours:"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for duration.");
                i--; // Retry this task
                continue;
            }

            newTask.status = showTaskStatus();

            // Generate a unique ID for the task
            taskCounter++;
            newTask.taskId = makeTaskId(newTask.taskName, taskCounter, newTask.developerDetails);

            // Add the new task to the list
            tasks.add(newTask);

            // Display task details
            displayTaskDetails(newTask);

            int choice = JOptionPane.showConfirmDialog(null, "Do you wish to add another task?", "Continue?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                break;
            }
        }

        JOptionPane.showMessageDialog(null, "Tasks added successfully!");
    }
    
    // Method that displays the total hours
    public int GetTotalHours() {
        int totalHours = 0;
        for (TaskManagers task : tasks) {
            totalHours += task.duration;
        }
        return totalHours;
    }

    // Method to display all tasks
    public void displayAllTasksReport() {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.", "Task Report", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder report = new StringBuilder("All Tasks Report:\n\n");
        for (int i = 0; i < tasks.size(); i++) {
            TaskManagers task = tasks.get(i);
            report.append(String.format(
                    "Task %d:\nTask Name: %s\nDeveloper: %s\nDuration: %d hours\nStatus: %s\nTask ID: %s\n\n",
                    i + 1, task.taskName, task.developerDetails, task.duration, task.status, task.taskId
            ));
        }

        JOptionPane.showMessageDialog(null, report.toString(), "Task Report", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to display tasks with status 'Done'
    public void displayTasksWithStatusDone() {
        StringBuilder result = new StringBuilder("Tasks with Status 'Done':\n\n");
        for (TaskManagers task : tasks) {
            if ("Done".equalsIgnoreCase(task.status)) {
                result.append(String.format("Task Name: %s, Developer: %s\n", task.taskName, task.developerDetails));
            }
        }
        
        JOptionPane.showMessageDialog(null, result.toString(), "Done Tasks", JOptionPane.INFORMATION_MESSAGE);
    }

    
    // Method to search tasks by developer
    public void searchTasksByDeveloper(String developer) {
        if (developer == null && developer.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Developer name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.", "Developer Tasks", JOptionPane.INFORMATION_MESSAGE);
            return;//Return this message if the name provided is incorrect
        }

        StringBuilder result = new StringBuilder("Tasks by Developer:\n\n");
        boolean found = false;

        for (TaskManagers task : tasks) {
            if (task.developerDetails.equalsIgnoreCase(developer)) {
                result.append(String.format("Task Name: %s, Status: %s\n", task.taskName, task.status));
                found = true;
            }
        }

        if (!found) {
            result.append("No tasks found for the specified developer.");
        }

        JOptionPane.showMessageDialog(null, result.toString(), "Developer Tasks", JOptionPane.INFORMATION_MESSAGE);
    }


    // Method to search task by name
    public void searchTaskByName(String taskName) {
        for (TaskManagers task : tasks) {
            if (task.taskName.equalsIgnoreCase(taskName)) {
                String taskInfo = String.format(
                        "Task Found:\nTask Name: %s\nDeveloper: %s\nStatus: %s\nDuration: %d hours\nTask ID: %s",
                        task.taskName, task.developerDetails, task.status, task.duration, task.taskId
                );
                JOptionPane.showMessageDialog(null, taskInfo, "Task Search Result", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.", "Task Search", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to delete a task by name
    public void deleteTaskByName(String taskName) {
        for (TaskManagers task : tasks) {
            if (task.taskName.equalsIgnoreCase(taskName)) {
                tasks.remove(task);
                JOptionPane.showMessageDialog(null, "Task '" + taskName + "' has been deleted.", "Delete Task", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.", "Delete Task", JOptionPane.ERROR_MESSAGE);
    }

    // Method to display the longest task
    public void displayLongestTask() {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.", "Longest Task", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        TaskManagers longestTask = tasks.get(0);
        for (TaskManagers task : tasks) {
            if (task.duration > longestTask.duration) {
                longestTask = task;
            }
        }

        String taskInfo = String.format(
                "Longest Task:\nTask Name: %s\nDeveloper: %s\nDuration: %d hours",
                longestTask.taskName, longestTask.developerDetails, longestTask.duration
        );
        JOptionPane.showMessageDialog(null, taskInfo, "Longest Task", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method that shows the task status
    private static String showTaskStatus() {
        String[] statusOptions = {"To-Do", "Doing", "Done"};
        int statusSelection = JOptionPane.showOptionDialog(
                null, "Choose the task status:", "Task Status",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, statusOptions, statusOptions[0]
        );
        return (statusSelection >= 0 && statusSelection < statusOptions.length) ? statusOptions[statusSelection] : "To-Do";
    }
    
     //Mehod for the generation of a task ID
    private static String makeTaskId(String taskName, int taskNumber, String developerDetails) {
        String taskNameInitials = taskName.substring(0, Math.min(2, taskName.length())).toUpperCase();
        String developerInitials = developerDetails.substring(Math.max(developerDetails.length() - 3, 0)).toUpperCase();
        return taskNameInitials + ":" + taskNumber + ":" + developerInitials;
    }
    
     //Method displaying the task details
    private void displayTaskDetails(TaskManagers task) {
        String taskInfo = String.format(
                "Task Details:\n\nTask Name: %s\nDescription: %s\nDeveloper: %s\nDuration: %d hours\nStatus: %s\nTask ID: %s",
                task.taskName, task.description, task.developerDetails, task.duration, task.status, task.taskId
        );
        JOptionPane.showMessageDialog(null, taskInfo, "Task Details", JOptionPane.INFORMATION_MESSAGE);
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


