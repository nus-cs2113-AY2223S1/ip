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
    7. [Mark a task as done: `done`](#mark-a-task-as-done-mark)
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
6. Type a command and press Enter to execute it.
7. Refer to the Features below for details of each command.

## Features

> Notes about the command format:
> - Words in UPPER_CASE are the parameters to be supplied by the user.
    e.g. in `event NAME /at TIME`, `NAME` and `TIME` are parameters which can be used as `event basketball /at noon`.
> - Extraneous parameters for commands that do not take in parameters will be ignored.

### Listing all tasks: `list`

Lists out the tasks in the task list. The letter in the first pair of [] refers to what type of task it is - T for a todo task, D for a deadline task and E for an event task. The letter in the second pair of [] refers to whether the task is done or not - \<space\> for a task that is not done and X for a task that is done. See [mark](#mark-a-task-as-done-mark).

Format: `list`

```
list
______________________________________
1. [E][ ] basketball (at: noon)

______________________________________

```

### Exiting: `bye`

Exits the application.

Format: `bye`

Sample:
```
bye
______________________________________
Bye. Hope to see you again soon!
───▄▄▄
─▄▀░▄░▀▄
─█░█▄▀░█
─█░▀▄▄▀█▄█▄▀
▄▄█▄▄▄▄███▀


______________________________________
```

### Find task by keyword: `find`

Finds all tasks with names containing a given search term.

Format: `find KEYWORD`

Sample:
```
find basketball
______________________________________
Here are the tasks in your list that contains 'basketball'
1. [E][ ] basketball (at: noon)
______________________________________

```

### Add a ToDo: `todo`

Creates a ToDo item on the task list.

Format: `todo TASK`

Sample:
```
todo read a book
______________________________________
Got it. I've added this task:
  [T][ ] read a book
Now you have 2 tasks in the list
______________________________________

```


### Add an Event: `event`

Use this command to keep track of events you need to attend. If a date or date and time is specified in a clear format, then Duke will be able to understand it and display it in a more helpful form. See more at [understanding dates and times](#understanding-dates-and-times).

Format: `event NAME /at TIME`

Sample:
```
event another Event /at 1234
______________________________________
Got it. I've added this task:
  [E][ ] another Event (at: 1234)
Now you have 3 tasks in the list
______________________________________
```

### Add a Deadline: `deadline`

Use this command to keep track of deadlines you need to meet. If a date or date and time is specified in a clear format, then Duke will be able to understand it and display it in a more helpful form. See more at [understanding dates and times](#understanding-dates-and-times).

Format: `deadline NAME /by TIME`

Sample:
```
deadline return book /by noon
______________________________________
Got it. I've added this task:
  [D][ ] return book (by: noon)
Now you have 4 tasks in the list
______________________________________
```
### Mark a task as done: `done`

Marks a task in your list as completed.

Format: `done INDEX`

Sample:
```
list
______________________________________
1. [E][ ] basketball (at: noon)
2. [T][ ] read a book
3. [E][ ] another Event (at: 1234)
4. [D][ ] return book (by: noon)

______________________________________
done 3
______________________________________
Nice! I've marked this task as done:
  [E][X] another Event (at: 1234)

______________________________________
list
______________________________________
1. [E][ ] basketball (at: noon)
2. [T][ ] read a book
3. [E][X] another Event (at: 1234)
4. [D][ ] return book (by: noon)

______________________________________
```


### Delete a task: `delete`

Deletes a task from your list.

Format: `delete INDEX`

Sample:
```
list
______________________________________
1. [E][ ] basketball (at: noon)
2. [T][ ] read a book
3. [E][X] another Event (at: 1234)
4. [D][ ] return book (by: noon)

______________________________________
delete 2
______________________________________
Noted. I've removed this task: [T][ ] read a book
 Now you have 3 tasks in the list.
______________________________________
list
______________________________________
1. [E][ ] basketball (at: noon)
2. [E][X] another Event (at: 1234)
3. [D][ ] return book (by: noon)

______________________________________
```
## Saving the Data

Duke data is saved to the hard disk automatically after any command that modifies the data. There is no need to save manually.

## Editing the data file

Duke data is saved as a plaintext file at `[JAR file location]/data/duke.txt`. Advanced users are welcome tp update data directly by editing that data file.

> Caution: If your changes to the data file makes it format invalid, there may be data loss.

## FAQ

Q: How do I transfer my data to another computer?

A: Duke is designed for portability. Copy the JAR file and the data folder over to your other computer and you are good to go.

## Command summary

| Action                | Format                   | Example                         |
|-----------------------|--------------------------|---------------------------------|
| List tasks            | `list`                   | `list`                          |
| Exit application      | `bye`                    | `bye`                           |
| Find tasks by keyword | `find KEYWORD`           | `find basketball`               | 
| Add a Todo            | `todo NAME`              | `todo read a book`              |
| Add an event          | `event NAME /at TIME`    | `event another Event /at 1234`  |
| Add a deadline        | `deadline NAME /by TIME` | `deadline return book /by noon` |
| Mark a task as done   | `done INDEX`             | `done 3`                        |
| Delete a task         | `delete INDEX`           | `delete 2`                      |