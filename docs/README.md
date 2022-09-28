# User Guide
Duke is a robot that allows you to track tasks. These tasks can be in either Todos, Deadlines or Events.
You can also get your tasks in a .txt file for you to do whatever you want with it!
## Features
- [Adding a Todo: todo](#adding-a-todo-todo)
- [Adding a Deadline: deadline](#adding-a-deadline-deadline)
- [Adding an Event: event](#adding-an-event-event)
- [Marking a Task: mark](#marking-a-task-mark)
- [Unmarking a Task: unmark](#unmarking-a-task-unmark)
- [Deleting a Task: delete](#deleting-a-task-delete)
- [View all tasks: list](#view-all-tasks-list)
- [Exiting the program: bye](#exiting-the-program-bye)

### Adding a Todo: `todo`
Adds a Todo to the list

Use the command:

`todo sometask`

Example of Usage:

`todo Math Tutorial`

Expected outcome:

```
-------------------------------------
Got it. I have added this task:
[T][ ] Math Tutorial
You now have 1 tasks.
-------------------------------------
```

### Adding a Deadline: `deadline`
Adds a Deadline to the list

Use the command:

`deadline sometask /by somedate`

Example of Usage:

`deadline Group Project /by Friday`

Expected outcome:

```
-------------------------------------
Got it. I have added this task:
[D][ ] Group Project (by: Friday)
You now have 2 tasks.
-------------------------------------
```

### Adding an Event: `event`
Adds an Event to the list

Use the command:

`event sometask /at somedate`

Example of Usage:

`event Concert /at Sunday 1900`

Expected outcome:

```
-------------------------------------
Got it. I have added this task:
[E][ ] Concert (at: Sunday 1900)
You now have 3 tasks.
-------------------------------------
```

### Marking a Task: `mark`

Marks a task.

Use the command:

`mark index`

Example of usage:

`mark 2`

Expected outcome:

```
-------------------------------------
Marked the following task:
[D][X] Group Project (by: Friday)
-------------------------------------
```

### Unmarking a Task: `unmark`

Unmarks a task.

Use the command:

`unmark index`

Example of usage:

`unmark 2`

Expected outcome:

```
-------------------------------------
Unmarked the following task:
[D][ ] Group Project (by: Friday)
-------------------------------------
```

### Deleting a Task: `delete`

Deletes a task.

Use the command:

`Delete index`

Example of usage:

`delete 2`

Expected outcome:

```
-------------------------------------
The following task has been deleted: 
[D][ ] Group Project (by: Friday)
You now have 2 tasks.
-------------------------------------
```

### View all tasks: `list`

Displays all the tasks currently in the list.

Use the command:

`list`

Expected outcome:

```
-------------------------------------
Here is the list of your tasks: 
1. [T][ ] Math Tutorial
2. [E][ ] Concert (at: Sunday 1900)
-------------------------------------
```

### Exiting the program: `bye`

Stops the execution of Duke.

Use the command:

`bye`

Expected outcome:

```
-------------------------------------
Goodbye, hope to see you again.
-------------------------------------
```

