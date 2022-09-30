# Duke (User Guide)

Duke is an Application for managing day-to-day tasks.  
The task list will be automatically saved to local storage upon any command that updates the task list.  


## Set-up  
1. Download the latest jar file and save into your preferred working directory
2. Open the command prompt and cd into the directory with the jar file.
3. Run `java -jar ip.jar` 
4. If successful, you will be welcomed with the following message in the terminal:

```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

Hello! I'm Duke 
What can I do for you? 
```

## Features 
1. [Add a todo task: `todo`](#add-a-todo-task-todo)
2. [Add a deadline task: `deadline`](#add-a-deadline-task-deadline)
3. [Add an event task: `event`](#add-an-event-task-event)
4. [List all tasks: `list`](#list-all-tasks-list)
5. [Mark task as completed: `mark`](#mark-task-as-completed-mark)
6. [Mark task as not completed: `unmark`](#mark-task-as-not-completed-unmark)
7. [Delete task: `delete`](#delete-task-delete)
8. [Find tasks: `find`](#find-tasks-find)
9. [Exit app: `bye`](#exit-app-bye)

### Important details to note
1. The commands are case-sensitive. Only lower case commands will be recognised.
2. Additional parameters will be ignored for commands which can only accept a certain number of parameters


### Listing tasks: `list` 
Displays all tasks currently in the list  

Format: `list`

Examples:

Input: `list`

Expected Output:
```
Here are the tasks in your list:
1.[T][ ] study hard for CS2113
2.[T][ ] study harder for CG2027 finals
3.[T][ ] work hard for TP!
____________________________________________________________
```

### Adding a todo: `todo`
Adds a todo to task list.  

Format: `todo DESCRIPTION`  

Sample:  
  
Input: `todo CS2113 team project with my groupmates!`  

Expected Output:  
```
Got it. I've added this task;
[T][ ] CS2113 team project with my groupmates!
Now you have 2 tasks in the list
____________________________________________________________
```


### Adding a deadline: `deadline`

Adds a deadline to task list.  

Format: `deadline DESCRIPTION /by DATE`

Sample:

Input: `deadline Get an internship /by 1-Oct-2022 11:59 PM`

Expected Output:
```
Got it. I've added this task:
[D][ ] Get an internship (by: 1-Oct-2022 11:59 PM)
You now have 4 tasks
____________________________________________________________
```


### Adding an event: `event`

Adds an event to task list.  

Format: `event DESCRIPTION /at DATE`

Sample:

Input: `event CS2106 finals /at 29-Jan-2023 7:00 PM`

Expected Output:
```
Got it. I've added this task:
[E][ ] CS2106 finals (at: 29-Jan-2023 7:00 PM)
You now have 3 tasks
____________________________________________________________
```

### Deleting a task: `delete`

Deletes a task from task list.  

Format: `delete TASK_INDEX`

Sample:

Input: `delete 4`

Expected Output:
```
Noted. I've removed this task:
[D][ ] Get an internship (by: 1-Oct-2022 11:59 PM)
Now you have 3 tasks in the list.
____________________________________________________________
```

### Marking a task: `mark`

Marks a task in list as done  

Format: `mark TASK_INDEX`

Sample:

Input: `mark 1`

Expected Output:
```
Nice! I've marked this task as done:
[T][X] dinner
____________________________________________________________
```

### Unmarking a task: `unmark`

Marks a task in list as not done

Format: `unmark TASK_INDEX`

Sample:

Input: `unmark 1'

Expected Output:
```
OK, I've marked this task as not done yet:
[T][ ] dinner
____________________________________________________________
```

### Finding tasks by description: `find`

Find tasks with relevant matching keyword in their description

Format: `find KEY_WORD'

Sample:

Input: `find finals`

Expected Output:
```
Here are the matching tasks in your list:
1. [E][ ] CS2106 finals  (at: 29-Jan-2023 7:00 PM)
2. [E][ ] EG2501 finals  (at: 20-Feb-2023 7:00 PM)
____________________________________________________________
```

### Exiting the application: `bye`

Closes and exits.

Format: `bye`

Sample:

Input: `bye`

Expected Output:
```
Bye. Hope to see you again soon!
____________________________________________________________
```
