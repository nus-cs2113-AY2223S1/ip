# User Guide
Duke is a desktop app for managing a todo list, optimized for user via a Command Line Interface (CLI).

Table of Contents
1. [Quick start](#quick-start)
2. [Features](#features)
    1. [Listing all tasks: `list`](#listing-all-tasks-list)
    2. [Exiting: `bye`](#exiting-bye)
    3. [Find task by keyword: `find`](#find-task-by-keyword-find)
    4. [Add a ToDo: `todo`](#add-a-todo-todo)
    5. [Add an Event: `event`](#add-an-event-event)
    6. [Add a Deadline: `deadline`](#add-a-deadline-deadline)
    7. [Mark a task as done: `mark`](#mark-a-task-as-done-mark)
    8. [Delete a task: `delete`](#delete-a-task-delete)
3. [Saving the Data](#saving-the-data)
4. [Editing the data file](#editing-the-data-file)
5. [FAQ](#faq)
6. [Command summary](#command-summary)

## Quick start
1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest duke.jar from here.
3. Copy the file to the folder you want to use as the *home folder* for your todo list.
4. Open the command line in that folder and run the command `java -jar duke.jar`.
5. When you first run the application, a greeting will be displayed.


```
______________________________________

______________________________________
______________________________________
Hello! I'm Duke!
───▄▄▄
─▄▀░▄░▀▄
─█░█▄▀░█
─█░▀▄▄▀█▄█▄▀
▄▄█▄▄▄▄███▀

How can I help you?
______________________________________
Please enter your taskList command: (send 'bye' to exit)
```


## Command summary

| Action                | Format                       | Example                               |
|-----------------------|------------------------------|---------------------------------------|
| List tasks            | `list`                       | `list`                                |
| Exit application      | `bye`                        | `bye`                                 |
| Find tasks by keyword | `find KEYWORD`               | `find project`                        | 
| Add a Todo            | `todo NAME`                  | `todo Do the team project`            |
| Add an event          | `event NAME /at DATETIME`    | `event Xmas party /at 25 dec 12:00pm` |
| Add a deadline        | `deadline NAME /by DATETIME` | `deadline iP /by tonight`             |
| Mark a task as done   | `done INDEX`                 | `done 1`                              |
| Delete a task         | `delete INDEX`               | `delete 1`                            |