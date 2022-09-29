# User Guide
Duke(Jarvis) is a personalized task manager that handles your todo list using the Command Line Interface.
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

## Table of Contents

1. [Getting Started](#getting-started)
2. [Features](#features)
    1. [Storage of task-list on hard-disk](#storage-of-task-list-on-hard-disk)
    2. [Datetime format understood by Duke](#datetime-format-understood-by-duke)
3. [Usage](#usage)
    1. [`todo`](#todo---adds-a-new-todo-task)
   2. [`deadline`](#deadline---adds-a-new-deadline-task)
   3. [`event`](#event---adds-a-new-event-task)
   4. [`list`](#list---lists-all-current-tasks)
   5. [`mark`](#mark---mark-a-current-task-as-done)
   6. [`unmark`](#unmark---mark-a-current-task-as-not-done)
   7. [`delete`](#find---find-tasks)
   8. [`find`](#find---find-tasks)
   9. [`bye`](#bye---exits-the-programme)
4. [Common Error Messages](#common-error-messages)

## Getting Started
1. Download the `jar` file.

2. Copy the `jar` file into an empty folder

3. Open a command window in that folder

4. Run the command `java -jar {filename}.jar` e.g., `java -jar Duke.jar`

## Features 

### Storage of task-list on hard-disk
Duke will automatically load previously added tasks from the hard-disk
and also save all added tasks to the hard-disk when Duke is closed.

### Datetime format understood by Duke
Currently, Duke only understands date in the format `YYYY-MM-DD` and time in the format `HHMM`.
Whenever date and time needs to be supplied by the user, the format should be `YYYY-MM-DD HHMM`.
If the date and time information provided cannot be understood by duke or no date or time or 
both is not provided, the date and time will be automatically set to one week from current date. 
Additionally, there will be another line output:
```
Sorry. Date and Time cannot be parsed
```

## Usage

### `todo` - Adds a new todo task

Adds a new todo task type in the task list in the format
`todo (task name)`

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

### `deadline` - Adds a new deadline task

Adds a new deadline task type in the task list in the format
`deadline (task name) /by (datetime)`

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

### `event` - Adds a new event task

Adds a new todo task type in the task list in the format
`event (task name) /at (datetime)`

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

### `list` - Lists all current tasks

Lists all the current tasks in the task list.

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

Mark a current task as done in the task list in the format `mark X`
where X is the index of the task to be marked.

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

Mark a current task as not done in the task list in the format `unmark X`
where X is the index of the task to be unmarked.

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

Delete a current task in the task list in the format `delete X`
where X is the index of the task to be deleted.

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

### `find` - Find tasks

Find all tasks in the task list that has a user-specified keyword in the format `find (keyword)`

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

### `bye` - Exits the programme

Exits the programme and save all tasks to the hard-drive

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
If no date or time or both is not provided, the date and time will be automatically set to one week from current date. Additional output:
```
Sorry. Date and Time cannot be parsed
```