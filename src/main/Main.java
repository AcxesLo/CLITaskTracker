package main;

import json.JsonFile;
import task.TaskLogic;

public class Main {

    //TODO
    // create enum for task status
    // AtomicInteger for task-id
    // HashMap to save up the tasks
    // from if to switch statements

    static void main(String[] args) {
        TaskLogic.taskCLILogic();
        JsonFile.writeJsonFile();
    }
}



