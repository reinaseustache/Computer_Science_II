package proj4sp18;

/**
 * <p>Title: MotorVehicleCollision class</p>
 * 
 * <p>Description: This class represents an occurrence of a car accident.
 * A MotorVehicleCollision object can store a time, borough, number of
 * persons killed, contributing factor, unique key and a vehicle type.
 * Accessor and mutator methods are defined for each instance variable 
 * along with a toString method. An equals method is included using the
 * uniqueKey which overrides the equals method in the Object class and a
 * compareTo method which overrides the compareTo method in the Comparable
 * interface.</p>
 * 
 * @author Reina Eustache
 */
public class MotorVehicleCollision implements Comparable<Object>
{
	private String time;
	private String borough;
	private int numKilled; // number of persons killed in a collision
	private String contributingFactor; // cause for the accident
	private int uniqueKey; // key field to identify a specific accident
	private String vehicleType;

	/**
	 * default constructor --
	 * initializes the time, borough, contributing factor and vehicle type to empty Strings and
	 * number of people killed and the unique key to -1.
	 */
	public MotorVehicleCollision()
	{
		time = "";
		borough = "";
		numKilled = -1;
		contributingFactor = "";
		uniqueKey = -1;
		vehicleType = "";
	}

	/**
	 * parameterized constructor --
	 * initializes the instance variables to all user specified values and throws an IllegalArgumentException
	 * if the value passed is not a valid entry, such as if the String passed for borough, contributingFactor,
	 * or vehicleType is an emptyString or the String passed to contributingFactor is "Unspecified"
	 * @param inputTime, String to be stored in time
	 * @param inputBorough, String to be stored in borough
	 * @param inputNumKilled, integer to be stored in numKilled
	 * @param inputContributingFactor, String to be stored in contributingFactor
	 * @param inputUniqueKey, integer to be stored in uniqueKey
	 * @param inputVehicleType, String to be stored in vehicleType
	 * @throws IllegalArgumentException if the value passed for a specified field is not a valid entry
	 */
	public MotorVehicleCollision(String inputTime, String inputBorough, int inputNumKilled, String inputContributingFactor, 
			int inputUniqueKey, String inputVehicleType) throws IllegalArgumentException
	{
		if (inputBorough.equals("") || inputContributingFactor.equals("Unspecified") || inputContributingFactor.equals("") || inputVehicleType.equals(""))
			throw new IllegalArgumentException();

		time = inputTime;
		borough = inputBorough;
		numKilled = inputNumKilled;
		contributingFactor = inputContributingFactor;
		uniqueKey = inputUniqueKey;
		vehicleType = inputVehicleType;
	}

	/**
	 * getTime --
	 * retrieves the value stored in the time instance variable for the object
	 * @return the time for that collision
	 */
	public String getTime()
	{
		return time;
	}

	/**
	 * getBorough --
	 * retrieves the value stored in the borough instance variable for the object
	 * @return the borough for that collision
	 */
	public String getBorough()
	{
		return borough;
	}

	/**
	 * getNumKilled --
	 * retrieves the value stored in the numKilled instance variable for the object
	 * @return the number of people killed for that collision
	 */
	public int getNumKilled()
	{
		return numKilled;
	}

	/**
	 * getContributingFactor --
	 * retrieves the value stored in the contributingFactor instance variable for the object
	 * @return the contributing factor for that collision
	 */
	public String getContributingFactor()
	{
		return contributingFactor;
	}

	/**
	 * getUniqueKey --
	 * retrieves the value stored in the uniqueKey instance variable for the object
	 * @return the unique key for that collision
	 */
	public int getUniqueKey()
	{
		return uniqueKey;
	}

	/**
	 * getVehicleType --
	 * retrieves the value stored in the vehicleType instance variable for the object
	 * @return the type of vehicle for that collision
	 */
	public String getVehicleType()
	{
		return vehicleType;
	}

	/**
	 * setTime method --
	 * stores the user specified value in the time instance variable
	 * @param inputTime the time to be stored
	 */
	public void setTime(String inputTime)
	{
		time = inputTime;
	}

	/**
	 * setBorough method --
	 * stores the user specified value in the borough instance variable
	 * @param inputBorough the borough to be stored
	 */
	public void setBorough(String inputBorough)
	{
		borough = inputBorough;
	}

	/**
	 * setNumKilled method --
	 * stores the user specified value in the numKilled instance variable
	 * @param inputNumKilled the number of people killed to be stored
	 */
	public void setNumKilled(int inputNumKilled)
	{
		numKilled = inputNumKilled;
	}

	/**
	 * setContributingFactor method --
	 * stores the user specified value in the contributingFactor instance variable
	 * @param inputContributingFactor the contributing factor to be stored
	 */
	public void setContributingFactor(String inputContributingFactor)
	{
		contributingFactor = inputContributingFactor;
	}

	/**
	 * setUniqueKey method --
	 * stores the user specified value in the uniqueKey instance variable
	 * @param inputUniqueKey the key to be stored
	 */
	public void setUniqueKey(int inputUniqueKey)
	{
		uniqueKey = inputUniqueKey;
	}

	/**
	 * setVehicleType method --
	 * stores the user specified value in the vehicleType instance variable
	 * @param inputVehicleType the type of vehicle to be stored
	 */
	public void setVehicleType(String inputVehicleType)
	{
		vehicleType = inputVehicleType;
	}

	/**
	 * equals method --
	 * overrides the equals method in the Object class, determines if two MotorVehicleCollision
	 * objects have the same value for uniqueKey
	 * @param theCollision, reference to the collision to be compared
	 * @return true if two MotorVehicleCollision objects have the same uniqueKey, and false if not
	 */
	public boolean equals(Object theCollision)
	{
		if (this.uniqueKey == ((MotorVehicleCollision)theCollision).uniqueKey)
			return true;
		return false;
	}

	/**
	 * compareTo method --
	 * overrides the compareTo method in the Comparable interface, determines if the 
	 * collision object the method is called on's unique key is less than, greater 
	 * than or equal to the collision object passed to theCollision
	 * @param theCollision, collision to be compared
	 * @return 0 if the keys are equal, a negative number if this' key is less than
	 * theCollision's and a positive number if this' key is greater than theCollision's
	 */
	public int compareTo(Object theCollision)
	{
		String keyStr = this.uniqueKey + ""; // converts this' uniqueKey to a String to be compared
		String otherKeyStr = ((MotorVehicleCollision)theCollision).uniqueKey + ""; ; // converts theCollision's uniqueKey to a String
		return keyStr.compareTo(otherKeyStr);
	}

	/**
	 * toString --
	 * returns a String containing the state of the MotorVehicleCollision object
	 * @return the String containing the values for the time, borough, numKilled, 
	 * contributingFactor, uniqueKey and vehicleType instance variables
	 */
	public String toString()
	{
		return "Time: " + time + ", Borough: " + borough + ", Persons Killed: " + numKilled + ", Contributing Factor: " + 
				contributingFactor + ", Unique Key: " + uniqueKey + ", Vehicle Type: " + vehicleType;
	}
}