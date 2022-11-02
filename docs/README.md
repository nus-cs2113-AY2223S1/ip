# User Guide

## Features 

### Feature-Add Tasks

Add three type of tasks that are classified as:

1. Todo task
2. Deadline task
3. Event task

### Feature-Remove Tasks

Remove a task that is determined by the user.

### Feature-Find Tasks

Find a task using keyword(s) that is specified by the user.

### Feature-Mark Task that is done

Mark a specified task fulfilled or unmark a task that is not fulfilled.

### Feature-Store deadline task period 

In addition to adding a deadline task, user can store a deadline period.  
e.g., Finish tp project by _Fri 30<sup>th</sup> Sept_

### Feature-Store event task location

In addition to adding an event task, user can store an event location.  
e.g., Club meeting at _NUS Utown_ 

### Feature-Shut down Flash bot

When user types a `bye` command, the bot will exit and stop.

## Usage

### `todo` - Add a todo task

Input the command `todo` followed by the _task detail_.  
Automatically saves the specified todo task to a list of tasks.

Example of usage: 

`todo join NUS Drone Club`

Expected outcome:

Displays an acknowledgment message to confirm the creation of  
todo task and inform the user the number of tasks in the task list.

```
	____________________________________________________________
	 Roger that. I've added this task:
	   [T][ ] join NUS Drone Club
	 Now you have 1 in the list.
	____________________________________________________________
```

### `deadline` - Add a deadline task

Input the command `deadline` followed by the _task detail_ & _deadline period_.
Automatically saves the specified deadline task to a list of tasks.

Example of usage:

`deadline return book /by Sunday`

Expected outcome:

Displays an acknowledgment message to confirm the creation of  
deadline task and inform the user the number of tasks in the task list.

```
	____________________________________________________________
	 Roger that. I've added this task:
	   [D][ ] return book (by: June 6th)
	 Now you have 2 in the list.
	____________________________________________________________
```

### `event` - Add an event task

Input the command `event` followed by the _task detail_ & _event location_.  
Automatically saves the specified event task to a list of tasks.   

Example of usage:

`event project meeting /at NUS Utown`

Expected outcome:

Displays an acknowledgment message to confirm the creation of  
event task and inform the user the number of tasks in the task list.

```
	____________________________________________________________
	 Roger that. I've added this task:
	   [E][ ] project meeting (at: NUS Utown)
	 Now you have 3 in the list.
	____________________________________________________________
```

### `mark` - Mark task as done

Input the command `mark` followed by _index of chosen task_.   

Example of usage:

`mark 1`

Expected outcome:

Marks the specified task to be done denoted as 'X' and  
display an acknowledgment message to confirm the specified task completion.

```
	____________________________________________________________
	 Awesome! I've marked this task as done:
	   [T][X] read book
	____________________________________________________________
```

### `unmark` - Mark task as not done

Input the command `unmark` followed by _index of chosen task_.

Example of usage:

`unmark 1`

Expected outcome:

Unmark the specified task as not done denoted as [ ] of the right box and    
display an acknowledgment message to confirm the specified task is not done.

```
	____________________________________________________________
	 Awesome! I've marked this task as not done yet:
	   [T][ ] read book
	____________________________________________________________
```

### `delete` - Delete a task

Input the command `delete` followed by _index of chosen task_.

Example of usage:

`delete 1`

Expected outcome:

The specified task would be deleted from the list of tasks and
displays an acknowledgment message for the user to confirm the deleted task.  
Will reflect the number of task left in the list.

```
	____________________________________________________________
	 Noted. I've removed this task:
	   [T][ ] read book
	 Now you have 6 tasks in the list.
	____________________________________________________________
```

### `Find` - Find task(s)

Input the command `find` followed by _keyword(s)_.

Example of usage:

`find book`

Expected outcome:

A list of tasks would be shown that matches the _keyword(s)_ i.e., 'book'.

```
	____________________________________________________________
	 Here are the matching tasks in your list:
	 1.[D][ ] return book (by: June 6th)
	 2.[T][ ] borrow book
	 3.[D][ ] return book (by: Sunday)
	____________________________________________________________
```

### `bye` - Exit Flash bot program (Shut down)

Input the command `bye`.

Example of usage:

`bye`

Expected outcome:

Exit from Flash bot and a goodbye message will be display to confirm shut down.

```
	____________________________________________________________
	 Bye. Hope to see you again soon!
	____________________________________________________________
```

