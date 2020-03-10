package proj5sp18;

/**
 * <p>Title: Friend Class</p>
 * 
 * <p>Description: This class represents a Friend object, each Friend can have
 * a name, securityLevel and a FriendList object which stores their friends.
 * An accessor method is defined only for the name instance variable, a Friend
 * can add friends, unfriend another friend and a friends with method checks if
 * two people are friends. The getFriendsByLevel method returns a person's 
 * friends as well as their friends if the security level is 1, if the security
 * level is 0 it returns just their friends. A getFriends and an equals are 
 * also defined.</p>
 * 
 * @author Reina Eustache
 */
public class Friend 
{
	private String name;
	private int securityLevel;
	private FriendList friends; // reference to FriendList object to store a person's friends

	/**
	 * parameterized constructor --
	 * Initializes the name and securityLevel instance variables to user specified values
	 * A new FriendList object is also instantiated using the default constructor
	 * @param inputName the String to be store in the name instance variable
	 * @param inputSecurityLevel the integer to be store in the securityLevel instance variable
	 */
	public Friend(String inputName, int inputSecurityLevel)
	{
		name = inputName;
		securityLevel = inputSecurityLevel;
		friends = new FriendList();
	}

	/**
	 * getName method --
	 * Returns the value stored in the name instance variable for that Friend object
	 * @return the String store in the name instance variable
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * addFriend --
	 * The addToFront method in the FriendList class is called on this friend's list
	 * passing over a new Friend to be added to the list. An if statement checks if
	 * the friend that is being added is not the same as this friends name to prevent
	 * someone from adding themselves as a friend
	 * @param newFriend the Friend object to be added to the FriendList
	 */
	public void addFriend(Friend newFriend)
	{
		// checks that a person is not trying to add themselves as a friend
		if(!(this.equals(newFriend)))
			friends.addToFront(newFriend);
	}

	/**
	 * unfriend method --
	 * The remove method in the FriendList class is called on this friend's list passing
	 * over a friend to be removed from the list. The friendsWith method is called to 
	 * check if that person is actually in their FriendList. If the person is not in the 
	 * list, the method does nothing.
	 * @param exFriend the Friend object to be removed from the FriendList
	 */
	public void unfriend(Friend exFriend)
	{
		// checks that the person is friends with the friend they are tying to remove
		if(friendsWith(exFriend))
			friends.remove(exFriend);
	}

	/**
	 * friendsWith --
	 * The search method is called on this person's FriendList to see if a specific
	 * Friend is present in the list, returns turn if the search method returns true
	 * that the Friend was found in the list and false if the search method returns 
	 * false that the Friend was not found in the FriendList
	 * @param maybeFriend the possible friend to look for in the FriendList
	 * @return true if the Friend is in the list and false otherwise
	 */
	public boolean friendsWith(Friend maybeFriend)
	{
		// returns what is returned by the search method in the FriendList class
		return friends.search(maybeFriend);
	}

	/**
	 * getFriends --
	 * The listOfFriends method in the FriendList class is called to get the names of 
	 * a person's friends and that String is returned
	 * @return a String containing this person's friends
	 */
	public String getFriends()
	{
		return getName() + " is friends with: " + friends.listOfFriends();
	}

	/**
	 * getFriendsByLevel --
	 * If a Friend's securityLevel is 0, only the specified Friend's friends
	 * are returned. If the Friend's security level is 1, that person's friends
	 * are returned as well as a list of each of their friend's friends
	 * @return a String containing a Friend's friends if the security level if
	 * 0 or a String of a Friend's friends and their friend's friends if the 
	 * security level is 1
	 */
	public String getFriendsByLevel()
	{
		String str = "";
		str += getFriends(); // gets a person's friends

		// concatenates a friend's friends to the String if their security level is 1
		if (securityLevel == 1)
		{
			friends.resetList();
			Friend currentFriend = null; // local variable to store the current friend whose friends are being concatenated

			// loops through the list of friend's concatenating their friends
			for(int i = 0; i < friends.size(); i++)
			{
				currentFriend = friends.getNextFriend();
				str += currentFriend.getFriends();
			}
		}

		return str;
	}
	
	/**
	 * equals method --
	 * Checks to see if the name of the person in this Friend object is the same 
	 * as the name of the Friend passed to the method. Returns true if the names
	 * are equal and false if not
	 * @param otherFriend the Friend's name to be compared
	 * @return true if the names are the same and false otherwise
	 */
	public boolean equals(Friend otherFriend)
	{
		// compares values stored in each object's name instance variable
		return this.name.equals(otherFriend.name);
	}
}
