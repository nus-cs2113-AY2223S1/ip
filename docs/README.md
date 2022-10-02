# User Guide

## Features 

### Feature - Add Todo Task

User can define a todo task with a description

### Feature - Add Deadline Task

User can define a deadline task with a description and a due time

### Feature - Add Event Task

User can define an event task with a description and a event time

### Feature - List all tasks

User can list all tasks in the task list

## Usage

### `todo` - Create a todo task

create a todo task

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

create a deadline task

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

create an event task

Example of usage: 

`event dining hall party /at 9pm    `

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

list all tasks in the task lists now

Example of usage: 

`event dining hall party /at 9pm    `

Expected outcome:

It should shows the task type, the completion status, the event time and the description

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
