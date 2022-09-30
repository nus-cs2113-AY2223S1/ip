# User Guide
Welcome to the user guide of Duke, a Personal Assistant Chatbot that helps you to keep track of various tasks.

## Features 

### Feature-ABC

Description of the feature.

### Feature-XYZ

Description of the feature.

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
Table of Contents

1. [Quick start](#quick-start)
2. [Features](#features)
    1. [Viewing help: `help`](#viewing-help-help)
    2. [Listing all tasks: `list`](#listing-all-tasks-list)
    3. [Exiting: `bye`](#exiting-bye)
    4. [Find task by keyword: `find`](#find-task-by-keyword-find)
    5. [Add a ToDo: `todo`](#add-a-todo-todo)
    6. [Add an Event: `event`](#add-an-event-event)
    7. [Add a Deadline: `deadline`](#add-a-deadline-deadline)
    8. [Mark a task as done: `mark`](#mark-a-task-as-done-mark)
    9. [Unmark a task as done: `unmark`](#unmark-a-task-as-done-unmark)
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
> - Words in [BRACKETS] are the parameters to be supplied by the user.
    e.g. in `event [NAME] /at [DATE]`, `[NAME]` and `[DATE]` are parameters which can be used in the form of `event Alibaba Shopping /at 2022-11-11`.
> - Extraneous parameters for commands that do not take in parameters will be ignored.

### Viewing help: `help`

Displays the list of commands available.

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

### Listing all tasks: `list`

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
    ____________________________________________________________

     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] return book (by: Sunday)
     3.[E][ ] project meeting (at: Mon 2-4pm)
    ____________________________________________________________
```

### Add a ToDo: `todo`

Creates a ToDo task.

Format: `todo NAME`

Sample:
```
todo borrow book
    ____________________________________________________________

     added: [T][ ] borrow book
    ____________________________________________________________
```

### Add an Event: `event`

Creates an Event

Format: `event NAME /at DATETIME`

Sample:
```
event project meeting /at Mon 2-4pm
    ____________________________________________________________

     added: [E][ ] project meeting (at: Mon 2-4pm)
    ____________________________________________________________


event christmas party /at 2022-12-25 2:00pm
    ____________________________________________________________

     added: [E][ ] christmas party (at: Sun, 25 Dec 2022, 14:00)
    ____________________________________________________________
```

### Add a Deadline: `deadline`

Use this command to keep track of deadlines you need to meet. If a date or date and time is specified in a clear format, then Duke will be able to understand it and display it in a more helpful form. See more at [understanding dates and times](#understanding-dates-and-times).

Format: `deadline [TASK] /by [DATE]`

Sample:
```
deadline return book /by Sunday
    ____________________________________________________________

     added: [D][ ] return book (by: Sunday)
    ____________________________________________________________
```

### Mark a task as done: `mark`

Marks a task in your list as completed.

Format: `mark INDEX`

Sample:
```
list
    ____________________________________________________________

     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] return book (by: Sunday)
     3.[E][ ] project meeting (at: Mon 2-4pm)
     4.[D][ ] do homework (by: no idea :-p)
    ____________________________________________________________

mark 2
    ____________________________________________________________

     Nice! I've marked this task as done:
      [D][X] return book (by: Sunday)
    ____________________________________________________________
```

### Unmark a task as done: `unmark`

Marks a task in your list as incomplete.

Format: `unmark INDEX`

Sample:
```
list
    ____________________________________________________________

     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][X] return book (by: Sunday)
     3.[E][ ] project meeting (at: Mon 2-4pm)
     4.[D][ ] do homework (by: no idea :-p)
    ____________________________________________________________

unmark 2
    ____________________________________________________________

     OK, I've marked this task as not done yet:
      [D][ ] return book (by: Sunday)
    ____________________________________________________________
```

### Find task by keyword: `find`

Finds all tasks with names containing a given keyword.

Format: `find KEYWORD`

Sample:
```
find project
    ____________________________________________________________

     Result of search for: project
     
     Here are the tasks in your list:
     1.[E][ ] project meeting (at: Mon 2-4pm)
    ____________________________________________________________
```

### Delete a task: `delete`

Deletes a task from your list.

Format: `delete INDEX`

Sample:
```
list
    ____________________________________________________________

     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] return book (by: Sunday)
     3.[E][ ] project meeting (at: Mon 2-4pm)
    ____________________________________________________________

delete 2
    ____________________________________________________________

     OK, I've deleted [D][ ] return book (by: Sunday)
     
     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[E][ ] project meeting (at: Mon 2-4pm)
    ____________________________________________________________
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
Credits to Owen Leong (owenl131) for inspiration for some functionality of Duke 
The format of UserGuide is adapted from Owen Leong as well 
