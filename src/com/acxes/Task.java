package com.acxes;

import java.util.ArrayList;
import java.util.List;

public class Task {
    public static int taskCount;
    private int taskID;
    private String taskName;
    private List<String> taskList = new ArrayList<>();
    private Boolean inProgress;

    public Task(String taskName,
                Boolean inProgress,
                int taskID) {
        this.taskName = taskName;
        this.inProgress = inProgress;
        this.taskID = taskID;

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
                "name='" + taskList + '\'' +
                ", inProgress=" + inProgress + '\'' +
                ", taskID=" + taskID +
                '}';
    }

    public void setInProgress(boolean isDone) {
        this.inProgress = isDone;
    }

    public boolean getInProgress() {
        return this.inProgress;
    }

    public void setTaskList(List<String> taskList) {
        this.taskList = taskList;
    }

    public int getTaskID() {
        return taskID;
    }
}
