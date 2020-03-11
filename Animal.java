package proj2sp18;
import java.util.Random;
 
/**
 * <p>Title: Animal Class </p>
 * 
 * <p>Description: This class generates random values for gender and
 * strength. Each animal can either be female or male and can have a 
 * strength from 0 to 50. This class can also compare the strengths of
 * two animals and can determine whether they are of the same type or 
 * gender. The type of animal in the object can be returned as a String and
 * an increaseStrength method increments the strength of the animal by 3.</p>
 * 
 * @author Reina Eustache
 */
public class Animal 
{
    private String gender; // instance variable to store a reference to the animal's gender as a String
    private int strength; // instance variable to store the animal's strength as an integer
      
    /**
     * Default constructor --
     * Initializes the gender instance variable with a random value
     * of either male or female and the strength instance variable
     * with a random value between 0 and 50.
     */
    public Animal()
    {
        Random rand = new Random();
        int num = rand.nextInt(2);
          
        if (num == 0)
            gender = "Female";
        else
            gender = "Male";
          
        strength = rand.nextInt(51);
    }
      
    /**
     * hasGreaterStrength method--
     * This method compares the strengths of two animals and returns 
     * true if the animal the method is called on has greater strength
     * than the other animal, and if not it returns false
     * @param theAnimal - the animal being compared
     * @return true if the animal the method is called on has greater
     * strength and false if not
     */
    public boolean hasGreaterStrength(Animal theAnimal)
    {
        if (this.strength > theAnimal.strength)
            return true;
        else
            return false;
    }
      
    /**
     * isSameType method --
     * This method compares the type of the animal the method is called 
     * on to the type of the animal that is passed as the argument. Returns
     * true if the animals are the same type and false otherwise.
     * @param theAnimal - the animal being compared
     * @return true if the animals are the same type and false if not
     */
    public boolean isSameType(Animal theAnimal)
    {
        if (this.toString().substring(0, 4).equals(theAnimal.toString().substring(0, 4)))
            return true;
        else
            return false;
    }
     
    /**
     * animalType method --
     * This method creates a new String containing the first four letters from the
     * String returned by the toString method in the Animal class' subclasses, the
     * Bear class and the Fish class. That new String is then returned by the method
     * with either "Bear" or "Fish".
     * @return a String containing the name of the type of Animal in an Animal object
     */
    public String animalType()
    {
        return this.toString().substring(0, 4);
    }
     
    /**
     * isSameGender method --
     * This method compares the gender of the animal the method is called 
     * on to the gender of the animal that is passed as the argument. Returns
     * true if the animals are the same gender and false otherwise.
     * @param theAnimal the animal whose gender is to be compared
     * @return true if the animals are the same gender and false if not
     */
    public boolean isSameGender(Animal theAnimal)
    {
        if (this.gender.equals(theAnimal.gender))
            return true;
        else
            return false;
    }
     
    /**
     * increaseStrength method --
     * This method increments the private instance variable strength by
     * 3 for the animal the method is called on.
     */
    public void increaseStrength()
    {
        strength = strength + 3;
    }
     
    /**
     * toString method --
     * Returns a String representing the state of the animal
     * with its appropriate gender and strength
     * @return a reference to a String containing the state of
     * the animal
     */
    public String toString()
    {
        return gender + " | Strength(" + strength + ")";
    }
}