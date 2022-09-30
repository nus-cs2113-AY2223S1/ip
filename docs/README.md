# User Guide
Duke(Jarvis) is a personalized task manager that handles your todo list using the Command Line Interface.


## Table of Contents

1. [Getting Started](#getting-started)
2. [Features](#features)
   1. [Storage of task-list on hard-disk](#storage-of-task-list-on-hard-disk)
   2. [Datetime format understood by Duke](#datetime-format-understood-by-duke)
   3. [Sample command formats given](#sample-command-formats-given)
3. [Usage](#usage)
   1. [`todo`](#todo---add-a-new-todo-task)
   2. [`deadline`](#deadline---add-a-new-deadline-task)
   3. [`event`](#event---add-a-new-event-task)
   4. [`list`](#list---list-all-current-tasks)
   5. [`mark`](#mark---mark-a-current-task-as-done)
   6. [`unmark`](#unmark---mark-a-current-task-as-not-done)
   7. [`delete`](#delete---delete-a-current-task)
   8. [`find`](#find---find-tasks-by-keywords)
   9. [`bye`](#bye---exit-the-programme)
4. [Common Error Messages](#common-error-messages)
5. [Command Summary](#command-summary)
6. [Credits](#credits)

## Getting Started

1. Ensure that you have `Java 11` or above installed on your computer

2. Download the `jar` file from [here](https://github.com/kohnh/ip/releases/tag/A-Release-v0.2)

3. Copy the `jar` file into an empty folder that you want to use as the base folder

4. Open a command window in that same folder

5. Run the command `java -jar {filename}.jar` e.g., `java -jar Duke.jar`

6. If the programme is running correctly, this welcome message should be shown

```
------------------------------------------------------------
Hello! I'm
     ____.  _____ ______________   ____.___  _________
    |    | /  _  \\______   \   \ /   /|   |/   _____/
    |    |/  /_\  \|       _/\   Y   / |   |\_____  \
/\__|    /    |    \    |   \ \     /  |   |/        \
\________\____|__  /____|_  /  \___/   |___/_______  /
                 \/       \/                       \/
What can I do for you?
Enter "bye" to exit.
------------------------------------------------------------
```

## Features 

### Storage of task-list on hard-disk
Duke will automatically load previously added tasks from the hard-disk
and also save all added tasks to the hard-disk when Duke is closed.

### Datetime format understood by Duke
Currently, Duke only understands date in the format `YYYY-MM-DD` and time in the 24-hour format `HHMM`.
Whenever date and time needs to be supplied by the user, the format should be `YYYY-MM-DD HHMM`.
(`Y` stands for year, `M` stands for month, `D` stands for date, `H` stands for hour and `M` stands for minutes)

If the date and time information provided cannot be understood by duke or no date or time or 
both is not provided, the date and time will be automatically set to one week from current date. 
Additionally, there will be another line output:
```
Sorry. Date and Time cannot be parsed
```

### Sample command formats given
Words in round brackets () represents parameters that you are supposed to supply to the programme.
e.g. `todo (discription)` can be used as `todo buy a pen`

## Usage

### `todo` - Add a new Todo task

Add a new todo task type in the task list in the format
`todo (discription)`

**Example of usage:** 

`todo read book`

**Expected outcome:**

Output to confirm addition of todo task where 'X' is the number of tasks currently stored in the list
```
------------------------------------------------------------
Got it. I've added this task:
[T][ ] read book
Now you have X tasks in the list.
------------------------------------------------------------
```

### `deadline` - Add a new Deadline task

Add a new deadline task type in the task list in the format
`deadline (discription) /by (YYYY-MM-DD HHMM)`

**Example of usage:**

`deadline return book /by 2025-05-12 1800`

**Expected outcome:**

Output to confirm addition of deadline task where 'X' is the number of tasks currently stored in the list
```
------------------------------------------------------------
Got it. I've added this task:
[D][ ] return book  (by: May 12 2025 6:00 PM)
Now you have X tasks in the list.
------------------------------------------------------------
```

### `event` - Add a new Event task

Add a new todo task type in the task list in the format
`event (discription) /at (YYYY-MM-DD HHMM)`

**Example of usage:**

`event book review /at 2025-04-01 1000`

**Expected outcome:**

Output to confirm addition of event task where 'X' is the number of tasks currently stored in the list
```
------------------------------------------------------------
Got it. I've added this task:
[E][ ] book review  (at: Apr 1 2025 10:00 AM)
Now you have X tasks in the list.
------------------------------------------------------------
```

### `list` - List all current tasks

List all the current tasks in the task list.

**Example of usage:**

`list`

**Expected outcome:**

If there are no tasks currently in the task list
```
------------------------------------------------------------
Here are the tasks in your list:

------------------------------------------------------------
```

### `mark` - Mark a current task as done

Mark a current task as done in the task list in the format `mark (index)`

**Example of usage:**

`mark 1`

**Expected outcome:**

If the task indicated exist, for example the previous `read book` task
```
------------------------------------------------------------
Nice! I've marked this task as done:
[T][X] read book
------------------------------------------------------------
```

### `unmark` - Mark a current task as not done

Mark a current task as not done in the task list in the format `unmark (index)`

**Example of usage:**

`unmark 1`

**Expected outcome:**

If the task indicated exist, for example the previous `read book` task
```
------------------------------------------------------------
OK, I've marked this task as not done yet:
[T][ ] read book
------------------------------------------------------------
```

### `delete` - Delete a current task

Delete a current task in the task list in the format `delete (index)`

**Example of usage:**

`delete 1`

**Expected outcome:**

If the task indicated exist, for example the previous `read book` task,
where X is the number of tasks left in the task list.
```
------------------------------------------------------------
Noted. I've removed this task:
[T][ ] read book
Now you have X tasks in the list.
------------------------------------------------------------
```

### `find` - Find tasks by keyword(s)

Find all tasks in the task list that has a user-specified keyword in the format `find (keyword(s))`

**Example of usage:**

`find book`

**Expected outcome:**

If the keyword indicated exist, for example the keyword 'book' is part of the previous `read book` task.
```
------------------------------------------------------------
Here are the tasks matching "book" in your list:
1.[T][ ] read book

------------------------------------------------------------
```

### `bye` - Exit the programme

Exit the programme and save all tasks to the hard-drive

**Example of usage:**

`bye`

**Expected outcome:**

```
------------------------------------------------------------
Bye. Hope to see you again soon!
------------------------------------------------------------
```

## Common Error Messages

If the task index does not exist
```
------------------------------------------------------------
☹ OOPS!!! The task number you specified does not exist.
------------------------------------------------------------
```

If the index provided is not a number
```
------------------------------------------------------------
☹ OOPS!!! You did not enter a number.
------------------------------------------------------------
```

If no date or time or both is not provided, the date and time will be 
automatically set to one week from current date
```
Sorry. Date and Time cannot be parsed
```

## Command summary

| Action                   | Format                                         | Example                                    |
|--------------------------|------------------------------------------------|--------------------------------------------|
| Add a new Todo task      | `todo (discription)`                           | `todo read book`                           |
| Add a new Deadline task  | `deadline (discription) /by (YYYY-MM-DD HHMM)` | `deadline return book /by 2025-05-12 1800` |
| Add an new Event task    | `event (discription) /at (YYYY-MM-DD HHMM)`    | `event book review /at 2025-04-01 1000`    |
| List all current tasks   | `list`                                         | `list`                                     |
| Mark a task as done      | `mark (index)`                                 | `mark 1`                                   |
| Mark a task as not done  | `unmark (index)`                               | `unmark 1`                                 |
| Delete a current task    | `delete (index)`                               | `delete 1`                                 |
| Find tasks by keyword(s) | `find (keyword(s))`                            | `find book`                                | 
| Exits the programme      | `bye`                                          | `bye`                                      |

## Credits
Credit to Owen Leong (@owenl131) for the inspiration and layout for the 
[Table of Contents](#table-of-contents), [Getting Started](#getting-started) and the [Command Summary](#command-summary) 
in this UserGuide.