# User Guide
Duke is a task tracker, which can keep track of 3 kinds of tasks from the user.
1. Todos-Tasks without any time components
2. Events-Tasks with a specific time at which they are done
3. Deadlines-Tasks with a specific time which acts as a deadline

Duke also provides a marking feature, 
to highlight important tasks, and can save tasks to a file in 
real time.
Duke also has a find feature, to find tasks containing 
a certain search string in their descriptions
## Features 
1. event-Adds an event task with description and time
2. deadline-Adds a deadline task with description and time
3. todo-Adds a todo event with a description
4. mark-Marks a task
5. unmark-Removes mark from a task
6. list-Lists all tasks currently present
7. delete-Removes a task
8. find-Finds a task containing a given search string in its description
9. Duke also saves all tasks to a text file in real time. Duke remembers previous tasks, even on reloading
## Usage

### `Keyword` - todo 
Adds a todo type task to our list

Example of usage: 

`todo Study for CS2113`

Expected outcome:
Adds a todo task to the list. Also displays the current number of tasks present
```
------------------------------------------------------------------------------
Got it. I've added this task:
[T][ ] Study for CS2113
Now you have 1 task in the list.
------------------------------------------------------------------------------
```
### `Keyword` - event
Adds an event type task to our list

Example of usage:

`event Study for CS2113 /at 6pm today`

Expected outcome:
Adds an event task to the list, with the event time being the string after /at. Also displays the current number of tasks present
```
------------------------------------------------------------------------------
Got it. I've added this task:
[E][ ] Study for CS2113 (at: 6pm today)
Now you have 2 tasks in the list.
------------------------------------------------------------------------------
```
### `Keyword` - deadline
Adds a deadline type task to our list

Example of usage:

`deadline Study for CS3230 /by 7pm today`

Expected outcome:
Adds an event task to the list, with the event time being the string after /at. Also displays the current number of tasks present
```------------------------------------------------------------------------------
Got it. I've added this task:
[D][ ] Study for CS3230 (by: 7pm today)
Now you have 3 tasks in the list.
------------------------------------------------------------------------------
```
### `Keyword` - mark
Marks a given task

Example of usage:

`mark 2`

Expected outcome:
Marks the second task in the list
```
------------------------------------------------------------------------------
Nice! I've marked this task as done
[E][X] Study for CS2113 (at: 6pm today)
------------------------------------------------------------------------------
```
### `Keyword` - unmark
Removes mark from a given task

Example of usage:

`unmark 2`

Expected outcome:
Marks the second task in the list
```
unmark 2
------------------------------------------------------------------------------
OK, I've marked this task as not done
[E][ ] Study for CS2113 (at: 6pm today)
------------------------------------------------------------------------------
```
### `Keyword` - list
Lists all the tasks

Example of usage:

`list`

Expected outcome:
Lists all the tasks
```
------------------------------------------------------------------------------
Here are the tasks in your list:
1.[T][ ] Study for CS2113
2.[E][ ] Study for CS2113 (at: 6pm today)
3.[D][ ] Study for CS3230 (by: 7pm today)
------------------------------------------------------------------------------
```
### `Keyword` - delete
deletes the given task

Example of usage:

`delete 2`

Expected outcome:
deletes the given task. Also, displays remaining number of tasks
```
------------------------------------------------------------------------------
Noted. I've removed this task:
[E][ ] Study for CS2113 (at: 6pm today)
Now you have 2 tasks in the list.
------------------------------------------------------------------------------
```
### `Keyword` - find
finds all tasks containing the given search string

Example of usage:

`find CS2113`

Expected outcome:
displays all the tasks containing "CS2113" in their descriptions
```
------------------------------------------------------------------------------
Here are the matching tasks in your list:
1.[T][ ] Study for CS2113
------------------------------------------------------------------------------
```
