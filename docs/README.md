# User Guide
Welcome to the user guide of Duke, a Personal Assistant Chatbot that helps users to manage and keep track of various tasks.

Duke is optimised for users proficient in Command Line Interface (CLI).

## Table of Contents

1. [Quick start](#quick-start)
2. [Features](#features)
   1. [Getting help: `help`](#getting-help-help)
   2. [Exiting: `bye`](#exiting-bye)
   3. [List out all tasks: `list`](#list-out-all-tasks-list)
   4. [Add a ToDo: `todo`](#add-a-todo-todo)
   5. [Add an Event: `event`](#add-an-event-event)
   6. [Add a Deadline: `deadline`](#add-a-deadline-deadline)
   7. [Mark a task: `mark`](#mark-a-task-mark)
   8. [Unmark a task: `unmark`](#unmark-a-task-unmark)
   9. [Find task by keyword: `find`](#find-task-by-keyword-find)
   10. [Delete a task: `delete`](#delete-a-task-delete)
3. [Understanding Dates and Times](#understanding-dates-and-times)
4. [Saving the Data](#saving-the-data)
5. [Editing the data file](#editing-the-data-file)
6. [FAQ](#faq)
7. [Command summary](#command-summary)

## Quick start

1. Ensure you have Java 11 (reccomended) or above installed on your computer.
2. Download the latest jar file from [here](https://github.com/CheahHaoYi/ip/releases).
3. Copy the file to the folder you want to use as the *home folder* for the application.
4. Open the command line in that folder and run the command `java -jar [filename].jar`.
5. When you first run the application, a greeting will be displayed.


```
     _________________________________________
   /  _____________________________________   \ 
   | |                                     |  | 
   | |  C:\> Initiating programme. .  .    |  | 
   | |                                     |  | 
   | |  C:\> Creating Duke...              |  | 
   | |                                     |  | 
   | |                                     |  | 
   | |                                     |  | 
   | |_____________________________________|  | 
    \_____________________________________/ 
       \________________________________/ 
        _________________________________ 
   _-'.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.--- `-_ 
_-'.-.-. .---.-.-.-.-.-.-.-.-.-.-.-.-.-.-.--..-.-.`-_ 

Hello! I'm Duke
What can I do for you?
```

6. Type the command and press Enter to execute it.
7. Refer to the Features below for details of each command.

## Features

> Notes about the command format:
> - Words in `[BRACKETS]` are the parameters to be supplied by the user.
    e.g. in `event [NAME] /at [DATE]`, `[NAME]` and `[DATE]` are parameters which can be used in the form of `event Alibaba Shopping /at 2022-11-11`.
> - Extraneous parameters for commands that do not take in parameters will be ignored.

### Getting help: `help`

Displays the list of commands available by Duke.

Format: `help`

Sample:
```
help
--------------------------------------

Duke - your CLI based Personal Assistant Chatbot

deadline [TASK] /by [DATE]	 - add a task to be done before a specific date
event [TASK] /at [DATE]	 - add a task that start at a specific date and ends at a specific date
todo [TASK]	 - add a task without any data/time attached
delete [INDEX]	 - delete a task from the list by index
bye	 - exits the application
find [KEYWORD]	 - find all tasks by searching for a keyword
help	 - provide a quick user guide
list	 - list out all the tasks added to list
mark [INDEX]	 - mark a task as done by index shown on list
unmark [INDEX]	 - mark a task as not done by index shown on list

Full Documentation at https://github.com/CheahHaoYi/ip

--------------------------------------
```

### Exiting: `bye`

Exits the application.

Format: `bye`

Sample:
```
bye
--------------------------------------
--------------------------------------
BEEP BEEP >>>> SEE >>> YOU >>>> AGAIN >>> BEEP BEWWWWW >>>
--------------------------------------
--------------------------------------
```

### List out all tasks: `list`

Lists out all the tasks in the task list. 
The letter in the first pair of `[ ]` indicate the type of task
* `[T]` for a todo task
* `[D]` for a deadline task 
* `[E]` for an event task

The letter in the second pair of `[ ]` indicate if the task is marked done 
* `[ ]` for a task that is not done 
* `[X]` for a task that is done. 

See [mark](#mark-a-task-as-done-mark) and [unmark](#unmark-a-task-as-done-unmark) for further description

Format: `list`

Sample:
```
list
--------------------------------------
Beep beep, listing out the tasks....Loading.....
	1.[T][X] Hao Yi: Answer CS2113 Tutorial questions
	2.[D][ ] weekly CS2113 quiz  (by: Monday 9pm)
	3.[E][X] CS2113 lecture  (at: Friday)
	4.[E][ ] CS1010 PE  (at: tomorrow)
--------------------------------------
```

### Add a ToDo: `todo`

Creates a ToDo task.

Format: `todo NAME`

Sample:
```
todo CS2113 finals
--------------------------------------
HELLO BEEP, added a new ToDo: CS2113 finals
	[T][ ] CS2113 finals
Beep boop, now you have 1 tasks
--------------------------------------
```

### Add an Event: `event`

Creates an Event with the event date

Format: `event [TASK] /at [DATE]`

Sample:
```
event CS2113 ip demo /at Friday
--------------------------------------
OH NO BEEP BEEP, a new Event: CS2113 ip demo 
	[E][ ] CS2113 ip demo  (at: Friday)
Beep boop, now you have 2 tasks
--------------------------------------
```

### Add a Deadline: `deadline`

Creates a Deadline with the time of the deadline 

Format: `deadline [TASK] /by [DATE]`

Sample:
```
deadline CS2113 project /by tonight
--------------------------------------
OH NO BEEP BEEP, a new Deadline: CS2113 project 
	[D][ ] CS2113 project  (by: tonight)
Beep boop, now you have 3 tasks
--------------------------------------
```

### Mark a task: `mark`

Marks a task in the list as done.

Format: `mark [INDEX]`

Sample:
```
list
--------------------------------------
Beep beep, listing out the tasks....Loading.....
	1.[T][ ] CS2113 finals
	2.[E][ ] CS2113 ip demo  (at: Friday)
	3.[D][ ] CS2113 project  (by: tonight)
--------------------------------------
mark 2
--------------------------------------
Nice! I've marked this task as done:
	[E][X] CS2113 ip demo  (at: Friday)
--------------------------------------
list
--------------------------------------
Beep beep, listing out the tasks....Loading.....
	1.[T][ ] CS2113 finals
	2.[E][X] CS2113 ip demo  (at: Friday)
	3.[D][ ] CS2113 project  (by: tonight)
--------------------------------------
```

### Unmark a task: `unmark`

Marks a task in the list as not done.

Format: `unmark [INDEX]`

Sample:
```
list
--------------------------------------
Beep beep, listing out the tasks....Loading.....
	1.[T][ ] CS2113 finals
	2.[E][X] CS2113 ip demo  (at: Friday)
	3.[D][ ] CS2113 project  (by: tonight)
--------------------------------------
unmark 2
--------------------------------------
OK, I've marked this task as not done yet:
	[E][ ] CS2113 ip demo  (at: Friday)
--------------------------------------
list
--------------------------------------
Beep beep, listing out the tasks....Loading.....
	1.[T][ ] CS2113 finals
	2.[E][ ] CS2113 ip demo  (at: Friday)
	3.[D][ ] CS2113 project  (by: tonight)
--------------------------------------
```

### Find task by keyword: `find`

List out all tasks that contains a particular keyword.

Format: `find [KEYWORD]`

Sample:
```
list
--------------------------------------
Beep beep, listing out the tasks....Loading.....
	1.[T][X] Hao Yi: Answer CS2113 Tutorial questions
	2.[D][ ] weekly CS2113 quiz  (by: Monday 9pm)
	3.[E][ ] CS1010 PE  (at: tomorrow)
	4.[T][ ] CS1010 Invigilation
--------------------------------------
find cs1010
--------------------------------------
Here are the matching tasks in your list:
	3.[E][ ] CS1010 PE  (at: tomorrow)
	4.[T][ ] CS1010 Invigilation
--------------------------------------
```

### Delete a task: `delete`

Deletes a task from the list.

Format: `delete [INDEX]`

Sample:
```
list
--------------------------------------
Beep beep, listing out the tasks....Loading.....
	1.[T][X] Hao Yi: Answer CS2113 Tutorial questions
	2.[D][ ] weekly CS2113 quiz  (by: Monday 9pm)
	3.[E][X] CS2113 lecture  (at: Friday)
	4.[E][ ] CS1010 PE  (at: tomorrow)
--------------------------------------
delete 3
--------------------------------------
Noted. I've removed this task: 
	[E][X] CS2113 lecture  (at: Friday)
Beep boop, now you have 3 tasks
--------------------------------------
```

## Saving the Data

Duke data is saved to the computer automatically when the programme exits. There is no need to save manually.

## Editing the data file

Duke data is saved as a plaintext file at `[JAR file location]/data/userData.txt`. Advanced users are welcome to update data directly by editing that data file.

> Caution: If your changes to the data file makes it format invalid, there may be data loss.

## FAQ

Q: How do I transfer my data to another computer?

A: Duke is designed with portability in mind. Simply copy the JAR file and the data folder over to your other computer and you are good to go.

## Command summary

| Action                | Format                       | Example                                |
|-----------------------|------------------------------|----------------------------------------|
| Add a deadline        | `deadline [TASK] /by [DATE]` | `deadline team project /by tomorrow`   |
| Add an event          | `event [TASK] /at [DATE]`    | `event April Fool's Day /at 1st April` |
| Add a Todo            | `todo [TASK]`                | `todo Do Luminus Quiz`                 |
| Delete a task         | `delete [INDEX]`             | `delete 1`                             |
| Exit application      | `bye`                        | `bye`                                  |
| Find tasks by keyword | `find [KEYWORD]`             | `find assignment`                      |
| View help             | `help`                       | `help`                                 |
| List tasks            | `list`                       | `list`                                 |
| Mark a task as done   | `mark [INDEX]`               | `mark 1`                               |
| Mark a task as undone | `unmark [INDEX]`             | `unmark 1`                             |


## Credits
Credits to Owen Leong (owenl131) for inspiration for some functionality of Duke.

The format of UserGuide is adapted from Owen Leong as well 
