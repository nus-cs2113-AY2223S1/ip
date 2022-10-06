# User Guide

Project duke is a **desktop application for jotting down your different types of tasks**,
using a Command Line Interface (CLI). 

## Contents

* [QuickStart](#quick-start)
* [Command Summary](#command-summary)
* [Features](#features)
  * [Add to-do](#adding-a-to-do-task-todo)
  * [Add deadline](#adding-a-task-with-deadline-deadline)
  * [Add event](#adding-a-task-for-an-event-event)
  * [Mark done](#marking-a-done-task-done)
  * [Delete task](#deleting-a-task-delete)
  * [List tasks](#listing-all-tasks-list)
  * [Find task](#finding-a-task-with-keyword-find)
  * [Help](#showing-help-page-help)
  * [Bye](#exiting-duke-bye)
  * [Save data](#saving-the-data)
  * [Edit data](#editing-the-data-file)


## Quick start

1. Ensure `Java 11` is installed in your computer.
   * Find the platform you are running on your computer and click on the corresponding 
     download link [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
2. Download the latest `duke.jar` [here](https://github.com//ip/releases/tag/A-Release).
3. Save it into an empty folder. This will be your *home folder* for duke.
4. For Windows user, type `cmd` into the location bar at the top of the window of your *home folder* and tap enter. 
For Linux users, `cd` into your *home folder* on your terminal.
5. Run `java -jar duke.jar` and you should see the start page as shown below

```
                      ____        _        
                    |  _ \ _   _| | _____ 
                    | | | | | | | |/ / _ \
                    | |_| | |_| |   <  __/
                    |____/ \__,_|_|\_\___|

=====================================================================
    Hello! I'm Duke_HTT.     What can I do for you?
=====================================================================

>>
```

6. Type `help` to see the list of commands.
7. Refer to the [Features](#features) below for detailed explanation for each command.

## Command summary

**Notes about the command format**

* Parameters encased in `[]` are **compulsory** to be supplied by the user.
* Parameters encased in `<>` are **optional**.
* Parameters must be in a specific order specified by `help` command.
* `date` must be supplied in `dd-MM-yyyy` format.
* `time` uses 24-hour format and supplied in `HHmm`.
* `[description]` parameter only accepts alphanumeric and special characters.

Command | Parameters
------- | ------
`todo` | `[description]`
`deadline` | `[description] /by [date] <time>`
`event` | `[description] /at [date] [time] <duration>`
`done` | `[task number]`
`delete` | `[task number]`
`list` | -
`find` | `[keyword]`
`help` | -
`bye`  | -


## Features

### Adding a to-do task: `todo`

Adds a to-do task to the task list.

Format: `todo [description]`

Example:
* `todo Eat food`

Expected output:
```
Okay, I have added this task!
  [T][ ] Eat food
You currently have (1) tasks in your list
```

### Adding a task with deadline: `deadline`

Adds a task with a deadline to the task list.

Format: `deadline [description] /by [date] <time>`
* `<time>` is defaulted to `23:59`

Examples:
* `deadline CS2113T Quiz /by 21-09-2021 1800`
* `deadline CS2106 Assignment /by 22-09-2021`

Expected output:
```
Okay, I have added this task!
  [D][ ] CS2106 Assignment (by: Sep 22 2021 23:59)
You currently have (2) tasks in your list
```

### Adding a task for an event: `event`

Adds an event into the task list.

Format: `event [description] /at [date] [time] <duration>`
* `<duration>` can only be supplied in complete hour.
  * Values such as `2.5` or `1.25` will not be accepted.
  
Examples: 
* `event CS2113T Lecture /at 24-09-2021 1600 2`
* `event CCA Fair /at 25-09-2021 0800`

Expected output:
```
Okay, I have added this task!
  [E][ ] CS2113T Lecture (at: Sep 24 2021 16:00 to Sep 24 2021 18:00)
You currently have (3) tasks in your list
```

### Marking a done task: `done`

Marks a specified task as done.

Format: `done [task number]`

Example: 
* `done 1`

Expected output:
```
Nice! I've marked this task as done:
  [T][X] Buy Groceries
```

### Deleting a task: `delete`

Deletes a specific task.

Format: `delete [task number]`

Example: 
* `delete 2`

Expected output:
```
Noted. I've removed this task:
  [E][ ] CS2113T Lecture (at: Sep 24 2021 16:00 to Sep 24 2021 18:00)
You currently have (2) tasks in your list
```
### Listing all tasks: `list`

Lists all the tasks in the task list.

Format: `list`

Expected output:
```
Here are the tasks in your list:
1. [T][X] Buy Groceries
2. [D][ ] CS2106 Assignment (by: Sep 22 2021 23:59)
```

### Finding a task with keyword: `find`

Lists the tasks with a specific keyword.

Format: `find [keyword]`
* `find` filters tasks including the `date` and `time` fields

Examples:
* `find groceries`

Expected output:
```
Here are the filtered tasks with keyword: groceries
1. [T][X] Buy Groceries
```

### Showing help page: `help`

Shows the help page with the list of commands available.

Format: `list`

Expected output:
```
Please enter only the following commands: 
- todo [description]
- deadline [description] /by [date] <time>
- event [description] /at [date] [time] <duration>
- done [task number]
- delete [task number]
- list
- bye
```

### Exiting duke: `bye`

Exits duke.

Format: `bye`

Expected output:
```
Bye. Hope to see you again soon!
```

### Saving the data

duke saves the tasks list **automatically** after running commands
that cause a modification in the tasks list.

### Editing the data file

duke stores the data in a text file at
`[JAR file location]/data/duke.txt`. Advanced users can
modify the file directly by editing the file.

**Note**

In case the data file is modified incorrectly such that duke
is unable to read, it will show the expected output below:
```
Error - file is corrupted!
```

To continue using duke, delete the file `duke.txt` and run the program as per normal
