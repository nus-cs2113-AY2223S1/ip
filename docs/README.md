# User Guide
Duke is a chat-bot that allows users to:
1. store and keep track of three different types of tasks: deadlines, events and todo
2. mark different tasks as done or incomplete
3. search for specific tasks by filtering out tasks based on different keywords / date
4. delete tasks when they are unnecessary

* [Quick Start](#quick-start)
* [Features](#features)
  * [List all tasks](#list-list-all-tasks)
  * [Add a deadline task](#add-a-deadline-task-deadline)
  * [Add an event task](#add-an-event-task-event)
  * [Add a todo task](#add-a-todo-task-todo)
  * [Mark a task as done](#mark-a-task-as-done-done)
  * [Delete a task](#delete-a-task-delete)
  * [Filter tasks by keywords](#filter-tasks-by-keywords-find)
  * [Filter tasks by date](#filter-tasks-by-date-date)
  * [Exit the program](#exit-the-program-bye)
* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------

## Quick start

1. Ensure you have Java 11 or above installed in your Computer.


2. Download the latest CS2113T_IP.jar from [here](https://github.com/LokQiJun/ip/releases)


3. Copy the CS2113T_IP.jar file to an empty folder.


4. **If you are using Windows**, click the Windows Start button and type in 'cmd' to open your command prompt. Then, navigate to the directory where CS2113T_IP.jar file is located.


5. **If you are using Mac or Linux or other systems**, open the terminal of your respective system and navigate to the directory where CS2113T_IP.jar file is located.


6. Run the command "java -jar CS2113T_IP.jar".


7. If the aforementioned steps are done properly, you should see the following.

```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
 Hello! I'm Duke
 What can I do for you?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Unable to load file. File does not exist or Path is wrong :(
If you have a load file, please check that the file is in the correct directory. Otherwise, ignore this.
```


--------------------------------------
# Features

## `list` - List all tasks 
Shows a list of all the user's tasks.
Task will be shown in the following format:
`[task type][completion status] description date(if any)`
Task type only be the following 3:
  E : Event
  D : Deadline
  T : Todo
Complete   : X
Incomplete : -
#### Format: `list`
Example of usage:

`list`

Expected outcome:
```
Here are the tasks in your list:
1: [D][-] finish up CS2113T coursemology                by: 17 September 2022
2: [E][-] birthday celebration          at: tomorrow evening
3: [T][-] attend CS2113T tutorial
```


## `deadline` - Add a deadline task
Adds a deadline task along with the due date of the deadline to the user's list of tasks.
#### Format: `deadline {task description} /by ({YYYY-MM-DD HH:MM} OR {date description})`
Example of usage:

`deadline finish up CS2113T coursemology /by 2022-09-17`

Expected outcome:
```
Added: 
[D][-] finish up CS2113T coursemology           by: 17 September 2022
There are currently 1 task(s) in the list :)
```


## `event` - Add an event task
Adds an event task along with the date and time of the event to the user's list of tasks.
#### Format: `event {task description} /at ({YYYY-MM-DD HH:MM} OR {date description})`
Example of usage:

`event birthday celebration /at tomorrow evening`

Expected outcome:
```
Added:
[E][-] birthday celebration             at: tomorrow evening
There are currently 2 task(s) in the list :)
```


## `todo` - Add a todo task 
Adds a todo task to the user's list of tasks.
#### Format: `todo {task description}`
Example of usage:

`todo attend CS2113T tutorial`

Expected outcome:
```
Added:
[T][-] attend CS2113T tutorial
There are currently 3 task(s) in the list :)
```


## `mark` / `unmark` - Mark a task as completed / incompleted:  
Marks a specified task as completed.
#### Format: `mark/unmark {task index in list}`
Example of usage:

`mark 2`

Expected outcome:
```
Nice! I've marked this task as completed:
[E][X] birthday celebration             at: tomorrow evening
```

Example of usage:

`unmark 3`

Expected outcome:
```
Nice! I've marked this task as incomplete:
[T][-] attend CS2113T tutorial
```


## `delete` - Delete a task
Deletes a specified task and shows how many tasks are remaining.
#### Format: `delete {task index in list}`
Example of usage:

`delete 3`

Expected outcome:
```
Deleted
[T][-] attend CS2113T tutorial
There are currently 2 task(s) in the list :)
```


## `find` - Find tasks that contains keywords
Shows a list of all the user's tasks that contain keywords provided by the user.
#### Format: `find {keyword(s)}`
Example of usage:

`find assignment`

Expected outcome:

```
Here are the tasks in your list that matches the given word:
[T][-] 2102 assignment
[D][-] cs3237 assignment                by: this saturday

```

Example of usage in the event that there is no matching keyword:

`find abcde`

Expected outcome:

```
Here are the tasks in your list that matches the given word:
There is no results.
Please make sure you have keyed in the right keyword
*note that keyword is case-sensitive*

```


## `date` - Find tasks with given date
Shows a list of all the user's tasks that occur on a specific date provided by the user.

#### Format: `date {YYYY-MM-DD}`
Example of usage:

`date 2022-09-17`

Expected outcome:
```
Here are the tasks in your list that matches the given date:
[D][-] finish cs2113 ip                 by: 17 September 2022
[E][-] do laundry               at: 17 September 2022

```

Example of usage in the event that there is no matching date:

`date 2022-09-21`

Expected outcome:
```
Here are the tasks in your list that matches the given date:
There is no results.
Please make sure you have keyed in the right date
```

## `bye` - Exit the program
Exits the program.
#### Format: `bye`

Expected outcome:
```
 Bye! Hope to see you again soon :)
```

--------------------------------------


## FAQ
Q: Are the commands case-sensitive?

A: No, you can have any mix of lower and upper lettercase as long as the command is spelled correctly. 
e.g. "deadline" and "DeadLiNE" both works

Q: How do I transfer my list of tasks and all associated data to another computer?

A: Transfer the text file named "Tasks.txt", located in the same directory as your iP.jar file, over to your other computer. Place it in the same directory as the CS2113T_IP.jar file on your other computer. Your tasks will then be loaded from this text file when you run CS2113T_IP.jar on your new computer.

--------------------------------------

## Command Summary

Description | Syntax | Examples
------------|--------|--------
Add a deadline task | `deadline {task description} /by {YYYY-MM-DD} OR {deadline description}` | `deadline submit cs2113T assignment /by 2022-10-01` `deadline submit cs2102 assignment /by two days later` 
Add an event task | `event {task description} /at {YYYY-MM-DD} OR {deadline description}` | `event run marathon /at 2022-11-03` `event national day /at 1 month ago` 
Add a todo task | `todo {task description}` | `todo buy new laptop`
Mark a task as completed/incomplete | `mark/unmark {task index in list}` | `mark 1`
Delete a task | `delete {task index in list}` | `delete 3`
Find tasks by keywords | `find {keyword}` | `find assignments`
Find tasks by date | `date {YYYY-MM-DD}` | `date 2022-09-30`
Exit the program | `bye` | `bye`
