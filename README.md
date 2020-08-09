# CSX42: Assignment 5
## Name: Preeti Priyam

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [textdecorators/src](./textdecorators/src/) folder.

Instruction to clean:
####Command: ant -buildfile textdecorators/src/build.xml clean

Description: It cleans up all the .class files that were generated when compiled the code.

Instruction to compile:
####Command: ant -buildfile textdecorators/src/build.xml all

Description: Compiles code and generates .class files inside the BUILD folder.

Instruction to run:
####Command: ant -buildfile textdecorators/src/build.xml run -Dinput1="input.txt" -Dmisspelled="misspelled.txt" -Dkeywords="keywords.txt" -Doutput="output.txt" -Ddebug="2"

Note: Arguments accept the absolute path of the files.

# Justification for the Data Structures used in the assignment in terms of time and/or space complexity.

List: ArrayList
I have used List implementation ArrayList because it is a dynamic size array which is better in terms of space and time complexity as we don't need to define a static size for the array. It's better to store objects in the ArrayList as it provides type checking through generics. Moreover, when compare to other data structure such as LinkedList, Stack, Queue, etc., in terms of time complexity for inserting, reading a value, ArrayList is better.

Time complexity for adding an element into an arbitrary indices of the List : O(n)
Time complexity for adding last element into the List : O(1)
Time complexity for reading an element from List : O(1)
Time complexity for searching an element from List : O(n)

Map: HashMap
I have used Map implementation HashMap. HashMap access element faster when compared to other data structure due to its hashing technique. HashMap provides constant time complexity for basic operations such as get and put if the hash function is properly implemented.

Time complexity for adding an element into Map : O(1)
Time complexity for reading an element from Map : O(1)
Time complexity for searching an element from Map : O(n)

# Slack day used: 3

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [08/08/2020]
