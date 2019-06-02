# Command Line Bowling Scoring Utility
This application allows bowlers to type in how many pins they hit on each roll and see their score up to the last complete frame.
This application scores for a standard 10 frame bowling game with 10 pins.

## Requirements
* Apache Maven version 3.5+
* Java 8

## Building
Navigate to root directory (the directory containing the pom.xml) and run
`mvn clean install`
This will build a jar and run all the tests.

## Running
`java -jar target/bowling-0.0.1-SNAPSHOT.jar` will start running the application.
You should see the following when running:

```
   ___             ___          
  / _ )___ _    __/ (_)__  ___ _
 / _  / _ \ |/|/ / / / _ \/ _ `/
/____/\___/__,__/_/_/_//_/\_, / 
                         /___/  
The command line bowling scoring app
Version 0.0.1-SNAPSHOT
Instructions: Type in pins hit on each roll and hit enter for current score.

```
The user can now type in how many pins they hit on each roll. When the game is complete the final score will be displayed and the program will terminate. 

## Scoring Display
This application waits for a frame's score to be completed before adding it to the score. This type of display is typically standard for most bowling alleys.
Since strikes and spares have special scoring rules, a user must wait until the rules are completed to see their score. 

