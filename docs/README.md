# User Guide - Project Duke

## Features 
Duke is a task manager chat bot that supports the addition of todo task, event task
and deadline task. It has versatile tools such as mark, unmark and find tasks to 
make your life much easier in tracking task.

### Add and manage various types of tasks
The main feature of the Duke App is to add and delete tasks of 3 different type
which supports mark Complete / Incomplete feature. The tasks can be shown all
at once for easy viewing.

#### Todo Task

Todo task is the simplest and bare-bone task. It only stores the description
of the todo.

#### Deadline Task

Deadline task is similar to Todo Task, with an extra information storing the 
time element (by when should the task be completed).

#### Event Task

Event task is similar to Todo Task, with an extra information storing the time
element (the time the event is held).

### Automatic saving
The tasks list entered so far will be tracked automatically and saved to the local
storage. The local file is in the MarkDown format for easy reference and compatibility 
with github. When the user initialises Duke, it will automatically loads the tasks
from the local file and populate the list.

### Finding tasks by keyword
It is possible to search for tasks in the current database by inputting its keyword.

## Usage

### Program initialisation 
Upon startup you should see the welcome page and the possible commands.
```
____________________________________________________________
 ____        _
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
Hello! I'm Duke
Possible commands are:
`help` 								-> List possible commands.
`bye` 								-> To exit the program.
`list` 								-> List out all the current task in the list.
`todo <description>` 				-> Add todo task.
`event <description> /at <time>` 	-> Add event task.
`deadline <description> /by <time>` -> Add deadline task.
`mark <task number>` 				-> Mark task as completed.
`unmark <task number>` 				-> Mark task as not completed.
`delete <task nummber>` 			-> Delete the task.
`find <keyword>` 					-> Find the task(s) based on the keyword.
____________________________________________________________
What can I do for you?
```

### `help` - List valid commands

Shows the possible command and its proper format in Duke.
Example of usage:

`help`

Expected outcome:

Shows all the possible command in Duke.

```
What can I do for you?
help
[Entered: help ]

____________________________________________________________
Possible commands are:
`help` 								-> List possible commands.
`bye` 								-> To exit the program.
`list` 								-> List out all the current task in the list.
`todo <description>` 				-> Add todo task.
`event <description> /at <time>` 	-> Add event task.
`deadline <description> /by <time>` -> Add deadline task.
`mark <task number>` 				-> Mark task as completed.
`unmark <task number>` 				-> Mark task as not completed.
`delete <task nummber>` 			-> Delete the task.
`find <keyword>` 					-> Find the task(s) based on the keyword.
____________________________________________________________
```

### `bye` - Exit the Duke App

Saves the list to the local storage and exits the program gracefully.

Example of usage:

`bye`

Expected outcome:

Successful exiting of the program.

```
What can I do for you?
bye
[Entered: bye ]

____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### `todo` - Add todo task

Adds a todo task to the task list. It only takes in the task description without
any other information. Supports Marking and Unmarking. Task will be saved to the
local file `./data/data.md` relative to the working directory. 

Example of usage: 

`todo submit CS2113 iP`

Expected outcome:

Successful addition to the task list.

```
What can I do for you?
todo submit CS2113 iP
[Entered: todo submit CS2113 iP ]

____________________________________________________________
I got you, added a Todo:
	[T][ ] submit CS2113 iP

Now you have 1 tasks in the list.
____________________________________________________________
```

### `deadline` - Add deadline task

Adds a deadline task to the task list. It also takes in time information to
indicate by when the task should be completed. Supports Marking and Unmarking. 

User must include the time information by including the `/by` command after deadline
description. The argument after that command will be perceived as the time information.

Task will be saved to the local file `./data/data.md` relative to the working directory.

Example of usage:

`deadline submit CS2113 iP /by friday 2359`

Expected outcome:

Successful addition to the task list with the time information stored.

```
What can I do for you?
deadline submit CS2113 iP /by friday 2359
[Entered: deadline submit CS2113 iP /by friday 2359 ]

____________________________________________________________
I got you, added a Deadline:
	[D][ ] submit CS2113 iP (by: friday 2359)

Now you have 2 tasks in the list.
____________________________________________________________
```

### `event` - Add event task

Adds a event task to the task list. It also takes in time information to
indicate when the event is happening. Supports Marking and Unmarking.

User must include the time information by including the `/at` command after event
description. The argument after that command will be perceived as the time information.

Task will be saved to the local file `./data/data.md` relative to the working directory.

Example of usage:

`event celebrate CS2113 iP submission /at saturday 1100`

Expected outcome:

Successful addition to the task list with the time information stored.

```
What can I do for you?
event celebrate CS2113 iP submission  /at saturday 1100
[Entered: event celebrate CS2113 iP submission  /at saturday 1100 ]

____________________________________________________________
I got you, added a Event:
	[E][ ] celebrate CS2113 iP submission (at: saturday 1100)

Now you have 3 tasks in the list.
____________________________________________________________
```

### `list` - View current tasks in the list
View a formatted output of the current tasks in the list. It includes information
such as
- Type of task (todo, event, deadline)
- Completion status
- Description of the task
- Time element of the task (for deadline and event task)

Example of usage:

`list`

Expected outcome:

A list of all the tasks in the list.

```
What can I do for you?
list
[Entered: list ]

____________________________________________________________
1. 	[T][ ] submit CS2113 iP
2. 	[D][ ] submit CS2113 iP (by: friday 2359)
3. 	[E][ ] celebrate CS2113 iP submission (at: saturday 1100)

____________________________________________________________
```

### `find` - Find tasks by keyword
Search for the keyword within the task list. Enter the keyword argument after the 
command.

Example of usage:

`find celebrate`

Expected outcome:

List of all the tasks that have its description matching the keyword.

```
What can I do for you?
find celebrate
[Entered: find celebrate ]

____________________________________________________________
Here are the matching tasks in your list: 
1. 	[E][ ] celebrate CS2113 iP submission (at: saturday 1100)

____________________________________________________________
```

### `mark` - Mark task as completed
Mark the task (inquired by user) as done. Once marked, the `X` symbol on the `list`
command will appear. The user is required to enter the task number after the command
as seen in the `list` command. Changes will be saved to the local file `./data/data.md`
relative to the working directory. 

Example of usage:

`mark 1`

Expected outcome:

`X` symbol appearing in the completed section of the description.
```
What can I do for you?
mark 1
[Entered: mark 1 ]

____________________________________________________________
Nice! I've marked this task as done:
	[T][X] submit CS2113 iP

____________________________________________________________
```

### `unmark` - Mark task as not completed
Mark the task (inquired by user) as not done. Once marked, the `X` symbol on the `list`
command will disappear. The user is required to enter the task number after the command.
Task number can be found from the `list` command. Changes will be saved to the local file 
`./data/data.md` relative to the working directory.

Example of usage:

`unmark 1`

Expected outcome:

` ` (space) symbol in the completed section of the description.
```
What can I do for you?
unmark 1
[Entered: unmark 1 ]

____________________________________________________________
Nice! I've marked this task as not done:
	[T][ ] submit CS2113 iP

____________________________________________________________
```

### `delete` - Delete the task
Delete the task (inquired by user) from the task list and local file. The user is
required to enter the task number after the command. Task number can be found from
the `list` command.

Example of usage:

`delete 1`

Expected outcome:
Task is removed from the list.
```
What can I do for you?
delete 1
[Entered: delete 1 ]

____________________________________________________________
Noted. I've removed this task:
	[T][ ] submit CS2113 iP

 Now you have 2 tasks in the list.
____________________________________________________________
```
