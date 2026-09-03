package com.acxes;

import java.util.*;

public class TaskLogic {
    private static String line;
    private static String[] parts;
    private static String taskText;
    private static List<Task> taskList = new ArrayList<>();
    private static int taskIDCount;
    private static boolean foundTarget = false;
    private static Scanner scanner;

    public static void taskCLILogic() {
        scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            line = scanner.nextLine();

            parts = line.split("\\s+");

            if (line.equalsIgnoreCase("help")) {
                System.out.println("<commands>\n"
                        + "task-cli add\n"
                        + "task-cli update [id]\n"
                        + "task-cli delete [id]\n"
                        + "task-cli mark-todo [id]\n"
                        + "task-cli mark-in-progress [id]\n"
                        + "task-cli mark-done [id]\n"
                        + "task-cli list\n"
                        + "task-cli list todo\n"
                        + "task-cli list in-progress\n"
                        + "task-cli list done\n");
            }

            if (parts[0].equalsIgnoreCase("task-cli") && parts[1].equalsIgnoreCase("add")) {
                taskText = String.join(" ", Arrays.copyOfRange(parts, 2, parts.length));
                taskText = taskText.replaceAll("^\"|\"$", "");


                Task.incrementTask();
                taskIDCount++;
                taskList.add(new Task(taskText, "todo", taskIDCount));

                System.out.println("Task added successfully (ID: " + taskIDCount + " )");
//                System.out.println("taskList size: " + taskList.size());

            } else if (parts[0].equalsIgnoreCase("task-cli") && parts[1].equalsIgnoreCase("update")) {
                int targetID = Integer.parseInt(parts[2]);
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getTaskID() == targetID) {
                        taskList.get(i).setUpdateDateTime();
                        taskText = String.join(" ", Arrays.copyOfRange(parts, 3, parts.length));
                        taskText = taskText.replaceAll("^\"|\"$", "");

                        taskList.get(i).setTaskList(Collections.singletonList(taskText));
                        foundTarget = true;
                        taskList.get(i).setUpdateDateTime();


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
                        foundTarget = true;
                        Task.decrementTask();
                        System.out.println("Task removed with TaskID " + targetID + " from the list.");
                    }
                }
                if (!foundTarget) {
                    System.out.println("Task with the ID " + targetID + " does not exist.(delete)");
                }

            } else if (parts[0].equalsIgnoreCase("task-cli") && parts[1].equalsIgnoreCase("mark-in-progress")) {
                int targetID = Integer.parseInt(parts[2]);
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getTaskID() == targetID) {
                        taskList.get(i).setTaskState("in-progress");
                        foundTarget = true;
                        System.out.println("Task with TaskID" + targetID + " was updated to 'in-progress'.");
                    }
                }
                if (!foundTarget) {
                    System.out.println("Task with the ID " + targetID + " does not exist.(mark-in-progress)");
                }

            } else if (parts[0].equalsIgnoreCase("task-cli") && parts[1].equalsIgnoreCase("mark-done")) {
                int targetID = Integer.parseInt(parts[2]);
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getTaskID() == targetID) {
                        taskList.get(i).setTaskState("done");
                        foundTarget = true;
                        System.out.println("Task with TaskID " + targetID + " was updated to 'done'.");
                    }
                }
                if (!foundTarget) {
                    System.out.println("Task with the ID " + targetID + " does not exist.(done)");
                }

            } else if (parts[0].equalsIgnoreCase("task-cli") && parts[1].equalsIgnoreCase("mark-todo")) {
                int targetID = Integer.parseInt(parts[2]);
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getTaskID() == targetID) {
                        taskList.get(i).setTaskState("todo");
                        foundTarget = true;
                        System.out.println("Task with TaskID " + targetID + " was updated to 'todo'.");
                    }
                }
                if (!foundTarget) {
                    System.out.println("Task with the ID " + targetID + " wasn't found.");
                }

            } else if (line.equalsIgnoreCase("task-cli list")) {
                System.out.println("TaskCount: " + Task.taskCount);
                for (Task taskLists : taskList) {
                    System.out.println(taskLists);
                }

            } else if (line.equalsIgnoreCase("task-cli list todo")) {
                boolean foundTodoTasks = false;
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getTaskState().equalsIgnoreCase("todo")) {
                        foundTodoTasks = true;
                        System.out.println(taskList.get(i));
                    }
                }
                if (!foundTodoTasks) {
                    System.out.println("There are no tasks marked as 'todo'.");
                }

            } else if (line.equalsIgnoreCase("task-cli list in-progress")) {
                boolean foundTodoTasks = false;
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getTaskState().equalsIgnoreCase("in-progress")) {
                        foundTodoTasks = true;
                        System.out.println(taskList.get(i));
                    }
                }
                if (!foundTodoTasks) {
                    System.out.println("There are no tasks marked as 'in-progress'.");
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

    public static List<Task> getTaskList() {
        return taskList;
    }
}
