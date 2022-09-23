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
3. [Understanding Dates and Times](#understanding-dates-and-times)
4. [Saving the Data](#saving-the-data)
5. [Editing the data file](#editing-the-data-file)
6. [FAQ](#faq)
7. [Command summary](#command-summary)

## Quick start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest duke.jar from here.
3. Copy the file to the folder you want to use as the *home folder* for your todo list. 
4. Open the command line in that folder and run the command `java -jar duke.jar`.
5. When you first run the application, a greeting will be displayed.
```
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

    ____________________________________________________________

     Hello! I'm Duke
     What can I do for you?
    ____________________________________________________________
```
6. Type the command and press Enter to execute it.
7. Refer to the Features below for details of each command. 

## Features 

> Notes about the command format:
> - Words in UPPER_CASE are the parameters to be supplied by the user.
 e.g. in `event NAME /at DATE`, `NAME` and `DATE` are parameters which can be used as `event Christmas party /at 2022-12-25`.
> - Extraneous parameters for commands that do not take in parameters will be ignored.

### Viewing help: `help`

Displays the list of commands available.

Format: `help`

Sample:
```
help
    ____________________________________________________________

     Duke - CLI based Task list manager
     
     > bye - exits the application
     > list - list out all your tasks
     > todo NAME - adds a todo task
     > deadline NAME /by DATE_TIME - adds a deadline task
     > event NAME /at DATE_TIME - adds an event task
     > delete INDEX - deletes a task
     > mark INDEX - marks a task as done
     > unmark INDEX - marks a task as undone
     > find KEYWORD - finds a task by name
     
     Full guide at https://owenl131.github.io/ip 
    ____________________________________________________________
    
```

### Listing all tasks: `list`

Lists out the tasks in the task list. The letter in the first pair of [] refers to what type of task it is - T for a todo task, D for a deadline task and E for an event task. The letter in the second pair of [] refers to whether the task is done or not - \<space\> for a task that is not done and X for a task that is done. See [mark](#mark-a-task-as-done-mark).

Format: `list`

Sample:
```
list
    ____________________________________________________________

     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] return book (by: Sunday)
     3.[E][ ] project meeting (at: Mon 2-4pm)
    ____________________________________________________________
```

### Exiting: `bye`

Exits the application gracefully.

Format: `bye`

Sample:
```
bye
    ____________________________________________________________

     Bye. Hope to see you again soon!
    ____________________________________________________________
```

### Find task by keyword: `find`

Finds all tasks with names containing a given keyword.

Format: `find KEYWORD`

Sample:
```
find project
    ____________________________________________________________

     Result of search for: project
     
     Here are the tasks in your list:
     1.[E][ ] project meeting (at: Mon 2-4pm)
    ____________________________________________________________
```

### Add a ToDo: `todo`

Creates a ToDo.

Format: `todo NAME`

Sample:
```
todo borrow book
    ____________________________________________________________

     added: [T][ ] borrow book
    ____________________________________________________________
```

### Add an Event: `event`

Use this command to keep track of events you need to attend. If a date or date and time is specified in a clear format, then Duke will be able to understand it and display it in a more helpful form. See more at [understanding dates and times](#understanding-dates-and-times).

Format: `event NAME /at DATETIME`

Sample:
```
event project meeting /at Mon 2-4pm
    ____________________________________________________________

     added: [E][ ] project meeting (at: Mon 2-4pm)
    ____________________________________________________________


event christmas party /at 2022-12-25 2:00pm
    ____________________________________________________________

     added: [E][ ] christmas party (at: Sun, 25 Dec 2022, 14:00)
    ____________________________________________________________
```

### Add a Deadline: `deadline`

Use this command to keep track of deadlines you need to meet. If a date or date and time is specified in a clear format, then Duke will be able to understand it and display it in a more helpful form. See more at [understanding dates and times](#understanding-dates-and-times).

Format: `deadline NAME /by DATETIME`

Sample: 
```
deadline return book /by Sunday
    ____________________________________________________________

     added: [D][ ] return book (by: Sunday)
    ____________________________________________________________
```

### Mark a task as done: `mark`

Marks a task in your list as completed.

Format: `mark INDEX`

Sample:
```
list
    ____________________________________________________________

     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] return book (by: Sunday)
     3.[E][ ] project meeting (at: Mon 2-4pm)
     4.[D][ ] do homework (by: no idea :-p)
    ____________________________________________________________

mark 2
    ____________________________________________________________

     Nice! I've marked this task as done:
      [D][X] return book (by: Sunday)
    ____________________________________________________________
```

### Unmark a task as done: `unmark`

Marks a task in your list as incomplete.

Format: `unmark INDEX`

Sample: 
```
list
    ____________________________________________________________

     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][X] return book (by: Sunday)
     3.[E][ ] project meeting (at: Mon 2-4pm)
     4.[D][ ] do homework (by: no idea :-p)
    ____________________________________________________________

unmark 2
    ____________________________________________________________

     OK, I've marked this task as not done yet:
      [D][ ] return book (by: Sunday)
    ____________________________________________________________
```

### Delete a task: `delete`

Deletes a task from your list.

Format: `delete INDEX`

Sample:
```
list
    ____________________________________________________________

     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] return book (by: Sunday)
     3.[E][ ] project meeting (at: Mon 2-4pm)
    ____________________________________________________________

delete 2
    ____________________________________________________________

     OK, I've deleted [D][ ] return book (by: Sunday)
     
     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[E][ ] project meeting (at: Mon 2-4pm)
    ____________________________________________________________
```

## Understanding Dates and Times

Specifying dates and times in a standard format lets Duke understand them better. Some understandable dates are 2022-12-25, 2022/12/25, 25/12/2022, 25 December 2022 and 25 dec 2022. If a year is not specified, e.g. 25 dec, then it defaults to the current year. Some understandable times are 23:59, 12:00, 2:30am and 4:15pm.

## Saving the Data

Duke data is saved to the hard disk automatically after any command that modifies the data. There is no need to save manually.

## Editing the data file

Duke data is saved as a plaintext file at `[JAR file location]/data/duke.txt`. Advanced users are welcome tp update data directly by editing that data file.

> Caution: If your changes to the data file makes it format invalid, there may be data loss.

## FAQ

Q: How do I transfer my data to another computer?

A: Duke is designed for portability. Copy the JAR file and the data folder over to your other computer and you are good to go.

## Command summary

| Action                | Format                        | Example                                   |
|-----------------------|-------------------------------|-------------------------------------------|
| View help             | `help`                        | `help`                                    |
| List tasks            | `list`                        | `list`                                    |
| Exit application      | `bye`                         | `bye`                                     |
| Find tasks by keyword | `find KEYWORD`                | `find project`                            | 
| Add a Todo            | `todo NAME`                   | `todo Do the team project`                |
| Add an event          | `event NAME /at DATETIME`     | `event Xmas party /at 25 dec 12:00pm`     |
| Add a deadline        | `deadline NAME /by DATETIME`  | `deadline iP /by tonight`                 |
| Mark a task as done   | `mark INDEX`                  | `mark 1`                                  |
| Unmark a task as done | `unmark INDEX`                | `unmark 1`                                |
| Delete a task         | `delete INDEX`                | `delete 1`                                |