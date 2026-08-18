package com.acxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String line;
    private static String[] parts;
    private static List<String> rest = new ArrayList<>();
    private static List<Task> taskList = new ArrayList<>();
    private static String deleteTask;
    private static int taskID = 0;

    //TODO
    /*

     */

    // TEST-QUOTES
    // task-cli add "Buy groceries"
    // task-cli update 1 "Buy groceries and cook dinner"
    // task-cli delete 1
    // task-cli mark-in-progress 1
    // task-cli mark-done 1
    // task-cli list
    // task-cli list done
    // task-cli list todo
    // task-cli list in-progress

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            line = scanner.nextLine();

            if (line.isEmpty()) {
                continue;
            }
            if (line.equalsIgnoreCase("task-cli list")) {
                for (Task taskLists : taskList) {
                    System.out.println(taskLists);
                }
            }
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            if (!(line.equalsIgnoreCase("exit") ||
                    line.equalsIgnoreCase("ls"))) {
                Task.incrementTask();
            }

            parts = line.split("\\s+");

            if (parts[0].equalsIgnoreCase("task-cli") && parts[1].equalsIgnoreCase("add")) {
                String taskText = String.join(" ", Arrays.copyOfRange(parts, 2, parts.length));
                taskText = taskText.replaceAll("^\"|\"$", "");

                System.out.println("Items added to the list:\n"
                        + "<"
                        + taskText
                        + ">");

            } else {
                System.out.println("Wrong command.");
                continue;
            }


            int taskIDCount = taskID += 1;
            taskList.add(new Task(rest, true, taskIDCount));
//            System.out.println("##log_output: " + "'" + line + "'\n" +
//                    "##task_count: " + "'" + Task.getTaskCount() + "'");

        }

        while (true) {
            System.out.println("<Delete a task>");
            deleteTask = scanner.nextLine();

            if (deleteTask.equalsIgnoreCase("exit")) {
                break;
            }
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


