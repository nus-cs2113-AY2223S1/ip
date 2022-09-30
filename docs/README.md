# Duke (User Guide)

Duke is a CLI task manager app.

## Setting up

1. Download duke.jar from [here](https://github.com/alfred-leong/ip/releases/download/v0.2/duke.jar)
2. Open a terminal and cd into the folder that contains duke.jar
3. Run "java -jar duke.jar"

If successful, you should be greeted with something like this:

   ```
   Hello from
 _____ __    _____ _____ _____
| __  |  |  |     |     |     |
| __ -|  |__|  |  |  |  | | | |
|_____|_____|_____|_____|_|_|_|

__________________________________________________
Hello! I'm Bloom, your personal task manager.

What can I do for you?
__________________________________________________
   ```
   
You might be wondering why is your task manager called Bloom, when it was introduced as Duke?
This is because everyone uses Duke. Since you are special, Bloom is your special assistant.
(Do not be confused. All files/folders will be named as Duke for the purpose of this project.)

## Features

1. [List all tasks: `list`](#list-all-tasks-list)
2. [Add a todo task: `todo`](#add-a-todo-task-todo)
3. [Add a deadline task: `deadline`](#add-a-deadline-task-deadline)
4. [Add an event task: `event`](#add-an-event-task-event)
5. [Mark task as completed: `mark`](#mark-task-as-completed-mark)
6. [Mark task as not completed: `unmark`](#mark-task-as-not-completed-unmark)
7. [Delete task: `delete`](#delete-task-delete)
8. [Find tasks: `find`](#find-tasks-find)
9. [Exit app: `bye`](#exit-app-bye)

## Usage

Here are the available commands and descriptions:

### List all tasks: `list`
Displays all tasks that are currently in the list

Format: `list`

```
list
__________________________________________________
Here are the tasks in your list:
1.[T][ ] Read a book
2.[D][ ] Return book (by: Monday)
3.[E][ ] Dinner with Bob (at: Tomorrow 6.30pm)
```

### Add a todo task: `todo`
Adds a todo task to the list

Format: `todo [taskName]`

```
todo Read a book
__________________________________________________
Got it. I've added this task:
[T][ ] Read a book
Now you have 1 tasks in your list.
```

### Add a deadline task: `deadline`
Adds a deadline task to the list (taskDateTime can be in any format ie. "today 5pm" or "1 Jan 2023")

Format: `deadline [taskName] /by [taskDateTime]`


```
deadline Return book /by Monday
__________________________________________________
Got it. I've added this task:
[D][ ] Return book (by: Monday)
Now you have 2 tasks in your list.
```

### Add an event task: `event`
Adds an event task to the list (taskDateTime can be in any format ie. "today 5-7pm" or "1 Jan 2023")

Format: `event [taskName] /at [taskDateTime]`

```
event Dinner with Bob /at Tomorrow 6.30pm
__________________________________________________
Got it. I've added this task:
[E][ ] Dinner with Bob (at: Tomorrow 6.30pm)
Now you have 3 tasks in your list.
```

### Mark task as completed: `mark`
Marks the corresponding task as completed

Format: `mark [taskID]`

```
mark 1
__________________________________________________
Nice! I've marked this task as done:
[T][X] Read a book
```

### Mark task as not completed: `unmark`
Marks the corresponding task as not completed

Format: `unmark [taskID]`
```
unmark 1
__________________________________________________
Okay, I've marked this task as not done yet:
[T][ ] Read a book
```

### Delete task: `delete`
Removes the corresponding task from the list

Format: `delete [taskID]`

```
delete 3
__________________________________________________
Noted. I've removed this task:
[E][ ] Dinner with Bob (at: Tomorrow 6.30pm)
Now you have 2 tasks in the list.
```

### Find tasks: `find`
Displays all tasks that have the keyword in their task names.

Format: `find [keyword]`

```
find book
__________________________________________________
Here are the matching tasks in your list:
1.[T][ ] Read a book
2.[D][ ] Return book (by: Monday)
```

### Exit app: `bye`
Exits the task manager app, and all information is stored in "data/duke.txt"

Format: `bye`

```
bye
__________________________________________________
__________________________________________________
Bye. Hope to see you again!
```

## Loading and Saving of Task List

1. Your task list will be saved in a .txt file called duke.txt and it is contained in a folder called data.
2. The data folder is stored in the directory where you run the program ie. (JAR File Location/data/duke.txt).
3. The task list data will be constantly updated when you pass in the various commands.
4. Upon running the program, the contents of duke.txt will be retrieved and loaded into the program.

## Command summary

| Action                | Format                                    | Example                                 |
|-----------------------|-------------------------------------------|-----------------------------------------|
| List tasks            | `list`                                    | `list`                                  |
| Add a todo task       | `todo [taskName]`                         | `todo read book`                        |
| Add a deadline task   | `deadline [taskName] /by [taskDateTime]`  | `deadline return book /by tomorrow`     |
| Add an event task     | `event [taskName] /at [taskDateTime]`     | `event Dinner with Bob /at Mon 2pm`     |
| Mark task             | `mark [taskID]`                           | `mark 1`                                |
| Unmark task           | `unmark [taskID]`                         | `unmark 1`                              |
| Delete task           | `delete [taskID]`                         | `delete 1`                              |
| Find tasks by keyword | `find [keyword]`                          | `find book`                             | 
| Exit app              | `bye`                                     | `bye`                                   |
