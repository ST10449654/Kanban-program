/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poemain;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
public class PoeMain {

/**
    /**
     * @param args the command line arguments
     */
  public static void main(String[] args) {
        //condition 1 = username contains underscore and is no more than 5 characters
        /*condition 2 = password is up to 8 characters long
        contains a capital letter
        contains a number
        contains a special character
        */
        
        
      //access the sub classes
     UserAuthentificate create_user = new UserAuthentificate();
        TaskManagers create_task = new TaskManagers();

        String username;
        String password;
        String firstname = null;
        String lastname = null;

        boolean exit = false;

        while (!exit) {
            String[] options = {"Register", "Login", "Exit"};//Registration menu is displayed
            int option = JOptionPane.showOptionDialog(
                    null,
                    "Choose an option",
                    "User Authentication",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (option) {
                case 0 -> {
                    // Registration
                    firstname = JOptionPane.showInputDialog("Enter your firstname");
                    lastname = JOptionPane.showInputDialog("Enter your lastname");
                    username = JOptionPane.showInputDialog("Enter your username");
                    password = JOptionPane.showInputDialog("Enter your password");
                    boolean usernameCorrect = create_user.CheckUserName(username);
                    boolean passwordCorrect = create_user.CheckPasswordComplexity(password);
                    if (usernameCorrect && passwordCorrect) {
                        create_user.LoginUser(firstname, lastname);
                        JOptionPane.showMessageDialog(null, "Registration is successful");
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration is unsuccessful. Please check username and password requirements.");
                    }
                }
                case 1 -> {
                    // Login
                    username = JOptionPane.showInputDialog("Enter your username");
                    password = JOptionPane.showInputDialog("Enter your password");
                    boolean CheckUserName = create_user.CheckUserName(username);
                    boolean CheckPasswordComplexity = create_user.CheckPasswordComplexity(password);

                    // Use AtomicBoolean for loggedIn to ensure thread-safety
                    AtomicBoolean loggedIn = new AtomicBoolean(false);

                    if (CheckUserName && CheckPasswordComplexity) {
                        create_user.LoginUser(firstname, lastname);
                        JOptionPane.showMessageDialog(null, "A successful login");

                        loggedIn.set(true);

                        JOptionPane.showMessageDialog(null, "=================\nWelcome to EasyKanban\n=================",
                                "Welcome", JOptionPane.INFORMATION_MESSAGE);

                        while (loggedIn.get()) {
                            // Display task options using JFrame
                            JFrame taskFrame = new JFrame("Task Options");
                            taskFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            taskFrame.setSize(400, 600); // Resize vertically
                            taskFrame.setLayout(new GridLayout(10, 1, 10, 10)); // Vertical layout with spacing

                            // Add WindowListener to detect when the frame is closed
                            taskFrame.addWindowListener(new WindowAdapter() {
                                //@Override
                                @Override
                                public void windowClosing(WindowEvent e) {
                                    loggedIn.set(false);
                                }
                            });

                            // Create task option buttons
                            JButton addTaskButton = new JButton("Add Task");
                            JButton totalHoursButton = new JButton("Show Total Hours");
                            JButton taskReportButton = new JButton("Show Task Report");
                            JButton doneTasksButton = new JButton("Display Done Tasks");
                            JButton longestTaskButton = new JButton("Longest Duration Task");
                            JButton searchTaskButton = new JButton("Search Task by Name");
                            JButton searchDeveloperButton = new JButton("Search Tasks by Developer");
                            JButton deleteTaskButton = new JButton("Delete Task");
                            JButton exitButton = new JButton("Exit");

                            // Add action listeners to buttons
                            addTaskButton.addActionListener(e -> create_task.addTasks());
                            totalHoursButton.addActionListener(e -> {
                                int totalHours = create_task.GetTotalHours();
                                JOptionPane.showMessageDialog(taskFrame, "Total task hours: " + totalHours, "Total Hours", JOptionPane.INFORMATION_MESSAGE);
                            });
                            taskReportButton.addActionListener(e -> create_task.displayAllTasksReport());
                            doneTasksButton.addActionListener(e -> create_task.displayTasksWithStatusDone());
                            longestTaskButton.addActionListener(e -> create_task.displayLongestTask());
                            searchTaskButton.addActionListener(e -> {
                                String taskNameToSearch = JOptionPane.showInputDialog(taskFrame, "Enter task name to search");
                                create_task.searchTaskByName(taskNameToSearch);
                            });
                            searchDeveloperButton.addActionListener(e -> {
                                System.out.println("Search Developer button clicked");//Debugging statement
                                String developerToSearch = JOptionPane.showInputDialog(taskFrame, "Enter developer name to search tasks");
                                create_task.searchTasksByDeveloper(developerToSearch);
                            });
                            deleteTaskButton.addActionListener(e -> {
                                String taskNameToDelete = JOptionPane.showInputDialog(taskFrame, "Enter task name to delete");
                                create_task.deleteTaskByName(taskNameToDelete);
                            });
                            exitButton.addActionListener(e -> {
                                loggedIn.set(false);
                                taskFrame.dispose();
                                JOptionPane.showMessageDialog(null, "Thank you for using EasyKanban. Exiting the application.", "Exit", JOptionPane.INFORMATION_MESSAGE);
                            });

                            // Add buttons to JFrame
                            taskFrame.add(addTaskButton);
                            taskFrame.add(totalHoursButton);
                            taskFrame.add(taskReportButton);
                            taskFrame.add(doneTasksButton);
                            taskFrame.add(longestTaskButton);
                            taskFrame.add(searchTaskButton);
                            taskFrame.add(searchDeveloperButton);
                            taskFrame.add(deleteTaskButton);
                            taskFrame.add(exitButton);

                            // Make the JFrame visible
                            taskFrame.setVisible(true);

                            // Wait for the JFrame to close
                            while (taskFrame.isVisible()) {
                                try {
                                    Thread.sleep(100); // Avoid busy-waiting
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed login. Username or password is incorrect.");
                    }
                }
                case 2 -> {
                    // Exit
                    exit = true;
                    JOptionPane.showMessageDialog(null, "Goodbye!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid option, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}