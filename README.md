# Project Duke

Project Duke is a Personal Assistant Chatbot that helps you keep track of various things.

## Launching Duke

Navigate to the directory containing 'Duke.jar' in a terminal, then enter the following command:

`java -jar Duke.jar`

## Tasks

There are 3 types of tasks:

1. Todo (tasks that simply needs to be done, no specific time period)
2. Deadline (tasks that needs to be done by a certain time)
3. Event (tasks that need to be done within a certain time period)

## Commands

### Add todo task to task list: `todo`

Format: `todo <descripton>`

- `<description>` is a String describing the task

### Add deadline task to task list: `deadline`

Format: `deadline <descripton> /by <time>`

- `<description>` is a String describing the task
- `<time>` is a String describing the time
- `/by` is needed to separate `<description>` and `<time>`

### Add event task to task list: `event`

Format: `event <descripton> /at <time>`

- `<description>` is a String describing the task
- `<time>` is a String describing the time
- `/at` is needed to separate `<description>` and `<time>`

### List all tasks in task list: `list`

Format: `list`

- Prints every task in the task list

### Delete task in task list: `delete`

Format: `delete <index of item>`

- `<index of item>` is an integer (one-based indexing)
- A prompt will be shown if the index is out of range

### Mark task as done in task list: `mark`

Format: `mark <index of item>`

- `<index of item>` is an integer (one-based indexing)
- A prompt will be shown if the index is out of range, or if the task is already marked

### Unmark task as done in task list: `unmark`

Format: `unmark <index of item>`

- `<index of item>` is an integer (one-based indexing)
- A prompt will be shown if the index is out of range, or if the task is already unmarked

### Find task in task list: `find`

Format: `find <substring>`

- `<substring>` is a String to search for
- All tasks with description containing the substring will be listed

### Exit the program: `bye`

Format: `bye`

- Tasks will be saved at `data/duke.txt` before exiting