package main;

import json.JsonFile;
import task.TaskService;

public class Main {

    //TODO
    // create enum for task status
    // AtomicInteger for task-id
    // HashMap to save up the tasks
    // from if to switch statements

    static void main(String[] args) {

        // <test-command>
        // task-cli add "groceries"

        TaskService.taskCLILogic();
    }
}



