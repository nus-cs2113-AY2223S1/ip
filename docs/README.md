# User Guide

## Quick Start

1. Copy the file "2113 iP.jar" from this repository into your folder of choice
2. Open terminal and change the directory to that folder.
3. Run the jar file by entering `java -jar "2113 iP.jar"` into the terminal.

You should be greeted by Duke as follows:
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
4. Input the commands below and press Enter to execute the command.
5. Exit Duke using the input "bye".

## Features
List of commands that Duke supports

### list
Displays all tasks in your task list.
```
list
_____________________________________________
Here are the tasks in your list:
1.[T][ ] Accounting Homework
2.[D][ ] individual project (by: Friday 2359）
3.[E][ ] Date with my girlfriend （at: next week sometime I haven't decide）
```
### todo
Adds a todo to your task list.

Format: todo `taskName`
```
todo Accounting Homework
_____________________________________________
Got it. I've added this task:
[T][ ] Accounting Homework
Now there are 1 tasks in the list.
```
### deadline
Adds a deadline to your task list.

Format: deadline  `taskName` / `taskDateTime`
```
deadline individual project /Friday 2359
_____________________________________________
Got it. I've added this task:
[D][ ] individual project (by: Friday 2359）
Now there are 2 tasks in the list.
```
### event
Adds an event to your task list

Format: event  `taskName` / `taskDateTime`
```
event Date with my girlfriend /next week sometime I haven't decide
_____________________________________________
Got it. I've added this task:
[E][ ] Date with my girlfriend （at: next week sometime I haven't decide）
Now there are 3 tasks in the list.
```

### mark
Mark a task in your list as done.

Format: mark `taskNumber`
```
mark 1
_____________________________________________
Nice! I've marked this task as done:
[T][X] Accounting Homework
```
### unmark
Mark a task in your list as not done.

Format: mark `taskNumber`
```
unmark 2
_____________________________________________
OK, I've marked this task as not done yet:
[D][ ] individual project (by: Friday 2359）
```
### delete
Delete a task from your task list.

Format: delete `taskNumber`
```
delete 1
_____________________________________________
Noted. I've removed this task:
[T][X] Accounting Homework
```
### find
Find relevant tasks based on a keyword.

Format: find `keyword`
```
find girlfriend
_____________________________________________
Here are the matching tasks in your list:
1.[E][ ] Date with my girlfriend （at: next week sometime I haven't decide）
```
### bye
Exits the application.
```
bye
_____________________________________________

Bye! See you :)
```
