# User Guide

## Features 

### Add Tasks

There are 3 types of tasks that can be added to Duke:
1. Deadline
2. Event
3. Todo

For task Deadline and Event, you can also include a date using the '/by' tag.

### Mark Tasks as Done

You can mark the tasks that you have added as done so you remember which tasks you have completed!

### Mark Tasks as Undone

You can also unmark or mark the task as undone in case you mark the wrong task as done!

### List Tasks

You can list all the tasks that you have added to Duke in case you forget what you have added.

### Delete Tasks

You can also delete tasks from Duke in case you made a mistake when previously adding a task.

### Find Tasks

Duke also allows you to find the tasks that contains a certain keyword and will list them for you.

### Save your Tasks Locally

Duke will also save your tasks locally so that you can check what tasks you have anytime you want!

## Usage

### `deadline` - Adds a deadline task

Adds a deadline task to Duke with a deadline date.

Example of usage: 

`deadline CS2113 Individual Project /by today`

Expected outcome:

Message that the deadline task has been added to Duke along with the number of tasks in Duke.

```
------------------------------------------------------------
Got it. I've added this task:
[D][ ]  CS2113 Individual Project  (by: today)
Now you have 1 tasks in the list.
------------------------------------------------------------
```


### `event` - Adds an event task

Adds an event task to Duke with an event date.

Example of usage:

`event CS2113 /on Thursday`

Expected outcome:

Message that the event task has been added to Duke along with the number of tasks in Duke.

```
------------------------------------------------------------
Got it. I've added this task:
[E][ ]  CS2113 Tutorial  (on: Thursday)
Now you have 2 tasks in the list.
------------------------------------------------------------
```

### `todo` - Adds a todo task

Adds a todo task to Duke.

Example of usage:

`todo Individual Project ReadMe`

Expected outcome:

Message that the todo task has been added to Duke along with the number of tasks in Duke.

```
------------------------------------------------------------
Got it. I've added this task:
[T][ ]  Individual Project ReadMe
Now you have 3 tasks in the list.
------------------------------------------------------------
```

### `list` - Lists all the tasks in Duke

Lists all the tasks you have added into Duke.

Example of usage:

`list`

Expected outcome:

A list of all the tasks in Duke.

```
------------------------------------------------------------
Here are the tasks in your list:
[D][ ]  CS2113 Individual Project  (by: today)
[E][ ]  CS2113 Tutorial  (on: Thuraday)
[T][ ]  Individual Project ReadMe
------------------------------------------------------------
```

### `delete` - Delete a task from Duke

Delete a task from Duke based on the task index.

Example of usage:

`delete 1`

Expected outcome:

A message of which task has been deleted from Duke.

```
------------------------------------------------------------
Noted. I've removed this task:
[D][ ]  CS2113 Individual Project  (by: today)
------------------------------------------------------------
```

### `mark` - Mark a task as done in Duke

Marks a task as done based on the task index.

Example of usage:

`mark 1`

Expected outcome:

A message showing that the task has been done.

```
------------------------------------------------------------
Nice! I've marked this task as done:
[E][X]  CS2113 Tutorial  (on: Thursday)
------------------------------------------------------------
```

### `unmark` - Mark a task as not done in Duke

Marks a task as not done based on the task index.

Example of usage:

`unmark 1`

Expected outcome:

A message showing that the task has been undone.

```
------------------------------------------------------------
OK, I've marked this task as not done yet:
[E][ ]  CS2113 Tutorial  (on: Thursday)
------------------------------------------------------------
```

### `find` - Finds all tasks that contain a certain keyword

Finds all the tasks that contain a keyword you have inputted!

Example of usage:

`find Tutorial`

Expected outcome:

A list of all the matching tasks in Duke.

```
------------------------------------------------------------
Here are the tasks in your list:
1. [E][ ]  CS2113 Tutorial  (on: Thursday)
------------------------------------------------------------
```

### `bye` - Exits Duke

Leaves Duke.

Example of usage:

`bye`

Expected outcome:

A signing off message from Duke.

```
------------------------------------------------------------
Bye. Hope to see you again soon!
------------------------------------------------------------
```