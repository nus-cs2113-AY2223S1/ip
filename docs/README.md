# User Guide

Duke is a **Command Line Interface** (CLI) based desktop application which 
will help the user plan their tasks. It allows users to add or delete three categories of tasks: todos, deadlines,
and events. They can mark these as done or not done and can also see a list of their tasks at
any particular point in time. Additionally, they can search for all tasks containing a particular keyword. 
The application will save the tasks and reload them every time the user restarts the application so it can store the list as a memory.
## Features 

### Feature - add a task
Adds a task to the current list of tasks. The task could either be a todo, a deadline, or an event. 

### Feature - delete a task

Deletes a task from the current list of tasks.

### Feature - list all tasks

Lists all tasks present in the current list.

### Feature - mark a task

Marks a particular task from the list as done

### Feature - unmark a task

Marks a particular task from the list as not done

### Feature - find tasks

Finds all tasks present in the current list that contain the word entered by the user

## Usage (An example workflow)

## `todo` - Adds a todo to the collection of tasks

#### *General format of command*
todo DESCRIPTION_OF_TASK
#### *Example of usage:* 

`todo read book`

#### *Expected outcome:*

'read book' will be added to the task list as a todo and currently be marked as not done

```
____________________________________________________________
Got it. I've added this task:
todo read book 
Now you have 1 tasks in the list.
____________________________________________________________
```


## `deadline` - Adds a deadline to the collection of tasks

#### *General format of command*
deadline DESCRIPTION_OF_TASK /by DEADLINE_OF_TASK
#### *Example of usage:*

`deadline return book /by Tuesday`

#### *Expected outcome:*

'return book' will be added to the task list as a dealine and currently be marked as not done. The deadline of the event will be stored as 'Tuesday'

```
____________________________________________________________
Got it. I've added this task:
deadline return book /by Tuesday 
Now you have 2 tasks in the list.
____________________________________________________________
```


## `event` - Adds an event to the collection of tasks

#### *General format of command*
event DESCRIPTION_OF_TASK /at TIME_OF_TASK
#### *Example of usage:*

`event go to party /at 6PM`

#### *Expected outcome:*

'go to party' will be added to the task list as an event and currently be marked as not done. The time of the event will be stored as '6PM'

```
____________________________________________________________
Got it. I've added this task:
event go to party /at 6PM 
Now you have 3 tasks in the list.
____________________________________________________________
```

## `delete` - Deletes a task from the collection of tasks

#### *General format of command*
delete POSITION_TO_DELETE
#### *Example of usage:*

`delete 2`

#### *Expected outcome:*

Currently, the second task in the list is the deadline to 'return book' so this task will be deleted.

```
____________________________________________________________
Noted. I've removed this task:
[D][ ] return book (by: Tuesday) 
Now you have 2 tasks in the list.
____________________________________________________________
```

## `list` - Lists all tasks in the collection 

#### *General format of command*
list
#### *Example of usage:*

`list`

#### *Expected outcome:*

Currently, the collection has 2 tasks: 'todo read book' and 'event go to party /at 6PM' so these two tasks will be listed.

```
____________________________________________________________
[T][ ] read book 
[E][ ] go to party (at: 6PM) 
____________________________________________________________
```

## `mark` - marks a task in the collection as done

#### *General format of command*
mark POSITION_TO_MARK
#### *Example of usage:*

`mark 2`

#### *Expected outcome:*

The second task is currently 'go to party /at 6PM'. This will be marked as done.

```
____________________________________________________________
Nice! I've marked this task as done:
[E][X] go to party (at: 6PM) 
____________________________________________________________
```

## `unmark` - marks a task in the collection as not done

#### *General format of command*
unmark POSITION_TO_UNMARK
#### *Example of usage:*

`unmark 2`

#### *Expected outcome:*

The second task is currently 'go to party /at 6PM'. This will be marked as not done.

```
____________________________________________________________
OK, I've marked this task as not done yet:
[E][ ] go to party (at: 6PM) 
____________________________________________________________
```


## `find` - finds all tasks in the collection which contain the word entered by user

#### *General format of command*
find WORD_TO_FIND
#### *Example of usage:*

`find book`

#### *Expected outcome:*

The first task is the only task which contains the word 'book' so that will be displayed.

```
____________________________________________________________
[T][ ] read book 
____________________________________________________________
```
