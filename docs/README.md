# Duke User Guide

## Features 

### Adding a to-do task: `todo`

Adds a to-do task to the list of tasks.

Format: `todo TODO_DESCRIPTION`

Example: `todo Go for a run`

Expected output: 

```
______________________________________________
Got it. I've added this task: 
 [T][ ] Go for a run
Now you have 1 tasks in the list 
______________________________________________
```


### Adding a deadline task: `deadline`

Adds a deadline task to the list of tasks.

Format: `deadline DEADLINE_DESCRIPTION /by DUE_DATE`

Example: `deadline Apply for internship /by 5th October`

Expected output: 

```
______________________________________________
Got it. I've added this task: 
 [D][ ] Apply for internship (by: 5th October)
Now you have 2 tasks in the list 
______________________________________________
```


### Adding an event task: `event`

Adds an event task to the list of tasks.

Format: `event EVENT_DESCRIPTION /at TIME_OF_EVENT`

Example: `event Attend zoom meeting /at 5pm`

Expected output: 

```
______________________________________________
Got it. I've added this task: 
 [E][ ] Attend zoom meeting (at: 5pm)
Now you have 3 tasks in the list 
______________________________________________
```


### Listing all tasks: `list`

Lists all the tasks in the task list

Format: `list`

Expected output: 

```
______________________________________________

1.[T][ ] Go for a run
2.[D][ ] Apply for internship (by: 5th October)
3.[E][ ] Attend zoom meeting (at: 5pm)
______________________________________________
```


### Marking a task as done: `mark` 

Marks the particular task as done.

Format: `mark INDEX`
- Marks the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** that is less than or equal to the number of tasks in the list.

Example: `mark 1`

Expected output:

```
______________________________________________
Nice! I've marked this task as done: 
  [T][X] Go for a run
______________________________________________
```


### Marking a task as not done: `unmark`

Marks the particular task as not done.

Format: `unmark INDEX`
- Marks the task at the specified `INDEX` as not done yet. The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** that is less than or equal to the number of tasks in the list.

Example: `unmark 1`

Expected output:

```
______________________________________________
OK, I've marked this task as not done yet: 
  [T][ ] Go for a run
______________________________________________
```


### Deleting a task in the list: `delete`

Deletes the particular task in the task list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** that is less than or equal to the number of tasks in the list.

Example: `delete 1`

Expected output:

```
______________________________________________
Noted. I've removed this task: 
  [T][ ] Go for a run
Now you have 2 tasks in the list 
______________________________________________
```


### Finding tasks in the list: `find`

Finds tasks whose descriptions contain the keywords provided.

Format: `find KEYWORD [MORE_KEYWORDS]`
- The keywords are case sensitive and order of keywords matters.

Example: `find meeting`

Expected output:

```
______________________________________________

Here are the matching tasks in your list: 
1.[E][ ] Attend zoom meeting (at: 5pm)
______________________________________________
```


### Exiting Duke: `bye`

Exits the Duke program.

Format: `bye`
