# User Guide for Duke

Duke is a software application that can store, load and display a list of tasks dynamically. The following sections will explain its features and demonstrate on how to use it.

---
## Feature Overview

1. Add Operation
2. List Operation
3. Find Operation
4. Mark/Unmark Operation
5. Delete Operation
6. Active Task Save

--- 
## Add Operation
This feature simply adds a task to the record list. There are three different types of tasks as shown below:
1. Todo
2. Deadline
3. Event

To add a task, invoke the following commands followed by the required task descriptions as shown in the following sub-sections.  
<br/>


### <span style = "background-color: #444444">todo</span> - Add a Todo Task
Format:`todo Task_Description`
<br>Example:
```
todo read book
```
Expected Output:
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 task in the list.
```
Note: A todo task is indicated by `[T]` as shown above

<br/>



### <span style = "background-color: #444444">deadline</span> - Add a Deadline Task
Format:`deadline Task_Description /by Date Time(if any)`
- Date Format: `dd/mm/yyyy`
- Time Format (24-hour): `0000` to `2359`
- Date must be provided.
- Time is optional.

Example 1: Date Only
```
deadline return book /by 12/8/2022
```
Expected Output 1:
```
Got it. I've added this task:
  [D][ ] return book (by: Aug 12 2022)
Now you have 1 task in the list.
```
Example 2: Date and Time
```
deadline return book /by 12/8/2022 1805
```
Expected Output 2:
```
Got it. I've added this task:
  [D][ ] return book (by: Aug 12 2022, 6.05pm)
Now you have 1 task in the list.
```
Note: A deadline task is indicated by `[D]` as shown above

<br/>

### <span style = "background-color: #444444">event</span> - Add a Event Task
Format:`event Task_Description /at Date StartTime-EndTime`
- Date Format: `dd/mm/yyyy`
- Time Format (24-hour): `0000` to `2359`;
- StartTime must be before EndTime
- Date, StartTime and EndTime must be provided
- Constraint: Period of event must be within same day

Example:
```
event christmas party /at 25/12/2022 1105-1930
```
Expected Output:
```
Got it. I've added this task:
  [E][ ] christmas party (at: Dec 25 2022, 11.05am-7.30pm)
Now you have 1 task in the list.
```
Note: A event task is indicated by `[E]` as shown above

---

## List Operation
This feature simply prints all the tasks found in the record list. If the list is empty, an empty list message will be printed instead.
### <span style = "background-color: #444444">list</span> - List all Task
Format:`list`
<br>Example:
```
list
```
Expected Output 1: Empty List
```
There is no task in your list.
```
Expected Output 2: List All Tasks
```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Aug 12 2022)
3.[D][ ] buy book (by: Aug 12 2022, 6.05pm)
4.[E][ ] christmas party (at: Dec 25 2022, 11.05am-7.30pm)
5.[E][ ] final test (at: Aug 12 2022, 8am-10am)
```
Note: Output 2 will be used to demonstrate the find operation in the next section below

---

## Find Operation
This feature simply query the record list for matching task based on the type of find command. There are two variations to the find operation as shown below:
- Find By Name
- Find By Date

Note: Demonstration of Find Operation is relative to Output 2 of List Operation

<br/>

### <span style = "background-color: #444444">find</span> - Find Task By Name
Format:`find keyword`
- Provide a keyword to be matched

Example 1:
```
find lunch
```
Expected Output 1: No Matching Task Found
```
OOPS!!! No matching task is found in your list.
```
Example 2:
```
find book
```
Expected Output 2: Matching Tasks Found
```
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Aug 12 2022)
3.[D][ ] buy book (by: Aug 12 2022, 6.05pm)
```

<br/>

### <span style = "background-color: #444444">findd</span> - Find Task By Date (Deadline/Event Only)
Format:`findd Date`
- Date Format: dd/mm/yyyy

Example 1:
```
findd 12/12/2022
```
Expected Output 1: No Matching Task Found
```
There is no deadline/event with the matching date.
```
Example 2:
```
findd 12/12/2022
```
Expected Output 2: Matching Tasks Found
```
[D][ ] return book (by: Aug 12 2022)
[D][ ] buy book (by: Aug 12 2022, 6.05pm)
[E][ ] final test (at: Aug 12 2022, 8am-10am)
```
---


## Mark/Unmark Operation
This feature allows the user to change the working status of a specified task. For example, a task can be marked to indicate that it is done or unmarked when it is not done.

Note: Task is unmarked by default when first added

<br/>

### <span style = "background-color: #444444">mark</span> - Mark a Task (Non-Empty List)
Format:`mark Task_Index`
- Task Index is relative to the current list
- Use `list` to display all the tasks if unsure of Task Index

Example: Non-Empty List
```
mark 3
```
Expected Output:
```
Nice! I've marked this task as done:
  [D][X] buy book (by: Aug 12 2022, 6.05pm)
```

For Empty List, the program simply prints the following message:
```
There is no task in your list.
```

<br/>

### <span style = "background-color: #444444">unmark</span> - Unmark a Task (Non-Empty List)
Format:`unmark Task_Index`
- Task Index is relative to the current list
- Use `list` to display all the tasks if unsure of Task Index

Example: Non-Empty List
```
unmark 3
```
Expected Output:
```
OK, I've marked this task as not done yet:
  [D][ ] buy book (by: Aug 12 2022, 6.05pm)
```

For Empty List, the program simply prints the following message:
```
There is no task in your list.
```
---


## Delete Operation
This feature simply removes a task specified by the user from the record list.

<br/>

### <span style = "background-color: #444444">delete</span> - Deletes a Task (Non-Empty List)
Format:`delete Task_Index`
- Task Index is relative to the current list
- Use `list` to display all the tasks if unsure of Task Index

Example: Non-Empty List
```
delete 2
```
Expected Output:
```
Noted. I've removed this task:
  [D][ ] return book (by: Aug 12 2022)
Now you have 4 tasks in the list.
```

For Empty List, the program simply prints the following message:
```
There is no task in your list.
```
---


## Active Task Save
There exist a storage that automatically updates its internal list at the instance when a task is added, marked, unmarked or deleted within the record list. The list stored in the storage will be retained even after the program closes and will automatically be loaded back into the program when it is relaunched.

Note: Storage refers to data text file

---
## Prerequisites
- Java 11
---

## Getting Started
1. Click on the link to download the latest version of Duke.
2. Open command line local to duke.jar.
3. Run the command `java -jar duke.jar`.

---
