package proj4sp18;
 
/**
 * <p>Title: EmptyCollectionException Class</p>
 *
 * <p>Description: Exception Class for use by all Container Classes</p>
 */
 
@SuppressWarnings("serial")
public class EmptyCollectionException extends RuntimeException
{
    /**
     * Initializes an EmptyCollectionException storing an appropriate message.
     */
    public EmptyCollectionException()
    {
        super("collection is empty");
    }
 
    /**
     * Initializes an EmptyCollectionException storing the type of the
     * collection (as specified by the user) along with an appropriate message.
     */
    public EmptyCollectionException(String collection)
    {
        super(collection + " collection is empty");
    }
}