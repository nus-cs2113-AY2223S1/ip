# User Guide

## Features
- Add Deadline task
- Add Event task
- Add ToDo task
- Delete task
- Mark task as done
- Unmark task
- List tasks
- Search tasks
<br><br>

### Feature - Add Deadline task: `deadline`
Adds a deadline task to the task list.

### Feature - Add Event task: `event`
Adds an event task to the task list.

### Feature - Add ToDo task: `todo`
Adds a todo task to the task list.

### Feature - Delete task: `delete`
Deletes a task from the task list.

### Feature - Mark task as done: `mark`
Marks a task as done.

### Feature - Unmark task : `unmark`
Marks a task as undone.

### Feature - List tasks: `list`
Lists all tasks from the task list.

### Feature - Search task: `find`
Searches task based on whether the search phrase matches the task description.
<br><br>


## Usage

### `deadline` - Add a deadline task to the task list

Adds a deadline task to the task list by specifying the task description and the deadline date and time.

<u>Example of usage</u>: `deadline spot 5 unicorns /by 2022-02-21 2359`

<u>Expected outcome</u>: Add a deadline for the activity `spot 5 unicorns` to the task list.
The formatted date of the deadline is `21 February 2022` and the time is `11:59PM`

```
    ____________________________________________________________
     Got it. I've added this task:
      [D][ ] spot 5 unicorns (by: February 21 2022 11:59PM)
     Now you have 1 tasks in the list.
    ____________________________________________________________
```
<br>

### `event` - Add an event task to the task list

Adds an event task to the task list by specifying the task description and the event date and time.

<u>Example of usage</u>: `event pig flying competition /at 2024-09-08 0400`

<u>Expected outcome</u>: Add an event `pig flying competition` to the task list.
The formatted date of the event is `08 September 2024` and the time is `04:00AM`

```
    ____________________________________________________________
     Got it. I've added this task:
      [E][ ] pig flying competition (at: September 08 2024 04:00AM)
     Now you have 1 tasks in the list.
    ____________________________________________________________

```
<br>

### `todo` - Add a ToDo task to the task list

Adds a ToDo task to the task list by specifying the task description.

<u>Example of usage</u>: `todo finish watching the anime One Piece`

<u>Expected outcome</u>: Add a ToDo `finish watching the anime One Piece` to the task list.

```
    ____________________________________________________________
     Got it. I've added this task:
      [T][ ] finish watching the anime One Piece
     Now you have 1 tasks in the list.
    ____________________________________________________________
```
 <br>

### `delete` - Delete a task from the task list

Deletes a task from the task list by specifying its task number from the list.

<u>Example of usage</u>: `delete 2`

<u>Expected outcome</u>: Delete task number 2 from the task list.

The original task list:
```
    ____________________________________________________________
    Here are the tasks in your list:
    1. [D][ ] spot 5 unicorns (by: February 21 2022 11:59PM)
    2. [E][ ] pig flying competition (at: September 08 2024 04:00AM)
    3. [T][ ] finish watching the anime One Piece
    ____________________________________________________________
```

After entering the command:
```
delete 2
```

The output is:
```
    ____________________________________________________________
    Noted, I've removed this task:
    [E][ ] pig flying competition (at: September 08 2024 04:00AM)
    Now you have 2 tasks in the list.
    ____________________________________________________________
```

The new task list is:
```
    ____________________________________________________________
    Here are the tasks in your list:
    1. [D][ ] spot 5 unicorns (by: February 21 2022 11:59PM)
    2. [T][ ] finish watching the anime One Piece
    ____________________________________________________________

```
<br>

### `mark` - Mark a task as done

Marks a task as done, by specifying its task number from the task list.

<u>Example of usage</u>: `mark 2`

<u>Expected outcome</u>: Marks task number 2 as done.
The original task list:
```
    ____________________________________________________________
     Here are the tasks in your list:
     1. [D][ ] spot 5 unicorns (by: February 21 2022 11:59PM)
     2. [T][ ] finish watching the anime One Piece
    ____________________________________________________________
```
After entering the command:
```
mark 2
```

The output is:
```
    ____________________________________________________________
     Nice! I've marked this task as done:
       [X] finish watching the anime One Piece
    ____________________________________________________________
```

The new task list is:
```
    ____________________________________________________________
     Here are the tasks in your list:
     1. [D][ ] spot 5 unicorns (by: February 21 2022 11:59PM)
     2. [T][X] finish watching the anime One Piece
    ____________________________________________________________
```
<br>

### `unmark` - Mark a task as undone

Marks a task as undone, by specifying its task number from the task list.

<u>Example of usage</u>: `unmark 2`

<u>Expected outcome</u>: Marks task number 2 as not done.
The original task list:
```
    ____________________________________________________________
     Here are the tasks in your list:
     1. [D][ ] spot 5 unicorns (by: February 21 2022 11:59PM)
     2. [T][X] finish watching the anime One Piece
    ____________________________________________________________
```
After entering the command:
```
unmark 2
```

The output is:
```
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [ ] finish watching the anime One Piece
    ____________________________________________________________
```

The new task list is:
```
    ____________________________________________________________
     Here are the tasks in your list:
     1. [D][ ] spot 5 unicorns (by: February 21 2022 11:59PM)
     2. [T][ ] finish watching the anime One Piece
    ____________________________________________________________
```
<br>

### `list` - Show all tasks in the task list

Displays all tasks and whether they are done.

<u>Example of usage</u>: `list`

<u>Expected outcome</u>: Lists all the tasks and their statuses.
```
    ____________________________________________________________
     Here are the tasks in your list:
     1. [D][ ] spot 5 unicorns (by: February 21 2022 11:59PM)
     2. [T][X] finish watching the anime One Piece
    ____________________________________________________________
```
<br>

### `find` - Search for tasks

Search for tasks based on whether the search phrase matches a section of or the whole the task description exactly.

<u>Example of usage</u>: `find unicorn`

<u>Expected outcome</u>: Lists all the tasks whose descriptions contain the search phrase.

The full task list:
```
    ____________________________________________________________
     Here are the tasks in your list:
     1. [D][ ] spot 5 unicorns (by: February 21 2022 11:59PM)
     2. [T][X] finish watching the anime One Piece
     3. [E][X] unicorn racing (at: February 19 2099 01:00PM)
     4. [E][ ] pig flying competition (at: September 08 2024 04:00AM)
     5. [T][X] build a rocket
     6. [E][ ] fight a mummy (at: August 08 2002 03:00AM)
     7. [D][ ] discover something that doesn't exist (by: December 31 2003 11:59PM)
    ____________________________________________________________
```
After entering the command:
```
find unicorn
```
The output is:
```
    ____________________________________________________________
     Here are the matching tasks in your list:
     1. [D][ ] spot 5 unicorns (by: February 21 2022 11:59PM)
     2. [E][X] unicorn racing (at: February 19 2099 01:00PM)
     
     Number of search results: 2
    ____________________________________________________________

```