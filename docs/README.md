# User Guide

## Features 

### Task List

There are three types of tasks that can be added to
the task list: todo, deadline and event. The task list
can track their status of completion and find tasks
through keywords. It will be stored locally and opened
automatically upon next launch of software.

## Usage

### `Add a task`

Add a todo, deadline or event to your task list.

Example of usage: 

`todo homework`

Expected outcome:

```
added: [T][] homework
```

Example of usage:

`deadline assignment /by: today`

Expected outcome:

```
added: [D][] assignment (by: today)
```

Example of usage:

`event celebration /at: Saturday`

Expected outcome:

```
added: [E][] celebration (at: Saturday)
```

### `List all tasks`

List all the tasks on the task list now.

Example of usage:

`list`

Expected outcome:
```
There are 2 tasks on the list now:
1.[D][] assignment (by: today)
2.[E][] celebration (at: Saturday)
```

### `Mark a task`

Mark a task as done, or unmark it as not done.

Example of usage:
`mark 1`

Expected outcome:

```
Task 1 is marked as done~
[D][X] assignment (by: today)
```

Example of usage:

`unmark 1`

Expected outcome:
```
Task 1 is unmarked as not done~
[D][] assignment (by: today)
```

### `Delete a task`

Delete a task from the task list.

Example of usage:

`delete 2`

Expected outcome:

```
Task 2: [E][] celebration (at: Saturday) is removed~
There are 1 tasks on the list now:
1.[D][] assignment (by: today)
```

### `Find a task`

Find a task whose description or time contains
a given keyword or keyphrase.

Example of usage:

`find assignment`

Expected outcome:
```
1 matching tasks are found:
1.[D][] assignment (by: today)
```