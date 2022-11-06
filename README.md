# User Guide

Duke is a Personal Assistant Chatbot that helps a person to keep track of various of things via a Command Line Interface.

You will use this as a baseline for the individual project, as well as the team project later in the semester.
## Contents
- Quick Start
- Features
  - Viewing all tasks: `list`
  - Add a Todo task: `todo`
  - Add a Deadline task: `deadline`
  - Add a event task: `event`
  - Mark a task as done: `mark`
  - Unmark a task: `unmark`
  - Deleting a task: `delete`
  - Finding tasks: `find`
  - Exit the programme: `bye`
  - Saving the data
  - Editing the data file
  - Changing the save location.
  
## Quick Start

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Download the latest ip.jar from the Releases. 
2. Copy the jar file to the folder you prefer to use as the home folder for Duke.
3. Double-click the file or run the command `java -jar ip.jar` to run the programme.
4. Type the command into the interface and press Enter to execute it.
   Some example commands you can try:
   - `list`: List all tasks
   - `deadline ip submission /by 2022-05-21 1800`: Add a deadline ip  submission to task list.
   - `mark 1`: Mark ip submission as done.
   - `bye`: Exits the app.
5. Refer to the Features below for details of each command.

## Features
Notes about the command format:<br>
Words `in UPPER_CASE` are the parameters to be supplied by the user.

### Viewing all tasks: `list`
Viewing all tasks in duke
Format: `list`

Example: `list`

### Add a Todo task: `todo`
Add a Todo task to duke.
Format: `todo DESCRIPTION`
Things to note:
- DESCRIPTION cannot be empty

Example:`todo shopping`

### Add a Deadline task: `deadline`
Add a Deadline task to duke.
Format: `deadline DESCRIPTION /by YYYY-MM-DD HHMM`
Things to note:
- DESCRIPTION cannot be empty
- Spaces around `/by`
- Dates and times cannot be empty and must in the format of YYYY-MM-DD HHMM

Example:`deadline ip submission /by 2022-05-21 1800`

### Add a Event task: `event`
Add an Event task to duke.
Format: `event DESCRIPTION /at YYYY-MM-DD HHMM`
Things to note:
- DESCRIPTION cannot be empty
- Spaces around `/at`
- Dates and times cannot be empty and must in the format of YYYY-MM-DD HHMM

Example: `event cs2113 final /at 2022-11-01 1700`

### Mark a task as done: `mark`
Mark task as done based on its index.
Format: `mark INDEX`
Things to note:
- INDEX cannot be empty.
- INDEX cannot be out of range.

Example: `mark 2`

### Unmark a task: `unmark`
Unmark task identified by its index.
Format: `unmark INDEX`
Things to note:
- INDEX cannot be empty.
- INDEX cannot be out of range.

Example: `unmark 2`

### Deleting a task: `delete`
Delete a task from duke based on the index of the task.
Format: `delete INDEX`
Things to note:
- INDEX cannot be empty.
- INDEX cannot be out of range.

Example: delete 2

### Finding tasks: `find`
Find tasks based on keyword
Format: `find KEYWORD`
Things to note:
- KEYWORD is case-sensitive.

Example: `find ip`

### Exiting the programme: `bye`
Exits the programme.
Format: `bye`

Example: `bye`

### Saving the data
Data from duke are saved in the hard disk automatically after executing the command that alters the data.

### Editing the data file
Duke data are stored save as txt file `[JAR file location]/data/duke.txt`. User can update data directly by editing that txt file.

### Changing the save location.
User can change the location of the save data by changing the string `DUKE_DATA_PATH` in Duke.java.
