# User Guide
Duke is a command line interface (CLI) ***task management*** program that manages 3 types of tasks 
which are *Todo, Deadline* and *Event*.

## Features 
Duke supports the management of three different type of tasks:
1. **Todo task**
   * A **basic** task.
2. **Deadline**
   * A task that has to be completed **by** a given time. 
3. **Event**
   * A task that occurs **at** a given time.
   
### Feature - Task Management

Duke is able to **add** and **delete** tasks. 
It also supports the tracking of task completion status via **mark** and **unmark** commands.

With a command, you can view tasks in 2 ways:
1. The whole **list** of tasks could be printed for you.
2. A list of tasks that contain a string of text that you are interested in could be **found**
   and printed.

### Feature - Storage

After every modification (**add, mark, unmark, delete**), Duke automatically saves your list of tasks
in the hard disk. 

Tasks are stored in this format: `type |  Completion status | description | time`.

For example, `D | 0 | assignment | tomorrow night`.

The type of task is represented by T, D and E for Todo, Deadline and Event tasks respectively.

The completion status of the task is represented by 1 for done and 0 for undone.

## Usage

### Setting up

To run Duke, navigate to the directory where Duke.jar is stored in the command line interface and enter:

`java -jar Duke.jar`

If the setup is correct, you should see something like the below as the output:

```
______________________________________________________________________
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
______________________________________________________________________
Hello! I'm Duke
What can I do for you?
______________________________________________________________________
```

### `todo` - Adds a todo task

Adds a Todo task with a **description** to the list of tasks.

Expected format:

`todo (description)`

Example of usage: 

`todo read CS2113T textbook`

Expected outcome:

A Todo task is added with the provided description.

```
______________________________________________________________________
Got it. I've added this task:
  [T][ ] read CS2113T textbook
Now you have 1 tasks in the list.
______________________________________________________________________
```


### `deadline` - Adds a Deadline task

Adds a Deadline task with a **description** and due **time** to the list of tasks.

A `/by` is required after the description, before the due time.

Expected format:

`deadline (description) /by (due time)`

Example of usage:

`deadline do quiz /by Sunday`

Expected output:


A Deadline task is added with the provided description and time.

```
______________________________________________________________________
Got it. I've added this task:
  [D][ ] do quiz (by: Sunday)
Now you have 2 tasks in the list.
______________________________________________________________________
```


### `event` - Adds a Event task

Adds a Deadline task with a **description** and **time** the event occurs to the list of tasks.

A `/at` is required after the description, before the time the event occurs.

Expected format:

`event (description) /at (time the event occurs)`

Example of usage:

`event CS2113T lecture /at Friday 4-6pm`

Expected output:

A Event task is added with the provided description and time.

```
______________________________________________________________________
Got it. I've added this task:
  [E][ ] CS2113T lecture (at: Friday 4-6pm)
Now you have 3 tasks in the list.
______________________________________________________________________
```


### `list` - Lists all tasks

Prints a formatted list of **all** tasks for you.

Tasks will be printed in the format:

```[index]. [type][completion status] description (by:/at: time)```

Index is the index of that task in the list. Completion status will be shown by `[X]` if done and `[ ]` for 
undone.  

Expected format:

`list`

Example of usage:

`list`

Expected output:

A formatted list of all tasks.

```
______________________________________________________________________
Here are the tasks in your list:
1.[T][ ] read CS2113T textbook
2.[D][ ] do quiz (by: Sunday)
3.[E][ ] CS2113T lecture (at: Friday 4-6pm)
______________________________________________________________________
```


### `mark` - Mark task as done

Marks a task as complete. 

Marking a completed task does nothing to its completion status.

A completed task will display `[X]` for its completion status.

A number is expected after `mark`, this integer can be found in the output of the
`list` command. This integer starts from 1.

Expected format:

`mark (integer)`

Example of usage:

`mark 1`

Expected output:

The task connected to the number provided will be marked as completed.

```
______________________________________________________________________
Nice! I've marked this task as done:
  [T][X] read CS2113T textbook
______________________________________________________________________
```


### `unmark` - Mark task as undone

Marks task as incomplete. 

Unmark an incomplete task does nothing to its completion status.

An incomplete task will display `[ ]` for its completion status.

A number is expected after `unmark`, this integer can be found in the output of the
`list` command. This integer starts from 1.

Expected format:

`unmark (integer)`

Example of usage:

`unmark 1`

Expected output:

The task connected to the number provided will be marked as incomplete.

```
______________________________________________________________________
OK, I've marked this task as not done yet:
  [T][ ] read CS2113T textbook
______________________________________________________________________
```

### `delete` - Deletes task

Deletes task from the list of tasks.

A number is expected after `delete`, this integer can be found in the output of the
`list` command. This integer starts from 1.

Expected format:

`delete (integer)`

Example of usage:

`delete 1`

Expected output: 

The task connected to the number provided will be deleted.

```
______________________________________________________________________
Noted. I've removed this task:
  [T][ ] read CS2113T textbook
Now you have 2 tasks in the list.
______________________________________________________________________
```

### `find` - Finds tasks

Duke will search for any task that contains the substring provided by you.

The list of tasks containing this substring will be printed for you.

An argument is required after `find`. This argument should be the substring you want to search with.

Expected format:

`find (keyword)`

Example of usage:

`find lecture`

Expected output:

The list of tasks containing the substring you provided.

```
______________________________________________________________________
Here are the matching tasks in your list:
2.[E][ ] CS2113T lecture (at: Friday 4-6pm)
______________________________________________________________________
```

### `bye` - Exit the program

Exit the program.

Expected format:

`bye`

Example of usage:

`bye`

Expected output:

Duke prints a goodbye message, and you exit the program.

```
______________________________________________________________________
Bye. Hope to see you again soon!
______________________________________________________________________

```

## Command summary

| Command  | Format                        | Summary                           | 
|----------|-------------------------------|-----------------------------------|
 | todo     | todo DESCRIPTION              | Adds a todo task                  |
 | deadline | deadline DESCRIPTION /by TIME | Adds a deadline task              |
 | event    | event DESCRIPTION /at TIME    | Adds an event task                |
 | list     | list                          | List all tasks                    |
 | mark     | mark INDEX                    | Marks a task as done              |
 | unmark   | unmark INDEX                  | Marks a task as undone            |
| delete   | delete INDEX                  | Deletes a task                    |
| find     | find KEYWORD                  | Finds tasks that contains keyword |
 | bye      | bye                           | Exits the program                 |

