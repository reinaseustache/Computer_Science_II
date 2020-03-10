package proj5sp18;
  
/**
 * <p>Title: FriendNotFoundException Class</p>
 *
 * <p>Description: </p>
 */
  
@SuppressWarnings("serial")
public class FriendNotFoundException extends RuntimeException
{
    /**
     * Initializes an FriendNotFoundException storing an appropriate 
     * message along with the type of the collection specified by the user
     */
    public FriendNotFoundException (String collection)
    {
        super ("The target friend is not in this " + collection);
    }
}