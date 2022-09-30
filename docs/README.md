# User Guide

## Welcome to Duke. How to get started?
1. Copy the latest release jar file available on this repository into your desired folder
2. Run terminal and change directory to the folder containing the jar file
3. Run the jar file by keying in `java -jar "ip.jar"`
## Duke Walkthrough
1. After running the jar file, you will be greeted with the following:
```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

------------------------------------
Hello! I'm Duke
What can I do for you?
------------------------------------
```
2. Key in desired commands and press enter to execute each command.
3. To exit Duke, simply key in "bye".
## Features
List of commands supported by Duke:
### list
To display all the tasks stored in task array

Expected Format: list
```
list
------------------------------------
Here are the tasks in your list:
	[T][X] todo read book
	[D][ ] return book (by: June 6th)
	[E][X] project meeting (at: Aug 6th 2-4pm)
------------------------------------
```

### todo
To create a todo

Expected Format: todo `taskname`
```
todo read manga
------------------------------------
Got it. I've added this task:
	[T][ ] todo read manga
Now you have 4 tasks in the list.
------------------------------------
```
### event
To create an event

Expected Format: event `taskname` /at `dateAndTime`
```
event dance competiton /at 23 june 2pm
------------------------------------
Got it. I've added this task:
	[E][ ] dance competiton (at: 23 june 2pm)
Now you have 5 tasks in the list.
------------------------------------
```
### deadline
To create a deadline

Expected Format: deadline `taskname` /by `dateAndTime`
```
deadline cs2113 ip /by 30 sept 2359
------------------------------------
Got it. I've added this task:
	[D][ ] cs2113 ip (by: 30 sept 2359)
Now you have 6 tasks in the list.
------------------------------------
```
### mark 
To Mark a task as done

Expected Format: mark `taskNumber`
```
mark 2
Nice! I've marked this task as done:
	[D][X] return book (by: June 6th)
------------------------------------
```

### unmark
To mark a task as not done

Expected Format: unmark `taskNumber`
```
unmark 3
OK, I've marked this task as not done yet:
	[E][ ] project meeting (at: Aug 6th 2-4pm)
------------------------------------
```
### delete
To delete a task from task array

Expected Format: delete `taskNumber`
```
delete 6
------------------------------------
Noted. I have removed this task:
	[D][ ] cs2113 ip (by: 30 sept 2359)
Now you have 5 tasks in the list.
```
### find
Find relevant tasks based on a keyword entered by user

Expected Format: find `keyword`
```
find book
------------------------------------
Here are the matching tasks in your list:
	[T][X] todo read book
	[D][X] return book (by: June 6th)
```

### bye
To exit the Duke application

Expected Format: bye
```
bye
------------------------------------
Bye. Hope to see you again soon!
------------------------------------
```
