package com.acxes;

import java.util.*;

public class Main {
    private static String line;
    private static String[] parts;
    private static String taskText;
    private static List<Task> taskList = new ArrayList<>();
    private static int taskIDCount;
    private static boolean foundTarget = false;

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

                Task.incrementTask();
                taskIDCount++;
                taskList.add(new Task(taskText, false, taskIDCount));

                System.out.println("taskList size: " + taskList.size());

            } else if (parts[0].equalsIgnoreCase("task-cli") && parts[1].equalsIgnoreCase("update")) {
                int targetID = Integer.parseInt(parts[2]);
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getTaskID() == targetID) {
                        taskText = String.join(" ", Arrays.copyOfRange(parts, 3, parts.length));
                        taskText = taskText.replaceAll("^\"|\"$", "");

                        taskList.get(i).setTaskList(Collections.singletonList(taskText));
                        foundTarget = true;

                        System.out.println("Items updated on taskID " + targetID + " to the list:\n"
                                + "<"
                                + taskText
                                + ">");
                    }
                }
                if (!foundTarget) {
                    System.out.println("Task with the ID " + targetID + " wasn't found.");
                }

            } else if (parts[0].equalsIgnoreCase("task-cli") && parts[1].equalsIgnoreCase("delete")) {
                int targetID = Integer.parseInt(parts[2]);
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getTaskID() == targetID) {
                        taskList.remove(taskList.get(i));

                        Task.decrementTask();

                        System.out.println("Task removed with TaskID " + targetID + " from the list.");
                    } else {
                        System.out.println("Task with the ID " + targetID + " does not exist.(delete)");
                    }
                }

            } else if (parts[0].equalsIgnoreCase("task-cli") && parts[1].equalsIgnoreCase("mark-in-progress")) {
                int targetID = Integer.parseInt(parts[2]);
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getTaskID() == targetID) {
                        taskList.get(i).setInProgress(true);
                        foundTarget = true;
                        System.out.println("Task with TaskID" + targetID + " was updated to 'in progress'.");
                    }
                }
                if (!foundTarget) {
                    System.out.println("Task with the ID " + targetID + " does not exist.(mark-in-progress)");
                }

            } else if (parts[0].equalsIgnoreCase("task-cli") && parts[1].equalsIgnoreCase("done")) {
                int targetID = Integer.parseInt(parts[2]);
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getTaskID() == targetID) {

                        taskList.get(i).setInProgress(false);
                        foundTarget = true;

                        System.out.println("Task with TaskID " + targetID + " was updated to 'done'.");
                    }
                    if (!foundTarget) {
                        System.out.println("Task with the ID " + targetID + " wasn't found.");
                    }
                }

            } else if (line.equalsIgnoreCase("task-cli list")) {
                System.out.println("TaskCount: " + Task.taskCount);
                for (Task taskLists : taskList) {
                    System.out.println(taskLists);
                }

            } else if (line.equalsIgnoreCase("task-cli list todo")) {
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getInProgress()) {
                        System.out.println(taskList);
                    } else {
                        System.out.println("There are no tasks that are 'in progress'.");
                    }
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



