
# Private Investigator

### Overview of the solution:
The solution outputs similar sentences with one word difference from an input and outputs to the expected format.
I have used StringUtils.java class from apache commons-lang3 library to determine the difference between two sentences.
Have modularized the code and made it generic so that it can easily be extended and maintained.

#### Notable mentions
The solution is implemented such that the input sentences would be from a file and have to output the similar 
sentences to a file.  
It can be easily extended to have inputs from different sources by extending AbstractProcessor and the results can 
be published to multiple sources by extending AbstractPublisher.  
Different types of algorithms can be implemented and used in the processor.  

### How to run:
####1. Run in an IDE
```text
1. Import the project to your IDE
2. Setting up input/output file paths, you can follow any of the below two options,
   a. Run it as is, the app would pick the sample input file (input.txt) from resources folder and will output 
   the results to output file (output.txt).
   b. You can provide input and output file paths by adding following two VM options in IDE.
      -DinputFilePath=<path-to-input-file> -DoutputFilePath=<path-to-output-file>
      
      Example: -DinputFilePath=C:\folder\input.txt -DoutputFilePath=C:\folder\output.txt
      
3. Run the main method from the PrivateInvestigator.java class
```

####2. Run from a jar:
```text
1. Ensure you have a java runtime version installed in your system. 
2. The jar privateInvestigator.jar and input.txt is added in the root path, keep them together.
3. Run the following command
   
   java -jar -DinputFilePath=<path-to-input-file> -DoutputFilePath=<path-to-output-file> privateInvestigator.jar
   Example: java -jar privateInvestigator.jar 
   Example: java -jar -DinputFilePath=./input.txt -DoutputFilePath=./output.txt privateInvestigator.jar
```

### 1. What can you say about the complexity of your code?
The algorithm uses StringUtils.indexOfDifference() method which internally compares characters between the two strings 
and returns the first index at which characters are not equal. Since the algorithm has to compare one sentence in the 
file with other sentences in the file, the time complexity is O(N^2).

### 2. How will your algorithm scale?
Since the time complexity is O(N^2) the runtime increases exponentially with the input.

### 3. If you had two weeks to do this task, what would you have done differently? What would be better?
**a.** I would have analyzed more on the different algorithms and libraries that are available which can perform the task of 
comparing and producing the difference between two sentences optimally.  
**b.** If there is none available then would have looked into the approach of using a graph like structure to keep track of 
all the sentences which were processed, have weights on the number of occurence of words and once the new sentence 
is getting processed it can find out the one word difference at that instant which can save both time and space.  
**c.** Add ability to process an input with multiple algorithms instead of a single algorithm, so that a different algorithm 
can be used to process the results of previous algorithm.