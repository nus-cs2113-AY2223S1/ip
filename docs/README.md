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
    7. [Mark a task as done: `done`](#mark-a-task-as-done-done)
    8. [Delete a task: `delete`](#delete-a-task-delete)
3. [Command summary](#command-summary)
4. [Credits](#credits)

## Quick start
1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest duke.jar release.
3. Copy the file to the folder you want to use as your *home folder* for Duke.
4. Open the command line in that folder and run the command `java -jar duke.jar`.
5. When you first run the application, a Duke will greet you.

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

1. Type a command and press Enter to execute it.
2. Refer to the Features below for details of each command.

## Features

> Notes about the command format:
> - Words in UPPER_CASE are the parameters to be supplied by the user.
    e.g. in `event NAME /at TIME`, `NAME` and `TIME` are parameters which can be used as `event basketball /at noon`.
> - `TIME` will be stored as plain string. The onus is up to the user to save `TIME` data in a format that is meaningful to them.
### Listing all tasks: `list`

Lists out all the tasks in the task list. 
Tasks types are displayed within the first square bracket, where the letter inside
indicated what type of task it is - 
- T for a todo task
- D for a deadline task 
- E for an event task

The letter X in the second square bracket represents tasks that are marked as done
and no letter indicates that the task is yet to be done.

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

Use this command to keep track of events you need to attend.

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

Use this command to keep track of deadlines you need to meet.

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

Marks a task in your list as done.

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

Duke data is saved to the hard disk automatically after any command that modifies the data.

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

## Credits
UserGuide formatting adapted from @owenl131
