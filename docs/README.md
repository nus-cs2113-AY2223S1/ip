# User Guide

## Table of Contents
1. [Add Todo task: `todo`](#add-todo-task-todo)
2. [Add Event task: `event`](#add-event-task-event)
3. [Add Deadline task: `deadline`](#add-deadline-task-deadline)
4. [Mark task as done: `mark`](#mark-task-as-done)
5. [Mark task as not done: `unmark`](#mark-task-as-not-done-unmark)
6. [List all tasks: `List`](#List-all-tasks-list)
7. [Find task: `find`](#find-task-find) 
8. [Remove task: `delete`](#delete-task-delete)
9. [Exit program: `bye`](#exit-program-bye)
  
## Features
> inputs by the user are shown in upper case
> e.g `todo TASKNAME`,  `TASKNAME` is an input which the user decides

### Add Todo task: `todo`
Adds a todo task to your list  
Format: `todo TASKNAME`

Sample output: 
```
---------------------------------------------------
You: todo TASKNAME1
---------------------------------------------------
Added:
1) [T][ ] TASKNAME1
Now you have 1 tasks in your list!
---------------------------------------------------
```

### Add Event task: `event`
Adds an event task to your list  
Format: `event TASKNAME at TIME`

Sample output:
```
---------------------------------------------------
You: event TASKNAME2 at TIME
---------------------------------------------------
Added:
2) [E][ ] TASKNAME2 (at: TIME)
Now you have 2 tasks in your list!
---------------------------------------------------
```

### Add Deadline task: `deadline`
Adds an event task to your list  
Format: `deadline TASKNAME by TIME`

Sample output:
```
---------------------------------------------------
You: deadline TASKNAME 3 by TIME
---------------------------------------------------
Added:
3) [D][ ] TASKNAME3 (by: TIME)
Now you have 3 tasks in your list!
---------------------------------------------------
```


### Mark task as done: `mark`
Marks an existing task in the task list as done  
Format: `mark TASKINDEX` or `mark TASKNAME`

Sample output:
```
---------------------------------------------------
You: mark TASKNAME1
---------------------------------------------------
Marked as done:
1) [T][X] TASKNAME1
---------------------------------------------------
```

```
---------------------------------------------------
You: mark 2
---------------------------------------------------
Marked as done:
2) [E][X] TASKNAME2 (at: TIME)
---------------------------------------------------
```

### Mark task as not done: `unmark`
Marks an existing task in the task list as done  
Format: `unmark TASKINDEX` or `unmark TASKNAME`

Sample output:

```
---------------------------------------------------
You: unmark TASKNAME1
---------------------------------------------------
Marked as not done:
1) [T][ ] TASKNAME1
---------------------------------------------------
```

```
---------------------------------------------------
You: unmark 2
---------------------------------------------------
Marked as not done:
2) [E][ ] TASKNAME2 (at: TIME)
---------------------------------------------------
```


### List all tasks: `list`
Lists out all existing tasks in the task list  
Format: `list`  

Sample output:

```
---------------------------------------------------
You: list
---------------------------------------------------
Tasks:
1) [T][ ] TASKNAME1
2) [E][ ] TASKNAME2 (at: TIME)
3) [D][ ] TASKNAME3 (by: TIME)
---------------------------------------------------
```

The first square ([ ]) denotes what type of task it is:
- T: todo 
- E: event 
- D: deadline 

An 'X' within the second square indicates that the task is completed

### Find task: `find`
Searches and prints for all tasks with the given contained within its NAME or TIME  
Format: `find KEYWORD`  

Sample Output:

```
---------------------------------------------------
You: find 2
---------------------------------------------------
Here are the tasks matching your search:
2) [E][ ] TASKNAME2 (at: TIME)
---------------------------------------------------
```

### Remove task: `delete`
Deletes a task in the list  
Format: `delete TASKINDEX` or `delete TASKNAME`

Sample Output:

```
---------------------------------------------------
You: delete 1
---------------------------------------------------
Removed:
1) [T][ ] TASKNAME1
Now you have 2 tasks in your list!
---------------------------------------------------
```

```
---------------------------------------------------
You: delete TASKNAME2
---------------------------------------------------
Removed:
1) [E][ ] TASKNAME2 (at: TIME)
Now you have 1 tasks in your list!
---------------------------------------------------
```

### Exit program: `bye`
Exits program  
Format: `bye`  

Sample output:
```
---------------------------------------------------
You: bye
---------------------------------------------------
Duke: Goodbye!
---------------------------------------------------
```