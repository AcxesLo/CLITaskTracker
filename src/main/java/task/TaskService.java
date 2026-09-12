package task;

import json.JsonFile;

import java.util.*;

public class TaskService {
    private static String line;
    private static String[] parts;
    private static List<Task> taskList = new ArrayList<>();
    private static boolean foundTarget = false;
    private static Scanner scanner = new Scanner(System.in);
    private static TaskLogic taskService = new TaskLogic();

    public static void taskCLILogic() {
        while (true) {
            System.out.print("> ");

            line = scanner.nextLine();
            parts = line.split("\\s+");

            if (line.isEmpty()) {
                continue;

            } else if (line.equalsIgnoreCase("exit")) {
                break;

            } else if (line.equalsIgnoreCase("task-cli --help")) {
                taskService.listCommands();

            } else if (line.equalsIgnoreCase("task-cli list")) {
                taskService.listTasks(taskList);

            } else if (line.equalsIgnoreCase("task-cli list todo")) {
                taskService.listTasksTodo(taskList);

            } else if (line.equalsIgnoreCase("task-cli list in-progress")) {
                taskService.listTasksInProgress(taskList);

            } else if (line.equalsIgnoreCase("task-cli list done")) {
                taskService.listTasksDone(taskList);

            } else if (line.equalsIgnoreCase("write json")) {
                JsonFile.writeJsonFile();

            } else if (parts[0].equalsIgnoreCase("task-cli")) {
                switch (parts[1].toLowerCase()) {
                    case "add":
                        taskService.addTask(parts, taskList);
                        break;
                    case "update":
                        taskService.updateTask(parts, taskList, foundTarget);
                        break;
                    case "delete":
                        taskService.deleteTask(parts, taskList, foundTarget);
                        break;
                    case "mark-todo":
                        taskService.markTodo(parts, taskList, foundTarget);
                        break;
                    case "mark-in-progress":
                        taskService.markInProgress(parts, taskList, foundTarget);
                        break;
                    case "mark-done":
                        taskService.markDone(parts, taskList, foundTarget);
                        break;
                }
            } else {
                System.out.println("Wrong command.");
            }
        }

    }

    public static List<Task> getTaskList() {
        return taskList;
    }

}
