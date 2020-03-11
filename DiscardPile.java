package proj3sp18;
 
/**
 * <p>Title: The DiscardPile Class</p>
 * 
 * <p>Description: This class uses a linked list to store references
 * to card objects in the discard pile. A new card can be added to 
 * the top of the list with the addCard method. Without altering the 
 * discard pile, the top-most card can be returned with the topCard 
 * method and the removeCard method removes the top-most card and 
 * returns the reference to that card. An EmptyCollectionException can
 * be thrown if the pile is empty.</p>
 * 
 * @author Reina Eustache
 */
public class DiscardPile 
{
    private Node<Card> top; // instance variable for the linked list
 
    /**
     * default constructor --
     * creates an empty card discard pile by setting the top instance 
     * variable to null
     */
    public DiscardPile()
    {
        top = null;
    }
 
    /**
     * addCard -- 
     * stores the new card passed to the method at the top of the discard pile.
     * A new Node is inserted containing theCard and adds the link to the rest 
     * of the list at the next location
     * @param theCard a reference to the card to be stored on top of the pile
     */
    public void addCard(Card theCard)
    {
        top = new Node<Card>(theCard, top);
    }
 
    /**
     * topCard --
     * returns the top-most card in the discard pile without removing it from
     * the pile. If the pile is empty then the top-most card cannot be returned
     * so, an EmptyCollectionException is thrown back to the calling block
     * @return a reference to the card which is stored on top of the pile
     * @throws EmptyCollectionException if the discard pile is empty
     */
    public Card topCard()
    {
        if (top == null) // checks if the pile is empty
            throw new EmptyCollectionException("DiscardPile");
 
        return top.getItem();
    }
 
    /**
     * removeCard --
     * removes the top-most card from the discard pile and sets top to the 
     * following location. If the pile is empty then a card cannot be removed
     * so, an EmptyCollectionException is thrown back to the calling block
     * @return a reference to the card that was removed
     * @throws EmptyCollectionException if the discard pile is empty
     */
    public Card removeCard()
    {
        if (top == null) // checks if the pile is empty
            throw new EmptyCollectionException("DiscardPile");
 
        // stores the top-most card in the local variable theCard and set
        // top to the next Node in the list
        Card theCard = top.getItem(); 
        top = top.getNext();
 
        return theCard;
    }
}