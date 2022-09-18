---
layout: page
title: User Guide for Project Duke
---

## <span style="color: #0451a3;">Table of Contents</span>
* [Introduction](https://bdthanh.github.io/ip/#introduction)
* [Quick start](https://bdthanh.github.io/ip/#quick-start)
* [Features and Usages](https://bdthanh.github.io/ip/#features-and-usage)

  - [Add a new todo ```todo```](https://bdthanh.github.io/ip/#todo---add-a-todo-task)
    
  - [Add a new deadline ```deadline```](https://bdthanh.github.io/ip/#deadline---add-a-deadline)

  - [Add a new event ```event```](https://bdthanh.github.io/ip/#event---add-an-event)

  - [List tasks ```list```](https://bdthanh.github.io/ip/#list---list-all-tasks)

  - [Mark as done ```mark```](https://bdthanh.github.io/ip/#mark---mark-as-done)

  - [Mark as undone ```unmark```](https://bdthanh.github.io/ip/#unmark---mark-as-undone)
 
  - [Delete task ```delete```](https://bdthanh.github.io/ip/#delete---delete-a-task)

  - [Find tasks by keyword ```find```](https://bdthanh.github.io/ip/#find---find-tasks-by-keyword)

  - [Exit Duke ```bye```](https://bdthanh.github.io/ip/#bye---exit-duke)


## <span style="color: #0451a3;">Introduction</span>
Duke is a **Command Line Interface (CLI) Personal Assistant Chatbot** that helps a user to manage daily tasks.
Currently, Duke supports 3 types of task:

- _Todo_
- _Deadline_ 
- _Event_

## <span style="color: #0451a3;">Quick start</span>

1. Install Java 11 (Oracle version is recommended) on your computer.
2. Download the latest release of ```Duke.jar``` [here](https://github.com/bdthanh/ip/releases/tag/A-Release).
3. Move the file to the desired folder for Duke.
4. Open the Command Prompt (for Windows) or Terminal (for Unix) and redirect to home folder of Duke.
5. Launch the chatbot Duke by using ```java -jar Duke.jar```.

   You are supposed to see a greeting message like this if the setup is correct: 
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Hello! I'm Banana
How can I help you?
  ____                                 
 |  _ \                                
 | |_) | __ _ _ __   __ _ _ __   __ _  
 |  _ < / _` | '_ \ / _` | '_ \ / _` | 
 | |_) | (_| | | | | (_| | | | | (_| | 
 |____/ \__,_|_| |_|\__,_|_| |_|\__,_| 
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```
6. Type the valid command and press Enter to run the command. See [Features and Usage](https://bdthanh.github.io/ip/#features-and-usage) for more information of commands.

## <span style="color: #003A79;">Features and Usage</span>

### ```todo``` - <span style="color: #F5821F;">Add a todo task</span>
>Adds a new todo task to the list

Format: ```todo DESCRIPTION```

Example of usage: ```todo reading book```

Expected outcome:
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Got it, I added this task to your list:
----->  [T][ ] reading book
Now you have 1 task(s) in the list
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

<br>

### ```deadline``` - <span style="color: #F5821F;">Add a deadline</span>
>Adds a new deadline to the list

Format: ```deadline DESCRIPTION /by yyyy-mm-dd```

Example of usage: ```deadline Increment Level-9 /by 2022-09-30```

Expected outcome:
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Got it, I added this task to your list:
----->  [D][ ] Increment Level-9 (due: Fri, Sep 30 2022)
Now you have 2 task(s) in the list
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

<br>

### ```event``` - <span style="color: #F5821F;">Add an event</span>
>Adds a new event to the list

Format: ```event DESCRIPTION /at yyyy-mm-dd```

Example of usage: ```event EE2211 Midterm /at 2022-09-20```

Expected outcome:
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Got it, I added this task to your list:
----->  [E][ ] EE2211 Midterm (at: Tue, Sep 20 2022)
Now you have 3 task(s) in the list
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

<br>

### ```list``` - <spn style="color: #F5821F;">List all tasks</span>
>Print all tasks information in the current list

Format: ```list```

Expected outcome:
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Here are 3 task(s) in your list:
   1. [T][ ] reading book
   2. [D][ ] Increment Level-9 (due: Fri, Sep 30 2022)
   3. [E][ ] EE2211 Midterm (at: Tue, Sep 20 2022)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

<br>

### ```mark``` - <span style="color: #F5821F;">Mark as done</span>
>Mark a task as done given its index number

Format: ```mark INDEX_NUMBER```

Example of usage: ```mark 2```

Expected outcome:
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Congratulations! You have done this task:
----->  [D][X] Increment Level-9 (due: Fri, Sep 30 2022)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

<br>

### ```unmark``` - <span style="color: #F5821F;">Mark as undone</span>
>Mark a task as undone given its index number

Format: ```unmark INDEX_NUMBER```

Example of usage: ```unmark 2```

Expected outcome:
```
OK, I've marked this task as not done yet:
----->  [D][ ] Increment Level-9 (due: Fri, Sep 30 2022)
```

<br>

### ```delete``` - <span style="color: #F5821F;">Delete a task</span>
>Delete a task in the list given its index number

Format: ```delete INDEX_NUMBER```

Example of usage: ```delete 1```

Expected outcome:
```
Noted. I've removed this task:
----->  [T][ ] reading book
Now you have 2 task(s) in the list
```

<br>

### ```find``` - <span style="color: #F5821F;">Find tasks by keyword</span>
>Find all tasks matched with a given keyword

Format: ```find KEYWORD```

Example of usage: ```find EE2211```

Expected outcome:
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Here are 1 matching task(s) in your list:
   1. [E][ ] EE2211 Midterm (at: Tue, Sep 20 2022)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

<br>

### ```bye``` - <span style="color: #F5821F;">Exit Duke</span>
>Exit Duke with a goodbye message

Format: ```bye```

Expected outcome:
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Good bye. Hope to see you again soon!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```