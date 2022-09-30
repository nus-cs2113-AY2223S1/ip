# User Guide

King Bob is a software which optimizes the use of the Command Line Interface (CLI) to manage tasks. :computer:

- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding a Todo Task: `todo`](#adding-a-todo-task-todo)
  - [Adding an Event Task: `event`](#adding-an-event-task--event)
  - [Adding a Deadline Task: `deadline`](#adding-a-deadline-task--deadline)
  - [List all Tasks: `list`](#list-all-tasks--list)
  - [Delete a Task: `delete`](#delete-a-task--delete)
  - [Mark a Task: `mark`](#mark-a-task--mark)
  - [Unmark a Task: `unmark`](#unmark-a-task--unmark)
  - [Locate a Task: `find`](#locate-a-task--find)
  - [Exit the Program: `bye`](#exiting-the-program--bye)
  - [Saving the Data](#saving-the-data)
  - [Editing the DataFile](#editing-the-datafile)
- [FAQ](#faq)
- [Command Summary](#command-summary)

-----
<br>

## Quick Start

1. Ensure you have Java `11` installed into your Computer.
2. Download the latest `iP.jar` [here](https://github.com/Franky4566/iP/releases)
3. Copy the file to the folder you want to use as the *homefolder* for your Duke
4. If you are using Windows, open a command prompt terminal and navigate to the folder where your `iP.jar` is stored.
5. For Mac or Linux users, execute step 4 with your respective system terminals.
6. Run `java -jar iP.jar`

If the program is running correctly, you should be able to see the following:

```
 ___  __    ___  ________   ________          ________  ________  ________
|\  \|\  \ |\  \|\   ___  \|\   ____\        |\   __  \|\   __  \|\   __  \
\ \  \/  /|\ \  \ \  \\ \  \ \  \___|        \ \  \|\ /\ \  \|\  \ \  \|\ /_
 \ \   ___  \ \  \ \  \\ \  \ \  \  ___       \ \   __  \ \  \\\  \ \   __  \
  \ \  \\ \  \ \  \ \  \\ \  \ \  \|\  \       \ \  \|\  \ \  \\\  \ \  \|\  \
   \ \__\\ \__\ \__\ \__\\ \__\ \_______\       \ \_______\ \_______\ \_______\
    \|__| \|__|\|__|\|__| \|__|\|_______|        \|_______|\|_______|\|_______|
____________________________________________________________
 Hello! I'm King Bob
 What can I do for you?
____________________________________________________________

```

7. Refer to the [Features](#features) below for the details of commands you can execute.

<br>

## Features

### Adding a Todo Task: `todo`

Adds a todo task to the list. todo tasks only contains a description of the task.

Format: `todo TASK_DESCRIPTION`

- Adds a todo task at the specified `TASK_DESCRIPTION`.

Example:

```
todo read book
____________________________________________________________
Got it. I've added this task:
[T][ ] read book
Now you have 1 task(s) in the list.
____________________________________________________________
```

<br>

### Adding an Event Task : `event`

Adds an event task to the list. An event task requires a task description and the details of the event, such as and
associated time or location.

Format: `event TASK_DESCRIPTION /at EVENT_DETAILS`

- Adds an event task at the specified `TASK_DESCRIPTION`.
- Stores the associated time or location at the specified `EVENT_DETAILS`.

Example:

```
event Kevin Birthday Party /at 7pm tonight
____________________________________________________________
Got it. I've added this task:
[E][ ] Kevin's Birthday Party (at: 7pm tonight)
Now you have 2 task(s) in the list.
____________________________________________________________
```

<br>

### Adding a Deadline Task : `deadline`

Adds a deadline task to the list. A deadline task requires a task description and the due date or time it should be
completed.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`

- Adds a deadline task at the specified `TASK_DESCRIPTION`.
- Stores due date or time at the specified `DEADLINE`.

Example:

```
deadline Buy Kevin's Present /by 5pm
____________________________________________________________
Got it. I've added this task:
[D][ ] Buy Kevin's Present (by: 5pm)
Now you have 3 task(s) in the list.
____________________________________________________________
```

<br>

### List all Tasks : `list`

Shows all tasks stored in King Bob.

Format: `list`

Example:

```
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] read book
2.[E][ ] Kevin's Birthday Party (at: 7pm tonight)
3.[D][ ] Buy Kevin's Present (by: 5pm)
____________________________________________________________
```

<br>

### Delete a Task : `delete`

Deletes a task in the list.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`.
- This index refers to the index number shown in the task list.
- The index number **must be a positive integer** 1, 2, 3...

Example:

```
delete 1
____________________________________________________________
Noted. I've removed this task:
[T][ ] read book
Now you have 2 task(s) in the list.
____________________________________________________________
```

<br>

### Mark a Task : `mark`

Marks a task in the list as done.

Format: `mark INDEX`

- Marks the task at the specified `INDEX`.
- This index refers to the index number shown in the task list.
- The index number **must be a positive integer** 1, 2, 3...

Example:

```
mark 2
____________________________________________________________
Nice! I've marked this task as done:
[X]  Buy Kevin's Present
____________________________________________________________
```

<br>

### Unmark a Task : `unmark`

Unmarks a task in the list as not done.

Format: `unmark INDEX`

- Unmarks the task at the specified `INDEX`.
- This index refers to the index number shown in the task list.
- The index number **must be a positive integer** 1, 2, 3...

Example:

```
unmark 2
____________________________________________________________
OK, I've marked this task as not done:
[ ]  Buy Kevin's Present
____________________________________________________________
```

<br>

### Locate a Task : `find`

Find any task(s) that contains the specified keywords.

Format: `find KEYWORDS`

- The search is not case-sensitive eg. `kevin` will match `Kevin`.
- The order of the Keywords matter eg. `Birthday Party` will not match `Party Birthday`.
- Partial words will also be matched eg. `kev` will match `kevin`
- Only the task description is searched

Example:

All `find kev`,`find kevin` and `find Kevin` return the same tasks 1 and 2

```
find kev
____________________________________________________________
Here are the matching tasks in your list:
1.[E][ ] Kevin Birthday Party (at: 7pm tonight)
2.[D][ ] Buy Kevin's Present (by: 5pm)
____________________________________________________________

find kevin
____________________________________________________________
Here are the matching tasks in your list:
1.[E][ ] Kevin Birthday Party (at: 7pm tonight)
2.[D][ ] Buy Kevin's Present (by: 5pm)
____________________________________________________________

find Kevin
____________________________________________________________
Here are the matching tasks in your list:
1.[E][ ] Kevin Birthday Party (at: 7pm tonight)
2.[D][ ] Buy Kevin's Present (by: 5pm)
____________________________________________________________
```

<br>

### Exiting the Program : `bye`

Closes and exits program.

Format: `bye`

Example:

```
bye
____________________________________________________________
Bye. Come back soon! :)
____________________________________________________________
```

<br>

### Saving the Data

King Bob automatically saves your task list in your local hard disk after exiting the program. There is no need to save
manually :).

<br>

### Editing the DataFile

The task list is saved as a text file located at `[JAR FILE LOCATION]/data/dukesaved.txt`. Users are welcome to edit and
update the data directly by editing this text file.
> **Caution!**
> Please make sure changes made to the data file are in the valid format!

<br>

-----
## FAQ

**Q**: How do I transfer data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous list home folder.

<br>

**Q**: I have forgotten to specify a `TASK_DESCRIPTION`! Will it add an empty task?

**A**: If the `TASK_DESCRIPTION` is empty not to worry, King Bob will prompt you for one and you can retype your command.

-----
<br>

## Command Summary

| Command        | Format                                     | Example                               |
|----------------|--------------------------------------------|---------------------------------------|
| Add : Todo     | `todo TASK_DESCRIPTION`                    | `todo read book`                      |
| Add : Event    | `event TASK_DESCRIPTION /at EVENT_DETAILS` | `event book club /at 9pm`             |
| Add : Deadline | `deadline TASK_DESCRIPTION /by DEADLINE`   | `deadline CS2113 iP /by 30 Sept 2359` |
| List           | `list`                                     | `list`                                |
| Delete         | `delete INDEX`                             | `delete 2`                            |
| Mark           | `mark INDEX`                               | `mark 3`                              |
| Unmark         | `unmark INDEX`                             | `unmark 4`                            |
| Find           | `find KEYWORDS`                            | `find book`                           |

<br>


 







