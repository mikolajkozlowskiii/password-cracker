# Goal of project
The purpose of the project is to create a multi-threaded program to break passwords encoded with the MD5 algorithm.
The program is based on the action of Producer Consumer. The Producer is responsible for searching for passwords, while the Consumer
is responsible for signaling the found passwords by displaying them in the terminal and saving them to a file in a thread-safe way.
The method used to find encrypted passwords is called brute-force solution. The input data is a file containing a dictionary and a file with encoded passwords.
Based on the extracted values from the dictionary, various combinations of words are created, taking into account digits, punctuation marks and case.
As this is a brute-force solution, it is not possible to create all possibilities of passwords while maintaining acceptable performance of the program.
Therefore, we have arbitrarily chosen various strategies that, in my opinion, are most commonly used when creating passwords.
After creating a word, it is encoded into MD5 format and then compared with the passwords given at the input, if they are equal it means that
the password has been found and can be transferred to the Consumer. 

## Producers
It is possible to create 36 different producers that can work parallelly in a thread-safe way.
They can be divided into producers looking for passwords that are created from two words from the dictionary or from one word.
They contain digits or not. If they contain digits, they can be at the beginning, the end or simultaneously at the beginning and the end.
May contain punctuation marks or not. If they contain punctuation marks, they can be at the beginning,
at the end of a word or in the middle of a word (if the producer creates a password from 2 words, then between the first and second word).
If contains punctuation marks, one producer is checking all 3 cases of punctuation marks. Arbitrarily decided on this strategy to optimize the operation of the program.
The word can be written in uppercase letters, lowercase letters or only the first letter can be uppercase.
Summarizing the strategies of created slogans by producers:
* number of words:
  * single 
  * double word 
* digits:
  * at the beginning 
  * end 
  * both 
* punctuation marks:
  * present (at the beginning, last, middle, all 3 cases are produced)
  * no punctuation marks 
* word size :
  * lowercase 
   * uppercase 
   * only first letter uppercase 


## Consumer
The consumer in thread-safe way accepts broken passwords from the manufacturer and signals it in 2 ways:
It displays the broken password in the terminal and saves it in a thread-safe way and in an interrupt-safe way to a file.e. Program interruption is possible due to the producer taking too long to look for passwords. There is always a possibility that the searched password in the input file, will not be within the range of created password configurations and may not be found.


## Usage  ##
As shown in the driver (main class) you should create objects of class Thread , providing in the constructor objects of class Consumer or Producer,
which implement the Runnable interface.  Then you need to run them using: `consumerThread.start()` `producerThread.start()`. 
It is important that they have a common, thread-safe list, which is a kind of buffer to which broken passwords are passed. 

## Room for improvment ###
The next step in this program is to replace synchronized blocks with watt-safe collections, which will significantly reduce the amount of code written. 
E.g. lists and blocking queue from java.util.concurrent package.
