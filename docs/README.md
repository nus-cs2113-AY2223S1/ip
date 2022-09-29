# User Guide for IP

## Features

### Feature #1 : Add a todo task - todo

Add a todo task when needed.

###### Format of input: `todo`

#### Example of Input and Output:
Input : `todo Do Homework`

Output:
```
---------------------------------------------
Got it. I've added this task
  [T][ ] Do Homework
Now you have 1 tasks in the list.
---------------------------------------------
```

### Feature #2 : Add an event task - event

Add an event task with date and/or time when needed.

###### Format of input: `event {Event Name} /at {Event date/time}`

#### Example of Input and Output:
Input : `event NUS Hall Event /at Sunday 5pm`

Output:
```
---------------------------------------------
Got it. I've added this task
  [E][ ] NUS Hall Event (at: Sunday 5pm)
Now you have 2 tasks in the list.
---------------------------------------------
```

### Feature #3 : Add a deadline task - deadline

Add a deadline task with date and/or time when needed.

###### Format of input: `deadline {deadline Name} /by {deadline date/time}`

#### Example of Input and Output:
Input : `deadline Complete CS Homework /by Thursday 23:59`

Output:
```
---------------------------------------------
Got it. I've added this task
  [D][ ] Complete CS Homework (by: Thursday 23:59)
Now you have 3 tasks in the list.
---------------------------------------------
```

### Feature #4 : List all task(s) - list

List down all task available.

###### Format of input: `list`

#### Example of Input and Output:
Input : `list`

Output:
```
---------------------------------------------
Here are the tasks in your list:
1. [T][ ] Do Homework
2. [E][ ] NUS Hall Event (at: Sunday 5pm)
3. [D][ ] Complete CS Homework (by: Thursday 23:59)
---------------------------------------------
```

### Feature #5 : Delete a task - delete

Delete any specific task that you do not want.

###### Format of input: `delete {task Number}`

#### Example of Input and Output:
Input : `delete 1`

Output:
```
---------------------------------------------
Noted. I've removed this task:
  [T][ ] Do Homework
Now you have 2 tasks in the list.
---------------------------------------------
```

### Feature #6 : Mark task - mark

Mark a task as done.

###### Format of input: `mark {task Number}`

#### Example of Input and Output:
Input : `mark 1`

Output:
```
---------------------------------------------
Nice! I've marked this task as done:
[X] NUS Hall Event (at: Sunday 5pm)
---------------------------------------------
```

### Feature #7 : Unmark a task - unmark

Unmark a task if incomplete.

###### Format of input: `unmark {task Number}`

#### Example of Input and Output:
Input : `unmark 1`

Output:
```
---------------------------------------------
OK, I've marked this task as not done yet:
[ ] NUS Hall Event (at: Sunday 5pm)
---------------------------------------------
```

### Feature #8 : Finding task(s) - find

List task(s) that contains any particular keyword (Letter case is ignored).

###### Format of input: `find {keywork}`

#### Example of Input and Output:
Input : `find hall`

Output:
```
---------------------------------------------
Here are the matching tasks in your list:
1.[E][ ] NUS Hall Event (at: Sunday 5pm)
---------------------------------------------
```

### Feature #9 : End Program - bye

End Program once done.

###### Format of input: `find {keywork}`

#### Example of Input and Output:
Input : `find hall`

Output:
```
---------------------------------------------
Bye. Hope to see you again soon!
---------------------------------------------
```
