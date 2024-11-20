/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package poemain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import javax.swing.*;
import java.util.ArrayList;


/**
 *
 * @author RC_Student_lab
 */
public class TaskManagersTest {

    private TaskManagers taskManagers;

    // Mocking JOptionPane for testing user input dialogs
    @Mock
    private JOptionPane mockOptionPane;

    @Before
    public void setUp() {
        // Initialize the TaskManagers object
        taskManagers = new TaskManagers();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddTaskValid() {
        // Test adding a task with valid input

        // Mocking the user input
        when(mockOptionPane.showInputDialog("Enter task name:")).thenReturn("Create Reports");
        when(mockOptionPane.showInputDialog("Enter task description (Max 50 characters):")).thenReturn("Report generation");
        when(mockOptionPane.showInputDialog("Enter developer details (name and surname):")).thenReturn("Mike Smith");
        when(mockOptionPane.showInputDialog("Enter the task duration in hours:")).thenReturn("8");
        when(mockOptionPane.showOptionDialog(null, "Choose the task status:", "Task Status", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"To-Do", "Doing", "Done"}, "To-Do")).thenReturn(0);

        // Adding a task
        taskManagers.addTasks();

        // Verify that the task was added to the list
        assertEquals(1, taskManagers.tasks.size());
        TaskManagers task = taskManagers.tasks.get(0);
        assertEquals("Create Reports", task.taskName);
        assertEquals("Mike Smith", task.developerDetails);
        assertEquals(8, task.duration);
    }

    @Test
    public void testAddTaskInvalidName() {
        // Test adding a task with invalid name input (empty name)

        when(mockOptionPane.showInputDialog("Enter task name:")).thenReturn(""); // Empty name
        when(mockOptionPane.showInputDialog("Enter task description (Max 50 characters):")).thenReturn("Report generation");
        when(mockOptionPane.showInputDialog("Enter developer details (name and surname):")).thenReturn("Mike Smith");
        when(mockOptionPane.showInputDialog("Enter the task duration in hours:")).thenReturn("8");
        when(mockOptionPane.showOptionDialog(null, "Choose the task status:", "Task Status", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"To-Do", "Doing", "Done"}, "To-Do")).thenReturn(0);

        // Try to add a task
        taskManagers.addTasks();

        // Verify no task was added due to invalid input
        assertEquals(0, taskManagers.tasks.size());
    }

    @Test
    public void testAddTaskInvalidDuration() {
        // Test adding a task with invalid duration (non-numeric)

        when(mockOptionPane.showInputDialog("Enter task name:")).thenReturn("Create Reports");
        when(mockOptionPane.showInputDialog("Enter task description (Max 50 characters):")).thenReturn("Report generation");
        when(mockOptionPane.showInputDialog("Enter developer details (name and surname):")).thenReturn("Mike Smith");
        when(mockOptionPane.showInputDialog("Enter the task duration in hours:")).thenReturn("abc"); // Invalid duration
        (JOptionPane.showOptionDialog(null, "Choose the task status:", "Task Status", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"To-Do", "Doing", "Done"}, "To-Do")).thenReturn(0);

        taskManagers.addTasks();

        // Verify no task was added due to invalid duration
        assertEquals(0, taskManagers.tasks.size());
    }

    @Test
    public void testGetTotalHours() {
        // Test the total hours calculation

        // Create mock tasks
        TaskManagers task1 = new TaskManagers();
        task1.duration = 5;
        TaskManagers task2 = new TaskManagers();
        task2.duration = 8;
        taskManagers.tasks.add(task1);
        taskManagers.tasks.add(task2);

        int totalHours = taskManagers.GetTotalHours();
        assertEquals(13, totalHours);
    }

    @Test
    public void testSearchTaskByNameFound() {
        // Test searching a task by name that exists

        TaskManagers task1 = new TaskManagers();
        task1.taskName = "Create Reports";
        task1.developerDetails = "Mike Smith";
        task1.status = "Done";
        task1.duration = 8;
        taskManagers.tasks.add(task1);

        taskManagers.searchTaskByName("Create Reports");

        // We can't directly test JOptionPane message, so this would need more complex mocking to test the JOptionPane message.
        // Assuming the message dialog works, this test will pass if no exceptions occur.
    }

    @Test
    public void testSearchTaskByNameNotFound() {
        // Test searching a task by name that doesn't exist

        TaskManagers task1 = new TaskManagers();
        task1.taskName = "Create Reports";
        taskManagers.tasks.add(task1);

        taskManagers.searchTaskByName("Non Existing Task");

        // The system should show a message dialog "Task not found."
    }

    @Test
    public void testDeleteTaskByNameFound() {
        // Test deleting a task by name that exists

        TaskManagers task1 = new TaskManagers();
        task1.taskName = "Create Reports";
        taskManagers.tasks.add(task1);

        taskManagers.deleteTaskByName("Create Reports");

        assertEquals(0, taskManagers.tasks.size());
    }

    @Test
    public void testDeleteTaskByNameNotFound() {
        // Test deleting a task by name that doesn't exist

        TaskManagers task1 = new TaskManagers();
        task1.taskName = "Create Reports";
        taskManagers.tasks.add(task1);

        taskManagers.deleteTaskByName("Non Existing Task");

        // Verify the task was not deleted
        assertEquals(1, taskManagers.tasks.size());
    }

    @Test
    public void testDisplayLongestTask() {
        // Test finding and displaying the longest task
        TaskManagers task1 = new TaskManagers();
        task1.taskName = "Create Reports";
        task1.developerDetails = "Mike Smith";
        task1.duration = 5;

        TaskManagers task2 = new TaskManagers();
        task2.taskName = "Create Login";
        task2.developerDetails = "Samantha Paulson";
        task2.duration = 8;

        taskManagers.tasks.add(task1);
        taskManagers.tasks.add(task2);

        taskManagers.displayLongestTask();

        // The system should display "Create Login" as the longest task
    }
}