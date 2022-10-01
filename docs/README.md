# User Guide

Duke is a Command Line Application for managing tasks.

Tasks are automatically saved to the hard disk after any command that modifies the data.

The location of this file will be at {directory where you are running the JAR file}/data/duke.txt

## Setup 
1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest ip.jar from [here](https://github.com/wangtingjia/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your todo list.
4. Open the command line in that folder and run the command `java -jar ip.jar`.
5. A default greeting is expected as per following :

```
Hello! I'm Timmy
 What can I do for you?


____________________________________________________________
 ____        _
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
____________________________________________________________
____________________________________________________________
Previously saved task:
No current tasks
____________________________________________________________
```


## Features 

### 1) Listing all task: `list`

List all the current tasks in the todo list

### Usage `list`
Input: `list`

Expected Output:
```
list
1. [E][ ] CS2113 tutorial (at: Wednesday 12pm)
2. [D][ ] CS2113 ip (by: tonight 2359)
3. [T][ ] CS2113 tp

```

____________________________________________________________
### 2) Add event `event`
Add an event to the current list of tasks

Format: `event {task name} /at {task time}`
### Usage `event`
Input: `event CS2113 tutorial /at Wednesday 12pm`

Expected Output:
```
event CS2113 tutorial /at Wednesday 12pm
Added event: [E][ ] CS2113 tutorial (at: Wednesday 12pm)
Total tasks = 1
```
____________________________________________________________
### 3) Add deadline `deadline`
Add a deadline to current list of tasks

Format: `deadline {task name} /by {task deadline}`
### Usage `deadline`
Input: `deadline CS2113 ip /by tonight 2359`

Expected Output:
```
deadline CS2113 ip /by tonight 2359
Added deadline: [D][ ] CS2113 ip (by: tonight 2359)
Total tasks = 2
```
____________________________________________________________
### 4) Add todo `todo`
Add a todo to the current list of tasks

Format: `todo {task name}`
### Usage `todo`
Input: `todo CS2113 tp`

Expected Output:
```
todo CS2113 tp
Added todo: [T][ ] CS2113 tp
Total tasks = 3
```
____________________________________________________________

### 5) Find task `find`
Find a task based on a keyword

Format: `find {keyword}`
### Usage `find`
Input: `find CS2113`

Expected Output:
```
find CS2113
1: [E][ ] CS2113 tutorial (at: Wednesday 12pm)
2: [D][ ] CS2113 ip (by: tonight 2359)
3: [T][ ] CS2113 tp
```
____________________________________________________________

### 6) Mark Tasks as completed `mark`
Mark current tasks as done

Take note to only mark tasks within range of (1 to number of tasks)

Format: `mark {task number}`
### Usage `mark`
Input: `mark 2`

Expected Output:
```
mark 2
Marked:
[D][X] CS2113 ip (by: tonight 2359)
```
____________________________________________________________
### 7) Unmark tasks that are completed `unmark`
Remove marking from current tasks

Take note to only remove marking of tasks within range of (1 to number of tasks)

Format: `unmark {task number}`
### Usage `unmark`
Input: `unmark 2`

Expected Output:
```
unmark 2
Unmarked:
[D][ ] CS2113 ip (by: tonight 2359)
```

____________________________________________________________
### 8) Delete tasks `delete`
Delete a task from the list

Take note to only delete tasks within the range of (1 to number of tasks)

Format: `delete {task number}`
### Usage `delete`
Input: `delete 1`

Expected Output:
```
delete 1
Deleting task:
[E][ ] CS2113 tutorial (at: Wednesday 12pm)
Remaining task count 2
```

____________________________________________________________

### 9) Exiting the app `bye`
Says bye to the user
### Usage `bye`

Expected Output:
```
bye
Bye. Hope to see you again soon

____________________________________________________________
```
____________________________________________________________
