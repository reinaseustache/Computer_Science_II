package proj5sp18;

/**
 * <p>Title: SFacebook Class</p>
 * 
 * <p>Description: This class represents an SFacebook object which contains a
 * Friend Array of size 20 referenced by theMembers and count representing the
 * number of members currently in the array. If a member is present in the 
 * SFacebook, the makeFriends and breakFriendship methods can friend two members
 * or unfriends two members. A getFriendStatus method can determine if two members
 * are friends, the getFriends method returns a String with a members Friends and
 * a getFriendsByLevel method can return their friend's friends based on security 
 * level. The private findFriend method is utilized by the other methods to determine
 * if a Friend is present in the array before performing actions on them, if they
 * are not found, a FriendNotFoundException is thrown. A toString method is also 
 * included in this class.
 * 
 * @author Reina Eustache
 */
public class SFacebook 
{
	private Friend[] theMembers; // array to hold Friend objects 
	private int count; // stores number of members in the SFacebook

	/**
	 * default constructor --
	 * Instantiates the Friend array referenced by theMembers to a size of 20
	 * and sets count to 0 indicating that there are no members in the array yet
	 */
	public SFacebook()
	{
		theMembers = new Friend[20];
		count = 0;
	}

	/**
	 * addToFacebook method --
	 * Instantiates a new Friend object passing over the user specified friendName and 
	 * friendSecurityLevel to the Friend class' parameterized constructor and stores it
	 * at the next available location in the array. The count instance variable is then
	 * incremented showing that an additional member is now in the array
	 * @param friendName the name of the new Friend to be added to the array
	 * @param friendSecurityLevel the security level of the new Friend to be added to the array
	 */
	public void addToFacebook(String friendName, int friendSecurityLevel)
	{
		theMembers[count] = new Friend(friendName, friendSecurityLevel); // adds a new Friend in the array
		count++;
	}

	/**
	 * makeFriends method --
	 * The private findFriend method is called to ensure that the names of the 
	 * two people passed to the method are members in this SFacebook before 
	 * calling the addFriend method in the Friend class to add each other as 
	 * friends
	 * @param personOne the name of one of the two people to make friends
	 * @param personTwo the name of the one of the two people to make friends
	 */
	public void makeFriends(String personOne, String personTwo)
	{
		findFriend(personOne).addFriend(findFriend(personTwo)); // adds the second person as first person's Friend
		findFriend(personTwo).addFriend(findFriend(personOne)); // adds the first person as the second person's Friend
	}

	/**
	 * breakFriendship method --
	 * The private findFriend method is called to ensure that the names of the 
	 * two people passed to the method are members in this SFacebook before 
	 * calling the unfriend method in the Friend class to remove each other as 
	 * friends
	 * @param personOne the name of one of the two people to unfriend
	 * @param personTwo the name of the one of the two people to unfriend
	 */
	public void breakFriendship(String personOne, String personTwo)
	{
		findFriend(personOne).unfriend(findFriend(personTwo)); // removes the second person as first person's Friend
		findFriend(personTwo).unfriend(findFriend(personOne)); // removes the first person as second person's Friend
	}

	/**
	 * getFriends method --
	 * The private findFriend method 
	 * @param aMember
	 * @return
	 */
	public String getFriends(String aMember)
	{
		return findFriend(aMember).getFriends();
	}

	/**
	 * 
	 * @param aMember
	 * @return
	 */
	public String getFriendsByLevel(String aMember)
	{
		return findFriend(aMember).getFriendsByLevel();
	}

	/**
	 * 
	 * @param friendOne
	 * @param friendTwo
	 * @return
	 */
	public boolean getFriendStatus(String friendOne, String friendTwo)
	{
		return findFriend(friendOne).friendsWith(findFriend(friendTwo));
	}


	/**
	 * toString --
	 * Loops through theMembers array count number of times concatenating 
	 * @return
	 */
	public String toString()
	{
		String str = "";
		for(int i = 0; i < count; i++)
		{
			str += getFriends(theMembers[i].getName());
		}
		return str;
	}

	/**
	 * 
	 * @param thisFriend
	 * @return
	 */
	private Friend findFriend(String thisFriend)
	{
		for(int i = 0; i < count; i++)
		{
			if(theMembers[i].getName().equals(thisFriend))
				return theMembers[i];
		}
		throw new FriendNotFoundException("SFacebook");
	}
}
