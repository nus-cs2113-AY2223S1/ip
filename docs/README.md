# User Guide
```
 ____        _
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
```

Duke is an application built to managing tasks such as to-dos, deadlines and events.

It is an interactive Command Line Interface (CLI) application written using Java 11.

## Prerequisites

1. Ensure that ```Java 11``` is installed on your system.
    1. Execute the command ```java --version```
    2. Verify that the version of Java installed is ```Java 11```
2. Ensure that you have write permissions for the directory in which you are executing the program.

## Getting Started

1. Download the program from Xzynos's [iP Repository](https://github.com/xzynos/ip/releases)
2. Execute the program using the command ```java -jar ip.jar```

## Features

### Add a to-do: ```todo```
Adds a to-do task to Duke.\
Syntax: ```todo DESCRIPTION```\
Examples:
- ```todo Attend CS2113T Meetings```

### Add a deadline: ```deadline```
Adds a deadline to Duke.\
Syntax: ```deadline DESCRIPTION /by dd/MM/yyyy HHmm```\
Examples:
- ```deadline Release Duke v0.2 /by 30/09/2022 2359```

### Add an event: ```event```
Adds an event to Duke.\
Syntax: ```event DESCRIPTION /at dd/MM/yyyy HHmm dd/MM/yyyy HHmm```\
Examples:
- ```event Attend CS2113T Week 8 Lecture /at 23/09/2022 1600 23/09/2022 1800```

### Mark a task as complete: ```mark```
Marks a task as complete.\
Syntax: ```mark TASK_NUMBER```\
Examples:

- ```mark 1```

### Mark a task as incomplete: ```unmark```

Marks a task as incomplete.\
Syntax: ```unmark TASK_NUMBER```\
Examples:

- ```unmark 1```

### List all tasks: ```list```

Lists all tasks added to Duke. You can specify a date and Duke will list all tasks that occurs at that date.\
Syntax: ```list```\
Examples:

- ```list```
- ```list 30/09/2022```

### Delete a task: ```delete```

Deletes a task from Duke.\
Syntax: ```delete TASK_NUMBER```\
Examples:

- ```delete 1```

### Find a task: ```find```

Finds and list tasks in Duke that fully or partially matches a description.\
Syntax: ```find DESCRIPTION```\
Examples:
- ```find CS2113T```

### Exit the program ```bye```
Exits the program.\
Syntax: ```bye```