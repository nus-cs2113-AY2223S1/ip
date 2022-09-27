# Duke (User Guide)

Duke is a CLI task manager app.

## Setting up

1. Download Duke.jar
2. Open a terminal and cd into the folder that contains Duke.jar
3. Run "java -jar Duke.jar"

If successful, you should be greeted with something like this:

   ```
____________________________________________________________
 __      __                       
/  \    /  \ ____   ____    ____  
\   \/\/   // __ \ /    \  / ___\ 
 \        /\  ___/|   |  \/ /_/  >
  \__/\  /  \___  >___|  /\___  / 
       \/       \/     \//_____/  
 Hello! I'm Weng!
 What can I do for ya?
____________________________________________________________
   ```

While the program has been introduced as Duke by default, I have personalised it and named it Weng.
(Do not be confused. All files/folders will be named as Duke for the purpose of this project.)

## Features

#### Add tasks to task list (todo, deadline, event)
#### Delete tasks from task list
#### Mark tasks as completed/not completed
#### Find tasks in task list
#### Save/load tasks from text file

## Usage

Here are the available commands and descriptions:

### list
Displays all tasks that are currently in the list
```
list
1.[T][X] Eat supper
2.[T][ ] hw
3.[T][ ] lol
4.[D][ ] do hw (by: sunday)
5.[E][ ] breakfast at home (at: tomorrow)
You have 5 task(s) in the list
____________________________________________________________
```

### todo (taskName)
Adds a todo task to the list
```
todo lol
Got it, I've added the task below.
[T][ ] lol
You have 3 task(s) in the list.
____________________________________________________________
```

### deadline (taskName) /by (taskDateTime)
Adds a deadline task to the list (taskDateTime can be in any format ie. "today 5pm" or "1 Jan 2023")
```
deadline do hw /by sunday
Got it, I've added the task below.
[D][ ] do hw (by: sunday)
You have 4 task(s) in the list.
____________________________________________________________
```

### event (taskName) /at (taskDateTime)
Adds an event task to the list (taskDateTime can be in any format ie. "today 5-7pm" or "1 Jan 2023")
```
event breakfast at home /at tomorrow
Got it, I've added the task below.
[E][ ] breakfast at home (at: tomorrow)
You have 5 task(s) in the list.
____________________________________________________________
```

### mark (taskID)
Marks the corresponding task as completed
```
mark 2
Nice! I've marked this task as done:
[T][X] hw
____________________________________________________________
```

### unmark (taskID)
Marks the corresponding task as not completed
```
unmark 2
Nice! I've marked this task as undone:
[T][ ] hw
____________________________________________________________
```

### delete (taskID)
Removes the corresponding task from the list
```
delete 1
Noted. I've removed this task:
[T][ ] hw
____________________________________________________________
```

### find (keyword)
Displays all tasks that have the keyword in their task names.
```
find hw
2.[D][ ] do hw (by: sunday)
4.[T][ ] check hw
5.[T][ ] submit hw
There are 3 match(es) in the list
____________________________________________________________

```

### bye
Exits the task manager app, and all information is stored in "Desktop/tasks.txt"
```
bye
____________________________________________________________
Bye!
____________________________________________________________
____________________________________________________________

```
