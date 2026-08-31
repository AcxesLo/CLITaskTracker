package com.acxes;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Main {
    private static String line;
    private static String[] parts;
    private static String taskText;
    private static List<Task> taskList = new ArrayList<>();
    private static List<String> taskTextList = new ArrayList<>();
    private static int taskID = 0;

    //TODO
    // adding function kinda done, needs to be added into the class --done

    // git comment
    // moved if statement into one unit to avoid wrong "wrong comment" comments

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

            parts = line.split("\\s+");

            if (parts[0].equalsIgnoreCase("task-cli") && parts[1].equalsIgnoreCase("add")) {
                taskText = String.join(" ", Arrays.copyOfRange(parts, 2, parts.length));
                taskText = taskText.replaceAll("^\"|\"$", "");

                System.out.println("Items added to the list:\n"
                        + "<"
                        + taskText
                        + ">");

                taskTextList.add(taskText);
                Task.incrementTask();
                int taskIDCount = taskID += 1;
                taskList.add(new Task(taskTextList, true, taskIDCount));

                System.out.println("taskList size: " + taskList.size());

            } else if (parts[0].equalsIgnoreCase("task-cli") && parts[1].equalsIgnoreCase("update")) {
                // here i wanna update the previously added task, and it should be selected by the taskID from index 2
                int targetID = Integer.parseInt(parts[2]);
                boolean found = false;
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getTaskID() == targetID) {
                        taskText = String.join(" ", Arrays.copyOfRange(parts, 3, parts.length));
                        taskText = taskText.replaceAll("^\"|\"$", "");

                        taskList.get(i).setTaskName(Collections.singletonList(taskText));

                        System.out.println("Items updated on taskID " + targetID + " to the list:\n"
                                + "<"
                                + taskText
                                + ">");
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("Task with the ID " + targetID + " wasn't found.");
                }

            } else if (line.equalsIgnoreCase("task-cli list")) {
                System.out.println("TaskCount: " + Task.taskCount);
                for (Task taskLists : taskList) {
                    System.out.println(taskLists);
                }

            } else if (line.isEmpty()) {
                continue;

            } else if (line.equalsIgnoreCase("exit")) {
                break;

            } else {
                System.out.println("Wrong command.");
                continue;
            }
        }
    }
}


