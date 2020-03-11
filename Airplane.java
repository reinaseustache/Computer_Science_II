package proj1sp18;
import javax.swing.JOptionPane;

/**
 * <p>Title: The Airplane Class</p>
 *
 * <p>Description: Airplane objects can store an array (seats)
 * containing 10 Seat object with 4 First Class and 6 Coach class
 * seats. The makeAReservation method allows the user to choose from
 * a list of only available seats in the specified class and the 
 * cancelAReservation method lets the user choose from seats that are
 * currently reserved using the showOptionDialog method in the 
 * JOptionPane class. A toString method can also display the state 
 * of all the seats in the array with their availability.</p>
 * 
 * @author Reina Eustache
 */
public class Airplane 
{
	private Seat[] seats; // instance variable to store an array of seats

	/**
	 * default constructor --
	 * creates a Seat array that can hold 10 Seat objects,
	 * and calling the parameterized constructor in the Seat
	 * class, the first seats are numbered from 1-4 and set as
	 * First Class seats and the rest of the seats are 
	 * numbered from 5-10 and set as Coach class seats
	 */
	public Airplane()
	{
		// instantiates a Seat array whose size is 10
		seats = new Seat[10];

		// numbers the seats for 1 to 10 and stores First class Seat objects in 
		// the first 4 indexes of the array and Coach class seats in the rest
		for (int i = 0; i < seats.length; i++)
		{
			if (i < 4)
				seats[i] = new Seat(i + 1, "First Class");
			else
				seats[i] = new Seat(i + 1, "Coach Class");
		}
	}

	/**
	 * makeAReservation --
	 * the JOptionPane showOptionDialog method uses the classChoices array to
	 * prompt the user to choose whether they would like a First Class or a 
	 * Coach Class seat then based on their choice, an array of available seats
	 * is created with either availableFirstSeats or availableCoachSeats and 
	 * displayed as choices again using the showOptionDialog method, the 
	 * reserveSeat method in the Seat class then reserves the users seat choice 
	 * and message dialog tells the user that the seat was successfully reserved. 
	 * If there are no available seats to put into newSeatsArray, a message dialog
	 * tells the use that there are no more seats available in the respective seat 
	 * class
	 */
	public void makeAReservation()
	{
		int seatChoice, classChoice; // local variables to store the users choice
		int availableFirstSeats = 0; // variable to store the number of available first class seats
		int availableCoachSeats = 0; // variable to store the number of available coach class seats
		String[] newSeatsArray; // array to store seat numbers to display to the user

		// prompts the user to choose which class they would like to reserve a seat from using a dialog box
		String[] classChoices = {"First Class", "Coach Class"}; 
		classChoice = JOptionPane.showOptionDialog(null, "Which class seats would you like to view?", 
				"Reservation System Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
				null, classChoices, classChoices[0]);

		// loops through the array of seats and checks if the seat at each location is empty
		// and depending on the seats class increments either availableFirstSeats or availableCoachSeats
		for (int i = 0; i < seats.length; i++)
		{
			if (seats[i].isEmpty() && seats[i].getSeatType().equals("First Class"))
				availableFirstSeats++;
			else if (seats[i].isEmpty() && seats[i].getSeatType().equals("Coach Class"))
				availableCoachSeats++;          
		}

		// executes if the user chose "First Class" from the option dialog
		if (classChoice == 0)
		{
			// instantiates a new String array with the size of the available first class seats
			newSeatsArray = new String[availableFirstSeats];

			// loops through both the first four seats in the seats array and the newSeatsArray and extracts the seat
			// number from that index and puts it into the leftmost index in newSeatsArray if the seat at that index is empty
			int j = 0;
			for (int i = 0; i < newSeatsArray.length; i++)
			{
				if (seats[j].isEmpty())
					newSeatsArray[i] = seats[j].getSeatNumber() + "";
				else
					i--;
				if (j <= 3)
					j++;
			}

			// if there are no seat objects in newSeatsArray because they are all occupied, a message dialog box
			// informs the user so or otherwise the empty first class seats are displayed to the user and their choice
			// is sent to the reserveSeat method in the Seat class and a successful message is shown to the user
			if (newSeatsArray.length == 0)
				JOptionPane.showMessageDialog(null, "Sorry, all of the First Class seats have been booked.");
			else
			{
				seatChoice = JOptionPane.showOptionDialog(null, "Which seat would you like to reserve?", "Reservation System Menu", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, newSeatsArray, newSeatsArray[0]);
				for (int i = 0; i < seats.length; i++)
				{
					if (newSeatsArray[seatChoice].toString().equals(seats[i].getSeatNumber() + ""))
					{
						seats[i].reserveSeat();
						JOptionPane.showMessageDialog(null, "Reservation for seat " + (i + 1) + " in First Class was successful.");
					}
				}  
			}
		}
		else // executes if the user chooses "Coach Class" from the option dialog
		{
			// instantiates a new String array with the size of the available coach class seats
			newSeatsArray = new String[availableCoachSeats];

			// loops through both the last six seats in the seats array and newSeatsArray and extracts the seat
			// number from that index and puts it into the leftmost index in newSeatsArray if the seat at that 
			// index is empty
			int j = 4;
			for (int i = 0; i < newSeatsArray.length; i++)
			{
				if (seats[j].isEmpty() && seats[j].getSeatType().equals("Coach Class"))
					newSeatsArray[i] = seats[j].getSeatNumber() + "";
				else
					i--;
				if (j < 9)
					j++;
			}

			// if there are no seat objects in newSeatsArray because they are all occupied, a message dialog box
			// informs the user so or otherwise the empty coach class seats are displayed to the user and their choice
			// is sent to the reserveSeat method in the Seat class and a successful message is shown to the user
			if (newSeatsArray.length == 0)
				JOptionPane.showMessageDialog(null, "Sorry, all of the Coach Class seats have been booked.");
			else
			{
				seatChoice = JOptionPane.showOptionDialog(null, "Which seat would you like to reserve?", "Reservation System Menu", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, newSeatsArray, newSeatsArray[0]);
				for (int i = 0; i < seats.length; i++)
				{
					if (newSeatsArray[seatChoice].toString().equals(seats[i].getSeatNumber() + ""))
					{
						seats[i].reserveSeat();
						JOptionPane.showMessageDialog(null, "Reservation for seat " + (i + 1) + " in Coach Class was successful.");
					}
				}
			}
		}
	}

	/**
	 * cancelAReservation --
	 * an array of occupied seats is created with the number of seats that return
	 * false when the isEmpty method is called and that array is displayed as 
	 * choices using the showOptionDialog method in the JOptionPane class, the 
	 * cancelSeat method in the Seat class then cancels the users seat choice 
	 * and message dialog tells the user that the seat was successfully cancelled. 
	 * If there are no occupied seats to put into newSeatsArray, a message dialog
	 * tells the use that there are no seats to cancel since they are all available
	 */
	public void cancelAReservation()
	{
		int userChoice; // local variable to store the users choice
		int occupiedSeats = 0; // variable to store the number of occupied seats
		String[] newSeatsArray; // array to store seat numbers to display to the user

		// loops through the array of seats and checks if the seat at each location is not empty
		// if the method returns false, occupiedSeats is incremented
		for (int i = 0; i < seats.length; i++)
		{
			if (!seats[i].isEmpty())
				occupiedSeats++;
		}

		// instantiates a new String array with the size of the occupied seats
		newSeatsArray = new String[occupiedSeats];

		// loops through both the seats array and newSeatsArray and extracts the seat number from that 
		// index and puts it into the leftmost index in newSeatsArray if the seat at that index is occupied
		int j = 0;
		for (int i = 0; i < newSeatsArray.length; i++)
		{
			if (!seats[j].isEmpty())
				newSeatsArray[i] = seats[j].getSeatNumber() + "";
			else
				i--;
			if (j < seats.length)
				j++;
		}

		// if there are no seat objects in newSeatsArray because they are all available, a message dialog box
		// informs the user so or otherwise the occupied seats are displayed to the user and their choice
		// is sent to the cancelSeat method in the Seat class and a successful message is shown to the user
		if (newSeatsArray.length == 0)
			JOptionPane.showMessageDialog(null, "Sorry, no seats could be cancelled because all of the seats are available.");
		else
		{
			userChoice = JOptionPane.showOptionDialog(null, "Which seat would you like to cancel?", "Cancellation System Menu", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, newSeatsArray, newSeatsArray[0]);
			for (int i = 0; i < seats.length; i++)
			{
				if (newSeatsArray[userChoice].toString().equals(seats[i].getSeatNumber() + ""))
				{
					seats[i].cancelSeat();
					JOptionPane.showMessageDialog(null, "Cancellation for seat " + (i + 1) + " in Coach Class was successful.");
				}
			} 
		}

	}

	/**
	 * toString --
	 * returns the state of all the seats in the array by calling
	 * the toString method on every Seat object which shows the 
	 * seat number, class and availability and concatenating
	 * it to the String returned for the next Seat object on a 
	 * new line
	 * @return returns the state of the Airplane object by relying on
	 * the String returned by the toString method for each Seat object
	 */
	public String toString()
	{
		String str = "";

		// loops through the seats array and concatenates the String returned
		// by the toString method in the Seat class for each Seat object on a
		// new line
		for (int i = 0; i < seats.length; i++)
			str += seats[i].toString() + "\n";

		return str;
	}
}
