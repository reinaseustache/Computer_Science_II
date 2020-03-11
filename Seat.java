package proj1sp18;

/**
 * <p>Title: The Seat Class</p>
 *
 * <p>Description: Seat objects can store a seatNumber of type
 * integer as well as a seatType and seatStatus of type String.
 * The class contains accessors methods for the seatNumber and 
 * seatType instance variables which return the current state of
 * the variable. Methods that check if seatStatus is empty, reserve
 * a seat, cancel a seat, along with a toString method are also 
 * provided.</p>
 * 
 * @author Reina Eustache
 */
public class Seat 
{
	private String seatType; // instance variable to store seat class as a String
	private String seatStatus; // instance variable to store seat status/availability as a String
	private int seatNumber; // instance variable to store seat number as an integer

	/**
	 * parameterized constructor --
	 * accepts values for the seat number and the type of seat and 
	 * stores them in the appropriate instance variable, when a 
	 * seat object is first created, seatStatus is initially set
	 * to empty 
	 * @param inputNumber - integer value to be stored in seatNumber
	 * @param inputType - String value to be stored in seatType
	 */
	public Seat(int inputNumber, String inputType)
	{
		// stores the values the user enter for seat number and seat class
		// in the appropriate instance variable and sets seatStatus to empty
		seatNumber = inputNumber;
		seatType = inputType;
		seatStatus = "Empty";
	}

	/**
	 * getSeatNumber --
	 * accessor method for seatNumber that returns the
	 * integer stored as the seat number in a Seat object
	 * @return returns the integer value stored in seatNumber
	 */
	public int getSeatNumber()
	{
		return seatNumber;
	}

	/**
	 * getSeatType --
	 * accessor method for seatType that returns the seat class/type
	 * of either first or coach class in a Seat object as a String
	 * @return returns the reference to the String containing the
	 * seat class
	 */
	public String getSeatType()
	{
		return seatType;
	}

	/**
	 * isEmpty --
	 * checks if the String stored in seatStatus is equal to 
	 * "Empty" and if it is, the method returns true, otherwise
	 * the method returns false
	 * @return returns true if the seat is empty and false if it is 
	 * occupied
	 */
	public boolean isEmpty()
	{
		// compares the String stored in seatStatus to the String "Empty" 
		// and if they are equal return true and otherwise false
		if (seatStatus.equals("Empty"))
			return true;
		else
			return false;
	}

	/**
	 * reserveSeat --
	 * stores the String "Occupied" in the seatStatus instance
	 * variable meaning that the seat the method is called on is 
	 * now taken and can no longer be reserved again by another
	 * user
	 */
	public void reserveSeat()
	{
		seatStatus = "Occupied"; // sets seatStatus to occupied
	}

	/**
	 * cancelSeat --
	 * stores the String "Empty" in the seatStatus instance variable
	 * meaning that the seat the method is called on is now available
	 * and can be reserved by another user
	 */
	public void cancelSeat()
	{
		seatStatus = "Empty"; // sets setStatus to empty
	}

	/**
	 * toString --
	 * returns a reference to String with the values for
	 * seatNumber followed by seatType then seatStatus for 
	 * the Seat object
	 * @return a String containing the state of the instance
	 * variables in the Seat object
	 */
	public String toString()
	{
		// concatenates the values stored in each instance variable
		// and returns it in a sentence
		return "Seat " + seatNumber + " in " + seatType + " is currently " + seatStatus;
	}
}
