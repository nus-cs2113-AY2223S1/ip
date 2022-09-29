# User Guide
Tommy is a task tracking application, run using Command Line Interface (CLI)

## System Requirements

This application was built on Java 11. Ensure that you have Java 11 or above installed.

## Quick Start

1. Download duke.jar file from this repository.
2. Copy the file into a folder. The task list will be saved into this folder.
3. Open terminal application and change directory to the above folder.
4. Run the jar file by entering `java -jar duke.jar` into the terminal.

You should be greeted with the following output:
```
____________________________________________________________
 Hello! I'm
 _____ ____  _______  _______  __ __  
  | | |   | || || || || || ||  |_ _|  
  | | |   | || || || || || ||   | |  
  |_| |___| || || || || || ||   | |  

 What can I do for you?
____________________________________________________________
```
5. Input one of the supported commands, and press the "Enter" key to execute.
6. Exit the application safely with the "bye" command.

## Features
The following commands are supported by Tommy.

### list
Displays all tasks currently in your task list.
```
list
____________________________________________________________

Here are the tasks in your list:
1.[T][ ] Read book
2.[D][ ] Return book  (by: June 6th)
3.[E][ ] Project meeting  (at: Monday 2-4pm)
____________________________________________________________
```
### todo
Adds a todo task to your task list.

Format: todo `taskName`
```
todo Read book
____________________________________________________________

 Got it. I've added this task:
	[T][ ] Read book
 Now you have 1 tasks in the list.
____________________________________________________________
```
### deadline
Adds a deadline task to your task list.

Format: deadline  `taskName` /by `taskDateTime`
```
deadline Return book /by June 6th
____________________________________________________________

 Got it. I've added this task:
	[D][ ] Return book  (by: June 6th)
 Now you have 2 tasks in the list.
____________________________________________________________
```
### event
Adds an event task to your task list

Format: event  `taskName` /at `taskDateTime`
```
event Project meeting /at Monday 2-4pm
____________________________________________________________

 Got it. I've added this task:
	[E][ ] Project meeting  (at: Monday 2-4pm)
 Now you have 3 tasks in the list.
____________________________________________________________
```

### mark
Mark a task in your list as done. 

Format: mark `index`
```
mark 1
____________________________________________________________

 Nice! I've marked this task as done:
	[T][X] Read book
____________________________________________________________
```
### unmark
Mark a task in your list as not done.

Format: mark `index`
```
unmark 1
____________________________________________________________

 OK, I've marked this task as not done yet:
	[T][ ] Read book
____________________________________________________________
```
### find
Find all tasks in your task list with the specified keyword in their names.

Format: find `keyword`
```
find book
____________________________________________________________

Here are the matching tasks in your list:
1.[T][ ] Read book
2.[D][ ] Return book  (by: June 6th)
____________________________________________________________
```
### delete
Delete a task from your task list.

Format: delete `index`
```
delete 3
____________________________________________________________

Noted. I've removed this task:
	[E][ ] Project meeting  (at: Monday 2-4pm)
 Now you have 2 tasks in the list.
____________________________________________________________
```
### bye
Exits the application.
```
bye
____________________________________________________________

 Bye. Hope to see you again soon!

____________________________________________________________
```
