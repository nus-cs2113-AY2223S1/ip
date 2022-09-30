# User Guide

## Features 

### Add Task

Add a task into task list.

### Delete Task

Delete a task from task list.

### Mark Task

Set the task as done/not done.

### Find Task

Find tasks whose description contain the given keywords.

### Exit

Exit the program.

## Usage

### `todo` - Add a todo object into the task list.

Add a todo object to the task list with the description of the 
todo object.

Format: `todo d/DESCRIPTION`
- The description of todo need is updated by input `DESCRIPTION`.

Example of usage:

`todo read book`

Expected outcome:

```
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] read book
     Now you have 4 tasks in the list.
    ____________________________________________________________
```


### `event` - Add an event object into the task list.

Add an event object to the task list with the description of
the event and the time of the event.

format: `event d/DESCRIPTION /at t/TIME`
- The description of event is updated by input `DESCRIPTION`.
- The time for event is updated by input `time`
- `/at` is required between the description and the time.

Example of usage:

`event orientation party /at Saturday`

Expected outcome:

```
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] orientation party (at: Saturday)
     Now you have 5 tasks in the list.
    ____________________________________________________________
```


### `deadline` - Add a deadline object into the task list.

Add a deadline object to the task list with the description of
the deadline and the deadline of the deadline object.

format: `deadline d/DESCRIPTION /by d/DEADLINE`
- The description of deadline is updated by input `DESCRIPTION`.
- The deadline for deadline object is updated by input `DEADLINE`
- `/by` is required between the description and the time.

Example of usage:

`deadline return book /by 3pm`

Expected outcome:

```
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] return book (by: 3pm)
     Now you have 6 tasks in the list.
    ____________________________________________________________
```


### `delete` - delete a task from the task list.

Delete the specified task from the task list.

format: `delete INDEX`
- Deletes the task at the specified INDEX.
- The index refers to the position of the task in the task list.
- The index must be a positive integer 1, 2, 3, ...

Example of usage:

`delete 2`

Expected outcome:

```
    ____________________________________________________________
     Noted. I've removed this task:
       [E][ ] burn book (at: Sat)
     Now you have 5 tasks in the list.
    ____________________________________________________________
```


### `mark` - Mark a task as done.

Set the isDone attribute of the specified task equals to True.

format: `mark INDEX`
- Mark the task as done at the specified INDEX.
- The index refers to the position of the task in the task list.
- The index must be a positive integer 1, 2, 3, ...

Example of usage:

`mark 2`

Expected outcome:

```
    ____________________________________________________________
     Nice! I've marked this task as done:
       [T][X] submit
    ____________________________________________________________
```


### `unmark` - Mark a task as not done.

Set the isDone attribute of the specified task equals to True.

format: `delete INDEX`
- Unmark the task as done at the specified INDEX.
- The index refers to the position of the task in the task list.
- The index must be a positive integer 1, 2, 3, ...

Example of usage:

`unmark 2`

Expected outcome:

```
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [T][ ] submit
    ____________________________________________________________
```


### `find` - Search tasks by keyword.

Find tasks whose description contain the given keywords.

format: `find k/KEYWORD`
- The search is case-sensitive.
- Only the description is searched.
- Only full words will be matched. e.g. `book will not match books`
- Words separated by other words will not be matched. 
e.g. `return book will not match return math book`

Example of usage:

`find book`

Expected outcome:

```
    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[D][X] return book at counter (by: 3pm)
     2.[T][ ] read book
     3.[D][ ] return book (by: 3pm)
    ____________________________________________________________
```

### `bye` - Exit the program

Exits the program.

format: `bye`

Example of usage:

`bye`

Expected outcome:

```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________

```