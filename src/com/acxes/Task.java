package com.acxes;

import java.util.List;

public class Task {
    private static int taskCount;
    private int taskID;
    private List<String> taskName;
    private Boolean inProgress;

    public Task(List<String> taskName,
                Boolean inProgress,
                int taskID) {
        this.taskName = taskName;
        this.inProgress = inProgress;
        this.taskID = taskID;
    }

    public static void incrementTask() {
        taskCount++;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + taskName + '\'' +
                ", inProgress=" + inProgress + '\'' +
                ", taskID=" + taskID +
                '}';
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public int getTaskID() {
        return taskID;
    }

    public List<String> getTaskName() {
        return taskName;
    }
}
