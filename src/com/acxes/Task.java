package com.acxes;

public class Task {
    private static int taskCount;
    private int taskID;
    private String name;
    private Boolean inProgress;

    public Task(String name, Boolean inProgress, int taskID) {
        this.name = name;
        this.inProgress = inProgress;
        this.taskID = taskID;
    }

    public static void incrementTask() {
        taskCount++;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
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

    public String getName() {
        return name;
    }
}
