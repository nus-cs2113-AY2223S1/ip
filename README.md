# Duke (User Guide)

Duke is a CLI task manager app.

## Setting up

1. Download Duke.jar
2. Open a terminal and cd into the folder that contains Duke.jar
3. Run "java -jar Duke.jar"

If successful, you should be greeted with something like this:

   ```
   Hello from
 _____ __    _____ _____ _____
| __  |  |  |     |     |     |
| __ -|  |__|  |  |  |  | | | |
|_____|_____|_____|_____|_|_|_|

__________________________________________________
Hello! I'm Bloom, your personal task manager.

What can I do for you?
__________________________________________________
   ```
   
You might be wondering why is your task manager called Bloom, when it was introduced as Duke?
This is because everyone uses Duke. Since you are special, Bloom is your special assistant.
(Do not be confused. All files/folders will be named as Duke for the purpose of this project.)

## Commands

Here are the available commands and descriptions:

1. **list**
   - Displays all tasks that are currently in the list
2. **todo (taskName)**
   - Adds a todo task to the list
3. **deadline (taskName) /by (taskDateTime)**
   - Adds a deadline task to the list (taskDateTime can be in any format ie. "today 5pm" or "1 Jan 2023")
4. **event (taskName) /at (taskDateTime)**
   - Adds an event task to the list (taskDateTime can be in any format ie. "today 5-7pm" or "1 Jan 2023")
5. **mark (taskID)**
   - Marks the corresponding task as completed
6. **unmark (taskID)**
   - Marks the corresponding task as not completed
7. **delete (taskID)**
   - Removes the corresponding task from the list
8. **find (keyword)**
   - Displays all tasks that have the keyword in their task names.
9. **bye**
   - Exits the task manager app, and all information is stored in "data/duke.txt"
