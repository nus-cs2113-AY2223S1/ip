# User Guide

## Quick Start

### Setup
Ensure you have JDK 11 installed on your device.


### Basic commands

`list`: Lists all tasks in the task list.

`todo [task]`: Adds a task to the list.

`mark [task number]`: Marks a task as done.

`delete [task number]`: Removes a task from the list.

`bye`: Exits the application.

## Commands

### `list`

Lists all tasks currently in the task list.

Example: 

`list`

Expected output:

```
____________________________________________________________

Here are the tasks in your list:
1. [T][ ] Eat lunch
2. [D][X] Coding assignment (by: Friday 2359)
3. [E][ ] Picnic (at: Marina Barrage, Sunday 1600)
____________________________________________________________
```

### `todo`

Adds a todo to the task list.

Example:

`todo Eat lunch`

Expected output:

```
____________________________________________________________
Got it. I've added this task:
[T][ ] Eat lunch
Now you have 1 task in the list.
____________________________________________________________
```

### `deadline`

Adds a deadline to the task list.

Example:

`deadline Coding assignment /by Friday 2359`

Expected output:

```
____________________________________________________________
Got it. I've added this task:
[D][ ] Coding assignment (by: Friday 2359)
Now you have 1 task in the list.
____________________________________________________________
```

### `event`

Adds an event to the task list.

Example:

`event Picnic /at Marina Barrage, Sunday 1600`

Expected output:

```
____________________________________________________________
Got it. I've added this task:
[E][ ] Picnic (at: Marina Barrage, Sunday 1600)
Now you have 1 task in the list.
____________________________________________________________
```
### `delete`

Removes a task from the task list based on its number in the list.

Example:

`delete 1`

Expected output:

```
____________________________________________________________

Noted. I've removed this task:
[T][ ] Eat lunch
Now you have 0 tasks in the list.
____________________________________________________________
```

### `mark`

Marks a task as done based on its number in the task list.

Example:

`mark 2`

Expected output:

```
____________________________________________________________
Nice! I've marked this task as done:
[D][X] Coding assignment (by: Friday 2359)
____________________________________________________________
```

### `unmark`

Marks a task as undone based on its number in the task list.

Example:

`unmark 2`

Expected output:

```
____________________________________________________________
Ok, I've marked this task as not done yet:
[D][ ] Coding assignment (by: Friday 2359)
____________________________________________________________
```

### `save`

Saves the task list to a file called Duke.txt from the data folder.

Example:

`save`

Expected output:

```
____________________________________________________________

Your tasks have been saved successfully.
____________________________________________________________
```

### `bye`

Exits the application.

Example:

`bye`

Expected output:

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```