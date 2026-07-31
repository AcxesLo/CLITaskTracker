package com.acxes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String addingTask;
    private static String deleteTask;
    private static int taskID = 0;

    static void main(String[] args) {
//        # Adding a new task
//        task-cli add "Buy groceries"
//        # Output: Task added successfully (ID: 1)
//
//        # Updating and deleting tasks
//        task-cli update 1 "Buy groceries and cook dinner"
//        task-cli delete 1
//
//        # Marking a task as in progress or done
//        task-cli mark-in-progress 1
//        task-cli mark-done 1
//
//        # Listing all tasks
//        task-cli list
//
//        # Listing tasks by status
//        task-cli list done
//        task-cli list todo
//        task-cli list in-progress
//
//                <Task Properties>
//                Each task should have the following properties:
//
//        - id: A unique identifier for the task
//        - description: A short description of the task
//        - status: The status of the task (todo, in-progress, done)
//        - createdAt: The date and time when the task was created
//        - updatedAt: The date and time when the task was last updated
        Scanner scanner = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        while (true) {
            System.out.println("<Adding a new task>");
            addingTask = scanner.nextLine();
            if (addingTask.equalsIgnoreCase("delete"))
                break;

            int taskIDCount = taskID += 1;
            Task.incrementTask();
            taskList.add(new Task(addingTask, true, taskIDCount));
            System.out.println("##log_output: " + "'" + addingTask + "'\n" +
                    "##task_count: " + "'" + Task.getTaskCount() + "'");

            for (Task taskLists : taskList)
                System.out.println(taskLists);
        }

        while (true) {
            System.out.println("<Delete a task>");
            deleteTask = scanner.nextLine();

            if (deleteTask.equalsIgnoreCase("exit"))
                break;
            if (deleteTask.equalsIgnoreCase("ls")) {
                for (Task taskLists : taskList) {
                    System.out.println(taskLists);
                }
            }

            try {
                int idToDelete = Integer.parseInt(deleteTask.trim());
                boolean removed = taskList.removeIf(t -> t.getTaskID() == idToDelete);

                if (removed) {
                    System.out.println("Task " + idToDelete + " deleted successfully.");
                } else {
                    System.out.println("No task found with ID " + idToDelete);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid task ID or 'exit' to quit the process.\n");
            }
        }
    }
}

