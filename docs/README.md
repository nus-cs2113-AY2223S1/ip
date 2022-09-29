# Project Duke
Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.

## Launching Duke
Navigate to the directory containing 'Duke.jar' in a terminal, then enter: `java -jar Duke.jar`

## Tasks
There are 3 tasks typs in Duke.
1. ToDos: tasks without any date/time attached to it e.g., visit new theme park
2. Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
3. Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm



## Features
### Create Todo `todo`
Creates a todo task.
Format: `todo TASK_DESCRIPTION`
* `TASK_DESCRIPTION` refers to the description of the task. The description must be a String.


### Create Deadline `deadline`
Creates a deadline task.
Format: `deadline TASK_DESCRIPTION /by DUE_DATE`
* `TASK_DESCRIPTION` refers to the description of the task. The description must be a String.
* 'DUE_DATE' refers to the due date of the task. The due date must be a String.
* Ensure that `/by` is in the command.



### Create Event `event`
Creates an event task.
Format: `event TASK_DESCRIPTION /at TIME`
* `TASK_DESCRIPTION` refers to the description of the task. The description must be a String.
* 'TIME' refers to the time of the task. The time must be a String.
* Ensure that `/at` is in the command.



### Mark a Task as done `mark`
Marks a task as done.
Format: `mark TASK_INDEX`
* `TASK_INDEX` refers to the number of the task in th etask list. The index must be an Integer.
* The task to be marked has to be uncompleted.



### Mark a Task as undone `mark`
Marks a task as undone.
Format: `mark TASK_INDEX`
* `TASK_INDEX` refers to the number of the task in the task list. The index must be an Integer.
* The task to be marked has to be completed.



### Delete a task `delete`
Deletes a task from the task list.
Format: `delete TASK_INDEX`
* `TASK_INDEX` refers to the number of the task in the task list. The index must be an Integer.



### Clear `clear`
Clear the task list.
Format: `clear`



### Find tasks using a keyword `find`
Finds all tasks with descriptions that contain a given keywork
Format: `find KEYWORD`
* `KEYWORD` refers to the word that the user wishes to find. The keyword must be a String.


### List all the tasks in the task list `list`
Prints all the task in the task list.
Format: `list`

### Goodbye `bye`
Exits the program.
Format: `bye`
# Project Duke
Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.

## Launching Duke
Navigate to the directory containing 'Duke.jar' in a terminal, then enter: `java -jar Duke.jar`

## Tasks
There are 3 tasks typs in Duke.
1. ToDos: tasks without any date/time attached to it e.g., visit new theme park
2. Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
3. Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm



## Features
### Create Todo `todo`
Creates a todo task.
Format: `todo TASK_DESCRIPTION`
* `TASK_DESCRIPTION` refers to the description of the task. The description must be a String.


### Create Deadline `deadline`
Creates a deadline task.
Format: `deadline TASK_DESCRIPTION /by DUE_DATE`
* `TASK_DESCRIPTION` refers to the description of the task. The description must be a String.
* 'DUE_DATE' refers to the due date of the task. The due date must be a String.
* Ensure that `/by` is in the command.



### Create Event `event`
Creates an event task.
Format: `event TASK_DESCRIPTION /at TIME`
* `TASK_DESCRIPTION` refers to the description of the task. The description must be a String.
* 'TIME' refers to the time of the task. The time must be a String.
* Ensure that `/at` is in the command.



### Mark a Task as done `mark`
Marks a task as done.
Format: `mark TASK_INDEX`
* `TASK_INDEX` refers to the number of the task in th etask list. The index must be an Integer.
* The task to be marked has to be uncompleted.



### Mark a Task as undone `mark`
Marks a task as undone.
Format: `mark TASK_INDEX`
* `TASK_INDEX` refers to the number of the task in the task list. The index must be an Integer.
* The task to be marked has to be completed.



### Delete a task `delete`
Deletes a task from the task list.
Format: `delete TASK_INDEX`
* `TASK_INDEX` refers to the number of the task in the task list. The index must be an Integer.



### Clear `clear`
Clear the task list.
Format: `clear`



### Find tasks using a keyword `find`
Finds all tasks with descriptions that contain a given keywork
Format: `find KEYWORD`
* `KEYWORD` refers to the word that the user wishes to find. The keyword must be a String.


### List all the tasks in the task list `list`
Prints all the task in the task list.
Format: `list`

### Goodbye `bye`
Exits the program.
Format: `bye`
