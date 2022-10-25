# Duke User Guide
Welcome to Duke, the leading Command-line Interface to keep track of your tasks!!

## Quick Start
1. Download `Java 11` in your Computer
2. Download the latest `ip.jar` from https://github.com/paullowse/ip/releases
3. Copy the file to a folder that will be the root folder for Duke. 
4. Start the terminal app on your computer
5. Type the command java -jar and drag the jar file into the terminal window
6. Run the command to enter the Duke App! 


## Features 
Duke has many comprehensive features as summarized below!
 
- Adding a Task to TaskList (todo, deadline or event)
- List all Tasks in TaskList
- Delete any Task
- Mark and unmark Tasks in TaskList
- Search for Tasks
- Exit the application
- All edits are saved in the duke.txt file in a data folder!

## Commands

Duke runs on CLI commands, and the command instructions will be listed below. Users should follow this format, 
replacing the square bracket segments (eg. **[description], [deadline], [place], [index]**) with the relevant information. 

### 1. `Todo ` - Add a Todo  
Description: Adds a Todo task to the TasksList

Format: `todo [description]`

Examples: 
- `todo code project`
- `todo run`

Expected outcome:
```$xslt
----------------------------------------------------
Got it. I've added this task: 
  [T][ ] code project
Now you have 1 task in the list

----------------------------------------------------
```
### 2. `Deadline ` - Add a Deadline  
Description: Adds a Deadline task to the TasksList

Format: `deadline [description] /by [deadline]`

Examples: 
- `deadline code project /by September 1st`
- `deadline finish Duke /by tomorrow`

Expected outcome:
```$xslt
----------------------------------------------------
Got it. I've added this task: 
  [D][ ] code project (by: September 1st)
Now you have 2 tasks in the list

----------------------------------------------------
```


### 3. `Event` - Add an Event  
Description: Adds a Event task to the TasksList

Format: `event [description] /by [deadline]`

Examples: 
- `event project meeting /at September 1st`
- `event swe class /at NUS`

Expected outcome:
```$xslt
----------------------------------------------------
Got it. I've added this task: 
  [E][ ] project meeting (at: September 1st)
Now you have 3 tasks in the list

----------------------------------------------------
```

### 4. `list` - List all Tasks  
Description: Lists all tasks in TaskList

Format: `list`

Expected outcome:
```$xslt
----------------------------------------------------
Here are the tasks in your list:
1. [T][ ] code project
2. [D][ ] code project (by: September 1st)
3. [E][ ] project meeting (at: September 1st)

----------------------------------------------------
```

### 5. `delete` - Delete a Task  
Description: Delete the task in index of TaskList

Format: `delete [index]`

Examples: 
- `delete 1`
- `delete 2`

Expected outcome:
```$xslt
----------------------------------------------------
Noted. I've removed this task: 
  [T][ ] code project
Now you have 2 tasks in the list

----------------------------------------------------
```

### 6. `mark` - Mark a Task as completed  
Description: Mark the task in index of TasksList as completed

Format: `mark [index]`

Examples: 
- `mark 1`
- `mark 2`

Expected outcome:
```$xslt
----------------------------------------------------
Nice! I've marked this task as done: 
  [D][X] code project (by: September 1st)

----------------------------------------------------
```

### 7. `unmark` - Unmark a Task to regard it as uncompleted  
Description: Unmark the task to show it as uncompleted

Format: `unmark [index]`

Examples: 
- `unmark 1`
- `unmark 2`

Expected outcome:
```$xslt
----------------------------------------------------
Oh no :( I've marked it as uncompleted: 
  [D][ ] code project (by: September 1st)

----------------------------------------------------
```

### 8. `find` - List all tasks that match the searchPhrase  
Description: find all tasks that contain a phrase matching the search phrase

Format: `find [searchPhrase]`

Examples: 
- `find code`
- `find project`

Expected outcome:
```$xslt
----------------------------------------------------
Here are the matching tasks in your list:
1. [D][ ] code project (by: September 1st)

----------------------------------------------------
```

### 9. `bye` - End the Duke App  
Description: Exit the App

Format: `bye`

Expected outcome:
```$xslt
----------------------------------------------------
Bye good friend! Hope to see you again soon!

----------------------------------------------------
```
