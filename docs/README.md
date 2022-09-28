# User Guide

Dude is a command-line interface (CLI) program for managing tasks. This tool can be used to track deadlines, events, and to-dos, and is designed for fast typists who are accustomed to the command line.

## Features 

In Dude, there are three types of tasks you can track. They are:
1. To-dos
   - A task to be completed at some point in the future.
2. Deadlines
   - A task that needs to be performed by a specified date and time.
3. Events
   - A task that needs to be performed at a specific date and time.

### Task management

Using the task management commands, you can add and delete tasks, see a list of tracked tasks and search for specific tasks by description, and mark tasks as complete or incomplete. By default, tasks are marked incomplete.

### File storage

Tasks are stored in the file `dude.txt` in the directory where the .JAR file is located. This file can be copied along with the .JAR file in order to make Dude portable.

## Usage

An installation of [Java](https://java.com) is required in order to run this program. To launch the program, open a command prompt and run `java -jar ip.jar`

### `todo` - Add a to-do

Adds a to-do with its description to the task list.

Example of usage: 

`todo Finish individual project`

Expected output:


```
OK, dude, I've added this task: 
[T][ ] Finish individual project
You have 1 tasks in the list.
```

### `deadline` - Add a deadline

Adds a deadline with its description and due date to the task list.

Example of usage:

`deadline Finish team project /by 20 October`

Expected output:


```
OK, dude, I've added this task: 
[D][ ] Finish team project (by: 20 October)
You have 2 tasks in the list.
```

### `event` - Add a event

Adds an event with its description and associated date to the task list.

Example of usage:

`event CS2102 Exam /at 3 October`

Expected output:


```
OK, dude, I've added this task: 
[E][ ] CS2102 Exam (at: 3 October)
You have 3 tasks in the list.
```

### `list` - List tasks

Lists the current tasks in the database.

Example of usage:

`list`

Expected output:


```
1.[T][ ] Finish individual project
2.[D][ ] Finish team project (by: 20 October)
3.[E][ ] CS2102 Exam (at: 3 October)
```

### `mark` and `unmark` - Set the status of a task

Changes the status of a task to completed or not completed respectively. Use the position of the task in the list to refer to the task.

Example of usage:

`mark 1`

Expected output:


```
Okay, dude, I've marked this task as done: 
Finish individual project
```

### `find` - Searches task list

Searches the task list for tasks matching the search term.

Example of usage:

`find project`

Expected output:


```
1.[T][X] Finish individual project
2.[D][ ] Finish team project (by: 20 October)
```

### `delete` - Deletes task from list

Deletes a task from the task list. Use the position of the task in the list to refer to the task.

Example of usage:

`delete 2`

Expected output:


```
Deleting task: Finish team project
```

### `bye` - Exits program

Exits the program. 
Example of usage:

`bye`

Expected output:


```
Catch you later, dude.
```