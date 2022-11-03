# User Guide

## Introduction
Duke is a Command Line Interface (CLI) program that tracks a user's tasks (todo, deadline and event) 

## Setting Up
1. Make sure you have Java 11 installed to run this program
2. Download the JAR file from the latest release
3. Create a new folder containing the JAR file
4. Using the terminal, change directory to the folder containing the JAR file.
5. Run the JAR file using the following command: `java -jar CS2113-ip.jar`
6. You should see the following output printed:

```
Hello I'm
                   _      
                  | |     
  _   _ _ __   ___| | ___ 
 | | | | '_ \ / __| |/ _ |
 | |_| | | | | (__| |  __/ 
  \__,_|_| |_|\___|_|\___| 

What you want?
```
## Features 

### Add Task

Allow users to add tasks(todo, deadline, event) to their list of tasks

### Delete Task

Allow users to delete specific task when they want to remove it

### List Task

Allow users to list the tasks in their list 

### Mark Task

Allow users to mark their tasks as done or not done

### Find Task

Allow users to find the tasks by inputting the keyword they want

## Usage

### `todo` - Add a todo task

Adds a todo task to the list of tasks using the command `todo description of task`

- **Example of usage:** 
`todo run`

- **Expected outcome and output:**
a todo task with description run is added to the list of tasks
```
task added
  [T][ ] run
you still have 11 tasks left
-------------------------------------------------
```

### `event` - Add an event task

Adds an event task to the list of tasks using the command `event description /at event date`.
Date will be stored as String

- **Example of usage:**
`event football match /at 2022-10-14`

- **Expected outcome and output:** 
an event task with description football match and date 2022-10-24 is added to the list of tasks
```
task added
  [E][ ] football match (at: 2022-10-24)
you still have 12 tasks left
-------------------------------------------------
```

### `deadline` - Add a deadline task

Adds a deadline task to the list of tasks using the command `deadline description /by deadline date time`.
If format of date and time follows `yyyy-mm-dd`, date of event will be stored as `MMM dd yyyy` format, else date will be
stored as a String.

- **Example of usage:**
`deadline CS2102 assignment /by 2022-10-01 2359`

- **Expected outcome and output:**
a deadline task with description CS2102 assignment and date 2022-10-01 2359 will be added to the list of tasks
```
task added
  [D][ ] CS2102 assignment (by: Oct 1 2022 2359)
you still have 13 tasks left
-------------------------------------------------
```

### `list` - List the tasks

List the tasks that are added to the tasks list using the command `list`

- **Example of usage:** `list`

- **Expected outcome and output**:
a list of tasks will be printed with the description and the date and time if any
```
come uncle show you your tasks
1. [E][ ] football match (at: 2022-10-14)
2. [D][ ] CS2102 assignment (by: Oct 1 2022 2359)
3. [T][ ] run
-------------------------------------------------
```

### `mark` - Mark task as done

Mark the task stated as done using the command `mark task number`

- **Example of usage:** `mark 1`

- **Expected outcome and output:** 
the first task will be marked as done
```
that was fast
  [E][X] football match (at: 2022-10-14)
-------------------------------------------------
```

### `unmark` - Unmark task as incomplete

Unmark the task as incomplete using the command `unmark task number`

- **Example of usage:** `unmark 1`

- **Expected outcome and output:**
the first task wil be unmarked as incomplete
```
can you make up your mind
  [E][ ] football match (at: 2022-10-14)
-------------------------------------------------
```

### `find` - Find task using keyword
 
Prints a list of task including tasks with the keyword inside the description using the command `find keyword`

- **Example of usage:** `find football`

- **Expected outcome and output**: find all related tasks with football in the description
```
Here are the tasks found:
1. [E][ ] football match (at: 2022-10-14)
2. [T][ ] buy football boots
-------------------------------------------------
```
### `bye` - Exits the program
Exits the program and saves the tasks in local text file called duke.txt which is located in a folder
called data

- **Example of usage:** `bye`
- **Expected outcome and output:** tasks are saved in a text file, and program exits
```
bye bye
-------------------------------------------------
```
