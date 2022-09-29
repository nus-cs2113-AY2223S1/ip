# User Guide (Duke)

Duke is a **desktop application for tracking tasks**, optimized for use via a Command Line Interface (CLI).

Reference for this User Guide was taken from https://se-education.org/addressbook-level3/UserGuide.html.

## Quick Start

1. Ensure that you have the latest version of Java 11 installed.
2. Download the latest `Duke.jar` from this repository.
3. Copy the file to the folder you want to use as the home folder for your task tracker.
4. Open your terminal, navigate to your home folder from step 3 and run this command: java -jar Duke.jar
5. You should see the following welcome message:
```
Hello from
 ____        _        
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

____________________________________________
Hello! I'm Duke.
What can I do for you?
____________________________________________
```
6. Input a command and press 'Enter' to execute it. Some example commands you can try are:
   - `todo Read book` : Adds a todo task called 'Read book'.
   - `list` : Lists all tasks added by the user.
   - `delete 2` : Deletes the second task as shown in the list.
7. Input the command 'bye' to exit the application.

## Features 

### Feature 1 - Add a ToDo task.

Keyword: `todo`
Format: `todo TASK_NAME`

Adds a todo task to the task list.
```
todo read book
        ____________________________________________
        Got it. I've added this task:
        [T][ ] read book
        Now you have 1 tasks in the list.
        ____________________________________________
```

### Feature 2 - Add a Deadline task.

Keyword: `deadline`
Format: `deadline TASK_NAME /by TASK_DEADLINE`

Adds a deadline task to the task list.
```
deadline submit homework /by midnight
        ____________________________________________
        Got it. I've added this task:
        [D][ ] submit homework (by: midnight)
        Now you have 2 tasks in the list.
        ____________________________________________
```

### Feature 3 - Add an Event task.

Keyword: `event`
Format: `event TASK_NAME /by EVENT_DATE`

Adds an event task to the task list.
```
event class test /at tomorrow afternon
        ____________________________________________
        Got it. I've added this task:
        [E][ ] class test (at: tomorrow afternon)
        Now you have 3 tasks in the list.
        ____________________________________________
```

### Feature 4 - Mark existing task as done.

Keyword: `mark`
Format: `mark TASK_INDEX`

Marks a task in the task list as done.
```
mark 1
        ____________________________________________
        Nice! I've marked this task as done:
            [T][X] read book
        ____________________________________________
```

### Feature 5 - Mark existing task as not done.

Keyword: `unmark`
Format: `unmark TASK_INDEX`

Marks a task in the task list as not done.
```
unmark 1
        ____________________________________________
        Ok. I've marked this task as not done yet:
            [T][ ] read book
        ____________________________________________
```

### Feature 7 - List all tasks added by the user.

Keyword: `list`
Format: `list`

List all tasks added by the user to the task list.
```
list
        ____________________________________________
        [T][ ] 1. read book
        [D][ ] 2. submit homework (by: midnight)
        [E][ ] 3. class test (at: tomorrow afternon)
        ____________________________________________
```

### Feature 8 - Delete an existing task.

Keyword: `delete`
Format: `delete 3`

Deletes an existing task in the task list.
```
delete 3
        ____________________________________________
        Noted. I've removed this task:
            [E][ ] class test
        Now you have 2 tasks in the list.
        ____________________________________________

```