# User Guide for Doraemon

Duke (Doraemon) is a personal robot cat assistant that helps a person such as Nobita
to keep track of his todos, deadlines and events. It provides an interactive way
for the tasks to be inputted via a command-line interface.

## Quick Start

1. Ensure you have Java 11 and above installed in your computer.
2. Download the latest `ip.jar` from [here](https://github.com/chydarren/ip/releases).
3. Copy the file to a folder that will be the home folder for Duke (Doraemon).
4. Launch a terminal and navigate to where the `ip.jar` file is located, e.g. `cd Desktop/Duke/ip.jar`.
5. Run `java -jar ip.jar`.
6. Have fun interacting with Doraemon! 

## Features

### Organise and keep track different types of tasks

Duke (Doraemon) enables you to keep track of different types of tasks including Todo, Deadlines and Events.

### Find specific tasks

With lots of tasks at hand, Duke (Doraemon) allows you to filter and find your tasks quickly.

### Mark tasks as done

You can mark tasks once you have finished them, so that you will have one less thing to do.

### Persistent data

The data in the Duke (Doraemon) are exportable and automatically saved into a text file.
Your data will be safely kept and not get lost.

### Own a Doraemon

Yes, with Duke (Doraemon), you can be free from your worries because he will always be there to assist you.

## Commands

### `todo` - Create a new Todo task

Adds a task of type Todo.

Format: `todo <title>`

Examples:

- `todo buy a Dorayaki for Doraemon`

Expected outcome:

The Todo task that has been added and updated number of tasks will be shown.

```
todo buy Dorayaki for Doraemon
    ____________________________________________________________
    Nobita, wake up. Here's your new task:
    [T][ ] buy Dorayaki for Doraemon
    Now you have 1 tasks in Doraemon's 4D pocket.
    ____________________________________________________________
```

### `deadline` - Create a new Deadline task

Adds a task of type Deadline which is due by a certain date.

Formats (either one):

- `deadline <title> /by <dd/mm/yyyy>`
- `deadline <title> /by <hhmm>`
- `deadline <title> /by <dd/mm/yyyy hhmm>`

Examples:

- `deadline Nobita marry Shizuka /by 3/09/2022 1500`
- `deadline Nobita buys Dorayakis /by 25/12/2022`

Expected outcome:

The Deadline task that has been added and updated number of tasks will be shown.

```
deadline Nobita marry Shizuka /by 3/09/2022 1500
    ____________________________________________________________
    Nobita, wake up. Here's your new task:
    [D][ ] Nobita marry Shizuka (by: 3/09/2022 1500)
    Now you have 2 tasks in Doraemon's 4D pocket.
    ____________________________________________________________
```

### `event` - Create a new Event task

Adds a task of type Event which takes place a certain time.

Format: `event <title> /at <eventTime>`

Examples:

- `event Singing concert by Gian /at 20/12/2030 1200 - 20/12/2030 1400`
- `event Baseball party at courtyard /at 4 February 2022`

Expected outcome:

The Event task that has been added and updated number of tasks will be shown.

```
event Singing concert by Gian /at 20/12/2030 1200 - 20/12/2030 1400
    ____________________________________________________________
    Nobita, wake up. Here's your new task:
    [E][ ] Singing concert by Gian (at: 20/12/2030 1200 - 20/12/2030 1400)
    Now you have 3 tasks in Doraemon's 4D pocket.
    ____________________________________________________________
```

### `list` - View the task list

Shows all the tasks that are in Doraemon's 4D pocket.

Format: `list`

The list of all tasks will be shown.

Expected outcome:

```
list
    ____________________________________________________________
    Here are the tasks stored in Doraemon's 4D pocket:
    [T][ ] buy Dorayaki for Doraemon
    [D][ ] Nobita marry Shizuka (by: 3/09/2022 1500)
    [E][ ] Singing concert by Gian (at: 20/12/2030 1200 - 20/12/2030 1400)
    ____________________________________________________________
```

### `find` - Find specific task(s) from the task list

Shows all the tasks that match your search expression in Doraemon's 4D pocket.

Format: `find <keywords>`

Examples:

`find Shizuka`

Expected outcome:

The list of all tasks relevant to the search expression will be shown.

```
find Shizuka
    ____________________________________________________________
    Here are the tasks that match your search expression in Doraemon's 4D pocket:
    [D][ ] Nobita marry Shizuka (by: 3/09/2022 1500)
    [T][ ] Shizuka to wear a pink blouse
    ____________________________________________________________
```

### `mark` - Mark a task as done

Marks a specific task from the task list as done.
Take note that the task index must be a valid integer shown on the task list.

Format: `mark <taskIndex>`

Examples:
- `mark 1`

Expected outcome:

The task that has been marked as done will be shown.

```
mark 1
    ____________________________________________________________
    Subarashi! Good job in completing your task, Nobita:
    [T][X] buy Dorayaki for Doraemon
    ____________________________________________________________
```

### `unmark` - Mark a task as not done

Marks a specific task from the task list as not done.
Take note that the task index must be a valid integer shown on the task list.

Format: `unmark <taskIndex>`

Examples:
- `unmark 1`

Expected outcome:

The task that has been unmarked as done will be shown.

```
unmark 1
    ____________________________________________________________
    Gambate Nobita, complete it soon! Don't procrastinate:
    [T][ ] buy Dorayaki for Doraemon
    ____________________________________________________________
```

### `delete` - Delete a task

Deletes a specific task from the task list.
Take note that the task index must be a valid integer shown on the task list.

Format: `delete <taskIndex>`

Examples:
- `delete 1`

Expected outcome:

The task that has been deleted and updated number of tasks will be shown.

```
delete 1
    ____________________________________________________________
    Nobita, sugoi! One task has been deleted from Doraemon's 4D pocket.
    [T][ ] buy Dorayaki for Doraemon
    Now you have 3 tasks in Doraemon's 4D pocket.
    ____________________________________________________________
```

### `bye` - Exit from Doraemon 

Wave goodbye to Doraemon and quit the application.

Format: `bye`

```
bye
    ____________________________________________________________
    Sayonara. Hope to see you again soon!
    ____________________________________________________________
```
