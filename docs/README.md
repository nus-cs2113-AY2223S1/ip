# User Guide

## Welcome to Duke. How to get started?
1. Copy the latest release jar file available on this repository into your desired folder
2. Run terminal and change directory to the folder containing the jar file
3. Run the jar file by keying in `java -jar "ip.jar"`
## Duke Walkthrough
1. After running the jar file, you will be greeted with the following:
![Screenshot 2022-09-30 at 10 05 10 PM](https://user-images.githubusercontent.com/64301417/193287451-fb56a17b-e662-4faa-bf9e-95c37ba12f77.png)


2. Key in desired commands and press enter to execute each command.
3. To exit Duke, simply key in "bye".
## Features
List of commands supported by Duke:
### list
To display all the tasks stored in task array

Expected Format: list
![Screenshot 2022-09-30 at 10 06 05 PM](https://user-images.githubusercontent.com/64301417/193287669-2db6f6c5-e021-4ebe-9b55-c234d6cabd12.png)


### todo
To create a todo

Expected Format: todo `taskname`
![Screenshot 2022-09-30 at 10 06 58 PM](https://user-images.githubusercontent.com/64301417/193287844-66f350c6-4548-471d-948d-4d982b85e0ce.png)

### event
To create an event

Expected Format: event `taskname` /at `dateAndTime`
![Screenshot 2022-09-30 at 10 07 18 PM](https://user-images.githubusercontent.com/64301417/193287923-17bdcf4c-2b59-47e0-b395-c74e6c1617b4.png)

### deadline
To create a deadline

Expected Format: deadline `taskname` /by `dateAndTime`
![Screenshot 2022-09-30 at 10 07 51 PM](https://user-images.githubusercontent.com/64301417/193288026-f31d5fa9-3b40-4e8c-99ba-f8ec16b17a4d.png)


### mark 
To Mark a task as done

Expected Format: mark `taskNumber`
![Screenshot 2022-09-30 at 10 08 11 PM](https://user-images.githubusercontent.com/64301417/193288087-5e9e9023-6a8d-46e3-bfd7-34609ce95784.png)


### unmark
To mark a task as not done

Expected Format: unmark `taskNumber`
![Screenshot 2022-09-30 at 10 08 34 PM](https://user-images.githubusercontent.com/64301417/193288161-c4cb8d21-a97a-4598-a19e-aa5e59e56258.png)

### delete
To delete a task from task array

Expected Format: delete `taskNumber`
![Screenshot 2022-09-30 at 10 09 01 PM](https://user-images.githubusercontent.com/64301417/193288252-d301a30a-d5d3-46d8-b1a0-7c69c2280989.png)

### find
Find relevant tasks based on a keyword entered by user

Expected Format: find `keyword`
![Screenshot 2022-09-30 at 10 09 20 PM](https://user-images.githubusercontent.com/64301417/193288337-486caa16-2c31-4a65-94ba-b18edaabd7dc.png)


### bye
To exit the Duke application

Expected Format: bye
![Screenshot 2022-09-30 at 10 09 48 PM](https://user-images.githubusercontent.com/64301417/193288424-d56d2dfa-676b-481d-9a9a-15d54b5ecfb9.png)

