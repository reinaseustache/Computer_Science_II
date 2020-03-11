package proj3sp18;
  
/**
 * <p>Title: The Hand Class</p>
 * 
 * <p>Description: This class represents a playing card hand. A linked
 * list to store references to card objects in the hand. A new card 
 * can be added to the top of the list with the addCard method. Without
 * altering the player's hand, the top-most card can be returned with the 
 * topCard method and the removeCard method removes the top-most card and 
 * returns the reference to that card. The numCards method returns a count
 * of the number of cards in the  hand and the totalPoints method calculates
 * the point total of all the cards in the player's hand. An EmptyCollectionException 
 * can be thrown if the pile is empty.</p>
 * 
 * @author Reina Eustache
 */
public class Hand 
{
    private Node<Card> top; // instance variable for the linked list
      
    /**
     * default constructor --
     * creates an empty card hand by setting the top instance variable to null
     */
    public Hand()
    {
        top = null;
    }
      
    /**
     * addCard -- 
     * stores the new card passed to the method at the top of the hand.
     * A new Node is inserted containing theCard and adds the link to the rest 
     * of the list at the next location
     * @param theCard a reference to the card to be stored on top of the hand
     */
    public void addCard(Card theCard)
    {
        top = new Node<Card>(theCard, top);
    }
      
    /**
     * topCard --
     * returns the top-most card in the hand without removing it from
     * the hand. If the pile is empty then the top-most card cannot be returned
     * so, an EmptyCollectionException is thrown back to the calling block
     * @return a reference to the card which is stored on top of the hand
     * @throws EmptyCollectionException if the hand is empty
     */
    public Card topCard()
    {
        if (top == null) // checks if hand is empty
            throw new EmptyCollectionException("Hand");
           
        return top.getItem();
    }
      
    /**
     * removeCard --
     * removes the top-most card from the hand and sets top to the following 
     * location. If the pile is empty then a card cannot be removed
     * so, an EmptyCollectionException is thrown back to the calling block
     * @return a reference to the card that was removed
     * @throws EmptyCollectionException if the hand is empty
     */
    public Card removeCard()
    {
        if (top == null) // checks if hand is empty
            throw new EmptyCollectionException("Hand");
   
        // stores the top-most card in the local variable theCard and set
        // top to the next Node in the list
        Card theCard = top.getItem();
        top = top.getNext();
   
        return theCard;
    }
      
    /**
     * numCards --
     * returns a count of the number of cards in the hand. A temporary local
     * variable current is used to store the current link of cards and traverse
     * through it while incrementing count
     * @return the number of cards in the hand
     */
    public int numCards()
    {
        int count = 0;
        Node<Card> current = top; // temporarily holds the link of cards
         
        while(current != null) // loops until the next location is empty
        {
            count++;
            current = current.getNext();        
        }
        
        return count;
    }
      
    /**
     * totalPoints --
     * calculates the point total of all the cards in the hand, that total is 
     * returned by the method. A temporary local variable current is used to
     * store the current link of cards and traverse through it while adding the
     * point value returned by the getPointValue method to the total
     * @return the point total of all the cards in the hand
     */
    public int totalPoints()
    {
        Node<Card> current = top; // temporarily holds the link of cards
        int total = 0;
         
        while(current != null) //loops until the next location is empty
        {
            // gets the point value of the card at the top of the list
            // and then sets current to the next location putting that next
            // Node now at the top
            total += current.getItem().getPointValue();
            current = current.getNext();
        }
           
        return total;
    }
}