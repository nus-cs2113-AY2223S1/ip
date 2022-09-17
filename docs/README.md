<!-- ABOUT THE PROJECT -->

## About The Project

**This is the CS2113 IP project made by Chin Han using OOP and software engineering practices.
It is a program designed to help users curate a list of taskings with different classifications , along with extra
functionalities.**

Here are the features of this program:

1) Adding new tasks
    1) Add Todo task
    2) Add Event task with date and time
    3) Add Deadline task with date and time
2) Marking and Unmarking of task
3) Deleting user-defined task entries
4) Search and list entries containing user-defined keywords
5) Listing of all task entries
6) Automatic task saving in dukeFile.txt for continuity

<!-- GETTING STARTED -->

## Getting Started

1) Retrieve the ip.jar [from Chin Han's repo release.](https://github.com/chinhan99/ip/releases)
2) Run the ip.jar file by going to the file where it is located in the command line and entering the command below.

```sh
java -jar ip.jar
```

3) Refer to the possible commands listed below. After each user action, users would be given **acknowledgement
   messages** or **error messages** for encountered errors.

### Prerequisites

Ensure you are running Java 11 on your system.


<!-- USAGE EXAMPLES -->

## Usage

**This is a list of commands that can be used with this program**

| Command  | Command Syntax                               | Example                                      |
|----------|----------------------------------------------|----------------------------------------------|
| help     | help                                         | help                                         |
| todo     | todo TASK_DESCRIPTION                        | todo get mushrooms for dinner                |
| event    | event TASK_DESCRIPTION /at d/MM/yyyy HHmm    | event go to Zouk /at 10/09/2021 2200         |
| deadline | deadline TASK_DESCRIPTION /by d/MM/yyyy HHmm | deadline submit homework /by 19/07/2022 1200 |
| mark     | mark TASK_NUMBER                             | mark 2                                       |
| unmark   | unmark TASK_NUMBER                           | unmark 44                                    |
| delete   | delete TASK_NUMBER                           | delete 3                                     |
| find     | find KEYWORD                                 | find food                                    |
| list     | list                                         | list                                         |








