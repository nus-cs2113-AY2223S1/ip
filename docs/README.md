# User Guide
Duke is a desktop application for making todo list via Command Line interface
## Quick Start 
1. Ensure that you have Java 11 installed
2. Download the latest `ip.jar` here
3. Copy the file to the folder you want to use as the home folder for your application
4. In the command line enter `java -jar ip.jar` to start using the application

## Features 
### Add, (Un)mark, Delete, Find by keyword and date
- Add task based on the type: 
	- Todo
	- Deadline
	- Event
- Mark task as done or unmark it
- Find keyword in the task description or find by date
- Delete tasks
### Ignorecase
- Command is case non-sensitive: `DeAdLINe` is recognized as `deadline` 

### Multiple indices
- Can (un)mark or delete multiple task at the same time regardless of order `mark 1 4 2 3 `
## Command

### `help` - help
- Show help 
```
>>> help
Supported commands:
To create tasks: todo, deadline, event
To edit tasks: mark, unmark, delete
To view tasks: list
To exit: exit
===============================================================================
```

### `exit` - exit 
- Exit the program
```
>>> exit
Bye!
===============================================================================
Program ends
```

### `todo` - todo task
- Add todo task
```
>>> todo <description>


>>> todo Task 1 
Todo added
	1. [T][ ] Task 1

===============================================================================

```

### `deadline` - deadline task
- Add deadline task
```
>>> deadline <description> / <date (can be in yyyy-mm-dd)>

>>> deadline Deadline 1 / now
Deadline added
	1. [D][ ] Deadline 1	(now)

===============================================================================

>>> deadline Deadline 2 / 2000-02-01
Deadline added
	1. [D][ ] Deadline 2	(01 Feb 2000)

===============================================================================
```
### `event` - event task
- Add event task
```
>>> event <description> / <date (can be in yyyy-mm-dd)>>

>>> event Event 1 / now
Event added
	1. [E][ ] Event 1	(now)

===============================================================================

>>> event Deadline 2 / 2000-02-01
Event added
	1. [E][ ] Event 2	(01 Feb 2000)

===============================================================================
```


### `list` - list
- List existing tasks in the task list
```
>>> list
=====================================LIST======================================
	1. [T][ ] Task 1
	2. [D][ ] Deadline 1	(now)
	3. [D][ ] Deadline 2	(01 Feb 2000)
	4. [E][ ] Event 1	(now)
	5. [E][ ] Event 2	(01 Feb 2000)

===============================================================================

```
### `mark` - mark
- Mark task as done
```
>>> mark <index 1> <index 2> <...>


>>> mark 1 2 4 5
Mark 4 tasks
	1. [T][X] Task 1
	2. [D][X] Deadline 1	(now)
	3. [E][X] Event 1	(now)
	4. [E][X] Event 2	(01 Feb 2000)

===============================================================================

>>> list
=====================================LIST======================================
	1. [T][X] Task 1
	2. [D][X] Deadline 1	(now)
	3. [D][ ] Deadline 2	(01 Feb 2000)
	4. [E][X] Event 1	(now)
	5. [E][X] Event 2	(01 Feb 2000)

===============================================================================
```

### `unmark` - unmark 
- Mark task as undone (unmark done)
```
>>> unmark <index 1> <index 2> <...>

>>> unmark 5 1
Unmark 2 tasks
	1. [E][ ] Event 2	(01 Feb 2000)
	2. [T][ ] Task 1

===============================================================================

>>> list
=====================================LIST======================================
	1. [T][ ] Task 1
	2. [D][X] Deadline 1	(now)
	3. [D][ ] Deadline 2	(01 Feb 2000)
	4. [E][X] Event 1	(now)
	5. [E][ ] Event 2	(01 Feb 2000)

===============================================================================
```


### `Find` - Find task
- Find task by keyword
```
>>> find <keyword>

>>> find Eve
Found 2 tasks with "Eve"
	1. [E][X] Event 1	(now)
	2. [E][ ] Event 2	(01 Feb 2000)

===============================================================================
```

### `Date` - Find task by date
- Find task by date
```
>>> date <yyyy-mm-dd>


>>> date 2000-02-01
Found 2 task on 2000-02-01
	1. [D][ ] Deadline 2	(01 Feb 2000)
	2. [E][ ] Event 2	(01 Feb 2000)

===============================================================================
```
### `delete` - delete task
- Delete task
```
>>> delete <index 1> <index 2> <...>

>>> delete 5 1 3
Delete 3 tasks
	1. [T][ ] Task 1
	2. [D][ ] Deadline 2	(01 Feb 2000)
	3. [E][ ] Event 2	(01 Feb 2000)

===============================================================================

>>> list
=====================================LIST======================================
	1. [D][ ] Deadline 1	(now)
	2. [E][ ] Event 1	(now)

===============================================================================
```
