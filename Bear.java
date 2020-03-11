package proj2sp18;

/**
 * <p>Title: Bear Class </p>
 * 
 *<p>Description: This class is a subclass of the Animal class
 * and inherits the properties and behaviors of that class. It
 * also uses the Animal class's default constructor and toString method
 * and concatenates the type of animal (Bear) to what is returned by the 
 * toString method in the Animal class. </p>
 * 
 * @author Reina Eustache
 */
public class Bear extends Animal
{
	/**
	 * Default constructor --
	 * It calls the default constructor in its parent class,
	 * Animal, and relies its constructor to initialize
	 * all variables
	 */
	public Bear()
	{
		super();
	}

	/**
	 * toString method --
	 * Returns a String representing the type of the animal (Bear)
	 * with its appropriate gender and strength by making a 
	 * call to its parent class Animal's toString method. If the 
	 * keyword 'super' is not there the toString method keeps calling 
	 * itself creating an infinite loop.
	 * @return a reference to a String containing the state of
	 * the animal
	 */
	public String toString()
	{
		return "Bear | " + super.toString();
	}
}