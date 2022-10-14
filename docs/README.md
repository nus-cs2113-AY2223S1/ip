# User Guide

## Features 

### Add different types of Tasks

You can add different types of tasks to todo list, Events, TODO, Deadline,

### Search tasks

You can search different tasks based on the description

### Delete tasks

You can delete tasks from the list 

### Mark, unmark tasks

You can mark tasks as done and also mark them as undone

## Usage

### list - list tasks

You can see the list of all your tasks

***Example of usage: ***

> list

***Expected outcome:***

See the list of tasks

```
Here are the tasks in your list:
	1) [T][] read 
```


### bye - leave programme

Exit the programme

***Example of usage: ***

> bye

Expected outcome:

Leave the programme and your list is saved

```
Bye. Hope to see you soon!
```


### mark - mark task 

mark task as done

***xample of usage: ***

> mark 1

***Expected outcome:***

Task is now marked as done and can be seen so in list

```
Nice! I've marked this task as done:
[X] read 
```


### unmark - unmark task 

unmark task as not done

***Example of usage: ***

> unmark 1

***Expected outcome:***

Task is now marked as not done and can be seen so in list

```
OK, I've marked this task as not done yet:
[] read
```


### todo - create new todo

create new todo task to do

***Example of usage: ***

> todo read book 

***Expected outcome:***

New todo is added to list

```
Got it. I've added this task: 
[T][] read book 
Now you have 1 task in the list.
```

### deadline - create new deadline

create new deadline task to do

***Example of usage: ***

> deadline return book /by Sunday

***Expected outcome:***

New deadline is added to list

```
Got it. I've added this task: 
[D][] return book (by: Sunday)
Now you have 2 tasks in the list.
```


### event - create new event

create new event 

***Example of usage: ***

> event project meeting /at Mon 2-4pm

***Expected outcome:***

New event is added to list

```
Got it. I've added this task: 
[E][] project meeting (at: Mon 2-4pm)
Now you have 3 tasks in the list.
```


### delete - delete task

delete task from list

***Example of usage: ***

> delete 2

***Expected outcome:***

Corresponding task is deleted from list

```
Noted. I've removed this task: 
[D][] return book (by: Sunday)
Now you have 2 tasks in the list.
```



### find - find task

find task from list based on description

***Example of usage: ***

```find book```

***Expected outcome:***

Tasks with matching descriptions are returned

```
I have found these tasks:
1.[T][] read book 
```
