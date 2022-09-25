# User Guide

Duke is a *task management* command line interface (CLI) program managing over 3 types of tasks, specifically designed for the tech-savvy fast typists.

## Features 
In Duke, there are three different types of tasks available. They are:
1. Todo
   - Represents a *basic* task to be completed
2. Deadline
   - Represents a task to be completed *before* a specific date and time
3. Event
   - Represents a task to be completed *at* a specific date and time

### Feature - Task management

The tasks in Duke can be <ins>added</ins>, <ins>listed</ins> and <ins>deleted</ins> in the application using the commands provided. For each task added in Duke, it can be either <ins>marked</ins> as done, or <ins>unmarked</ins> as not done. The tasks are set as not done by default. 

### Feature - Search for tasks

Search query can be used to <ins>find</ins> the tasks where the task description contains the search query given. Alternatively, the event and deadline tasks can also be <ins>filtered</ins> by a given specific date.

### Feature - File storage

The tasks added to the list will be automatically stored to `tasks.txt`, and is read by the program every time it runs to initialise the stored task list.

## Command Summary
Notes on the command format:
 - Command words are insensitive
 - Words in `UPPERCASE` refers to the parameters of the command
 - All parts of the command are mandatory
   - e.g. A deadline cannot have missing separator or datetime
 - For `DATETIME`, the `DATE` must be entered before the `TIME`

| Command | Format | Description |
| ------- | ------ | ----------- |
| todo | todo DESCRIPTION | Add a todo |
| deadline | deadline DESCRIPTION /by DATETIME | Add a deadline |
| event | event DESCRIPTION /at DATETIME | Add an event |
| list | list | List the tasks |
| mark | mark TASKINDEX | Mark a task as done |
| unmark | unmark TASKINDEX | Unark a task as not done |
| find | find QUERY | Find the tasks with a query |
| filter | filter DATE | Filter the tasks with a date |
| delete | delete TASKINDEX | Delete a task |
| bye | bye | Exit the program |

Available date format:

| Date Format | Example |
| ----------- | ------- |
| dd/MM/yyyy | 18/09/2022 |
| yyyy-MM-dd | 2022-09-18 |
| MMM dd yyyy | Sep 18 2022 |

Available time format:

| Time Format | Example |
| ----------- | ------- |
| hh:mm a | 06:00 AM |
| HHmm | 0600 |

## Usage

### `todo` - Add a todo

Add a todo task with a description into the task list.

Example of usage: 

`todo Visit McDonalds for new burger`

Expected outcome:

A todo task is added with the description "Visit McDonalds for new burger".

```
____________________________________________________________
Got it. I've added this task: 
	[T][ ] Visit McDonalds for new burger
Now you have 1 tasks in the list
____________________________________________________________
```

### `deadline` - Add a deadline

Add a deadline task with a description and a datetime into the task list.

Example of usage: 

`deadline Submit CS2113 Assignment /by 30/09/2022 11:59 PM`

Expected outcome:

A deadline task is added with the description "Submit CS2113 Assignment" and datetime "Sep 30 2022 11:59 PM".

```
____________________________________________________________
Got it. I've added this task: 
	[D][ ] Submit CS2113 Assignment (by: Sep 30 2022 11:59 PM)
Now you have 2 tasks in the list
____________________________________________________________
```

### `event` - Add an event

Add an event task with a description and a datetime into the task list.

Example of usage: 

`event CS2113 Exam /at 29/11/2022 05:00 PM`

Expected outcome:

An event task is added with the description "CS2113 Exam" and datetime "Nov 29 2022 05:00 PM".

```
____________________________________________________________
Got it. I've added this task: 
	[E][ ] CS2113 Exam (at: Nov 29 2022 05:00 PM)
Now you have 3 tasks in the list
____________________________________________________________

```

### `list` - List the tasks

List all the tasks inside the task list.

Example of usage: 

`list`

Expected outcome:

The tasks added to the list are listed with their details.

```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] Visit McDonalds for new burger
2.[D][ ] Submit CS2113 Assignment (by: Sep 30 2022 11:59 PM)
3.[E][ ] CS2113 Exam (at: Nov 29 2022 05:00 PM)
There are a total of 3 tasks.
____________________________________________________________

```

### `mark` - Mark a task as done

Mark a selected task as done.

Example of usage: 

`mark 1`

Expected outcome:

The tasks selected is marked as done.

```
____________________________________________________________
Nice! I've marked this task as done:
	[X] Visit McDonalds for new burger
____________________________________________________________
```

### `unmark` - Unmark a task as not done

Unmark a selected task as not done.

Example of usage: 

`unmark 1`

Expected outcome:

The tasks selected is unmarked as not done.

```
____________________________________________________________
OK, I've marked this task as not done yet:
	[ ] Visit McDonalds for new burger
____________________________________________________________
```

### `find` - Find the tasks with a query

Find the tasks which the description contains the search query.

Example of usage: 

`find CS2113`

Expected outcome:

The tasks containing the search query are listed with their details.

```
____________________________________________________________
Here are the tasks in your list:
1.[D][ ] Submit CS2113 Assignment (by: Sep 30 2022 11:59 PM)
2.[E][ ] CS2113 Exam (at: Nov 29 2022 05:00 PM)
There are a total of 2 tasks.
____________________________________________________________
```

### `filter` - Filter the tasks with a date

Filter the deadlines or events by a specific date.

Example of usage: 

`filter 29/11/2022`

Expected outcome:

The deadlines or events matching the requested date are listed with their details.

```
____________________________________________________________
Here are the tasks in your list:
1.[E][ ] CS2113 Exam (at: Nov 29 2022 05:00 PM)
There are a total of 1 tasks.
____________________________________________________________

```

### `delete` - Delete a task

Delete a selected task from the task list.

Example of usage: 

`delete 3`

Expected outcome:

The task selected is deleted from the task list.

```
____________________________________________________________
Noted. I've removed this task:
	[E][ ] CS2113 Exam (at: Nov 29 2022 05:00 PM)
Now you have 2 tasks in the list
____________________________________________________________

```

### `bye` - Exit the program

Example of usage: 

`bye`

Expected outcome:

Exit the program without error.

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
