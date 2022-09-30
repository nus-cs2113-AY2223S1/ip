# Duke User Guide

Duke is a command-line interface (CLI) program that manages tasks. It can keep track tasks such as To-dos, Deadlines and Events.

## Features
 

### Feature-ABC
### Feature-Task Management

Tasks of types To-do, Deadline and Event can be added to Duke and stored in a file together with their description. Deadline and Event can further add their respective deadline date and event date.
Description of the feature.

### Feature-XYZ
### Feature-Storage

Any amendments on the tasks is saved to a .txt file. When Duke is booted up, it can load up the saved tasks.

Description of the feature.

## Usage

### `Keyword` - Describe action
###  `todo` - Add Todo task

Add a todo task with its description into the task list.

Example of usage: 

`todo read book`

Expected outcome:

A todo task is added to the task list. The task type, done status and task description and the total number of tasks will be displayed.

```
Got it. I've added this task:
[T][] read book
Now you have 1 tasks in the list.
```

### `deadline` - Add deadline task

Add a deadline task with its description and date into the task list.

Example of usage:

`deadline return book /by Saturday`

Expected outcome:

A deadline task is added to the task list. The task type, done status and task description, date and the total number of tasks will be displayed.

```
Got it. I've added this task:
[D][] return book(by: Saturday)
Now you have 2 tasks in the list.
```

### `event` - Add event task

Add a event task with its description and date into the task list.

Example of usage:

`event project meeting /at Friday 2-4pm`

Expected outcome: 

An event task is added to the task list. The task type, done status and task description, date and the total number of tasks will be displayed.

```
Got it. I've added this task:
[E][] project meeting(at: Friday 2-4pm)
Now you have 3 tasks in the list.
```

### `list` - list all the tasks

List all the tasks that are stored in Duke in order of the time the task is added.

Example of usage:

`list`

```
Here are the tasks in your list:
1. [T][] read book
2. [D][] return book(by: Saturday)
3. [E][] project meeting(at: Friday 2-4pm)
```

### `mark` - Mark the task

Mark the desired task as done.

Example of usage:

`mark 2`

Mark the 2nd task in the task list.

```
Nice! I've marked this task as done:
[D][X] return book(by: Saturday)
```

### `unmark` - Unmark the task

Unmark the desired task as not done.

Example of usage:

`unmark 2`

Unmark the 2nd task in the task list.

```
OK, I've marked this task as not done yet:
[D][] return book(by: Saturday)
```

### `delete` - Delete task

Delete the desired task from the list.

`delete 3` 

Delete the 2nd task in the task list and update the number of tasks in the list.

```
Noted. I've removed this task:
[E][] project meeting(at: Friday 2-4pm)
Now you have 2 tasks in the list. 
```

### `find` - Find the tasks

Find the desired tasks from the list.

Example of usage:

`find book`

Find all the tasks that contains book in their description and list them.

```
Here are the matching tasks in your list:
1. [T][] read book
2. [D][] return book(by: Saturday)
```

### `bye` - Exit Duke

Exit Duke program.

Example of usage:

`bye duke`

Print goodbye message and exit duke program.

```
Bye. Hope to see you soon.
```



