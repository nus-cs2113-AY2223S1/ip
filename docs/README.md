# User Guide

## Table of contents

* [Features](#features)
    * [Viewing help: `help`](#viewing-help-help)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Adding a todo task: `todo`](#adding-a-todo-task-todo)
    * [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
    * [Adding an event task: `event`](#adding-an-event-task-event)
    * [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
    * [Unmarking a task from done: `unmark`](#unmarking-a-task-from-done-unmark)
    * [Deleting a task: `delete`](#deleting-a-task-delete)
    * [Finding tasks by description: `find`](#finding-tasks-by-description-find)
    * [Exiting the program: `bye`](#exiting-the-program-bye)
    * [Saving the data](#saving-the-data)

## Features

### Viewing help: `help`

Shows the command menu with description of each command and its syntax.

Format: `help`

Expected outcome: 
Supported commands' descriptions and syntax are listed in the command-line interface.

Expected output:
```
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

Expected outcome: 
* A numbered list of the tasks in the list with its status, description and datetime (if applicable) shown.
* Each task is represented by a format `[task-type][status][description](datetime if applicable)` 
* The task types are `T` for todo, `D` for deadline and `E` for event tasks.
* The status `[X]`  means the task is done, while the blank status `[ ]` means the task is not done.
* Date and time is specified for deadline and event tasks. 
    * Deadline's datetime is shown as `(by: datetime)`.
    * Event's datetime is shown as `(at: datetime)`.

Examples:
* Assume the task list have the following tasks:
    1. A todo task with description "Buy a pencil" and is marked as done
    2. A deadline task with description "Submit lab assignment", deadline date "Sep 30 23:59" and is marked as not done
    3. An event task with description "Movie watching", date "1 Oct 16:00 - 18:00" and is marked as not done
    `list` returns the following output
```
list
1. [T][X] Buy a pencil
2. [D][ ] Submit lab assignment (by: Sep 30 23:59)
3. [E][ ] Movie watching (at: 1 Oct 16:00 - 18:00)
==================================================================
```

* If the task list has no tasks, `list` returns the following output
```
list
There are no tasks added yet. Type 'help' if you need help.
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
todo Buy a pencil
Task added: [T][ ] Buy a pencil
==================================================================
```

* `todo` Shows error message that the input is invalid as the description is missing.
```
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
deadline Submit lab assignment /by Sep 30 23:59
Task added: [D][ ] Submit lab assignment (by: Sep 30 23:59)
==================================================================
```

* `deadline` Shows error message that the input is invalid as both the description and datetime are missing.
```
deadline
Invalid input, please provide the description and deadline
Syntax: deadline <description> /by <deadline-datetime>
==================================================================
```

* `deadline Submit lab assignment` Shows error message that the input is invalid as the datetime is missing.
```
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
event Movie watching /at 1 Oct 16:00 - 18:00
Task added: [E][ ] Movie watching (at: 1 Oct 16:00 - 18:00)
==================================================================
```
* `event` Shows error message that the input is invalid as both the description and datetime are missing.
```
event
Invalid input, please provide the description and date time
Syntax: event <description> /at <event-datetime>
==================================================================
```
* `event Movie watching` Shows error message that the input is invalid as the datetime is missing.
```
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

Expected outcomes:
* The task specified by the task number will be marked as done in the list.
* No operations will be done on the list if the task number is not provided, out of range or not an integer.

Examples:
Given the list by`list` 
```
list
1. [T][X] Buy a pencil
2. [D][ ] Submit lab assignment (by: Sep 30 23:59)
3. [E][ ] Movie watching (at: 1 Oct 16:00 - 18:00)
==================================================================
```

* `mark 2` Marks the 2nd task of the list as done.
```
mark 2
Marked as done: [D][X] Submit lab assignment (by: Sep 30 23:59)
==================================================================
```

* `mark 4` Shows an error message that the task number is out of range.
```
mark 4
Sorry, the task number is out of range. Type "list" for viewing all the tasks.
Syntax: mark <task-number>
==================================================================
```

* `mark` Shows an error message that the task number is missing from the command.
```
mark 
Sorry, you have not provide the task number.
Syntax: mark <task-number>
==================================================================
```

* `mark two` Shows an error message when the task number is not provided as an integer.
```
mark two
Invalid input. Please type an integer for the task number.
Syntax: mark <task-number>
==================================================================
```

### Unmarking a task from done: `unmark`

Unmarks the specified task from the task list from being done.

Format: `unmark <task-number>`
* Unmark the task at the specified `task-number` from done.
* The task number refers to the number shown in the displayed task list.
* The task number must be a positve integer ranging from 1 to the total number of tasks in the task list.

Expected outcomes:
* The task specified by the task number will be marked as not done in the list.
* No operations will be done on the list if the task number is not provided, out of range or not an integer.

Examples:
Given the list by`list` 
```
list
1. [T][X] Buy a pencil
2. [D][X] Submit lab assignment (by: Sep 30 23:59)
3. [E][ ] Movie watching (at: 1 Oct 16:00 - 18:00)
==================================================================
```

* `unmark 2` Marks the 2nd task of the list as not done.
```
unmark 2
Unmarked done: [D][ ] Submit lab assignment (by: Sep 30 23:59)
==================================================================
```

* `unmark 4` Shows an error message that the task number is out of range.
```
unmark 4
Sorry, the task number is out of range. Type "list" for viewing all the tasks.
Syntax: unmark <task-number>
==================================================================
```

* `unmark` Shows an error message that the task number is missing from the command.
```
unmark
Sorry, you have not provide the task number.
Syntax: unmark <task-number>
==================================================================
```

* `unmark two` Shows an error message when the task number is not provided as an integer.
```
unmark two
Invalid input. Please type an integer for the task number.
Syntax: unmark <task-number>
==================================================================
```

### Deleting a task: `delete`

Deletes the specified task from the task list.

Format: `delete <task-number>`
* Delete the task at the specified `task-number` from the task list.
* The task number refers to the number shown in the displayed task list.
* The task number must be a positve integer ranging from 1 to the total number of tasks in the task list.

Expected outcomes:
* The task specified by the task number will be deleted from the list.
* No operations will be done on the list if the task number is not provided, out of range or not an integer.

Examples:
Given the list by`list` 
```
list
1. [T][X] Buy a pencil
2. [D][ ] Submit lab assignment (by: Sep 30 23:59)
3. [E][ ] Movie watching (at: 1 Oct 16:00 - 18:00)
==================================================================
```

* `delete 3` Deletes the 3rd task from the list.
```
delete 3
Task 3 deleted
==================================================================
```

* `delete 4` Shows an error message that the task number is out of range.
```
delete 4
Sorry, the task number is out of range. Type "list" for viewing all the tasks.
Syntax: delete <task-number>
==================================================================
```

* `delete` Shows an error message that the task number is missing from the command.
```
delete
Sorry, you have not provide the task number.
Syntax: delete <task-number>
==================================================================
```

* `delete two` Shows an error message when the task number is not provided as an integer.
```
delete two
Invalid input. Please type an integer for the task number.
Syntax: delete <task-number>
==================================================================
```

### Finding tasks by description: `find`

Finds all the tasks that include the search word in their descriptions.

Format: `find <search-word>`
* Shows all the tasks that have the entire `search-word` being a substring of the tasks' descriptions.
* The search result is case-insensitve.
* Only the description is searched.
* The search result does not search task description that the seach word is splitted by some other characters or strings. e.g Searching `pencil buy` will not return the result `[T][X] Buy a pencil`.

Expected outcomes:
* All the tasks that have the entire search word as a substring of the their description will be listed.
* If the search word is not provided, it will not find anything.

Examples:
Given the list by`list` 
```
list
1. [T][X] Buy a pencil
2. [D][ ] Submit lab assignment (by: Sep 30 23:59)
3. [E][ ] Movie watching (at: 1 Oct 16:00 - 18:00)
4. [T][ ] Submit survey
==================================================================
```

* `find submit` Returns the list of tasks that has the word `submit` in the description.
```
find submit
1. [D][ ] Submit lab assignment (by: Sep 30 23:59)
2. [T][ ] Submit survey
==================================================================
```

* `find apple` Returns the message that no tasks match with the search word.
```
find apple
No matching tasks for the given search word.
==================================================================
```

* `find` Shows an error message that a search word was not provided in the command.
```
find
Please provide a search word to find the task.
Syntax: find <search-word>
==================================================================
```

### Exiting the program: `bye`

Exit the program.

Format: `bye`

Expected output:
```
bye
Bye. Hope to see you again soon!
```

### Saving the data
Data in the task list of Ever are saved as a .txt file `[JAR file location]/data/duke.txt` automatically after any operation that changes the list. No manual saving is needed nor allowed.