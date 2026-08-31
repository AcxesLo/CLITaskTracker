package com.acxes;

import java.util.List;

public class Task {
    public static int taskCount;
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

    public static void decrementTask() {
        taskCount--;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + taskName + '\'' +
                ", inProgress=" + inProgress + '\'' +
                ", taskID=" + taskID +
                '}';
    }

    public void setTaskName(List<String> taskName) {
        this.taskName = taskName;
    }

    public int getTaskID() {
        return taskID;
    }
}
