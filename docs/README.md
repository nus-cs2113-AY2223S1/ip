# User Guide

## Features 

### Addition

Adding a task into task list.

### List

Listing out all the tasks in task list.

### Marking

Marking the state of completion of the task specified.

### Find

Finding tasks containing the content specified.

### Deletion

Deleting a task from task list.

### Exit

Exiting and ending the program.


## Usage

### `todo` 

Adding a todo task into the task list and showing a message indicating the addition.

Format of Input: `todo {description}`

Example of usage:

`todo read book`

Expected outcome:

```
  ____________________________________________________________
	Added:
	  [T][ ]read book
	Now you have 1 tasks in the list
  ____________________________________________________________
```


### `deadline`

Adding a deadline task into the task list and showing a message indicating the addition.

Format of Input: `deadline {description} /by {due}`

Example of usage:

`deadline write book /by tmr`

Expected outcome:

```
  ____________________________________________________________
	Added:
	  [D][ ]write book (by: tmr)
	Now you have 2 tasks in the list
  ____________________________________________________________
```

### `event`

Adding an event task into the task list and showing a message indicating the addition.

Format of Input: `event {description} /at {time}`

Example of usage:

`event read recipe /at next mon`

Expected outcome:

```
  ____________________________________________________________
	Added:
	  [E][ ]read recipe (at: next mon)
	Now you have 3 tasks in the list
  ____________________________________________________________
```


### `list`

Listing out all the tasks stored in the task list.

Format of Input: `list`

Example of usage:

`list`

Expected outcome:

```
  ____________________________________________________________
	These are the tasks in your list:
	1.[T][ ]read book
	2.[D][ ]write book (by: tmr)
	3.[E][ ]read recipe (at: next mon)
  ____________________________________________________________
```

### `mark`

Marking a task as completed and showing a message indicating the action.

Format of Input: `mark {index}`

Example of usage:

`mark 1`

Expected outcome:

```
  ____________________________________________________________
	Noted, the following task is set as requested:
	  [T][X]read book
  ____________________________________________________________
```

### `unmark`

Marking a task as not completed and showing a message indicating the action.

Format of Input: `unmark {index}`

Example of usage:

`unmark 1`

Expected outcome:

```
  ____________________________________________________________
	Noted, the following task is set as requested:
	  [T][ ]read book
  ____________________________________________________________
```

### `find`

Finding and printing the tasks containing the content specified.

Format of Input: `find {content}`

Example of usage:

`find read`

Expected outcome:

```
  ____________________________________________________________
	Below are the tasks with the content specified:
	 [T][ ]read book
	 [E][ ]read recipe (at: next mon)
  ____________________________________________________________
```


### `delete`

Deleting a task from the task list and showing a message indicating the deletion.

Format of Input: `delete {index}`

Example of usage:

`delete 2`

Expected outcome:

```
  ____________________________________________________________
	Noted. I have removed this task:
	  [D][ ]write book (by: tmr)
	Now you have 2 tasks in the list
  ____________________________________________________________
```


### `bye`

Exit the program and shows a message indicating the exit.

Format of Input: `bye`

Example of usage:

`bye`

Expected outcome:

```
  ____________________________________________________________
	Thank you for choosing duke. Hope to see you again soon!!!
  ____________________________________________________________
```
