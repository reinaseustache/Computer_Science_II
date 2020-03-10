package proj5sp18;

/**
 * <p>Title: FriendList class</p>
 * 
 * <p>Description: This class represents a FriendList object capable 
 * of storing references to Friend objects in a Linked List. The instance 
 * variable first, serves as the external link to the list. A new Friend can
 * be added at the beginning of the the list as well as removed from the 
 * list with an empty sentinel Node eliminating the special case for these 
 * actions. A resetList method sets currentPos back to the beginning of the 
 * list and getNextFriend gets the Friend at the next Node. A listOfFriends,
 * search, size and isEmpty method are also defined for this class.</p>
 * 
 * @author Reina Eustache
 */
public class FriendList 
{
	private Node<Friend> first; // external link to the list
	private int count; // stores the number of friends in the list
	private Node<Friend> currentPos; // stores a current position in the list

	/**
	 * default constructor --
	 * Initializes the first, count and currentPos instance variables. First is
	 * initially set to an empty Node that acts as a sentinel for the linked list
	 */
	public FriendList()
	{
		first = new Node<Friend>(); // instantiates a sentinel Node containing null for the list
		count = 0;
		currentPos = null;
	}

	/**
	 * size method --
	 * Returns the state of the count instance variable which is the 
	 * number of Friend objects currently in the linked list
	 * @return an integer of the number of Friends in the list
	 */
	public int size()
	{
		return count;
	}

	/**
	 * isEmpty method --
	 * Checks to see if there are any Friends in the list, returns true if 
	 * there are no friends in the list and false if there is at least one 
	 * Friend in the list
	 * @return true if the list has no friends, false otherwise
	 */
	public boolean isEmpty()
	{
		return (count == 0);
	}

	/**
	 * addToFront method --
	 * Adds a Node containing a new Friend object at the beginning of the
	 * list after the empty sentinel Node and links the new Node to the 
	 * rest of the list, the count instance variable is also incremented
	 * @param newFriend the new Friend Object to be added to the the list
	 */
	public void addToFront(Friend newFriend)
	{
		// sets the next location of the dummy Node to a Node containing 
		// newFriend and the Friend Node that used to be at the front of
		// the list in the new Node's next location
		first.setNext(new Node<Friend>(newFriend, first.getNext()));
		count++;
	}

	/**
	 * remove --
	 * loops through the linked list until targetFriend is found and removes 
	 * the Node containing that Friend. The local variable current refers to 
	 * the Node currently being compared to targetFriend and prev refers to the
	 * Node before it, once the Friend to remove is found, the next location of
	 * prev is set to the Node following current. The Friend in current is then
	 * returned
	 * @param targetFriend the Friend to be taken out of the list
	 * @return the reference to the Friend that was taken out of the list
	 */
	public Friend remove (Friend targetFriend)
	{
		Node<Friend> current = first.getNext(); // initially refers to the Friend at the beginning of the list 
		Node<Friend> prev = first; // initially refers to the dummy Node
		boolean found = false; // checks if the Friend to delete was found

		// loops if the Friend was not and deleted and we are not at the end of the list
		while (!found && current != null)
		{
			// checks if the friend's name in the current matches the target Friend's name and if so removes that Node
			// decrements count and sets found to true which ends the loop
			if (current.getItem().getName().equals(targetFriend.getName()))
			{
				found = true;
				prev.setNext(current.getNext());
				count--;
			}
			else
			{
				// moves prev and current over to check the next Node
				prev = current;
				current = current.getNext();
			}
		}
		
		// returns the Friend in the Node that was deleted
		return current.getItem();
	}
	
	/**
	 * resetList method --
	 * sets the currentPos instance variable back to the dummy Node at the beginning of the list
	 */
	public void resetList()
	{
		currentPos = first;
	}
	
	/**
	 * getNextFriend method --
	 * Move the currenPos instance variable over to the next Node and returns the Friend
	 * at that location
	 * @return the reference to the Friend in the next Node
	 */
	public Friend getNextFriend()
	{
		currentPos = currentPos.getNext();
		return currentPos.getItem();
	}
	
	/**
	 * search method --
	 * Loops through the linked list of Friends and compares aFriend's name
	 * to another Friend's name in a current Node and if they are equal, 
	 * returns true, if the Friend was not found it returns false
	 * @param aFriend the Friend to look for in the list
	 * @return true if the Friend was found and false otherwise
	 */
	public boolean search(Friend aFriend)
	{
		Node<Friend> current = first.getNext(); // temporary variable to traverse through the list
		
		// loops until the end of the list and if the Friend was not found to compare each
		// Friend's name to aFriend's
		while(current != null)
		{
			if(current.getItem().getName().equals(aFriend.getName()))
				return true;
			current = current.getNext();
		}
		return false;
	}
	
	/**
	 * listOfFriends method --
	 * Loops through the whole linked list and concatenates the names of each Friend
	 * in the list to a String which is then returned by the method
	 * @return the names of the Friends in the list
	 */
	public String listOfFriends()
	{
		String str = "";
		Node<Friend> current = first.getNext(); // temporary variable to traverse through the list
		
		while(current != null)
		{
			// adds the name of the Friend in the current Node to the String then moves
			// current over to the next Node
			str += current.getItem().getName() + " ";
			current = current.getNext();
		}

		return str + "\n";
	}
}
