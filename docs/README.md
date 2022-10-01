# User Guide

## Introduction
Duke is an assistant chat bot which helps you handle a list of tasks.

## Launching Duke
Navigate to the directory containing 'Duke.jar' in a terminal, then entering the following command:
`java -jar Duke.jar`

## Tasks 

### Types of tasks:
1. Todo (task that needs to be done, does not have a specific time period allocated to it)
2. Event (task that needs to be done within a specific time period)
3. Deadline (task that needs to be done by a specified time period)


## Commands

#### `todo` - Adds a Todo task to the task list
Format:
`todo <description>`

`<description>` the description of the task to be inserted

Example of usage: 

`todo read book`

Expected outcome:


```
Got it. I've added this task:
[T][ ] read book
Now you have <value> tasks in the list.
```
`<value>` the number of tasks currently in the list
___
#### `deadline` - Adds a Deadline task to the task list
Format:
`deadline <description> /by <time period>`

`<description>` the description of the task to be inserted

`<time period>` the time period which the task has to be completed by 

Example of usage:

`deadline homework /by Friday 7pm`

Expected outcome:
```
Got it. I've added this task:
[D][ ] homework (by: Friday 7pm)
Now you have <value> tasks in the list.
```
`<value>` the number of tasks currently in the list

___
#### `event` - Adds an Event task to the task list

Format: `event <description> /at <time period>`

`<description>` the description of the task to be inserted

`<time period>` the time period which the task is to be done within.

Example of usage:

`event CS2113 lecture /at Friday 4-6pm`

Expected outcome:
```
Got it. I've added this task:
[E][ ] CS2113 lecture (at: Friday 4-6pm)
Now you have <value> tasks in the list.
```

`<value>` the number of tasks currently in the list

___
#### `list` - Lists the tasks which are in the task list

Format:`list`

Expected outcome:
```
Here are the tasks in your list:

1.[D][ ] homework (by: Friday 7pm)
2.[E][ ] CS2113 lecture (at: Friday 4-6pm)
```
___
#### `mark` - Marks the specified task as done

Format: `mark <number>`

`<number>` the position of the task in the list to be marked as done

Example of usage:

`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:

[D][X] homework (by: Friday 7pm)
```
___
#### `unmark` - Un-marks the specified task to show that it is not done

Format: `mark <number>`

`<number>` the position of the task in the list to be marked as not done

Example of usage:

`unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:

[D][ ] homework (by: Friday 7pm)
```
___
#### `delete` - Deletes the task from the task list

Format: `delete <number>`

`<number>` the position of the task in the list to be deleted

Example of usage:

`delete 1`

Expected outcome:
```
Noted. I've removed this task:

[D][ ] homework (by: Friday 7pm)
Now you have <value> tasks in the list.
```

`<value>` the number of tasks left in the list
___
#### `find` - Lists the tasks which contain the keyword given

Format: `find <keyword>`

`<keyword>` the keyword to filter out specific tasks from the task list

Example of usage:

`find lecture`

Expected outcome:
```
Here are the matching tasks in your list:

1.[E][ ] CS2113 lecture (at: Friday 4-6pm)
```
___
#### `exit` - Exits the program
Format: `exit`

Expected outcome:
```
Exiting the program now.
Bye. Hope to see you again soon!
```