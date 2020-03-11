# Computer_Science_II

Projects

### Project 1
* Proj1App.java
* Airplane.java
* Seat.java

This program creates a new airplane object and using the showOptionDialog method in the JOptionPane class shows the user options to either make a reservation, cancel a reservation, show a seating chart or quit from the array referenced by choices. The users choice value is stored in the integer choice. If the user chooses to make a reservation (0), the makeAReservation method is called on the Airplane object, if they choose to cancel a reservation, the cancelAReservation method is called, to display a seating chart, the toString method is called. This menu is displayed and the appropriate method is called until the user chooses quit.
 
### Project 2
* Proj2App.java
* River.java
* Fish.java
* Bear.java
* Animal.java

This program accepts two values from the user using the JOptionPane showInputDialog method and using those values instantiates a new River object and displays the state of the object to the console with all the animals in the 2D array. The move method is called 33 times and displayed to the console using the toString method after each move. Since the parseInt method can throw a NumberFormatException if the user enters nothing or something other than an integer, statements including that method are placed in a try/catch block to appropriately handle the exception. That exception is also thrown when the user enter a number less than 5 or greater than 10. The user gets 3 opportunities to enter a valid size for the river, if they fail to do so, the program terminates

### Project 3
* Proj3App.java
* Hand.java
* DiscardPile.java
* Deck.java
* Card.java

This program accepts a value greater than or equal to 25 from the user for their desired point value target. The user gets 3 opportunities to enter a valid target, if they fail to do so, the program terminates. Once the target is set, the computer goes and draws a card either from the deck or the discard pile if there are any, the JOptionPane showMessageDialog also informs the user whether the computer discarded any card and their point total. The computer always discards when the game is on the line. The player is then informed of the card they can draw from the discard pile and the card they can discard from their hand. They must choose to draw from either the deck or the discard pile, the showOptionDialog method then asks the player whether they would like to discard and their point total is displayed. They rotate until someone reaches the target, or if the deck runs out of cards is closest to the target or has the least number of cards. Statements that may throw an exception are placed in a try/catch block to appropriately handle the EmptyCollectionException. When the game ends, each player's hand, point total, and the winner is displayed.

### Project 4
* Proj4App.java
* MotorVehicleCollision.java
* ArrayUnorderedList.java
* ArrayList.java
* EmptyCollectionException.java
* ElementNotFoundException.java
* NYPD_Motor_Vehicle_Collisions.csv (Sample input)

This class reads data from the "NYPD_Motor_Vehicle_Collisions.csv" file and stores specific fields from each row in a MotorVehicleCollision object. If an IllegalArgumentException is thrown by the constructor or an InputMismatchException is encountered due to malformed rows, the Exception catch block handles these by reading the rest of the row and moving on. Once the data has been parsed, the user is presented with a menu with four types of analysis that can be performed by the program. The result for the number of people killed for each borough, the type of car that is most likely to have an accident in a particular borough, the number of people killed per contributing factor and the percentage for each contributing factor can be displayed in the dialog box. The field(s) of data used from each object to determine the result is displayed in the console.

### Project 5
* SFacebook.java
* Friend.java
* FriendList.java
* FriendNotFoundException.java
* Proj5App.java
* fBData.txt (Sample input)

This program reads a sequence of command from an fBData file and calls methods from the SFacebook class in order to mimic a simple version of Facebook. If a P is read from the data file, a new Friend is added to the SFacebook, F makes two people friends, U unfriends two people, L lists a person's friends, Q checks whether two people are friends, V lists a person's friends and maybe their friends depending on security level and X terminates the program.
