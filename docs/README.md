# User Guide

Welcome to Duke, a resourceful task manager! 
With Duke, you can keep track of various types of tasks by adding, deleting and marking them as done respectively!
Duke also comes with an auto-save feature, so when you close and load Duke again, all your previous tasks and their completion statuses are done.

## Features 

### Help

Gives a list of valid commands and their formats.

### List

Lists all present tasks in a neat, numbered list.

### Add

Add new tasks to Duke.
Tasks are of 3 kinds:
- Todos, basic type of Tasks with just a description
- Events, tasks that are scheduled to happen at a specific time
- Deadlines, tasks that are to be done by a specific time

### Mark/Unmark

Mark a specific task as done or not done.

### Delete

Delete an existing task.

### Find

Shortlist tasks that match a given keyword and view them in a numbered list.

## Usage

### `help`

Output a message that lists all commands and their formats.

### `list`

List all tasks that are present in Duke currently.

Example of usage:

`list`

Expected outcome (example):

	────────────────────────────────────────────────────────────
	Here are the tasks in your list: 
	1. [T][X] read book
	2. [D][ ] return book (by: Jun 12 2022, 12:00)
	3. [E][ ] project meeting (at: Aug 22 2022, 18:00)
	4. [T][X] join sports club
	────────────────────────────────────────────────────────────

Description of the outcome:

```
The 4 tasks in the current task list are printed, along with their completion statuses and timings (if any).
```

### `todo`

Adds a todo to the list of tasks and saves the data accordingly.

Example of usage:

`todo [description]`

Expected outcome:

	────────────────────────────────────────────────────────────
	Got it. I've added this task:
	  [T][ ] 50 pushups
	Now you have 5 task(s) in the list.
	────────────────────────────────────────────────────────────

Description of the outcome:

```
After the todo command was entered, a new Todo was added to the list, indicated by 'T', with a 'not done' completion status.
```

### `event`

Adds an event to the list of tasks and saves the data accordingly.

Example of usage:

`event [description] /at [time (in YYYY-MM-DD HH:mm format)]`

Expected outcome:

	────────────────────────────────────────────────────────────
	Got it. I've added this task:
	  [E][ ] movie screeening (at: Oct 08 2022, 22:00)
	Now you have 6 task(s) in the list.
	────────────────────────────────────────────────────────────

Description of the outcome:

```
After a valid event command was entered, a new Event was added to the list, indicated by 'E' with a 'not done' completion status
and the time at which this event is scheduled.
```

### `deadline`

Adds a deadline to the list of tasks and saves the data accordingly.

Example of usage:

`deadline [description] /by [time (in YYYY-MM-DD HH:mm format)]`

Expected outcome:

	────────────────────────────────────────────────────────────
	Got it. I've added this task:
	  [D][ ] CS2113 project (by: Sep 30 2022, 11:00)
	Now you have 7 task(s) in the list.
	────────────────────────────────────────────────────────────

Description of the outcome:

```
After a valid deadline command was entered, a new Deadline was added to the list, 
indicated by 'D' with a 'not done' completion status and the time by which the deadline is due.
```

### `mark`

Marks a task as done and saves the data accordingly.

Example of usage:

`mark [number of task]`

Expected outcome:

	────────────────────────────────────────────────────────────
	Nice! I've marked this task as done: 
	  [E][X] movie screeening (at: Oct 08 2022, 22:00)
	────────────────────────────────────────────────────────────

Description of the outcome:

```
The task corresponding to the input number was marked as done, and the subsequent status is reflected.
```

### `unmark`

Marks a task as not done.

Example of usage:

`unmark [number of task]`

Expected outcome:

	────────────────────────────────────────────────────────────
	OK, I've marked this task as not done yet: 
	  [E][ ] movie screeening (at: Oct 08 2022, 22:00)
	────────────────────────────────────────────────────────────

Description of the outcome:

```
The task corresponding to the input number was marked as not done, and the subsequent status is reflected.
```

### `find`

Finds all tasks matching an input keyword and lists them in a numbered list.
Note this method ignores case when searching for matches.

Example of usage:

`find [keyword]`

Expected outcome:

	────────────────────────────────────────────────────────────
	Here are the matching tasks in your list: 
	1. [T][X] read book
	2. [D][ ] return book (by: Jun 12 2022, 12:00)
	────────────────────────────────────────────────────────────

Description of the outcome:

```
When the command 'find book' was entered, all tasks with the word 'book' were shortlisted and printed.
```

### `delete`

Deletes the task corresponding to the input number, and saves the data accordingly.

Example of usage:

`delete [number of task]`

Expected outcome:

	────────────────────────────────────────────────────────────
	Noted. I've removed this task:
	  [T][ ] 50 pushups
	Now you have 6 task(s) in the list.
	────────────────────────────────────────────────────────────

Description of the outcome:

```
The task corresponding to the input number was deleted from the list. This is reflected in the output, as well as the number of remaining tasks.
```

### `bye`

Exits the program.

Example of usage:

`bye`

Expected outcome:

	────────────────────────────────────────────────────────────
	Bye. Hope to see you again soon!
	────────────────────────────────────────────────────────────

Description of the outcome:

```
A simple message is printed to show that the program has terminated.
```