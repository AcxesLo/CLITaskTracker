# CLI Task Tracker
CLI application that takes commands to add, update and delete tasks. Tasks will be saved as a .json file.

## Features 
- **Add Task:** Adds a new task with a description, status, creation/update date and an id.
- **Update Task:** Updates the created tasks.
- **Delete Task:** Deletes the created tasks.
- **Mark Task:** Marks the tasks as "in-progress", "done" or back to "todo".
- **List Task:** Lists all tasks, all tasks marked as "in-progress", "done" or "todo".

## Installation 

**Clone the repository:**
````
bash
git clone https://github.com/AcxesLo/CLITaskTracker.git
cd CLITaskTracker
````
**Run the application:**
````
task-cli <command> [arguments]
````

## Usage
````
# Listing all commands
task-cli --help

# Adding a new task
task-cli add "description"

# Updating a task
task-cli update [id] "updated-description"

# Deleting a task
task-cli delete [id]

# Marking a task as in progress
task-cli mark-in-progress [id]

# Marking a task as done
task-cli mark-done [id]

# Listing all tasks
task-cli list

# Listing tasks by status
task-cli list todo
task-cli list in-progress
task-cli list done
````

