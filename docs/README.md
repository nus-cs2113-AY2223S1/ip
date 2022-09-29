# User Guide

Duke is a Personal Assistant Chatbot that helps a person to keep track of various tasks (Todos, Deadlines and Events). It operates via Command Line Interface (CLI) and is designed for the fast typists. 

## Features 

### Feature-Task Management

Tasks of types Todo, Deadline and Event can be added into duke with their description. For
deadlines and events, their time can also be entered. Tasks can be listed with their
details. Their completion status can be changed. They can be deleted from Duke. They can also be searched for based
on their descriptions.

### Feature-Storage

Any action on tasks in Duke is automatically saved to a .txt file. Upon starting the program, tasks are loaded from the save file.

## Summary

Command words are lower case. *Italicized text* in the Format Column indicate where the parameters should be entered. 
Description can contain spaces. Position is the 1-based index of the task in the task list.

| Command  | Format                            | Description                      |
|----------|-----------------------------------|----------------------------------|
| todo     | todo *description*                | add a todo                       |
| deadline | deadline *description* /by *time* | add a deadline                   |
| event    | event *description* /at *time*    | add an event                     |
| list     | list                              | list all tasks                   |
| mark     | mark *position*                   | mark a task as done              |
| unmark   | unmark *position*                 | mark a task as not done          |
| delete   | delete *position*                 | delete a task                    |
| find     | find *text*                       | find all task that contains text |
| bye      | bye                               | exits program                    |

## Usage

### `todo` - add a todo

Adds a todo task with its description into the task list.

Example of usage: 

`todo move an immovable rock`

Expected outcome:

The todo is added to the tasks list. Its task type, done status and the total number of tasks is displayed.

```
I have added this task!
[T][ ] move an immovable rock
Okay loser! You now have 1 in the list. Get to work!
```
### `deadline` - add a deadline

Adds a deadline task with its description and time into the task list.

Example of usage:

`deadline take a poop /by 5`

Expected outcome:

The deadline is added to the tasks list. Its task type, done status, time and the total number of tasks is displayed.

```
I have added this task!
[D][ ] take a poop (by: 5)
Okay loser! You now have 2 in the list. Get to work!
```
### `event` - add an event

Adds an event task with its description and time into the task list.

Example of usage:

`event the end of all things /at 6`

Expected outcome:

The event is added to the tasks list. Its task type, done status, time and the total number of tasks is displayed.

```
I have added this task!
[E][ ] the end of all things (at: 6)
Okay loser! You now have 3 in the list. Get to work!
```
### `list` - list all tasks

Lists all the tasks in the tasks list.

Example of usage:

`list`

Expected outcome:

A list of all the tasks in 1-based indexing and their details.

```
It just keeps piling up
1.[T][ ] move an immovable rock
2.[D][ ] take a poop (by: 5)
3.[E][ ] the end of all things (at: 6)
```
### `mark` - mark a task as done

Sets the status of a task to done at the position in the list specified (1-based indexing).

Example of usage:

`mark 2`

Expected outcome:

The task and its details along with its new status is displayed.

```
I've marked this task as done, now go do something else!:
[D][X] take a poop (by: 5)
```
### `unmark` - mark a task as not done

Sets the status of a task to not done at the position in the list specified (1-based indexing).

Example of usage:

`unmark 2`

Expected outcome:

The task and its details along with its new status is displayed.

```
I've marked this task as not done, get working!:
[D][ ] take a poop (by: 5)
```
### `delete` - delete a task

Deletes a task at the specified position (1-based indexing).

Example of usage:

`delete 1`

Expected outcome:

Description of the outcome.

```
I've deleted this task, lucky!:
[T][ ] move an immovable rock
You now have 2 tasks left. Yay!
```
### `find` - find a task

Finds tasks whose description contains the specified text.

Example of usage:

`find poop`

Expected outcome:

A list of all tasks that contains the text

```
Look what I found:
1.[D][ ] take a poop (by: 5)
```
### `bye` - exit program

Exits the program with a goodbye message

Example of usage:

`keyword (optional arguments)`

Expected outcome:



```
Bye. Hope to see you again soon! Groovy!
```