# calculator
calculator is a desktop application built using the Java programming language. It implements an algorithm that accepts infix expressions as input, converts the infix expression to postfix, and evaluates the expression to update the GUI. 

A user can select various operands and operators and calculator uses the Infix to Postfix Conversion Algorithm to perform the necessary artithmetic. The input is accepted as a String object and that object is tokenized using the StringTokenizer class. Depending on what the token is, the token is either added to a stack or a queue. As more tokens are accepted the previously added tokens are removed from the stack and queue depending on precedence rules. This results in a new string that is in postfix notation making it easier to perform the proper arithmetic. The GUI was made using JFrame and the datastructures were implemented from scratch. 

![screen shot 2019-02-12 at 3 07 09 am](https://user-images.githubusercontent.com/27708647/52631465-b851dc00-2e73-11e9-991f-2fa5e600d1e4.png)

### Prerequisites
* [JVM](https://www.java.com/en/download/) - The runtime environment used


### Installing
* Using your Command Line Interface (CLI) type the command "java -version" to check if you have the Java Virtual Machine (JVM) installed on your machine
* If you don't have JVM installed on your machine, please follow the link provided above and install

* Enter the bin directory within the project folder
* Type the command "java Calculator" to run the application


## Built With
* [JFrame](https://docs.oracle.com/javase/tutorial/uiswing/components/frame.html) - GUI made using Swing components
* [Java](https://docs.oracle.com/javase/7/docs/api/) - Programming language used


## Authors
I implemented the Infix To Postfix Conversion algorithm along with the data structures used. The GUI and List interface was written by Professor Alan Riggins @ San Diego State University
* **Gerardo Lopez** - ExpressionEvaluator.java, LinkedList.java, Queue.java, Stack.java
* **Alan Riggins(SDSU)** - Calculator.java, UnorderedListADT.java
