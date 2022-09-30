# User Guide

## Features 

### Add deadline
The user can add and save a deadline.

### Add todo
The user can add and save a todo.

### Add event
The user can add and save an event into the file by entering description
and date and time of the event.

### List tasks
The user can view all tasks saved.

### Delete task
The user can delete a task by entering its index in the list.

### Mark as done
The user can mark a task by entering its index in the list and can change the status back to not done.

### Find tasks
The user can find a task base on its description by entering keywords.

### Quit
The user quit the program.

### Load
The user can load tasks from the saved file.

### Save
Duke automatically save changes.

### Create
Duke can automatically create a file when one at the specific location doesn't exist

## Usage

### `deadline` - Add and save a deadline to the list of tasks

Add and save a deadline to the list of tasks by entering description
and date and time of the deadline.

Example of usage: 

`deadline return book /by Sunday`

Expected outcome:
Show a new task is added and display how many tasks are in the list.
```
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 6 tasks in the list.
____________________________________________________________
```

### `todo` - Add and save a todo to the list of tasks

Add and save a todo to the list of tasks by entering description.

Example of usage:

`todo borrow book`

Expected outcome:
Show a new task is added and display how many tasks are in the list.
```
____________________________________________________________
Got it. I've added this task:
  [T][ ] borrow book
Now you have 8 tasks in the list.
____________________________________________________________
```

### `event` - Add and save an event to the list of tasks

Add and save an event to the list of tasks by entering description
and date and time of the event.

Example of usage:

`event project meeting /at Mon 2-4pm`

Expected outcome:
Show a new task is added and display how many tasks are in the list.
```
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (at: Mon 2-4pm)
Now you have 7 tasks in the list.
____________________________________________________________

```

### `list` - view all tasks saved
The user can view all tasks saved.

Example of usage:

`list`

Expected outcome:
print all tasks in the list with index.
```
____________________________________________________________
Here are the tasks in your list:
  1.[T][ ] cook
  2.[D][ ] return book (by: Sunday)
  3.[E][ ] project meeting (at: Mon 2-4pm)
  4.[T][ ] borrow book
____________________________________________________________
```

### `delete` - delete the specified task
The user can delete a task base on its index.

Example of usage:

`delete 2`

Expected outcome:
print all tasks in the list with index.
```
Noted. I've removed this task:
[D][ ] return book (by: Sunday)
Now you have 3 tasks in the list.
____________________________________________________________
```

### `mark` - mark a task as done
The user can mark a task as done base on its index.

Example of usage:

`mark 1`

Expected outcome:
print the marked task.
```
____________________________________________________________
Nice! I've marked this task as done:
[T][X] cook
____________________________________________________________
```

### `unmark` - mark a task as not done
The user can mark a task as not done base on its index.

Example of usage:

`unmark 1`

Expected outcome:
print the unmarked task.
```
____________________________________________________________
OK, I've marked this task as not done yet:
[T][ ] cook
____________________________________________________________
```

### `find` - find tasks that contains the given description
The user can find tasks by entering keywords. Relevant tasks will be displayed.

Example of usage:

`find c`

Expected outcome:
tasks that contain the keyword are printed.
```
____________________________________________________________
     Here are the matching tasks in your list:
     [T][ ] cook
     [E][X] project meeting (at: Mon 2-4pm)
____________________________________________________________
```

### `bye` - quit the program
The user can quit tasks by entering "bye".

Example of usage:

`bye`

Expected outcome:
The process safely finished.
```
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
Process finished with exit code 0
```

### `quit` - quit the program
The user can quit tasks by entering "quit".

Example of usage:

`quit`

Expected outcome:
The process safely finished.
```
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
Process finished with exit code 0
```

### `load` - load from a file
The program will automatically read and load tasks from "data/duke.txt" 
which is under the same folder as Duke.jar during the initialization stage.


Expected outcome:
tasks are loaded.
```
____________________________________________________________
 Reading from data/duke.txt
 start loading
____________________________________________________________
Got it. I've added this task:
  [T][ ] cook
Got it. I've added this task:
  [E][X] project meeting (at: Mon 2-4pm)
Got it. I've added this task:
  [T][ ] borrow book
____________________________________________________________
 done loading.
____________________________________________________________
```

### `save` - save into a file
The program will automatically save data into "data/duke.txt"
when changes are made. No message will be displayed unless an error occur.

### `create` - create a file
The program will automatically create a file when the file at the path "data/duke.txt" doesn't exist.