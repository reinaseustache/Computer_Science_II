package proj3sp18;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * <p>Title: Project 3 Application Class</p>
 * 
 * <p>Description: This class accepts a value greater than or equal to 25 from the 
 * user for their desired point value target. The user gets 3 opportunities to 
 * enter a valid target, if they fail to do so, the program terminates. Once the 
 * target is set, the computer goes and draws a card either from the deck or the discard
 * pile if there are any, the JOptionPane showMessageDialog also informs the user whether
 * the computer discarded any card and their point total. The computer always discards 
 * when the game is on the line. The player is then informed of the card they can draw
 * from the discard pile and the card they can discard from their hand. They must choose
 * to draw from either the deck or the discard pile, the showOptionDialog method then
 * asks the player whether they would like to discard and their point total is displayed.
 * They rotate until someone reaches the target, or if the deck runs out of cards is
 * closest to the target or has the least number of cards. Statements that may throw an 
 * exception are placed in a try/catch block to appropriately handle the EmptyCollectionException. 
 * When the game ends, each player's hand, point total, and the winner is displayed.</p>
 * 
 * @author Reina Eustache
 */
public class Proj3App 
{
	public static void main(String[] args)
	{
		int target = 0; // variable to store what the user enters for a point target
		int invalidInput = 0; // counter for the number of times the user enters an invalid input
		boolean done = false; // variable to know if the user has successfully entered a valid target

		// loops if the user has not successfully entered a valid target and has not exceeded their number of tries
		while (invalidInput < 3 && !done)
		{
			try
			{
				// prompts the user to enter a target and converts the string returned by the method to an integer
				target = Integer.parseInt(JOptionPane.showInputDialog("Please enter a target greater "
						+ "than or equal to 25:"));

				if (target < 25)
					throw new NumberFormatException();

				// executes if the previous statements have not thrown any exceptions indicating the user has entered valid input
				done = true;
			}
			catch (NumberFormatException ex)
			{
				if (invalidInput < 2) // allows the user to try again if their input was invalid
				{
					JOptionPane.showMessageDialog(null, "ERROR! Please enter a valid target.");
					invalidInput++;
				}
				else // lets the user know they have used up their 3 tries and terminates program
				{
					invalidInput++;
					JOptionPane.showMessageDialog(null, "The target was not successfully set. Program terminating...");
				}
			}
		}

		// executes only if the user entered a valid target at some point during their tries
		if(done)
		{
			// instantiates and shuffles a new Deck of Card 
			Deck theDeck = new Deck();
			theDeck.shuffleDeck();

			// instantiates new object to store the player and the computer's hand and a discard pile
			Hand playerHand = new Hand();
			Hand computerHand = new Hand();
			DiscardPile thePile = new DiscardPile();

			// variables to store what the player chose from JOptionPane showOptionDialog method
			int dealFromWhere = 0;
			int chooseToDiscard = 0;

			// variables to store the card dealt by the player or the computer
			Card card1 = null;
			Card card2 = null;

			// used to randomly choose if the computer deals from the deck or the discard pile and who goes first for each game
			Random rand = new Random();
			int randomCompChoice;
			int whoGoes = rand.nextInt(2);

			// variable to keep track of how many cards have been dealt from to deck to prevent dealing from an empty deck
			int numDealtCards = 0;

			// rotates between the computer and the player until one of them reaches the target or the deck is empty
			while (playerHand.totalPoints() != target && computerHand.totalPoints() != target && numDealtCards != 52)
			{
				// executes for the computer's turn if the random number generated for the first turn was 0 or was set to 0 after player's turn and the deck is not empty
				if(numDealtCards != 52 && whoGoes == 0)
				{
					JOptionPane.showMessageDialog(null, "It's the computer's turn to go.");

					// generates two random numbers to decide if the computer draws from the deck or the discard pile
					randomCompChoice = rand.nextInt(2);

					if (randomCompChoice == 0)
					{
						// a new card is dealt and its reference is stored in card1, numDealtCards is incremented
						card1 = theDeck.dealCard();
						numDealtCards++;
						JOptionPane.showMessageDialog(null, "The computer went and drew the " + card1 + " from the deck.");
					}
					else
					{
						// if the number generated was 1, the computer tries to remove the top card from the discard pile and if
						// that method throws an exception because it is empty, the computer defaults to dealing from the deck
						try
						{
							card1 = thePile.removeCard();
							JOptionPane.showMessageDialog(null, "The computer went and drew the " + card1 + " from the discard pile.");
						}
						catch (EmptyCollectionException ex)
						{
							card1 = theDeck.dealCard();
							numDealtCards++;
							JOptionPane.showMessageDialog(null, "The computer went and drew the " + card1 + " from the deck.");
						}
					}

					// as long as the computer's total points is less than or equal to the target, the new card that was drawn is always added to its hand
					// in order to keep getting as close as possible to the target
					if((card1.getPointValue() + computerHand.totalPoints()) <= target)
					{
						computerHand.addCard(card1);
						JOptionPane.showMessageDialog(null, "The computer did not discard any of its cards."
								+ " It has a point total of: " + computerHand.totalPoints());
					}
					else
					{
						// if the card that was dealt makes the computer's total exceed the target, the computer always discards the previous card and the
						// card that was just dealt and was stored in card1 is then added to the hand
						thePile.addCard(computerHand.removeCard()); // the previous card is removed from the hand and added to the discard pile
						computerHand.addCard(card1); // the new card is then added to the hand
						JOptionPane.showMessageDialog(null, "The computer discarded the " + thePile.topCard() +
								" from last time. It has a point total of: " + computerHand.totalPoints());
					}
					whoGoes = 1; // sets whoGoes to 1 which means that the computer's turn is done and the player can now go
				}

				// executes for the player's turn if the random number generated for the first turn was 1 or was set to 1 after computer's turn and the deck is not empty
				if (computerHand.totalPoints() != target && numDealtCards != 52 && whoGoes == 1)
				{
					JOptionPane.showMessageDialog(null, "It's your turn. You now has a point total of: " + playerHand.totalPoints());

					// tells user what card they can discard, if any
					if(playerHand.numCards() == 0)
						JOptionPane.showMessageDialog(null, "There are no cards in your hand to discard at this time.");
					else
						JOptionPane.showMessageDialog(null, "From your hand, you can discard the " + playerHand.topCard() + ".");

					// tells the user what card they can draw from the discard pile, if any
					try
					{
						JOptionPane.showMessageDialog(null, "From the discard pile, you can take the " + thePile.topCard() + ".");
					}
					catch (EmptyCollectionException ex)
					{
						JOptionPane.showMessageDialog(null, "The discard pile is empty and cannot be drawn from right now.");
					}

					// displays buttons asking the user where they would like to draw from
					try
					{
						thePile.topCard(); // if this method throws an exception it means that drawing from the discard pile is not an option, otherwise we give the user both options
						String[] choices = {"Card from deck", "Card from discard pile"};
						dealFromWhere = JOptionPane.showOptionDialog(null, "From where would you like to choose a card?", "Main Menu", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
					}
					catch (EmptyCollectionException ex)
					{
						// gives the user the option of drawing only from the deck if the topCard method called on the discard pile threw an exception in the try block
						String[] choices = {"Card from deck"};
						dealFromWhere = JOptionPane.showOptionDialog(null, "From where would you like to choose a card?", "Main Menu", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
					}

					// executes if the user chooses to draw from the deck
					if (dealFromWhere == 0)
					{
						// a new card is dealt  from the deck and stored in card2 and the number of cards dealt stored in numDealtCards is incremented
						card2 = theDeck.dealCard();
						numDealtCards++;
						JOptionPane.showMessageDialog(null, "You drew the " + card2 + " from the deck. Your total is now: "
								+ (playerHand.totalPoints() + card2.getPointValue()));
					}
					else if (dealFromWhere == 1) // if user chooses to draw from discard pile
					{
						// the top most card is removed from the discard pile can stored in card2
						card2 = thePile.removeCard();
						JOptionPane.showMessageDialog(null, "You drew the " + card2 + " from the discard pile. Your total is now: "
								+ (playerHand.totalPoints() + card2.getPointValue()));
					}

					try
					{
						playerHand.topCard(); // if this does not throw an exception that means the user does have the option to remove a previous card
						String[] choices = {"Yes", "No"};
						chooseToDiscard = JOptionPane.showOptionDialog(null, "Would you like to discard the " + playerHand.topCard() + " from last time?", "Main Menu", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
						
						// executes if player chooses yes to discarding
						if(chooseToDiscard == 0)
						{
							// removes the topmost card from the players hand and adds it to the discard pile, and the card that was dealt for this turn and stored in card2
							// is now added to the player's hand
							thePile.addCard(playerHand.removeCard());
							playerHand.addCard(card2);
							JOptionPane.showMessageDialog(null, "Your point total is now: " + playerHand.totalPoints());
						}
						if(chooseToDiscard == 1) // does not want to discard
						{
							// the card that was just dealt for this turn is just added to the player's hand
							playerHand.addCard(card2);
							JOptionPane.showMessageDialog(null, "Your point total is now: " + playerHand.totalPoints());
						}
					}
					catch(EmptyCollectionException ex)
					{
						// if there were no previously added cards and the topCard method threw an exception, the user does not asked if they want to discard 
						// and the new card that was dealt is just added to the hand
						playerHand.addCard(card2);
						JOptionPane.showMessageDialog(null, "This turn is done. Your point total is now: " + playerHand.totalPoints());
					}
					whoGoes = 0; // sets whoGoes to 0 which means that the player's turn is done and the computer can now go
				}
			}

			String playerCards = ""; // String to store cards currently in the player's hand
			String computerCards = "";  // String to store cards currently in the computer's hand
			String winner = ""; // String to store who the winner was
			int computerPoints = computerHand.totalPoints(); // gets the computer's final point total and stores it since this number will change as the cards are removed from the hand to display
			int playerPoints = playerHand.totalPoints(); // gets the player's final point total and stores it since this number will change as the cards are removed from the hand to display
			int numPlayerCards = playerHand.numCards(); // gets the player's number of cards and stores it since this number will change as the cards are removed from the hand to display
			int numComputerCards = computerHand.numCards(); // gets the computer's number of cards and stores it since this number will change as the cards are removed from the hand to display

			if(computerHand.totalPoints() == target) //sets the computer to the winner if they reached the target exactly
				winner = "Game Over...THE COMPUTER WON.";
			else if(playerHand.totalPoints() == target) //sets the player to the winner if they reached the target exactly
				winner = "Game Over...YOU WON!!";
			else if((target - computerHand.totalPoints()) < (target - playerHand.totalPoints()) && !(computerHand.totalPoints() > target)) // otherwise checks if the computer is closest to the target but not over it
				winner = "Game Over...THE COMPUTER WON.";
			else if((target - playerHand.totalPoints()) < (target - computerHand.totalPoints()) && !(playerHand.totalPoints() > target)) // checks if the player is closest to the target but not over it
				winner = "Game Over...YOU WON!!";
			else if(numComputerCards < numPlayerCards) // otherwise checks if the computer has the least amount of cards
				winner = "Game Over...THE COMPUTER WON.";
			else if(numPlayerCards < numComputerCards) // checks if the player has the least amount of cards
				winner = "Game Over...YOU WON!!";
			else // in any other case its a tie
				winner = "Game Over...IT'S A TIE!!";

			// loops through the player's number of cards and concatenates each card as they are removed from the hand
			for (int i = 0; i < numPlayerCards; i++)
			{
				playerCards += " " + playerHand.removeCard().toString() + ",";
				if (i == 13 || i == 26 || i == 39) // adds new lines so that the JOptionPane wraps in a better format if there are a lot of cards
					playerCards += "\n\t";
			}
			
			// loops through the computer's number of cards and concatenates each card as they are removed from the hand
			for (int i = 0; i < numComputerCards; i++)
			{
				computerCards += " " + computerHand.removeCard().toString() + ",";
				if (i == 13 || i == 26 || i == 39) // adds new lines so that the JOptionPane wraps in a better format if there are a lot of cards
					computerCards += "\n\t";
			}

			JOptionPane.showMessageDialog(null, winner + "\n\nComputer's hand: " + computerCards + "\nComputer's point total: " + computerPoints
					+ ".\n\nPlayer's hand: " + playerCards + "\nYour point total: " + playerPoints + ".");
		} 
	}
}