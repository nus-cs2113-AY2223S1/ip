# User Guide for Duke
Duke is a software application that records a list of task. The subsequent sections below will show the instruction on how to use Duke.

## **Features**
1. Add Operation
2. List Operation
3. Delete Operation
4. Find Operation
5. Mark/Unmark Operation
6. Save Task

### Add Operation
This feature will append **3 different kind of tasks** for storage:
- Todo
- Deadline 
- Event

### List Operation
This feature will **print out all the task** that is currently in storage.

### Delete Operation
The delete feature will **remove** the task from storage.

### Find Operation
This feature will accept user input to **query** the list of task for matching task.

### Mark/Unmark Operation
When task is first added, it will be unmarked by default. This feature allows the user to **mark or unmark task** which represent whether the task is completed.

### Save Task
Whenever each task is added, deleted or edited, it will be updated in storage. Even when the program is re-ran, the **data will be saved** as long as the data file is retained.

---
## Requirement
- Java 11 or better
---
## Getting Started

1. Click on the link [here](https://github.com/wilsonngja/ip/releases) to download the latest release of Duke.
2. Open a command line which is local to duke.jar.
3. Run the command `java -jar duke.jar`.
---
## Usage
### `todo` - Add **Todo** Task
To add a new todo task, invoke with ***todo*** command followed by the ***description***. A todo task is indicated as `[T]`. For example:
```
todo study for exam.
```
#### Expected output:
```
Got it. I added to the list.
  [T][ ] study for exam
You now have 1 task(s)
```

### `deadline` - Add **Deadline** Task
To add a new deadline task, invoke with ***deadline*** command followed by the ***description***. However, <span style="color:#e65c5c">**/by must to be included**</span>. A deadline task is indicated as `[D]`. For example:
```
deadline return book /by2/10/2022
```
#### Expected output:
```
Got it. I added to the list.
  [D][ ] return book (by:Oct 2 2022)
You now have 1 task(s)
```

### `event` - Add **Event** Task
To add a new event task, invoke with ***event*** command followed by the description. However, <span style="color:#e65c5c">**/at must to be included**</span>. A event task is indicated as `[E]`.
For example:
```
event CS1231 exam /at5/10/2022
```
Expected output:
```
Got it. I added to the list.
  [E][ ] CS1231 exam (at:5/10/2022)
You now have 2 task(s)
```
### `list` - **List** all Task
To print out all the list that has been stored, use the **list** command. For example:
```
list
```
Expected output:
```
1. [D][ ] return book (by:Oct 2 2022)
2. [E][ ] CS1231 exam (at:5/10/2022)
```

### `delete` - **Delete** Task
To delete a task from the list, start with **delete** command with the task number to delete. However, note that delete can only be  <span style="color:#e65c5c">**invoked by digit (not text)**</span>. For example:
```
delete 1
```
Expected output:
```
Hehe... I've deleted the task below: 
  [D][ ] return book (by:Oct 2 2022)
You now have 1 task(s)
```

### `find` - **Find** Task
To query for task, invoke a **find** command with the **query text** to search for. For example: 
```
find exam
```
Expected output:
```
Here are the matching tasks in your list: :)
1. [E][ ] CS1231 exam (at:5/10/2022)
2. [T][ ] Study for exam
```

### `mark` - **Mark** Task
To mark a task, invoke a **mark** command, followed by the **task number** to mark. However, note that mark can only be invoked <span style="color:#e65c5c">**by digit (not text)**</span>. For example:
```
mark 1
```
Expected output:
```
1. [E][X] CS1231 exam (at:5/10/2022)
```  

### `unmark` - **Unmark** Task
To unmark a task, invoke a **unmark** command, followed by the **task number** to unmark. However, note that unmark can only be invoked <span style="color:#e65c5c">**by digit (not text)**</span>. For example:
```
unmark 1
```
Expected output:
```
1. [E][ ] CS1231 exam (at:5/10/2022)
```

---

## Errors Message

| Error Message | Reason | Fix |
| ----------- | ------------- |---- |
| Please re-enter and specify the deadline time. :/ | `/by` is not specified | Re-enter the command but with `/by` |
| Please re-enter and specify the time. :/ | `/at` is not specified  | Re-enter the command but with `/at` |
|Incorrect command entered. Please re-enter with the correct command.|command is not recognised|Use one of the code listed in the previous section.
|OOPS!!! The description of the task cannot be empty.| The description of the command is empty| Re-enter but description is enter, `separated by space` from command.|
|Please enter only digits for the parameters. :/|Non-digit parameter has been invoked for `mark`, `unmark` and `delete` operations. | Re-enter but `enter only digit` for the description.|
|The digit you entered is beyond the number of task. :/|The digit invoked for `mark`, `unmark` and `delete` operation is beyond the list.| Re-enter but the constraint the digit `within the scope` of the list. Check the list with `list` command.|
|Error writing to file.| The file has failed to write into the file. This could be because the `file does not exist` and `creation of file has failed`.| If error persist, manually create the directory from the local `jar file` with `./data/duke.txt`. 
