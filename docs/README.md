# User Guide

## Introduction

Duke is a personal task manager that helps you keep track of your tasks. It is optimized for use via a Command Line Interface (CLI). If you can type fast, Duke can help you manage your tasks faster than traditional GUI apps.

## Setting up

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/deenliong/ip/releases/download/A-Release/Duke.jar)
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Open a command line and pass in the following command to run the jar file: `java -jar duke.jar`
5. If the setup is correct, you should see the welcome screen as shown below.

```
 ____        _
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

_____________________

Hello! I'm Duke, your personal task manager!
What can I do for you?
_____________________
```

## Features

Here's a list of features available in Duke. Click on the links to jump to the relevant section.

- [Getting help: `help`](#getting-help-help)
- [Adding a todo task: `todo <name_of_todo>`](#adding-a-todo-task-todo)
- [Adding a deadline task: `deadline <name_of_deadline> /by <yyyy-mm-dd>`](#adding-a-deadline-task-deadline)
- [Adding an event task: `name_of_event> /at <location>`](#adding-an-event-task-event)
- [Listing all tasks: `list`](#listing-all-tasks-list)
- [Marking a task as done: `mark <existing_task_number>`](#marking-a-task-as-done-done)
- [Unmarking a task as done: `unmark <existing_task_number>`](#unmarking-a-task-as-done-unmark)
- [Deleting a task: `delete <existing_task_number>`](#deleting-a-task-delete)
- [Finding a task: `find <keyword>`](#finding-a-task-find)
- [Get total number of task: `total`](#getting-total-number-of-task)
- [Exiting the program: `bye`](#exiting-the-program-bye)

### Getting help: `help`

List all available commands and their usage.

Format: `help`

### Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `todo <name_of_todo>`

Example of usage:

`todo read book`

Expected outcome:

```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```

### Adding a deadline task: `deadline`

Adds a deadline task to the task list.

Format: `deadline <name_of_deadline> /by <yyyy-mm-dd>`

- The date must be in the format `yyyy-mm-dd`.

Example of usage:

`deadline return book /by 2020-09-15`

Expected outcome:

```
Got it. I've added this task:
[D][ ] return book (by: Sep 15 2020)
Now you have 2 tasks in the list.
```

### Adding an event task: `event`

Adds an event task to the task list.

Format: `event <name_of_event> /at <location>`

- The location can be any string.

Example of usage:

`event project meeting /at NUS`

Expected outcome:

```
Got it. I've added this task:
[E][ ] project meeting (at: NUS)
Now you have 3 tasks in the list.
```

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Sep 15 2020)
3. [E][ ] project meeting (at: NUS)
```

### Marking a task as done: `done`

Marks a task as done.

Format: `mark <existing_task_number>`

- The task number must be a positive integer 1, 2, 3, ...
- The task number refers to the index number shown in the displayed task list.
- The task number must be a valid task number in the task list.

Example of usage:

`mark 2`

Expected outcome:

```
Nice! I've marked this task as done:
[D][X] return book (by: Sep 15 2020)
```

### Unmarking a task as done: `unmark`

Unmarks a task as done.

Format: `unmark <existing_task_number>`

- The task number must be a positive integer 1, 2, 3, ...
- The task number refers to the index number shown in the displayed task list.
- The task number must be a valid task number in the task list.

Example of usage:

`unmark 2`

Expected outcome:

```
Nice! I've unmarked this task as done:
[D][ ] return book (by: Sep 15 2020)
```

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete <existing_task_number>`

- The task number must be a positive integer 1, 2, 3, ...
- The task number refers to the index number shown in the displayed task list.
- The task number must be a valid task number in the task list.

Example of usage:

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
[D][ ] return book (by: Sep 15 2020)
Now you have 2 tasks in the list.
```

### Finding a task: `find`

Finds tasks whose names contain any of the given keywords.

Format: `find <keyword>`

- The search is case-insensitive. e.g `book` will match `Book`
- The order of the keywords does not matter. e.g. `Book meeting` will match `meeting Book`
- Only the name is searched.
- Only full words will be matched e.g. `Book` will not match `Books`
- Tasks matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Book meeting` will return `project meeting` and `return book`

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Sep 15 2020)
```

### Getting total number of task

Gets the total number of tasks in the task list.

Format: `total`

Example of usage:

`total`

Expected outcome:

```
Now you have 2 tasks in the list.
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

## Command summary

| Action       | Format, Examples                                                                                |
| ------------ | ----------------------------------------------------------------------------------------------- |
| **Help**     | `help`                                                                                          |
| **Todo**     | `todo <name_of_todo>` <br> e.g., `todo read book`                                               |
| **Deadline** | `deadline <name_of_deadline> /by <yyyy-mm-dd>` <br> e.g., `deadline return book /by 2020-09-15` |
| **Event**    | `event <name_of_event> /at <location>` <br> e.g., `event project meeting /at NUS`               |
| **List**     | `list`                                                                                          |
| **Done**     | `done <existing_task_number>` <br> e.g., `done 2`                                               |
| **Unmark**   | `unmark <existing_task_number>` <br> e.g., `unmark 2`                                           |
| **Delete**   | `delete <existing_task_number>` <br> e.g., `delete 2`                                           |
| **Find**     | `find <keyword>` <br> e.g., `find book`                                                         |
| **Total**    | `total`                                                                                         |
| **Bye**      | `bye`                                                                                           |

## Saving data

Duke data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke home folder.
