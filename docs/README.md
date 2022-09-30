# User Guide

Welcome to Duke! Let me walk you through the various capabilities of this CLI, designed for busy people.

## Features 

There are three types of tasks you can add: a todo, a deadline, and an event

### Adding a todo: `todo [description]`

Allows the user to add a todo with a description to the list

#### Example Output
<img width="398" alt="image" src="https://user-images.githubusercontent.com/31297758/193044048-56fcaa94-9a9a-4b9c-9c3f-cf4a3711c91a.png">

### Adding a deadline: `deadline [description] /by [date]`

Allows the user to add a deadline with a description and a date to the list

#### Example Output
<img width="487" alt="image" src="https://user-images.githubusercontent.com/31297758/193044338-3c898c36-9ae2-438f-98c7-e48f761372eb.png">

### Adding an event: `event [description] /at [date]`

Allows the user to add an event with a description and a date to the list

#### Example Output
<img width="412" alt="image" src="https://user-images.githubusercontent.com/31297758/193044911-59691dd8-1db4-4da5-b04f-fcc2cec383a2.png">

### Delete from list: `delete [index]`

Will allow the user to delete a task from the list using an index

#### Example Output
<img width="402" alt="image" src="https://user-images.githubusercontent.com/31297758/193045200-4e30579c-3bde-43bc-a702-9a103c7289d8.png">

### Viewing list: `list`

Will allow the user to view a list of their current tasks

#### Example Output
<img width="410" alt="image" src="https://user-images.githubusercontent.com/31297758/193048576-076577c2-b00a-431a-8288-87ee70668489.png">

### Mark a task as complete: `mark [index]`

Will allow the user to mark a task as complete from the list using an index

#### Example Output
<img width="420" alt="image" src="https://user-images.githubusercontent.com/31297758/193045374-043df1a7-d204-4eb8-a1e7-9822c3b187ed.png">

### Mark a task as incomplete: `unmark [index]`

Will allow the user to mark a task as incomplete from the list using an index

#### Example Output
<img width="434" alt="image" src="https://user-images.githubusercontent.com/31297758/193045499-fd7dde18-ae01-4ff6-ad53-467b0443b4a2.png">

### Filter through tasks based on a keyword: `find [word]`

Will allow the user to find tasks that include the given keyword/s

### Terminate the program: `bye`

Will allow the user to terminate the program

#### Example Output
<img width="424" alt="image" src="https://user-images.githubusercontent.com/31297758/193045662-a89c3c6c-5ebc-4817-a8b8-7991949db2aa.png">

## Special Features

### Persistence of tasks

Duke will save the tasks that are inputted once the program is closed in a readable text-file under data/tasks.txt. These tasks will be loaded when Duke is started up again, and will persist through each iteration of Duke as they are modified and changed.

<img width="402" alt="image" src="https://user-images.githubusercontent.com/31297758/193047902-71ed7db8-94ab-4078-bf7a-ef4aad32413b.png">


### Unique Exception

Duke is created with a unique Exception called DukeException, which allows it to fail open and alert the user of the error without disrupting the files or the saving process.

### Error-Handling

Duke has some special ways to capture common accidental errors. Putting just "todo" for example, alerts the user of their error. Adding a bunch of extra spaces does the same thing

<img width="433" alt="image" src="https://user-images.githubusercontent.com/31297758/193047948-bdd92221-3df3-44e9-ae0b-e16f6acddb25.png">
<img width="560" alt="image" src="https://user-images.githubusercontent.com/31297758/193048054-398fafd3-5569-4339-9786-3f3803cb8fa4.png">
