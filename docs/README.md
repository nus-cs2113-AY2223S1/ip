# User Guide

Duke is a Command Line Application for managing day-to-day tasks.  
The task list will be automatically saved to local storage upon any command that updates the task list.  
The task list will be stored in {current working directory}/data/duke.txt with rows of specially formatted strings for each task.  
Do not edit the text file manually or it may corrupt your save file.

## Set-up  
1. Ensure you have minimally Java 11 installed on your computer
   1. Open command prompt
   2. Run command `java --version`
   3. If your Java version is outdated or Java is not installed, please follow the link [here](https://www.oracle.com/java/technologies/downloads/) to install Java 11 or later
2. Download the latest jar file from [here]() and save into your preferred working directory
3. Open the command prompt and navigate to your preferred working directory
4. Run the command `java -jar ip.jar` to start the application
5. If set-up is successful, you should see the following welcome message in the terminal:

```
____________________________________________________________
Hello! I'm Duke!
What can I do for you?
____________________________________________________________
```

## Features 

### Notes about the command format:
1. The commands are case-sensitive. Only lower case commands will be recognised.
2. Words in `UPPER_CASE` are parameters to be input by user.
3. Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.
4. `DATE` parameter has no format restriction

### Listing tasks: `list` 

Outputs all tasks in your task list to the terminal.  

Format: `list`

Examples:

Input: `list`

Expected Output:
```
Here are the tasks in your list:
1.[T][ ] CS2113 individual project
2.[T][ ] study for CS2106 midterms
____________________________________________________________
```

### Adding a todo: `todo`

Adds a todo to task list.  

Format: `todo DESCRIPTION`  

Examples:  
  
Input: `todo CS2113 individual project`  

Expected Output:  
```
Got it. I've added this task:
[T][ ] CS2113 individual project
You now have 1 tasks
____________________________________________________________
```

### Adding an event: `event`

Adds an event to task list.  

Format: `event DESCRIPTION /at DATE`

Examples:

Input: `event CS2113 finals /at 29-Nov-2022 5:00 PM`

Expected Output:
```
Got it. I've added this task:
[E][ ] CS2113 finals (at: 29-Nov-2022 5:00 PM)
You now have 2 tasks
____________________________________________________________
```

### Adding a deadline: `deadline`

Adds a deadline to task list.  

Format: `deadline DESCRIPTION /at DATE`

Examples:

Input: `deadline CS2113 individual project /by 30-Sep-2022 11:59 PM`

Expected Output:
```
Got it. I've added this task:
[D][ ] CS2113 individual project (by: 30-Sep-2022 11:59 PM)
You now have 3 tasks
____________________________________________________________
```

### Deleting a task: `delete`

Deletes a task from task list.  

Format: `delete TASK_INDEX`

Examples:

Input: `delete 1`

Expected Output:
```
Nice! I've deleted this task:
[T][ ] CS2113 individual project
You now have 2 tasks
____________________________________________________________
```

### Marking a task: `mark`

Marks a task in list as done  

Format: `delete TASK_INDEX`

Examples:

Input: `mark 1`

Expected Output:
```
Nice! I've marked this task:
[E][X] CS2113 finals (at: 29-Nov-2022 5:00 PM)
____________________________________________________________
```

### Unmarking a task: `unmark`

Marks a task in list as not done

Format: `unmark TASK_INDEX`

Examples:

Input: `mark 1`

Expected Output:
```
Nice! I've unmarked this task:
[E][ ] CS2113 finals (at: 29-Nov-2022 5:00 PM)
____________________________________________________________
```

### Finding tasks by description: `find`

Find tasks based on a single input key phrase matching a substring of its description.

Format: `find KEY_PHRASE`

Examples:

Input: `find CS2113`

Expected Output:
```
Here are the search results for tasks with description containing CS2113
[E][ ] CS2113 finals (at: 29-Nov-2022 5:00 PM)
[D][ ] CS2113 individual project (by: 30-Sep-2022 11:59 PM)
____________________________________________________________
```

### Exiting the application: `bye`

Exits the application.

Format: `bye`

Examples:

Input: `bye`

Expected Output:
```
Bye. Hope to see you again soon!
____________________________________________________________
```