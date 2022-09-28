# User Guide for IP

## Features 

### Feature #1 : Add a todo task - todo

Add a todo task when needed.

#### Format of input:
`todo`

#### Example of Input and Output:
Input : `todo Do Homework`

Output:
```
Got it. I've added this task
  [T][ ] todo Do Homework
Now you have 1 tasks in the list.
```

### Feature #2 : Add an event task - event

Add an event task with date and/or time when needed.

#### Format of input:
`event {Event Name} /at {Event date/time}`

#### Example of Input and Output:
Input : `event NUS Hall Event /at Sunday 5pm`

Output:
```
Got it. I've added this task
  [E][ ] NUS Hall Event (at: Sunday 5pm)
Now you have 1 tasks in the list.
```

### Feature #3 : Add a deadline task - deadline

Add a deadline task with date and/or time when needed.

#### Format of input:
`deadline {deadline Name} /by {deadline date/time}`

#### Example of Input and Output:
Input : `deadline Complete CS Homework /by Thursday 23:59`

Output:
```
Got it. I've added this task
  [D][ ] Complete CS Homework (by: Thursday 23:59)
Now you have 1 tasks in the list.
```

### Feature #4 : List all task(s) - list

List down all task available.

### Feature #5 : Delete a task - delete

Delete any specific task that you dont want.

### Feature #6 : Mark task - mark

Mark a task as done.

### Feature #7 : Unmark a task - unmark

Unmark a task if incomplete.

### Feature #8 : Finding task(s) - find

List task(s) that contains any particular keyword.

### Feature #9 : End Program - bye

End Program once done.


## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
