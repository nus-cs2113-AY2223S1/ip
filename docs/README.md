# User Guide

Duke is a desktop app for managing a todo list, optimized for user via a Command Line Interface (CLI).

Table of Contents

1. [Quick start](#quick-start)
2. [Features](#features) 
    1. [Viewing help: `help`](#viewing-help-help)
    2. [Listing all tasks: `list`](#listing-all-tasks-list)
    3. [Exiting: `bye`](#exiting-bye)
    4. [Find task by keyword: `find`](#find-task-by-keyword-find)
    5. [Add a ToDo: `todo`](#add-a-todo-todo)
    6. [Add an Event: `event`](#add-an-event-event)
    7. [Add a Deadline: `deadline`](#add-a-deadline-deadline)
    8. [Mark a task as done: `mark`](#mark-a-task-as-done-mark)
    9. [Unmark a task as done: `unmark`](#unmark-a-task-as-done-unmark)
    10. [Delete a task: `delete`](#delete-a-task-delete)
3. [Saving the Data](#saving-the-data)
4. [Editing the data file](#editing-the-data-file)
5. [FAQ](#faq)
6. [Command summary](#command-summary)

## Quick start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest duke.jar from here.
3. Copy the file to the folder you want to use as the *home folder* for your todo list. 
4. Open the command line in that folder and run the command `java -jar duke.jar`.
5. Type the command and press Enter to execute it.
6. Refer to the Features below for details of each command. 

## Features 

> Notes about the command format:
> - Words in UPPER_CASE are the parameters to be supplied by the user.
 e.g. in `event NAME /at DATE`, `NAME` and `DATE` are parameters which can be used as `event Christmas party /at 2022-12-25`.
> - Extraneous parameters for commands that do not take in parameters will be ignored.

### Viewing help: `help`

Displays the list of commands available.

Format: `help`

### Listing all tasks: `list`

Lists out the tasks in the task list.

Format: `list`

### Exiting: `bye`

Exits the application gracefully.

Format: `bye`

### Find task by keyword: `find`

Format: `find KEYWORD`

### Add a ToDo: `todo`

Format: `todo NAME`

### Add an Event: `event`

Format: `event NAME /at DATETIME`

### Add a Deadline: `deadline`

Format: `deadline NAME /by DATETIME`

### Mark a task as done: `mark`

Format: `mark INDEX`

### Unmark a task as done: `unmark`

Format: `unmark INDEX`

### Delete a task: `delete`

Format: `delete INDEX`

## Saving the Data

Duke data is saved to the hard disk automatically after any command that modifies the data. There is no need to save manually.

## Editing the data file

Duke data is saved as a plaintext file at `[JAR file location]/data/duke.txt`. Advanced users are welcome tp update data directly by editing that data file.

> Caution: If your changes to the data file makes it format invalid, there may be data loss.

## FAQ

Q: How do I transfer my data to another computer?
A: Duke is designed for portability. Copy the JAR file and the data folder over to your other computer and you are good to go.

## Command summary

| Action | Format | Example |
|--------|--------|---------|
| View help | help | help |
| List tasks | list | list |
| Exit application | bye | bye |
| Find tasks by keyword | find KEYWORD | find project | 
| Add a Todo | todo NAME | todo Do my tP |
| Add an event | event NAME /at DATETIME | |
| Add a deadline | deadline NAME /by DATETIME | |
| Mark a task as done | mark INDEX | mark 1 |
| Unmark a task as done | unmark INDEX | unmark 1 |
| Delete a task | delete INDEX | delete 1 |