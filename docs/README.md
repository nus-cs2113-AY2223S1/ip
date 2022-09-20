# Duke User Guide

**Duke is a task manager application run on the command line interface  
Duke is powered by Slave Kai, Zhou Zhou's 256 IQ bot**

## Features

**Duke recognises 3 types of tasks: ToDos, Deadlines and Events**

> ### Add tasks
>Add a new task to track  
> Each task has a completion status and task description  
> Each deadline & event has a date and optional time

> ### Modify tasks
>Make changes to existing tasks:
>- Mark a task as done or not done (tasks done are marked with 'X')
>- Delete a task *(this action is irreversible!)*

> ### View tasks
>List all tasks, or filter tasks by:
>- keyword - tasks containing the keyword in their description
>- date - tasks (of types deadline and event) before, on, or after a particular date
>- completion status - tasks that are done or not done
>
>> **Tasks are displayed in the format:**
>>```
>>{number}.[{type}][{done status}] {description} ({date} {time} (if applicable))  
>>```
>>**Example:**
>>```
>>3.[D][X] make a new friend (31 Dec 2021 23:59)
>>```
>>Tasks are displayed in the order by which they were added

> ### Save and load tasks
>Slave Kai will save your tasks and reload them the next time you run Duke  
> The saved tasks are stored in src/main/java/duke/data/data.txt  
> Slave Kai will automatically create a new save file if one does not exist

## Usage

**Deadlines and events must be associated with a date, but a time is optional  
All dates must be entered in the format dd-mm-yyyy, eg. 22-7-2026  
All times must be entered in the format hhmm in 24hr notation, eg. 2359**

> ### `bye` - to exit Duke
><details><summary>details</summary>
><p>  
>
>Slave Kai will end Duke program and save tasks, if any
>> Example of usage:  
>>`bye`
>>
>>Expected outcome:  
>> Slave Kai ends Duke program and saves tasks, if any
>>```
>>Please don't go :(
>>------------------------------
>>Slaving tasks......
>>```
></p>
></details>

> ### `help` - to try asking for help
><details><summary>details</summary>
><p>  
>
>Slave Kai will consider helping
>> Example of usage:  
>>`help`
>>
>>Expected outcome:  
>>Slave Kai's decision to help
>>```
>>You asked for help, but I don't feel like helping ;p
>>Maybe try saying the magic word?
>>```
></p>
></details>

> ### `please` - to actually get help
><details><summary>details</summary>
><p>  
>
>Slave Kai will display help message
>> Example of usage:  
>>`please`
>>
>>Expected outcome:  
>> Help message displaying available commands
>>```
>>Slave Kai is glad to help! 
>>Available commands: 
>>bye - to exit Duke
>>help - to try asking for help
>>please - to actually get help
>>todo {description} - to add a new todo
>>event {description} /{date} {time (optional)} - to add a new event
>>deadline {description} /{date} {time (optional)} - to add a new deadline
>>mark/unmark {task number} - to mark a task as done/not done
>>delete {task number} - to delete a task
>>list - to list all tasks
>>find {keyword} - to list tasks containing {keyword}
>>done/undone - to list tasks that are marked as done/not done
>>on/before/after {date} - to list tasks on/before/after {date}
>>
>>Note: all dates must be entered in the format dd-mm-yyyy, eg. 22-7-2026
>>and all times must be entered in the format hhmm, eg. 2359
>>```
></p>
></details>

> ### `todo {description}` - to add a new todo
><details><summary>details</summary>
><p>
>
>Slave Kai will add a new todo with {description}
>> Example of usage:  
>> `todo find a girlfriend`
>>
>>Expected outcome:  
>> Todo is created
>>```
>>Task added:
>>1.[T][ ] find a girlfriend
>>```
></p>
></details>

> ### `event {description} /{date} {time}` - to add a new event
><details><summary>details</summary>
><p>  
>
>Slave Kai will add a new event with {description} on {date} at {time (optional)}
>> Example of usage:  
>> `event go on a date /1-1-2066`
>>
>>Expected outcome:  
>> Event is created
>>```
>>Task added:
>>2.[E][ ] go on a date (1 Jan 2066)
>>```
></p>
></details>

> ### `deadline {description} /{date} {time}` - to add a new deadline
><details><summary>details</summary>
><p>  
>
>Slave Kai will add a new deadline with {description} on {date} at {time (optional)}
>> Example of usage:  
>> `deadline make a new friend /31-12-2021 2359`
>>
>>Expected outcome:  
>> Deadline is created
>>```
>>Task added:
>>3.[D][ ] make a new friend (31 Dec 2021 23:59)
>>```
></p>
></details>

> ### `mark/unmark {task number}` - to mark a task as done/not done
><details><summary>details</summary>
><p>  
>
>Slave Kai will mark that task as done/not done
>> Example of usage:  
>> `mark 3`
>>
>>Expected outcome:  
>> Task is marked as done/not done
>>```
>>Well done. I've marked this task as done:
>>3.[D][X] make a new friend (31 Dec 2021 23:59)
>>```
></p>
></details>

> ### `delete {task number}` - to delete a task
><details><summary>details</summary>
><p>  
>
>Slave Kai will delete that task (irreversible!)
>> Example of usage:  
>> `delete 3`
>>
>>Expected outcome:  
>> Task is deleted
>>```
>>Task deleted:
>>3.[D][X] make a new friend (31 Dec 2021 23:59)
>>```
></p>
></details>

> ### `list` - to list all tasks
><details><summary>details</summary>
><p>  
>
>Slave Kai will list all tasks and total number of tasks
>> Example of usage:  
>> `list`
>>
>>Expected outcome:  
>> All tasks in Slave Kai's memory
>>```
>>You have 2 tasks
>>1.[T][ ] find a girlfriend
>>2.[E][ ] go on a date (1 Jan 2066)
>>```
></p>
></details>

> ### `find {keyword}` - to list tasks containing {keyword}
><details><summary>details</summary>
><p>  
>
>Slave Kai will list tasks containing {keyword} in task description
>> Example of usage:  
>> `find friend`
>>
>>Expected outcome:  
>> Tasks containing friend in task description
>>```
>>Slave Kai found these 1 tasks:
>>1.[T][ ] find a girlfriend
>>```
></p>
></details>

> ### `done/undone` - to list tasks that are marked as done/not done
><details><summary>details</summary>
><p>  
>
>Slave Kai will list tasks that are marked as done/not done
>> Example of usage:  
>> `undone`
>>
>>Expected outcome:  
>> Tasks marked as not done
>>```
>>Slave Kai found these 2 tasks:
>>1.[T][ ] find a girlfriend
>>2.[E][ ] go on a date (1 Jan 2066)
>>```
></p>
></details>

> ### `on/before/after {date}` - to list tasks on/before/after {date}
><details><summary>details</summary>
><p>  
>  
>Slave Kai will list tasks (of types deadline and event) on/before/after {date}  
>> Example of usage:  
>> `after 22-7-2026`  
>>  
>>Expected outcome:  
>> Tasks (of types deadline and event) after {date}
>>```
>>Slave Kai found these 1 tasks:
>>2.[E][ ] go on a date (1 Jan 2066)
>>```
></p>
></details>

> ### `hi` - to greet Slave Kai
><details><summary>details</summary>
><p>  
>  
>Slave Kai will respond with a greeting  
>> Example of usage:  
>> `hi`  
>>  
>>Expected outcome:  
>> Slave Kai's response  
>>```  
>>Howdy!  
>>```  
></p>
></details>
