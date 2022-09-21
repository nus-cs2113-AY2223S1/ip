# User Guide

Welcome to the Jackson bot user guide! Jackson bot is a task manager which you can interact with using various commands. Below are the contents on how to use the program. Please read them carefully and enjoy using the program!

## Contents
- How to use
- Jackson bot features
   - View all tasks: `list`
   - Add a Todo task: `todo`
   - Add a Deadline task: `deadline`
   - Add a Event task: `event`
   - Mark a task: `mark`
   - Unmark a task: `unmark`
   - Delete a task: `delete`
   - Finding tasks: `find`
   - Exiting the Duke bot: `exit`
   - Uploading data
   - Saving data
   
## How to use
1. Check that Java `11` or above installed on your Computer.
2. Download the latest version of `Jackson.jar` from the `Releases` tab on the right of the repo.
3. Move `Jackson.jar` to your preferred folder. This will be the home folder of your Duke bot program.
4. Double-click the jar file to run the program. An interface will be similiar to what you see below:
![jacksonbot](https://user-images.githubusercontent.com/47519031/191457307-6973f457-a625-460a-b7b6-816350eee72b.png)
5. Type your desired command into the interface and press Enter to execute it. 
6. Refer to the `Jackson bot features` section below to get the details for specific commands.

## Jackson bot features

### View all tasks: `list`
View all the tasks you currently have in your task list
Command: `list` 

### Add a Todo task: `todo`
Adds a todo task to the list
Command format: `todo <description>`
- Things to note:
   - Task description cannot be empty
e.g. `todo Go for CS2113 lecture`
   
### Add a Deadline task: `deadline`
Adds a task with deadline to the list
Command format: `deadline <description> /by <time>`
- Things to note:
   - Task description cannot be empty
   - Date and time must both be provided
      - Date format: `YYYY-MM-DD`
      - Time format: `HH:mm`
e.g. `deadline Go for CS2113 lecture /by 2022-09-23 16:00

### Add a Event task: `event`
Adds a event with timing to the list
Command format: `event <description> /at <time>`
- Things to note:
   - Task description cannot be empty
   - Date and time must both be provided
      - Date format: `YYYY-MM-DD`
      - Time format: `HH:mm`
e.g. `event CS2113 lecture /at 2022-09-23 16:00
      
###Mark a task: `mark`
Marks the selected task in the list as complete
Command format: mark <index>
- Things to note:
   - Task index must be in the list 
   - index refers to the task ranking on the list
e.g. `mark 1`

### Unmark a task: `unmark`
Unmarks the selected task in the list as incomplete
Command format: unmark <index>
- Things to note:
   - Task index must be in the list 
   - index refers to the task ranking on the list
e.g. `unmark 1`

### Delete a task: `delete`
Deletes the selected task from the list
Command format: `delete <index>`
- Things to note:
   - Task index must be in the list 
   - index refers to the task ranking on the list
e.g. `delete 1`

### Finding tasks: `find`
Find all the tasks in the list that includes the given keyword. The respective indexes of the tasks are also included.
Command format: `find <keyword>`
- Things to note:
   - Keyword must not be empty
e.g. `find CS2113`

### Exiting the Jackson bot: `exit`
Exits the Jackson bot program
Command format: `exit`

### Uploading data
When booting up the program, Jackson will check the current file directory for the `data.txt` file to load exisitng tasks to the list. If no such file is present Jackson will create a new file.
Note: If you want to add in your own data directly into the `data.txt` file, ensure that you follow the task format in the data file. If not, Jackson will not be able to understand the task and crash.

### Saving data
Jackson will automatically save and update tasks in the `data.txt` file as commands are entered. Hence no manual saving of data is needed. 
