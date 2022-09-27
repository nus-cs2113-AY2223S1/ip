# User Guide

## Table of contents

* [Features](#features)
    * [Viewing help: `help`](#viewing-help-help)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Adding a todo task: `todo`](#adding-a-todo-task-todo)
    * [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
    * [Adding an event task: `event`](#adding-an-event-task-event)
    * [Marking a task as done: `mark`](#marking-a-task-as-done-mark)

## Features

### Viewing help: `help`

Shows the command menu with description of each command and its syntax.

Format: `help`

Expected outcome: 
Supported commands' descriptions and syntax are listed in the command-line interface.

Expected output:
```
==================================================================
help

Get help for the commands supported and their syntax
    help

View all tasks with their task number
    list

Add a todo task
    todo <description>

Add a task with its deadline
    deadline <description> /by <deadline-datetime>

Add an event with its date and time
    event <description> /at <event-datetime>

Mark a task as done
    mark <task-number>

Unmark a task from done
    unmark <task-number>

Delete a task from the list
    delete <task-number>

Find task(s) with keywords in the description
    find <search-word>

Exit the application
    bye
==================================================================
```

### Listing all tasks: `list`

Shows a numbered list of all tasks in the task list.

Format: `list`

Expected outcome: A numbered list of the tasks in the list with its status, description and datetime (if applicable) shown.

Examples:
Assume the task list have the following tasks:
* A todo task with description "Buy a pencil" and is marked as done
* A deadline task with description "Submit lab assignment", deadline date "Sep 30 23:59" and is marked as not done
* An event task with description "Movie watching", date "1 Oct 16:00 - 18:00" and is marked as not done
`list` returns the following outcome
```
==================================================================
list
1. [T][X] Buy a pencil
2. [D][ ] Submit lab assignment (by: Sep 30 23:59)
3. [E][ ] Movie watching (at: 1 Oct 16:00 - 18:00)
==================================================================
```

### Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `todo <description>`

Expected outcome: 
* A todo task with the given description will be added to the task list. 
* If the description is not provided, no todo task will be added to the list.

Examples:
* `todo Buy a pencil` Adds the todo task `Buy a pencil` to the list.
```
==================================================================
todo Buy a pencil
Task added: [T][ ] Buy a pencil
==================================================================
```

* `todo` Shows error message that the input is invalid as the description is missing.
```
==================================================================
todo
Invalid input, todo task could not be added
Syntax: todo <description>
==================================================================
```

### Adding a deadline task: `deadline`

Adds an event task to the task list.

Format: `deadline <description> /by <deadline-datetime>`

Expected outcome:
* A deadline task with the given description and datetime will be added to the task list.
* If the description or datetime are not specified or the separator `/by` is not included in the command, then no deadline task will be added to the list.

Examples:
* `deadline Submit lab assignment /by Sep 30 23:59`
```
==================================================================
deadline Submit lab assignment /by Sep 30 23:59
Task added: [D][ ] Submit lab assignment (by: Sep 30 23:59)
==================================================================
```

* `deadline` Shows error message that the input is invalid as both the description and datetime are missing.
```
==================================================================
deadline
Invalid input, please provide the description and deadline
Syntax: deadline <description> /by <deadline-datetime>
==================================================================
```

* `deadline Submit lab assignment` Shows error message that the input is invalid as the datetime is missing.
```
==================================================================
deadline Submit lab assignment
Invalid input, please provide the description and deadline
Syntax: deadline <description> /by <deadline-datetime>
==================================================================
```

### Adding an event task: `event`

Adds an event task to the task list.

Format: `event <description> /at <event-datetime>`

Expected outcome:
* An event task with the given description and datetime will be added to the task list.
* If the description or datetime are not specified or the separator `/at` is not included in the command, then no event task will be added to the list.

Examples:
* `event Movie watching /at 1 Oct 16:00 - 18:00` Adds an event task `Movie watching` which happens at `1 Oct 16:00 - 18:00` to the task list.
```
==================================================================
event Movie watching /at 1 Oct 16:00 - 18:00
Task added: [E][ ] Movie watching (at: 1 Oct 16:00 - 18:00)
==================================================================
```
* `event` Shows error message that the input is invalid as both the description and datetime are missing.
```
==================================================================
event
Invalid input, please provide the description and date time
Syntax: event <description> /at <event-datetime>
==================================================================
```
* `event Movie watching` Shows error message that the input is invalid as the datetime is missing.
```
==================================================================
event Movie watching
Invalid input, please provide the description and date time
Syntax: event <description> /at <event-datetime>
==================================================================
```

### Marking a task as done: `mark`

Marks the specfied task from the task list as done.

Format: `mark <task-number>`
* Mark the task at the specified `task-number` as done.
* The task number refers to the number shown in the displayed task list.
* The task number must be a positve integer ranging from 1 to the total number of tasks in the task list.
