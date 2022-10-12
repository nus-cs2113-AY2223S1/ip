# User Guide

## Features 

### Feature-list

Show all tasks and corresponding details in the taskList

### Feature-add
Add task/deadline/event/todo into the taskList

### Feature-delete
Delete the task indicated by input number

### Feature-mark
Mark the task indicated by input number

### Feature-unmark
Unmark the task indicated by input number

### Feature-find
Find tasks whose description contains the specified keyword

### Feature-load
Load duke system from the specified path to start

### Feature-dump
Dump tasks in the taskList to the specified path before exiting

## Usage

### `deadline` - Add a deadline

Add deadline with description and due time.
Time should be specified using `/by`

Example of usage: 

`deadline submit assignment /by 23:59 p.m.`

Expected outcome:

Prompt added message, abbreviate deadline as 'D' and show its due time.

```
>>>Added: [D][ ]submit assignment (by: 23:59 p.m.)
Now we have 1 tasks in the list.
==================================================
```
### `event` - Add an event

Add event with description and happening time.
Time should be specified using `/at`

Example of usage:

`event have dinner with friends /at 6:00-7:00 p.m.`

Expected outcome:

Prompt added message, abbreviate event as 'E' and show its happening time.

```
>>>Added: [E][ ]have dinner with friends (at: 6:00-7:00 p.m.)
Now we have 2 tasks in the list.
==================================================
```
### `todo` - Add a todo

Add todo with description.

Example of usage:

`todo Watch a movie on weekend`

Expected outcome:

Prompt added message, abbreviate todo as 'O'.

```
>>>Added: [O][ ]Watch a movie on weekend
Now we have 3 tasks in the list.
==================================================
```

### `list` - Show the list of tasks
Show tasks currently in the list, and their details.

Example of usage:

`list`

Expected outcome:

Show every task with an index, label and detailed time (if has)

```
>>>Current Tasks:
>>>1.[D][ ]submit assignment (by: 23:59 p.m.)
>>>2.[E][ ]have dinner with friends (at: 6:00-7:00 p.m.)
>>>3.[O][ ]Watch a movie on weekend
>>>4.[T][ ]Look for a teammate
==================================================
```
### `delete` - Delete a task
Delete a task indicated by given number from the list.
> The number given should start from 1, 
> and not be greater than the count of current tasks

Example of usage:

`delete 3`

Expected outcome:

Show deleted task and current count of tasks in the list.

```
Noted. I've removed this task:
>>>[O][ ]Watch a movie on weekend
Now we have 3 tasks in the list.
==================================================
```
### `mark` - Mark a task
Mark a task indicated by given number in the list.

Example of usage:

`mark 3`

Expected outcome:

Show marked task with label '√'.

```
>>>Nice! I've marked this task as done:
[T][√]Look for a teammate
==================================================
```
### `unmark` - Unmark a task
UnMark a task indicated by given number in the list.

Example of usage:

`unmark 3`

Expected outcome:

Show unmarked task without label '√'.

```
>>>OK, I've marked this task as not done yet:
[T][ ]Look for a teammate
==================================================
```
### `find` - find tasks with keyword
Find tasks containing given keyword from the list.

Example of usage:

`find submit`

Expected outcome:

Show all tasks whose description contains the keyword with index and details.

```
Here are the matching tasks in your list:
1.[D][ ]submit assignment (by: 23:59 p.m.)
==================================================
```
### `bye` - Exit the system
Exit and close the system when user enter `bye`.

Example of usage:

`bye`

Expected outcome:
Goodbye message and exit code = 0.

```
Bye. Hope to see you again soon!
==================================================

Process finished with exit code 0
```
### `blah` - Deal with nonsense
Prompt unknown command and skip this input.

Example of usage:

`blah`

Expected outcome:

Prompt message.

```
OOPS!!! I'm sorry, but I don't know what that means :-(
==================================================
```
### `storage` - load&dump tasks in the list
Load tasks from given file after starting the system.
Dump tasks from the taskList before exiting the system.
> The storage operations are executed automatically,
> which means user does not need to enter any command for it.
> To let it happen, user must exit in an expected way, that is, using 'bye' cmd.

The file used for storage is formatted as:

| Abbreviation | isMarked |       Description        |     [Time]     |
|:------------:|:--------:|:------------------------:|:--------------:|
|      D       |    U     |    submit assignment     |   23:59 p.m.   |
|      E       |    U     | have dinner with friends | 6:00-7:00 p.m. |
|      T       |    M     |   Look for a teammate    |                |

`list`(after starting the system)

Expected outcome:

Load and show list of tasks, that has been dumped into the file beforehand.

```
 ____        _
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

==================================================
Hello! I'm Duke ^_^
What can I do for you?
==================================================
list 
>>>Current Tasks:
>>>1.[D][ ]submit assignment 23:59 (by: p.m.)
>>>2.[E][ ]have dinner with friends 6:00-7:00 (at: p.m.)
>>>3.[T][√]Look for a teammate 
==================================================
```