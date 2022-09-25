# User Guide

## Features 

### Feature-Add different types of tasks

The user can add 3 types of tasks to the task list including todo, deadline, and event. 

### Feature-Manage tasks

The user can see the current task list, mark a task as done or undone, delete a task, or even find certain tasks using keyword.

### Feature-Save and load task list

The user can save current task list in a text file, and load it when next time using Duke.

## Usage

### `todo` - Add a todo task

Add a todo task to the task list

Example of usage: 

`todo answer tutorial questions`

Expected outcome:

        ____________________________________________________________
         Got it. I've added this task:
           [T][ ] answer tutorial questions
         Now you have 1 tasks in the list.
        ____________________________________________________________



### `deadline` - Add a deadline task

Add a deadline task to the task list

Example of usage: 

`deadline weekly quiz /by Monday 9pm`

Expected outcome:

        ____________________________________________________________
         Got it. I've added this task:
           [D][ ] weekly quiz (by: Monday 9pm)
         Now you have 2 tasks in the list.
        ____________________________________________________________



### `event` - Add an event task

Add an event task to the task list

Example of usage: 

`event lecture quiz /at Friday 4 to 6 pm`

Expected outcome:

        ____________________________________________________________
         Got it. I've added this task:
           [E][ ] lecture quiz (at: Friday 4 to 6 pm)
         Now you have 3 tasks in the list.
        ____________________________________________________________



### `list` - List all current tasks

Show the task list that includes all the uncompleted tasks.

Example of usage: 

`list`

Expected outcome:

```
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] answer tutorial questions
     2.[D][ ] weekly quiz (by: Monday 9pm)
     3.[E][ ] lecture quiz (at: Friday 4 to 6 pm)
    ____________________________________________________________
```



### `mark` - Mark a task as done

Mark a task as done given the task id

Example of usage: 

`mark 3`

Expected outcome:

        ____________________________________________________________
         Nice! I've marked this task as done:
           [E][X] lecture quiz (at: Friday 4 to 6 pm)
        ____________________________________________________________



### `unmark` - Unmark a task as undone

Unmark a task as undone given the task id

Example of usage: 

`unmark 3`

Expected outcome:

        ____________________________________________________________
         OK, I've marked this task as not done yet:
           [E][ ] lecture quiz (at: Friday 4 to 6 pm)
        ____________________________________________________________



### `find` - Find tasks by keyword

Find tasks that contain keyword in the description

Example of usage: 

`find quiz`

Expected outcome:

        ____________________________________________________________
         Here are the matching tasks in your list:
         1.[D][ ] weekly quiz (by: Monday 9pm)
         2.[E][ ] lecture quiz (at: Friday 4 to 6 pm)
        ____________________________________________________________



### `delete` - Delete a task

Delete a task given the task id

Example of usage: 

`delete 2`

Expected outcome:

        ____________________________________________________________
         Noted. I've removed this task:
           [D][ ] weekly quiz (by: Monday 9pm)
         Now you have 2 tasks in the list.
        ____________________________________________________________



### `bye` - Exit Duke

Exit Duke with Goodbye message

Example of usage: 

`bye`

Expected outcome:

        ____________________________________________________________
         Bye. Hope to see you again soon!
        ____________________________________________________________
