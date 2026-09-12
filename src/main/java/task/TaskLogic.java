package task;

import enums.TaskStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskLogic {
    private static int taskIDCount;
    private static String taskText;

    public void listCommands() {
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
                + "task-cli list done\n"
                + "write json"
                + "exit");
    }

    public void addTask(String[] parts, List<Task> taskList) {
        taskText = String.join(" ", Arrays.copyOfRange(parts, 2, parts.length));
        taskText = taskText.replaceAll("^\"|\"$", "");

        taskIDCount++;
        taskList.add(new Task(taskText, TaskStatus.TODO.status, taskIDCount));
        Task.incrementTask();

        System.out.println("Task added successfully (ID: "
                + taskIDCount
                + " )");
    }

    public void updateTask(String[] parts, List<Task> taskList, Boolean foundTarget) {
        try {
            int targetID = Integer.parseInt(parts[2]);

            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getTaskID() == targetID) {

                    taskText = String.join(" ", Arrays.copyOfRange(parts, 3, parts.length));
                    taskText = taskText.replaceAll("^\"|\"$", "");

                    taskList.get(i).setTaskList(Collections.singletonList(taskText));
                    taskList.get(i).setUpdateDateTime();
                    taskList.get(i).setTaskName(taskText);
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

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("##invalid_input \n" +
                    "<task-id is missing>");
        }
    }

    public void deleteTask(String[] parts, List<Task> taskList, Boolean foundTarget) {
        try {
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
                System.out.println("Task with the ID " + targetID + " does not exist.");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("##invalid_input \n" +
                    "<task-id is missing>");
        }

    }

    public void markTodo(String[] parts, List<Task> taskList, Boolean foundTarget) {
        try {
            int targetID = Integer.parseInt(parts[2]);

            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getTaskID() == targetID) {
                    taskList.get(i).setTaskState(TaskStatus.TODO.status);
                    foundTarget = true;
                    System.out.println("Task with TaskID"
                            + targetID
                            + " was updated to 'todo'.");
                }
            }
            if (!foundTarget) {
                System.out.println("Task with the ID "
                        + targetID
                        + " does not exist.");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("##invalid_input \n" +
                    "<task-id is missing>");
        }

    }

    public void markInProgress(String[] parts, List<Task> taskList, Boolean foundTarget) {
        try {
            int targetID = Integer.parseInt(parts[2]);
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getTaskID() == targetID) {
                    taskList.get(i).setTaskState(TaskStatus.IN_PROGRESS.status);
                    foundTarget = true;
                    System.out.println("Task with TaskID"
                            + targetID
                            + " was updated to 'in-progress'.");
                }
            }
            if (!foundTarget) {
                System.out.println("Task with the ID "
                        + targetID
                        + " does not exist.");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("##invalid_input \n" +
                    "<task-id is missing>");
        }

    }

    public void markDone(String[] parts, List<Task> taskList, Boolean foundTarget) {
        try {
            int targetID = Integer.parseInt(parts[2]);
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getTaskID() == targetID) {
                    taskList.get(i).setTaskState(TaskStatus.DONE.status);
                    foundTarget = true;
                    System.out.println("Task with TaskID"
                            + targetID
                            + " was updated to 'done'.");
                }
            }
            if (!foundTarget) {
                System.out.println("Task with the ID "
                        + targetID
                        + " does not exist.");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("##invalid_input \n" +
                    "<task-id is missing>");
        }

    }

    public void listTasks(List<Task> taskList) {
        System.out.println("TaskCount: " + Task.taskCount);
        for (Task taskLists : taskList) {
            System.out.println(taskLists);
        }
    }

    public void listTasksTodo(List<Task> taskList) {
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
    }

    public void listTasksInProgress(List<Task> taskList) {
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
    }

    public void listTasksDone(List<Task> taskList) {
        boolean foundTodoTasks = false;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTaskState().equalsIgnoreCase("done")) {
                foundTodoTasks = true;
                System.out.println(taskList.get(i));
            }
        }
        if (!foundTodoTasks) {
            System.out.println("There are no tasks marked as 'done'.");
        }
    }
}
