## Snake Game

The snake game was created back in the 1970s. And int the 1990s, Nokia had this version of the snake game in all its mobile phone. 

## Rules

The game is related to moving to moving a snakehead by using the arrows left, right, up and down to eat an apple. 
On eating the apple, the snakes becomes one block bigger. 
If the head of the snake touched any part of its body, the game is over. 
The score is dependent on number of apples eaten. 
Each apple eaten gives you an increase in score by five points.

## Setup

#### Java Setup

Make sure to install Java 8 or further versions in your linux/Windows/Mac OS. 
This particular program has been made in Visual Studio Code, but Eclpse and Intellij Idea and other IDEs can be used as well. 
JAVA needs to be set up by setting the environment variables for JAVA_HOME and PATH: 

JAVA_HOME="/Desktop/jdk1.8.0_73"
export JAVA_HOME
PATH=$JAVA_HOME/bin:$PATH
export PATH

## Setting up the IDE

Whichever IDE you are using, you first need to open a project. 
After this, two folders namely src and lib will be visible. 
Left click on the src folder, and create a class(Eclipse/Intellij) or a new file(Visual Studio Code).
Once this is done, and a new window opens with the java class visible, start coding.

This program need two classes-
1. Main.java
2. Game.java