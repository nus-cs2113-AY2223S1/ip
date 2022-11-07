# User Guide

This is a task manager bot named Azmuth. It allows the user to keep track of his/her tasks through the command line.

## Features

#### Add tasks of different types
- [x] Add new todo task.
- [x] Add new deadline task.
- [x] Add new event task.

#### Common task management operations
- [x] Delete a task.
- [x] List all tasks in task list.
- [x] Mark and unmark a task as done.

#### Easy task searching
- [x] Search for tasks with keyword.
- [x] Search for tasks with specific date.

## Set-up to run
1. Download and copy the jar file into an empty folder.
2. Open a command window or terminal for mac in that folder.
3. Run the command `java -jar duke.jar` in the same folder.

## Usage

### `todo <taskName>` - Add todo task
Adds a new todo task of name `<taskName>`.

**Parameters**
`taskName: String` - name given to the todo task

**Example of usage:**
`todo play minecraft`

**Expected outcome:**
New todo task is successfully added into the task list.
```
Added new todo task: play minecraft
 [T][ ] play minecraft
You have 1 tasks in the list.
____________________
```
---
### `deadline <taskName>/<date> <time>` - Add deadline task
Adds a new deadline task of name `<taskName>` with date `<date>` and time `<time>`.

**Parameters**
`taskName: String` - name given to the deadline
`date: String` - date of deadline in the format `YYYY-MM-DD`
`time: String` - time of deadline in the format `HH:MM`

**Example of usage:**
`deadline download minecraft/2022-09-20 23:59`

**Expected outcome:**
New deadline task is successfully added into the task list.
```
Added new deadline task: download minecraft
 [D][ ] download minecraft (by: Sep 20 2022, TUESDAY, 23:59)
You have 2 tasks in the list.
____________________
```
---
### `event <taskName>/<date> <time>` - Add event task
Adds a new event task of name `<taskName>` with date `<date>` and time `<time>`.

**Parameters**
`taskName: String` - name given to the event
`date: String` - date of event in the format `YYYY-MM-DD`
`time: String` - time of event in the format `HH:MM`

**Example of usage:**
`event minecraft event 2022/2022-10-15 19:30`

**Expected outcome:**
New event task is successfully added into the task list.
```
Added new event task: minecon 2022
 [E][ ] minecraft event 2022 (at: Oct 15 2022, SATURDAY, 19:30)
You have 3 tasks in the list.
____________________
```
---
### `list` - List all tasks
Lists out all tasks in the task list in index order.

**Example of usage:**
`list`

**Expected outcome:**
Current task list is listed in order ot initial entry. Type, markings and any date/time are indicated.
```
Here is your list of tasks:
1. [T][ ] play minecraft
2. [D][ ] download minecraft (by: Sep 20 2022, TUESDAY, 23:59)
3. [E][ ] minecraft event 2022 (at: Oct 15 2022, SATURDAY, 19:30)

____________________
```
---
### `mark <taskNumber>` - Mark task as done
Marks the task of that index in the task list as done.

**Parameters**
`taskNumber: Integer` - the index of the task as shown in the task list

**Example of usage:**
`mark 1`

**Expected outcome:**
Specified task of the index is marked in the task list, indicating that it is done.
```
play minecraft has been marked as done!
____________________
```
---
### `unmark <taskNumber>` - Unmark task
Unmarks the task of that index in the task list that was previously marked as done.

**Parameters**
`taskNumber: Integer` - the index of the task as shown in the task list

**Example of usage:**
`unmark 1`

**Expected outcome:**
Specified task of the index is unmarked in the task list, indicating that it is not done.
```
play minecraft has been unmarked!
____________________
```
---
### `delete <taskNumber>` - Delete a task
Deletes the task of that index from the task list.

**Parameters**
`taskNumber: Integer` - the index of the task as shown in the task list

**Example of usage:**
`delete 1`

**Expected outcome:**
Specified task of the index is successfully deleted.
```
Deleted: [T][ ] play minecraft
You have 2 tasks in the list.
____________________
```
---
### `find <keyword>` - Find specific task
Finds a matching tasks with a specified keyword in its name.

**Parameters**
`keyword: String` - the keyword to match by

**Example of usage:**
`find minecraft`

**Expected outcome:**
All tasks with task names with any instance of the specified keyword will be listed out.
```
Here are the matching results from your task list!
[D][ ] download minecraft (by: Sep 20 2022, TUESDAY, 23:59)
[E][ ] minecraft event 2022 (at: Oct 15 2022, SATURDAY, 19:30)
```
---
### `checkout <date>` - Find tasks on a specific date
Lists out deadlines or events on a specified date, if any.

**Parameters**
`date: String` - date of to be searched in the format `YYYY-MM-DD`

**Example of usage:**
`checkout 2022-10-15`

**Expected outcome:**
All deadlines and events occurring on the specified date will be listed out.
```
Here are the corresponding deadlines and events!
[E][ ] minecraft event 2022 (at: Oct 15 2022, SATURDAY, 19:30)
____________________
```
---
### `help` - Lists out commands
Lists out all other commands that Azmuth recognises.

**Example of usage:**
`help`

**Expected outcome:**
A list of recognised commands (truncated user guide) will be listed out to the user.
```
____________________
Here if a list of commands that I recognise!
Date format is 'yyyy-mm-dd' and Time format is 'hh:mm'

'todo <taskName>': Add a new todo task
'deadline <taskName>/<date> <time>': Add a new deadline task
'event <taskName>/<date> <time>': Add a new event task
'list': Lists out tasks in index order
'mark <taskNumber>': Mark a certain task as done
'unmark <taskNumber>': Unmark a certain task that was done
'delete <taskNumber>': Delete the task from the list
'find <something>': Searches the task list to find matching task names'checkout <date>': Checkout what deadlines or events you have on  a specific date
'bye': Quit the system
____________________
```
---
### `bye` - Ends the program
Saves the task list and quits the program.

**Example of usage:**
`bye`

**Expected outcome:**
Current task list is saved to `data.txt` (will be created if missing), after which the program exits.
```
Successfully saved your task list!
Bye and see you again soon!
```
---
### Note
- A ***data.txt*** file will be created in the same directory as the jar file to save the task list.