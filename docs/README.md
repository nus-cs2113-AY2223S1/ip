# User Guide

## About
Duke is a command line interface app to help manage your tasks.

## Features
Duke currently supports three different types of tasks: Todo, Deadline and Event and the following actions are supported.  

### Add Task
User can add new tasks to their tasks list.

### Delete Task
User can remove tasks from their tasks list.

### Mark Task
User can mark done and undone to the tasks in their tasks list.

### Find Task
User can find the tasks with a specific keyword from their tasks list.

### List Task
User can view all the tasks that they have added.


## Usage

### Adding new tasks

#### `todo {description}` - Add a todo task
Adds a todo task to the user's tasks list 

Example of usage:
`todo homework`

Expected outcome:
The todo : "homework" is added as a todo task.

```
__________________________________________________ 

Got it. I have added this task:
[T][] homework
Now you have 4 tasks left
__________________________________________________ 
```

#### `deadline {description} /by {datetime}` - Add a deadline task
Adds a deadline task to the user's tasks list

Example of usage:
`deadline submit this assignment /by tonight`

Expected outcome:
The deadline : "submit this assignment " is added as a deadline task that needs to be completed by "tonight".

```
__________________________________________________ 

Got it. I have added this task:
[D][] submit this assignment (by: tonight)
Now you have 5 tasks left
__________________________________________________ 
```

#### `event {description} /at {datetime}` - Add an event task
Adds a deadline task to the user's tasks list

Example of usage:
`event musical /at Saturday 9 pm`

Expected outcome:
The event : "musical " is added as an event task that would take place on "Saturday 9 pm".

```
__________________________________________________ 

Got it. I have added this task:
[E][] musical (at: Saturday 9 pm)
Now you have 6 tasks left
__________________________________________________ 

```


### Marking Tasks

#### `mark {taskNumber} ` - Marks a task as done
Marks the task at the given index as done

Example of usage:
`mark 0`

Expected outcome:
The first task in the user's tasks list is marked as done.

```
__________________________________________________ 

Okiii... This task has been marked as done
homework
__________________________________________________ 
```

#### `unmark {taskNumber} ` - Marks a task as not done
Marks the task at the given index as not done

Example of usage:
`unmark 0`

Expected outcome:
The first task in the user's tasks list is marked as not done.

```
__________________________________________________ 

Okiii... This task has been marked as not done yet
homework
__________________________________________________ 
```

### Deleting Tasks

#### `delete {taskNumber} ` - Removes a task
Deletes the task at the given index from the user's tasks list

Example of usage:
`delete 0`

Expected outcome:
The first task in the user's tasks list is deleted.

```
__________________________________________________ 

Okiii... This task has been deleted: 
[T][] homework
Now you have 3 tasks left
__________________________________________________ 
```

### Finding Tasks

#### `find {keyword} ` - Finds relevant tasks
Lists out all the tasks that contain the given keyword.

Example of usage:
`find assignment`

Expected outcome:
All tasks containing the keyword "assignment" would be printed.

```
__________________________________________________ 

Here are the matching tasks in your list
2 [D][] submit this assignment (by: tonight)
4 [T][] assignment
__________________________________________________ 

```


### Listing Tasks

#### `list ` - Lists all the tasks
Lists out all the tasks that are in the user's tasks list.

Example of usage:
`list`

Expected outcome:
All tasks in the user's tasks list would be printed.

```
__________________________________________________ 

Here are the tasks in your list:
1 [T][] homework
2 [D][] submit this assignment (by: tonight)
3 [E][] musical (at: Saturday 9 pm)
4 [T][] assignment
__________________________________________________ 

```

### Exit

#### `bye ` - Terminates the program
Finishes program execution.

Example of usage:
`bye`

Expected outcome:
The program is terminated successfully.

```
__________________________________________________ 

Bye. Hope to see you again soon! 
```

