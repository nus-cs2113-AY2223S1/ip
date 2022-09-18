<link rel="stylesheet" href="styles.css">

# User Guide

Axel is a **Command Line Application for managing tasks, optimised for use via the command-line interface (CLI)**.

## Table of contents
* [Getting started](#getting-started)
* [Axel's features](#axels-features)
    * [Adding a task](#adding-a-task)
        * [Todo: `todo`](#todo-todo)
        * [Deadline: `deadline`](#deadline-deadline)
        * [Event: `event`](#event-event)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
    * [Marking a task as undone: `unmark`](#marking-a-task-as-undone-unmark)
    * [Deleting a task: `delete`](#deleting-a-task-delete)
    * [Finding matching tasks by keyword: `find`](#finding-matching-tasks-by-keyword-find)
    * [Closing the application: `bye`](#closing-the-application-bye)
    * [Saving your lists of tasks](#saving-your-list-of-tasks)
    * [Editing your data](#editing-your-data)
* [Table of valid user commands](#table-of-valid-user-commands)
* [FAQ](#faq)

<br>

## Getting started

1. Please ensure that you have **Java 11 or above** set up in your computer.
2. Download the latest version of the application `iP.jar` [here](https://github.com/nshian/ip/releases).
3. Copy the file to the folder you have designated as the **default folder** for Axel.
4. Open a terminal and type `java -jar iP.jar`.
5. If the program is initialised and runs successfully, you will be greeted by Axel as per the following:

    ```
            _____                .__
           /  _  \ ___  ___ ____ |  |
          /  /_\  \\  \/  // __ \|  |
         /    |    \>    <\  ___/|  |__
         \____|__  /__/\_ \\___  >____/
        __________________________________________________________________________________
         Hello! My name is Axel. :-)
         How may I help you today?
        __________________________________________________________________________________
    ```

6. Begin by entering a command below the greeting and press <kbd>Enter</kbd> to execute it. Here is an example you can try:

    * `bye`: Terminates the program and closes the application.

7. Refer to [Axel's features](#axels-features) below for further details of valid commands to enter.
8. Enjoy using Axel for your task management needs :D
<br>

## Axel's features

<div class="notes box" markdown="1">

**Take note:**
* All commands you enter are case-sensitive. For instance, `Todo` is **NOT** equivalent to `todo`.
* Words in `UPPER CASE` are parameters. For instance, `todo READ BOOK`.

</div>

### Adding a task

* ### Todo: `todo`

  **Explanation**: A todo is a type of task that only contains a description and does not have a time-related attribute associated with it.<br>
  <br> **Function**: Adds a todo to your current list of tasks.<br>
  <br>
  **Usage**: `todo DESCRIPTION`

  **Example**:
  ```
  todo return book
  ________________________________________________________________________________
  Got it. I've added this task:
    [T][ ] return book
  Now you have 1 task(s) in the list.
  ________________________________________________________________________________
  ```

* ### Deadline: `deadline`

  **Explanation**: A deadline is a type of task that contains a description along with an associated deadline that indicates when the task should be completed.<br>
  <br> **Function**: Adds a deadline to your current list of tasks.<br>
  <br>
  **Usage**: `deadline DESCRIPTION /by DEADLINE`

  **Example**:
  ```
  deadline CS3243 Project /by Week 6 Sunday
  ________________________________________________________________________________
  Got it. I've added this task:
    [D][ ] CS3243 Project (by: Week 6 Sunday)
  Now you have 2 task(s) in the list.
  ________________________________________________________________________________
  ```

* ### Event: `event`

  **Explanation**: An event is a type of task that contains a description along with an associated timestamp of when the event takes place.<br>
  <br> **Function**: Adds an event to your current list of tasks.<br>
  <br>
  **Usage**: `event DESCRIPTION /at DAY_AND_TIME`

  **Example**:
  ```
  event CS2113 project meeting /at 17 Sep 2022
  ________________________________________________________________________________
  Got it. I've added this task:
    [E][ ] CS2113 project meeting (at: 17 Sep 2022)
  Now you have 3 task(s) in the list.
  ________________________________________________________________________________
  ```
<br>

### Listing all tasks: `list`

**Usage**: `list`

**Example**:
```
list
________________________________________________________________________________
Here are the tasks in your list:
1.[T][ ] return book
2.[D][ ] CS3243 Project (by: Monday 2359hrs)
3.[E][ ] CS2113 project meeting (at: 17 Sep 2022)
________________________________________________________________________________
```

<br>

### Marking a task as done: `mark`

**Usage**: `mark TASK_NUMBER`

**Example**:
```
mark 2
________________________________________________________________________________
Nice! I've marked this task as done:
[D][X] CS3243 Project (by: Monday 2359hrs)
________________________________________________________________________________
```

<br>

### Marking a task as undone: `unmark`

**Usage**: `unmark TASK_NUMBER` <br>
This function comes in handy when rectifying a mistake in accidentally marking a task as done even though it has not been completed yet. 

**Example**:
```
unmark 2
________________________________________________________________________________
OK, I've marked this task as not done yet:
[D][] CS3243 Project (by: Monday 2359hrs)
________________________________________________________________________________
```

<br>

### Deleting a task: `delete`

**Usage**: `delete TASK_NUMBER`

**Example**:
```
delete 1
________________________________________________________________________________
Noted. I've removed this task:
  [T][ ] return book
Now you have 2 task(s) in the list.
________________________________________________________________________________
```

<br>

### Finding matching tasks by keyword: `find`

**Usage**: `find KEYWORD`

**Example**:
```
find project
________________________________________________________________________________
Here are the matching tasks in your list:
1.[D][X] CS3243 Project (by: Monday 2359hrs)
2.[E][ ] CS2113 project meeting (at: 17 Sep 2022)
________________________________________________________________________________
```

<br>

### Closing the application: `bye`

**Usage**: `bye`

**Example**:
```
bye
________________________________________________________________________________
Goodbye. Hope to see you again soon!
________________________________________________________________________________
```

<br>

### Saving your list of tasks

Your data is automatically saved in your local hard disk after every command you enter. You do not need to save manually :-)

<br>

### Editing your data

Your data is saved as a `txt` file located at `[JAR folder]/data/duke.txt`.

The format of your tasks saved in that file is as follows: `TYPE | STATUS | DESCRIPTION | TIMESTAMP`

* `TYPE` refers to the type of task. It will take on the value of `T` for `Todo`, `D` for `Deadline`, or `E` for `Event`.
* `STATUS` refers to whether you have completed the task. It will take on the value of either `0` (not done) or `1` (done).
* `DESCRIPTION` refers to the task's description.
* `TIMESTAMP` refers to the time-related attribute associated with the task. This would be the deadline for `Deadline` or day and time for`Event`.

**Example**:

```
T | 0 | return book
D | 1 | CS3243 Project | Monday 2359hrs
E | 0 | CS2113 project meeting | 17 Sep 2022
```

## Table of valid user commands

| Command    | Format                               | Example                                        |
|:-----------|:-------------------------------------|:-----------------------------------------------|
| `todo`     | `todo DESCRIPTION`                   | `todo return book`                             |
| `deadline` | `deadline DESCRIPTION /by DEADLINE`  | `deadline CS3243 Project /by Monday 2359hrs`   |
| `event`    | `event DESCRIPTION /at DAY_AND_TIME` | `event CS2113 project meeting /at 17 Sep 2022` |
| `list`     | `list`                               | `list`                                         |
| `mark`     | `mark TASK_NUMBER`                   | `mark 1`                                       |
| `unmark`   | `unmark TASK_NUMBER`                 | `unmark 1`                                     |
| `delete`   | `delete TASK_NUMBER`                 | `delete 1`                                     |
| `find`     | `find KEYWORD`                       | `find project`                                 |
| `bye`      | `bye`                                | `bye`                                          |


## FAQ

**Q**: Must I enter the user command exactly as specified? <br>
**A**: Yes, otherwise Axel will not be able to recognise the command you entered. <br>

**Q**: What happens if I make a typo in the type of command? <br>
**A**: Axel will show you the following error:
```
lis
    ____________________________________________________________
     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(
    ____________________________________________________________

```
But fret not, you can still continue to enter commands :-) <br>

**Q**: I accidentally tried to mark/unmark or delete a task I have not included. Will the program crash? <br>
**A**: No. Axel will show you the following error(s):
```
mark 1
    ____________________________________________________________
     ☹ OOPS!!! The task you specified is already marked!
    ____________________________________________________________

```
```
delete 3
    ____________________________________________________________
     ☹ OOPS!!! The task you have specified is out of bounds :-(

You currently have 1 task(s) in your list.
    ____________________________________________________________
```
Notice that the bottom-most line gives you information on the number of tasks in your list. Following this, you can then enter a valid task number :-) <br>

**Q:** I bought a new computer. How do I transfer my data over?<br>
**A:** On your other computer, download the latest version of the application `iP.jar` which can be found [here](https://github.com/nshian/ip/releases).
Install and set up the application as per the instructions in the [Getting started](#getting-started) section above.
You can then overwrite the empty text file that Axel creates with your original file (from your previous default folder) that contains your previously saved list of tasks.<br>
