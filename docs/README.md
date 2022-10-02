# User Guide

## Features 

### Add Todo Task

User can create a todo task with a description

### Add Deadline Task

User can create a deadline task with a description and a due time

### Add Event Task

User can create an event task with a description and an event time

### List All Tasks

User can list all tasks in the task list

### Delete a Task

User can delete a task by its index

### Mark a Task

User can mark a task as done by its index

### Unmark a Task

User can unmark a task as done by its index

### Find a Task

User can find a task as done by keywords

### Exit the program

User can exit the program

### Save and Restore the tasks

All tasks will be automatically saved and restored so user do not need to input anything

## Usage

### `todo` - Create a todo task

Create a todo task

Example of usage: 

`todo take the written exam`

Expected outcome:

It should shows the task type('T' for Task), the completion status and the description

```
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] take the written exam
     Now you have 7 tasks in the list.
    ____________________________________________________________
```

### `deadline` - Create a deadline task

Create a deadline task

Example of usage: 

`deadline upload the written test /by 5th of Oct`

Expected outcome:

It should shows the task type('D' for Deadline), the completion status, the due time and the description

```
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] upload the written test (by: 5th of Oct)
     Now you have 9 tasks in the list.
    ____________________________________________________________
```

### `event` - Create a deadline task

Create an event task

Example of usage: 

`event dining hall party /at 9pm`

Expected outcome:

It should shows the task type, the completion status, the event time and the description

```
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] dining hall party (at: 9pm)
     Now you have 10 tasks in the list.
    ____________________________________________________________
```

### `list` - List all tasks

List all tasks in the task list now

Example of usage: 

`list`

Expected outcome:

It should shows all the tasks in the task list

```
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][X] read book
     2.[E][ ] project meeting(at:Aug 6th 2-4pm)
     3.[T][X] take the written exam
     4.[D][ ] upload the written test (by: 5th of Oct)
     5.[E][ ] dining hall party (at: 9pm)
    ____________________________________________________________
```

### `delete` - Delete a task

Delete a task by its index (which must be valid)

Example of usage: 

`delete 5`

Expected outcome:

It should shows the information of the deleted task

```
    ____________________________________________________________
     Noted. I've removed this task:
     [E][ ] dining hall party (at: 9pm)
     Now you have 4 tasks in the list.
    ____________________________________________________________
```

Example of usage: 

`delete 9`

Expected outcome:

Invalid index

```
    ____________________________________________________________
     OOPS!!! Index out of bound.
    ____________________________________________________________

```

### `mark` - Mark a task as done

Mark a task as done by its index (which must be valid)

Example of usage: 

`mark 4`

Expected outcome:

It should shows the task and the modified completion status 

```
    ____________________________________________________________
    Nice! I've marked this task as done:
     [D][X] upload the written test (by: 5th of Oct)
    ____________________________________________________________
```

### `unmark` - Unmark a task as done

Unmark a task as not done by its index (which must be valid)

Example of usage: 

`unmark 4`

Expected outcome:

It should shows the task and the modified completion status 

```
    ____________________________________________________________
    OK, I've marked this task as not done yet:
     [D][ ] upload the written test (by: 5th of Oct)
    ____________________________________________________________
```

### `find` - Find a task

Find a task by given keywords

Example of usage: 

`find written`

Expected outcome:

It should shows all the tasks that include the keywords

```
    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[T][X] take the written exam
     2.[D][ ] upload the written test (by: 5th of Oct)
    ____________________________________________________________
```

### `bye` - Exit the program

Exit the program

Example of usage: 

`bye`

Expected outcome:

It should shows the goodbye message

```
    ____________________________________________________________
    Bye. Hope to see you again soon!
    ____________________________________________________________
```