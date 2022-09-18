# User Guide

Duke is a desktop app for managing a todo list, optimized for user via a Command Line Interface (CLI).

Table of Contents

1. [Quick start]()
2. [Features]() 

## Quick start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest duke.jar from here.
3. Copy the file to the folder you want to use as the *home folder* for your todo list. 
4. Open the command line in that folder and run the command `java -jar duke.jar`.
5. Type the command and press Enter to execute it.
6. Refer to the Features below for details of each command. 

## Features 

> Notes about the command format:
> - Words in UPPER_CASE are the parameters to be supplied by the user. e.g. in `event NAME /at DATE`, `NAME` and `DATE` are parameters which can be used as `event Christmas party /at 2022-12-25`.
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
