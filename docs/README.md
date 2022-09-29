# Duke User Guide
#### Task List Manager

## Types of tasks
### Todo
A `todo` task which contains: 
- Name 
### Event
An `event` task which contains:
- Name
- Date & Time of `event`
### Deadline
A `deadline` task which contains:
- Name
- Date & Time that `deadline` is due by

## Features of Duke

### Starting the programme

When you start the programme, you should see the following message. 
```
 ____        _        
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

Hello! I'm Duke
What can I do for you?
```

### Task List of Todos, Events and Deadlines

A collated Task List of tasks that you can view.
### `list` - To view full list of tasks
#### Example of usage:

`list`

#### Expected output:
```
Here are the tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: Oct 15 2019)
3.[E][ ] project meeting (at: Aug 6 2019 2-4pm)
4.[T][X] join sports club
```

### Add new tasks to Task List

### `Todo` - Add Todo to Task List

#### Example of usage:

`todo (task name)`

#### Expected output:

If your todo is added successfully, you should see following message below. **Duke** should also update you on the total number of tasks in your Task List. 

```
Got it. I've added this task:
  [T][ ] Name
Now you have 5 tasks in the list.
```

### `Event` - Add Event to Task List

#### Example of usage:

`event (task name) /at (task date and time)`

The input date must be given in the format: *YYYY-MM-DD*.

#### Expected output:

If your `event` is added successfully, you should see following details below. **Duke** should also update you on the total number of tasks in your Task List.
```
Got it. I've added this task:
  [E][ ] Name (at: Date Time)
Now you have 5 tasks in the list.
```

### `Deadline` - Add Deadline to Task List

#### Example of usage:

`deadline (task name) /by (task date and time)`

The input date must be given in the format: *YYYY-MM-DD*.

#### Expected output:

If your `deadline` is added successfully, you should see following details below. **Duke** should also update you on the total number of tasks in your Task List.
```
Got it. I've added this task:
  [D][ ] Name (by: Date Time)
Now you have 5 tasks in the list.
```

### Deleting tasks from Task List
You can remove tasks from Task List using their index number. This works for all 3 types of tasks. 

#### Example of usage:

`delete (task index)`

#### Expected output:

If your task is deleted successfully, you should see following details below. **Duke** should also update you on the total number of tasks in your Task List.
```
Noted. I've removed this task:
  [E][ ] Event Name (at: Jan 21 2021 2100)
Now you have 4 tasks in the list.
```

### Finding tasks in Task List
You can find specific tasks from Task List using keywords as search input. This works for all 3 types of tasks.

#### Example of usage:

`find (search input)`

#### Expected output:

If a task contains the search input, **Duke** will print out the full details of the task. An example of the list of tasks that contains the keyword 
`"book"` is shown below. 
```
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: Oct 15 2019)
```

### Marking/Unmarking tasks in Task List
You can mark/unmark specific tasks from Task List using their index number. This works for all 3 types of tasks.

#### Example of usage:

`mark (task index)`

`unmark (task index)`

#### Expected output:

If your task is marked/unmarked successfully, **Duke** will print out the full details of the task with the corresponding message. 

This is a successful example of marking a task as done. 
```
Nice! I've marked this task as done:
  [E][X] Name (at: Jun 21 2021 6pm)
```
This is a successful example of unmarking a task as done.
```
OK, I've marked this task as not done yet:
  [E][ ] Name (at: Jun 21 2021 6pm)
```

### Ending the programme
Once you are done modifying or viewing your Task List, you can end the programme. 

#### Example of usage:

`bye`

#### Expected output:

If your programme has ended successfully, **Duke** will print out the following message.
```
Bye. Hope to see you again soon!
```

## Storing your Task List
**Duke** stores the data of the Task List as `String` in a text file, `duke.txt`. The programme is made to find this text file in the relative path:
`[project_root]/data/duke.txt`
### Retrieving data from file
**Duke** automatically reads data from `duke.txt` at the start of the programme, and loads your Task List with the data stored. 

If `duke.txt` is not found in the relative path, **Duke** will create a new `duke.txt` in the relative file path. 
### Writing data to file
**Duke** appends the current Task List as `String` to another text file, `temp.txt`. The programme is made to create this temporary text file in the relative path:
`[project_root]/data/temp.txt`. **Duke** then copies the contents of `temp.txt` into `duke.txt`, and deletes `temp.txt`. Hence, `duke.txt` now stores the updated 
Task List. 