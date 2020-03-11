package proj2sp18;
import javax.swing.JOptionPane;
 
/**
 * <p>Title: Project 2 Application Class </p>
 * 
 * <p>Description: This class accepts two values from the user using the
 * JOptionPane showInputDialog method and using those values instantiates 
 * a new River object and displays the state of the object to the console 
 * with all the animals in the 2D array. The move method is called 33 times 
 * and displayed to the console using the toString method after each move.
 * Since the parseInt method can throw a NumberFormatException if the user
 * enters nothing or something other than an integer, statements including
 * that method are placed in a try/catch block to appropriately handle the
 * exception. That exception is also thrown when the user enter a number less
 * than 5 or greater than 10. The user gets 3 opportunities to enter a valid
 * size for the river, if they fail to do so, the program terminates.</p>
 * 
 * @author Reina Eustache
 */
public class Proj2App 
{
    public static void main(String[] args)
    {
        int riverRows = 0; // variable to store what the user inputs for rows from JOptionPane Dialog box
        int riverColumns = 0; // variable to store what the user inputs for columns from JOptionPane Dialog box
        boolean done = false; // variable to know if the user has successfully entered a valid size
        int invalidInput = 0; // counter for the number of times the user enters an invalid input
 
        // loops if the user has not successfully entered a valid size and has not exceeded their number of tries
        while (invalidInput < 3 && !done)
        {
            try
            {
            	// prompts the user to input a row, that string is then converted to an integer if possible and stored in riverRows
                riverRows = Integer.parseInt(JOptionPane.showInputDialog("Please enter a height "
                        + "(# of rows) between 5 and 10 for the river:"));
                // and exception is thrown back to the calling block if the user enters a number that is outside the range
                if (riverRows < 5 || riverRows > 10)
                    throw new NumberFormatException();
                
                // prompts the user to input a column, that string is then converted to an integer if possible and stored in riverColumns
                riverColumns = Integer.parseInt(JOptionPane.showInputDialog("Please enter a width "
                        + "(# of columns) between 5 and 10 for the river:"));
                // and exception is thrown back to the calling block if the user enters a number that is outside the range
                if (riverColumns < 5 || riverColumns > 10)
                    throw new NumberFormatException();
                
                // this executes if all of the previous statements have not thrown any exceptions indicating the user has entered valid input
                done = true;
            }
            catch (NumberFormatException ex)
            {
                if (invalidInput == 2) // executes if the user fails to enter a valid size on their last try
                {
                    invalidInput++;
                    JOptionPane.showMessageDialog(null, "The river was not successfully created. Program terminating...");
                }
                else // executes if the user enters an invalid size allowing them to try again
                {
                    JOptionPane.showMessageDialog(null, "ERROR! Please enter a valid size for the river.");
                    invalidInput++;
                }
            }
        }
 
        // executes only if the user entered a valid size at some point during their tries
        if (done)
        {
        	// a new River object is instantiated using the user's input and displayed to the console
            River myRiver = new River(riverRows, riverColumns);
            System.out.println("This is the original state of the river:\n\n" + myRiver.toString());
 
            // the move method is called on the River object and displayed to the console after each move 33 times
            for (int i = 0; i < 33; i++)
            {
                System.out.println("\n" + (i + 1) + ". This is the river after " + myRiver.move() + "\n");
                System.out.println(myRiver.toString());
            }
        }
    }
}