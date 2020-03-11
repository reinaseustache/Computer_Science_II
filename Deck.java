package proj3sp18;

/**
 * <p>Title: The Deck class</p>
 *
 * <p>Description: This class represents a Deck of 52 playing cards.  It
 * gets created in order and then must be shuffled to randomize the order of
 * the cards.  Cards are dealt from the top of the deck.</p>
 * 
 * @author CSC130 Instructor
 */
public class Deck
{
	//instance variables
    private Card[] cards;
    private int numCards;

    /**
     * default constructor which creates the deck of 52 cards (without Jokers) in order
     */
    public Deck()
    {
        numCards = 52;
        cards = new Card[numCards];
        for (int i=0; i<cards.length;i++)
        {
            cards[i] = new Card(i);
        }
    }

    /**
     * shuffleDeck method -- places the cards in the deck in a random order
     */
    public void shuffleDeck()
    {
        Card temp;
        int ran1, ran2;
        for (int i=0; i<100; i++)
        {
            ran1 = (int)(Math.random()*52);
            ran2 = (int)(Math.random()*52);
            temp = cards[ran1];
            cards[ran1] = cards[ran2];
            cards[ran2] = temp;
        }
        numCards = 52;
    }
    
    /**
     * dealCard -- deals the top card from the deck and decreases the number
     * of cards in the deck by 1
     * @return a reference to the Card being dealt
     */
    public Card dealCard()
    {
		if (numCards > 0)
		{
			numCards--;
			return cards[numCards];
		}
		else
			throw new EmptyCollectionException("Deck");
	}
    
    /**
     * toString --
     * returns the state of the deck as a string
     * @return a string containing the cards currently in the deck
     */
    public String toString()
    {
    	String temp = new String();
    	for (int i=numCards-1; i>=0; i--)
    		temp = temp + cards[i] + "\n";
    	return temp;
    }
}

