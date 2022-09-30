# User Guide

## Features

### Manage and view task list

Allows a user to add and delete tasks from a stored list

### Add Todos

Allows a user to add todos to the task list.

### Add Deadlines

Allows a user to add a deadline to the task list.

### Add events

Allows a user to add an event to the task list.

### Persistent storage

Saves the list upon stoppage of program and loads list from storage
upon restarting program.

### Mark tasks 

Allows a user to mark a task as complete or incomplete

### Search for a keyword

Allows a user to search for a keyword within the task list

## Usage

### `list` - displays task list

A task list is displayed in the order the tasks were added. Also displays whether a task is marked.

Example of usage:

`list`

Expected outcome:

A task list will be displayed

```
1.[E][X] event_1 (at: Dec 1 2019)
2.[D][ ] deadline_1  (by: Dec 1 2019)
3.[T][X] todo_1
```
<br />


### `find (term)` - searches for a keyword

Searches for the specified keyword within the added tasks.

Example of usage:

`find term`

Expected outcome:

A task list will be displayed with tasks that match the search term

```
1.[E][X] hello term (at: Dec 1 2019)
2.[D][ ] second term (by: Dec 1 2019)
3.[T][X] this term
```
<br />


### `todo (description)` - adds a todo 

Adds a todo with a description into the task list

Example of usage:

`todo test`

Expected outcome:

A confirmation will be outputted with the newly added todo.

```
Got it. I've added this task:
  [T][ ] test
Now you have 7 tasks in the list.
```  
<br />

### `deadline (description) /by (yyyy-mm-dd)` - adds a deadline 

Adds a deadline with a description and date into the task list

Example of usage:

`deadline test /by 2022-01-01`

Expected outcome:

A confirmation will be outputted with the newly added deadline.

```
Got it. I've added this task:
  [D][ ] test (by: Jan 1 2022)
Now you have 8 tasks in the list.
```
<br />

### `event (description) /at (yyyy-mm-dd)` - adds an event 
Adds an event with a description and date into the task list

Example of usage:

`event test /at 2022-01-01`

Expected outcome:

A confirmation will be outputted with the newly added event.

```
Got it. I've added this task:
  [E][ ] test (at: Jan 1 2022)
Now you have 9 tasks in the list.
```
<br />

### `delete (# in list)` - delete a task 

Deletes a task within the task list

Example of usage:

`delete 1`

Expected outcome:

A confirmation will be outputted with the deleted task.

```
Noted. I've removed this task:
[E][ ] teo (at: Dec 1 2019)
Now you have 8 tasks in the list.
```
<br />

### `mark (# in list)` - marks a task as complete 

Marks a task complete within the task list

Example of usage:

`mark 1`

Expected outcome:

A confirmation will be outputted with the newly marked task.

```
Nice! I've marked this task as done: 
  [E][X] task 1 (at: Dec 1 2019)
```
<br />

### `unmark (# in list)` - marks a task as incomplete

Unmarks a task within the task list

Example of usage:

`unmark 1`

Expected outcome:

A confirmation will be outputted with the newly unmarked task.

```
OK, I've marked this task as not done yet: 
  [E][ ] task 1 (at: Dec 1 2019)
```
