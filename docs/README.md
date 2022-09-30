# User Guide

Duke is a task tracker for all the user’s tasks,
the application allows the user to see and update all of his tasks divided into three categories - events, to-dos, and deadlines.
For each task, there is adescriptionn and a mark if the task was done or not.
Also, for events and deadlines there is a date description.
The user can update the task lists with different runs, the task listsavese the list each time the userexistst the application.


## Features 

* help - all the features available
* mark/unmark - mark a task as done or not
* delete - delete a task from the list
* add a task - todo/deadline/event 
* find all the tasks with certain words
* list all the tasks currently in the duke application

## Usage

input one of the features keywords and additional needed information regarding the keyword.

### `Keyword`
* 'help'
Example of usage: 
help
* 'list'
Example of usage: 
list
* 'mark' {index of task number to mark as done}
Example of usage: 
mark 1
* 'unmark' {index of task number to mark as done}
Example of usage: 
unmark 2
* 'delete' {index of task number to delete}
Example of usage: 
delete 1
* 'todo' {task description}
Example of usage: 
todo plan the weekend
* 'event' {task description} '/at' {date_desciption}
Example of usage: 
event F1 /at Singapore
* 'deadline' {task description} '/at' {date_desciption}
deadline CS assignment /by tomorrow
* 'find' {words to search for in the tasks}
find CS

Expected outcome:

a text file with all the current tasks in the list
