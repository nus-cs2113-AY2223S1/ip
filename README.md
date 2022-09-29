# Albot v0.2 User Guide
**Created by: Felicia Beatrice Budiawan :octocat:**

## Table of Contents
- About
- Features
- Commands

## About
Albot is your personal assistant Chatbot who will help you organize and keep track of your current tasks! :two_hearts:

He will also become your new best friend! Learn more about what he can do down below ~

## Features
**Here are the things Albot can do for you: :shipit:**
1. Add and delete tasks in the form of Todos, Deadlines, or Events
2. Mark a task as done or undone
3. Display a list containing all tasks
4. Find a task corresponding to substring provided

## Commands
### ```todo```
creates a ToDo and adds this to the task list

**Format:**
>```todo <description>```

**Example:**
>```todo bake cookies```

	----------------------------------------------------
	Got it! (๑˃ᴗ˂)ﻭ I've added this task:
	  [T][ ] bake cookies
	Now you have 1 task(s) in the list! 凸(￣ヘ￣)

	----------------------------------------------------

### ```deadline```
creates a Deadline and adds this to the task list

**Format:**
>```deadline <description> /by <deadline>```

**Example:**
>```deadline CS2113 iP /by Friday 30 September```

	----------------------------------------------------
	Got it! (๑˃ᴗ˂)ﻭ I've added this task:
	  [D][ ] CS2113 iP (by: Friday 30 September)
	Now you have 2 task(s) in the list! 凸(￣ヘ￣)

	----------------------------------------------------

### ```event```
creates an Event and adds this to the task list

**Format:**
>```event <description> /at <time of event>```

**Example:**
>```event dance practice /at 1 October 2pm```

	----------------------------------------------------
	Got it! (๑˃ᴗ˂)ﻭ I've added this task:
	  [E][ ] dance practice (at: 1 October 2pm)
	Now you have 3 task(s) in the list! 凸(￣ヘ￣)

	----------------------------------------------------

### ```delete```
removes a task from the task list

**Format:**
>```delete <index>```

**Example:**
>```delete 2```

	----------------------------------------------------
	Noted! (๑˃ᴗ˂)ﻭ I've removed this task:
	  [D][ ] CS2113 iP (by: Friday 30 September)
	Now you have 2 task(s) in the list! 凸(￣ヘ￣)

	----------------------------------------------------

### ```mark```
marks a task as done

**Format:**
>```mark <index>```

**Example:**
>```mark 1```

	----------------------------------------------------
	Nice! (〃＾▽＾〃) I've marked this task as done:
	  [X] bake cookies
	Well done completing your task, friend! (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧

	----------------------------------------------------

### ```unmark```
marks a task as undone

**Format:**
>```unmark <index>```

**Example:**
>```unmark 1```

	----------------------------------------------------
	OK, I've marked this task as not done yet:
	  [ ] bake cookies
	You better stop procrastinating, friend.. (╥﹏╥)

	----------------------------------------------------

### ```list```
displays the list of current tasks

**Format:**
>```list```

	----------------------------------------------------
	Here are the tasks in your list:
	1.[T][ ] bake cookies
	2.[E][ ] dance practice (at: 1 October 2pm)

	----------------------------------------------------

### ```find```
displays all the tasks containing the specified keyword(s)

**Format:**
>```find <substring>```

**Example:**
>```find cookies```

	----------------------------------------------------
	  Here are the matching tasks in your list:
	  1.[T][ ] bake cookies
	----------------------------------------------------

### ```bye```
exits the program

**Format:**
>```bye```

	----------------------------------------------------
	Aww, you're leaving already? I'm gonna miss you (╥﹏╥)
	But anyways bye! Hope to see you again soon, friend! (ɔ◔‿◔)ɔ ♥

	----------------------------------------------------

## Note from Developer
This chatbot is named after someone special to me who makes me happier and a better person every single day! :two_hearts:

Love you Albert <3
-Lish