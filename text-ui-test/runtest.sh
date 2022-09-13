#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/duke/*.java ../src/main/java/duke/task/*.java ../src/main/java/duke/task/exception/*.java ../src/main/java/duke/task/model/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin duke.Duke < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "UI test result: PASSED"
else
    echo "UI test result: FAILED"
fi

# convert to UNIX format
cp data/EXPECTED-DUKE.TXT data/EXPECTED-DUKE-UNIX.TXT
dos2unix data/duke.txt data/EXPECTED-DUKE-UNIX.TXT

# compare the save output to the expected save output
diff data/duke.txt data/EXPECTED-DUKE-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Save test result: PASSED"
    exit 0
else
    echo "Save test result: FAILED"
    exit 1
fi
