# User Guide

Juke is a command-line task-tracking bot. Juke lets a user add, delete and mark multiple tasks which are saved when the program is terminated.

## Quick Start
1. Ensure that you have Java 11 installed.
2. Download the latest `Duke.jar` from this repository.
3. Copy the file into a folder. The .txt file that stores the task list will be saved in a data folder one directory above the folder where the jar file sits.
4. On your terminal, navigate to the folder where the jar file resides and run: `java -jar ip.jar`
5. You should see a welcome message if the application successfully starts. 
6. Input any of the supported commands
7. Exit the application with the "bye" command.

## Features
The feature set comprises the following:

### list tasks

Prints out all the tasks currently added to the tracker.

**Format:** `list`
```
list
        ------------------------

        1. [T] [ ] buy milk

        ------------------------
```

### new todo

Adds a new Todo task to the list.

**Format:** `todo {description}`
```
todo read book
        ------------------------
        Got it. I've added this task:
        [T] [ ] read book

        Now you have 1 task(s) in the list.
        ------------------------
```

### new event

Adds a new Event task to the list.

**Format:** `event {description} /at {time string}`
```
event Jake's birthday /at 5 sep
        ------------------------
        Got it. I've added this task:
        [E] [ ] Jake's birthday (at: 5 sep)

        Now you have 1 task(s) in the list.
        ------------------------
```

### new deadline

Adds a new Deadline task to the list.

**Format:** `deadline {description} /by {time string}`
```
deadline write report /by thursday night
        ------------------------
        Got it. I've added this task:
        [D] [ ] write report (by: thursday night)

        Now you have 1 task(s) in the list.
        ------------------------
```

### mark task

Marks the specified task as done.

**Format:** `mark {TASK NUMBER}`
```
mark 2
        ------------------------

        1. [T] [ ] buy milk

        2. [E] [X] Jake's birthday (at: 5 sep)

        3. [D] [ ] write report (by: thursday night)

        ------------------------
```

### unmark task

sets a task as unmarked and prints out the list reflecting the change.

**Format:** `unmark {TASK NUMBER}`
```
unmark 2
        ------------------------

        1. [T] [ ] buy milk

        2. [E] [ ] Jake's birthday (at: 5 sep)

        3. [D] [ ] write report (by: thursday night)

        ------------------------
```

### delete task

removes the task specified by the number from the tracker and displays the changed list.

**Format:** `delete {TASK NUMBER}`
```
delete 2
        ------------------------
        Got it. I've removed this task:
        [E] [ ] Jake's birthday (at: 5 sep)

        Now you have 2 task(s) in the list.
        ------------------------

        ------------------------

        1. [T] [X] buy milk

        2. [D] [ ] write report (by: thursday night)

        ------------------------
```

### find with keyword

finds all tasks whose description has a match with the inputted keyword string.

**Format:** `find {keyword string}`
```
find wri

        Here are the matching tasks:
        ------------------------

        1. [D] [ ] write report (by: thursday night)

        2. [T] [ ] write story

        ------------------------
```

### bye

Exits the program, saving your current task list in a .txt file.

**Format:** `bye`
```
See you again!
```
