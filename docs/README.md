# User Guide

## Features 

### Feature: View your list of Tasks

Request Duke to show the list of tasks you have to complete

### Feature: Add, remove, or complete a task on/to your list

You can add or remove specific tasks to your list and also mark them as complete when you finish them off

### Feature: Save the list as a file/ import a file to your list

You get to save your list at the end of the session as a file in the folder, and you can also place a file in the same format as required and import a list

## Usage

### `list` - display the list

Duke will print out the list when asked to

Example of usage: 

`list`

Expected outcome:

A list that is ordered and shows completion and type of task

```
1. [T][X] study for midterm
2. [D][ ] homework (by: 2:30 PM)
```

### `todo` - add a todo task to the list

Duke will add a todo task to the list

Example of usage: todo study for midterm

`todo [n/Name]`

Expected outcome:

A successful addition to the list

```
Got it. I have added this task:
[T][ ] study for midterm
Now you have 1 tasks in your list.
```

### `event` - add an event task to the list

Duke will add the event with given parameters to the list

Example of usage: event midterm /at 6:00 PM

`event [n/Name] /at [d/Date or Time]`

Expected outcome:

The event is successfully added to the list

```
Got it. I have added this task: 
[E][ ] Midterm (at: 6:00 PM)
Now you have 3 tasks in your list.
```

### `deadline` - add a deadline task to the list

Duke will add the deadline with the given parameters to the list

Example of usage: deadline homework /by 2:30 PM

`deadline [n/Name] /by [d/Date or Time]`

Expected outcome:

The deadline task is successfully added to the list

```
Got it. I have added this task: 
[D][ ] homework (by: 2:30 PM)
Now you have 3 tasks in your list.
```

### `mark` - mark a task as complete on the list

Duke will mark the task at the given number as complete

Example of usage: mark 1

`mark [int]`

Expected outcome:

If the task is on the list and incomplete it will be marked as complete

```
I have marked this task as complete
[T][X] study for midterm
```

### `unmark` - mark a task on the list as incomplete

Duke will mark a task as incomplete on the list

Example of usage: unmark 2

`unmark [int]`

Expected outcome:

The if the task is on the list and complete it will be marked as incomplete

```
Task was already marked like that
[E][ ] midterm (at: 6:00 PM)
```

### `find` - find a word amongst the list

Duke will find tasks with names that contain the term the user wants to find

Example of usage: find midterm

`find [String]`

Expected outcome:

The list of terms with a matching string or that there are no matching entries

```
Here are the matching tasks in your list
1. [T][X] study for midterm
2. [E][ ] midterm (at: 6:00 PM)
```

### `delete` - delete a task from the list

Duke will delete the task at the given position from the list

Example of usage: delete 2

`delete [int]`

Expected outcome:

The task gets removed from the list

```
Got it. I have removed this task: 
[E][ ] midterm (at: 6:00 PM)
Now you have 2 tasks in your list.
```

### `bye` - say bye to Duke and exit the program

Duke end the program and save the list to a file

Example of usage: 

`bye`

Expected outcome:

Program ends

```
Bye
```