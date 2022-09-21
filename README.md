# Duke Project
Welcome to Duke, the leading Command-line Interface to keep track of your tasks!!

## Running the application
Run the Jar file provided!

## Command Format

Duke runs on CLI commands, and the command instructions will be listed below. Users should follow this format, 
replacing the square bracket segments (eg. **[description], [deadline], [place], [index]**) with the relevant information. 

**1. Add a todo task to the TasksList**
> `todo` [description]

**2. Add a deadline task to the TasksList**
> `deadline` [description] `/by` [deadline] 

**3. Add an event task to the TasksList**
> `event` [description] `/at` [place] 

**4. List out all tasks in TasksList**
> `list`

**5. Delete the task in index [index] of TasksList**
> `delete` [index]

**6. Mark the task in index [index] of TasksList as completed**
> `mark` [index]

**7. Unmark the task in index [index] of TasksList to make it uncompleted**
> `unmark` [index]

**8. List all tasks that match the searchPhrase **
> `find` [searchPhrase]

**9. End the Duke App**
> `bye`