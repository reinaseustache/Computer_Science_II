package proj1sp18;
import javax.swing.JOptionPane;

/**
 * <p>Title: Project 1 Application class</p>
 *
 * <p>Description: This class creates a new airplane object and using
 * the showOptionDialog method in the JOptionPane class shows the user
 * options to either make a reservation, cancel a reservation, show a
 * seating chart or quit from the array referenced by choices. The 
 * users choice value is stored in the integer choice. If the user chooses
 * to make a reservation (0), the makeAReservation method is called on the
 * Airplane object, if they choose to cancel a reservation, the 
 * cancelAReservation method is called, to display a seating chart, the
 * toString method is called. This menu is displayed and the appropriate
 * method is called until the user chooses quit.</p>
 * 
 * @author Reina Eustache
 */
public class Proj1App 
{
	public static void main(String[] args)
	{
		// Code used to test methods in the Seat Class to ensure they work correctly
		// Seat theSeat = new Seat(6, "Coach Class");
		// System.out.println(theSeat.toString());
		// theSeat.reserveSeat();
		// System.out.println(theSeat.toString());
		// System.out.println(theSeat.isEmpty());
		// theSeat.cancelSeat();
		// System.out.println(theSeat.toString());
		// System.out.println(theSeat.getSeatNumber());
		// System.out.println(theSeat.getSeatType());

		Airplane thePlane = new Airplane(); // a new Airplane object referenced by thePlane is instantiated by calling the default constructor
		int choice; // variable to store users choice from main menu output

		do
		{
			// prompts the user to choose from a set of options whether they would like to make a reservation,
			// cancel one, show a seating chart or quit, the users choice is stored in the integer variable choice
			String[] choices = {"Make a Reservation", "Cancel a Reservation", "Display a Seating Chart", "Quit"};
			choice = JOptionPane.showOptionDialog(null, "Please choose from the following options...", "Main Menu", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

			// depending on what the user chose from the main menu, the appropriate method is called on the Airplane object
			if (choice == 0)
				thePlane.makeAReservation();
			else if (choice == 1)
				thePlane.cancelAReservation();
			else if (choice == 2)
				JOptionPane.showMessageDialog(null, thePlane.toString());
			
		} while (choice != 3); // keeps looping until the user chooses quit
	}
}