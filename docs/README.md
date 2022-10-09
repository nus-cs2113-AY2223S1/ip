# User Guide

## Features 
This Project contains the following Features:
1) List
2) Todo
3) Deadline
4) Event
5) Mark
6) Unmark
7) Bye


## Feature - List

This function adds the ability to display all tasks back to the user when requested.

### Usage

### `list` 

Lists down all the tasks inputted by the User.

Example of usage:

`list`

Expected outcome:

```
list
 ____________________________________________________________
    Here are the tasks in your list:
    1.[T][ ] Homework
 ____________________________________________________________
```

## Feature - Todo

This function adds tasks without any date/time attached to it.

### Usage

### `todo <description>` 

Adds a task with the name of description.

Example of usage:

`todo Homework`

Expected outcome:

```
todo Homework
 ____________________________________________________________
    Got it. I've added this task:
        [T][ ] Homework
    Now you have 1 tasks in the list.
  ____________________________________________________________
```
## Feature - Deadline

This function adds tasks that need to be done before a specific date/time.

### Usage

### `deadline <description> /by <Date/Time>`

Adds a task with the name of description and a Specific time to complete it.

Example of usage:

`deadline return book /by Sunday`

Expected outcome:

```
deadline return book /by Sunday
 ____________________________________________________________
    Got it. I've added this task:
        [D][ ] return book (by: Sunday)
    Now you have 2 tasks in the list.
 ____________________________________________________________
```

## Feature - Event

This function adds tasks that start at a specific time and ends at a specific time.

### Usage

### `event <description> /at <Day> <StartTime-EndTime>`

Adds a task with the name of description, a Start Time and an End Time.

Example of usage:

`event project meeting /at Mon 2-4pm`

Expected outcome:

```
deadline return book /by Sunday
 ____________________________________________________________
    Got it. I've added this task:
        [E][ ] project meeting (at: Mon 2-4pm)
    Now you have 3 tasks in the list.
 ____________________________________________________________
```

## Feature - Mark

This function adds the ability to mark tasks as done.

### Usage

### `mark <taskNumber>`

Marks a task as done based on the Task Number.

Example of usage:

`mark 2`

Expected outcome:

```
mark 2
 ____________________________________________________________
    Nice! I've marked this task as done:
        [D][X] return book (by: Sunday)
 ____________________________________________________________
```

## Feature - Unmark

This function adds the ability to mark tasks as undone.

### Usage

### `unmark <taskNumber>`

Marks a task as undone based on the Task Number.

Example of usage:

`unmark 2`

Expected outcome:

```
unmark 2
 ____________________________________________________________
    OK, I've marked this task as not done yet:
        [D][] return book (by: Sunday)
 ____________________________________________________________
```

## Feature - Bye

This function exits the program.

### Usage

### `bye`

Exits the program.

Example of usage:

`bye`

Expected outcome:

```
bye
 ____________________________________________________________
    Bye. Hope to see you again soon!
 ____________________________________________________________
```
