# User Guide
_Bob_ is a **Command Line Application** that helps users **manage and track tasks** easily and conveniently!
_Bob_ will be your best buddy to give you your much needed sanity check when needed.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Add Todo task : `todo`](#add-todo-task-todo)
  - [Add Deadline task: `deadline`](#add-deadline-task-deadline)
  - [Add Event task: `event`](#add-event-task-event)
  - [Mark task: `mark`](#mark-task-mark)
  - [Unmark task: `unmark`](#unmark-task-unmark)
  - [List tasks: `list`](#list-tasks-list)
  - [Delete task: `delete`](#delete-task-delete)
  - [Find task: `find`](#find-task-find)
  - [Exit application: `bye`](#exit-application-bye)
- [Command Summary](#command-summary)

## Quick Start
1. Download [Java 11](https://www.oracle.com/java/technologies/downloads/) on your computer.
2. Download the latest release of [`Bob.jar`](https://github.com/qianz-z/ip/releases).
3. Copy `Bob.jar` into an empty folder of your choice.
4. Open a command window in that folder. [_Not sure how to do this?_](https://www.groovypost.com/howto/open-command-window-terminal-window-specific-folder-windows-mac-linux/)
5. In the same window, run the command `java -jar Bob.jar`. 

All ready to go! Bob is at your command!

## Features 
#### ℹ️ Important things to take note:
- The command line inputs are **case-sensitive**
- `command` words should be in **lowercase**



### Add Todo task: `todo`
Adds a Todo task to the list. Todo tasks do not take in any date and time.

> Format: `todo TASK_DESCRIPTION`

Example: `todo revise CS2113`
````
todo revise CS2113
_________________________________________________________________________________
Bob has added this task to your list:
[T][ ] revise CS2113
Now you have 1 task in the list. You can do it!!
_________________________________________________________________________________
````

### Add Deadline task: `deadline`
Adds a Deadline task to the list. Deadline tasks takes in date that the task needs to be completed by.

> Format: `deadline TASK_DESCRIPTION /by DATE`

Example: `deadline CS2113 IP /by 30/09/2022`
````
deadline CS2113 IP /by 30/09/2022
_________________________________________________________________________________
Bob has added this task to your list:
[D][ ] CS2113 IP (by: 30/09/2022)
Now you have 2 tasks in the list. You can do it!!
_________________________________________________________________________________
````

### Add Event task: `event`
Adds an Event task to the list. Event tasks takes in date that the task will occur.

> Format: `event TASK_DESCRIPTION /at DATE`

Example: `event RC4 Open House /at 11 Nov 2021`
````
event RC4 Open House /at 11/11/2021
_________________________________________________________________________________
Bob has added this task to your list:
[E][ ] RC4 Open House (at: 11 Nov 2021)
Now you have 3 tasks in the list. You can do it!!
_________________________________________________________________________________
````

### Mark task: `mark`
Marks the task of the given task number. Acts as a checklist to indicate that the task has been completed.

> Format: `mark TASK_NUMBER`

Example: `mark 2`
````
mark 2
_________________________________________________________________________________
Good job! Bob has marked this task as done:
[D][X] CS2113 IP (by: 30/09/2022)
__________________________________________________________________________________
````

### Unmark task: `unmark`
Unmarks the task of the given task number. Acts as a checklist to indicate that the task has not been completed.

> Format: `unmark TASK_NUMBER`

Example: `unmark 2`
````
unmark 2
_________________________________________________________________________________
Oh no... Bob has mark this task as UNdone:
[D][ ] CS2113 IP (by: 30/09/2022)
__________________________________________________________________________________
````

### List all tasks: `list`
Lists all the tasks in Bob's list. 

`[T]` indicates the type of the task and `[X]` indicates that the task has been marked.
1. [T] - todo task
2. [D] - deadline task
3. [E] - event task
>Format: `list`

Example: `list`
````
list
_________________________________________________________________________________
Here are the list of tasks that you have:
1.[T][ ] revise CS2113
2.[D][ ] CS2113 IP (by: 30/09/2022)
3.[E][ ] RC4 Open House (at: 11 Nov 2021)
_________________________________________________________________________________
````

### Delete task: `delete`
Deletes the task of the given task number.

> Format: `delete TASK_NUMBER`

Example: `delete 1`
````
delete 1
_________________________________________________________________________________
Noted. Bob has removed this task: 
[T][ ] revise CS2113
Current number of tasks: 2
_________________________________________________________________________________
````

### Find task: `find`
Shows a list of tasks with descriptions that match the given search input.

:bulb: The search term can consist of a single word, or multiple words, and is **case-sensitive**.

> Format: `find KEYWORD`

Example: `find CS2113`
````
find CS2113
_________________________________________________________________________________
1.[T][ ] revise CS2113
2.[D][ ] CS2113 IP (by: 30/09/2022)
_________________________________________________________________________________
````

### Exit application: `bye`
Exits the application. _Bob_ greets goodbye :<



## Command Summary

Command | Format | Example
------ | ------ | -------
Add Todo task |  `todo TASK_DESCRIPTION` | `revise CS2113`
Add Deadline task | `deadline TASK_DESCRIPTION /by DATE` | `deadline CS2113 /by 30/09/2022`
Add Event task | `event TASK_DESCRIPTION /at DATE` |  `event RC4 Open House /at 11/11/2021`
Mark task | `mark TASK_NUMBER` | `mark 2`
Unmark task | `unmark TASK_NUMBER` | `unmark 2`
List tasks | `list` | `list`
Delete task | `delete TASK_NUMBER` | `delete 1`
Find task | `find KEYWORD` | `find CS2113`
Exit application | `bye` | `bye`
