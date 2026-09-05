package task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {
    public static int taskCount;
    private int taskID;
    private String taskName;
    public static List<String> taskList = new ArrayList<>();
    private String taskState;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(String taskName,
                String taskState,
                int taskID) {
        this.taskName = taskName;
        this.taskState = taskState;
        this.taskID = taskID;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        addTask(this.taskName);
    }

    public void addTask(String taskName) {
        this.taskList.add(taskName);
    }

    public static void incrementTask() {
        taskCount++;
    }

    public static void decrementTask() {
        taskCount--;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", taskName='" + taskName + '\'' +
                ", taskState='" + taskState + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public void setUpdateDateTime() {
        this.updatedAt = LocalDateTime.now();
    }

    public void setTaskState(String state) {
        this.taskState = state;
    }

    public void setTaskName(String name) {
        this.taskName = name;
    }

    public String getTaskState() {
        return this.taskState;
    }

    public void setTaskList(List<String> taskList) {
        this.taskList = taskList;
    }
    
    public static List<String> getTaskList() {
        return taskList;
    }

    public int getTaskID() {
        return taskID;
    }
}
