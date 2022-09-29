# User Guide

This is a short user guide for CS2113 IP by joshuan98

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
```
______________________________
Hihi, my name is Jay!
What can I do for you today?
______________________________
```
## Features

### Feature - Greet

Upon startup, Jay greets the user with a friendly welcome message

### Feature - Goodbye

On exit, Jay sends the user off with a kind goodbye message

### Feature - Tasks

Jay will keep track of your various tasks and display them so that you will not forget them

### Feature - Mark

You can tell Jay to mark tasks when you are done or unmark tasks when you need to complete them in the future

### Feature - Save and Load

Jay is capable of saving and loading data form previous interactions!

## Usage

### `list` - Lists the tasks stored

Displays the tasks stored thus far.

Example of usage:
`list`

Expected outcome:

```
1. [T][X] read book
2. [D][ ] return book (by: Oct 15 2019)
3. [E][ ] project meeting (at: Aug 6th 2-4pm)
4. [T][X] join sports club
5. [T][ ] borrow book
```

### `todo` - Adds a new Todo task

Creates a new Todo task and stores it in the task list. Also displays the number of tasks currently stored in your list.

Example of usage:
`todo {description}`

Expected outcome:

```
added: [T][ ] borrow book
There are 5 task(s) in your list.
```

### `deadline` - Adds a new Deadline task

Creates a new Deadline task and stores it in the task list. Requires an input of a valid date.

Example of usage:
`deadline {description} /by {date}`

Expected outcome:

```
added: [D][ ] return book (by: Oct 15 2019)
There are 2 task(s) in your list.
```

### `event` - Adds a new Event task

Creates a new Event task and stores it in the task list. Requires an input of a valid time.

Example of usage:
`event {description} /by {time}`

Expected outcome:

```
added: [E][ ] project meeting (at: Aug 6th 2-4pm)
There are 3 task(s) in your list.
```

### `mark` - Marks a task

Mark the task number. Requires an input of a valid task number.

Example of usage:
`mark {task number}`

Expected outcome:

```
Completed! The following task is marked as done:
[T][X] read book
```

### `unmark` - Unmarks a task

Unmarks the task number. Requires an input of a valid task number.

Example of usage:
`unmark {task number}`

Expected outcome:

```
Oh no! The following task is marked as undone:
[T][ ] read book
```

### `delete` - Deletes a task

Deletes the task at given task number. Also displays the number of tasks remaining after deletion. Requires an input of a valid task number.

Example of usage:
`delete {task number}`

Expected outcome:

```
deleted:
[T][ ] this
There are 6 task(s) in your list.
```

### `find` - Find keyword

Search keyword within description of all tasks in task list. Displays a list of tasks with the given keyword in the description.

Example of usage:
`find {keyword}`

Expected outcome:

```
1. [T][X] read book
2. [D][ ] return book (by: Oct 15 2019)
3. [T][ ] borrow book
```
