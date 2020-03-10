package proj5sp18;
import java.io.File;
import java.util.Scanner;

/**
 * <p>Title: Project 5 Application Class</p>
 * 
 * <p>Description: This class reads a sequence of command from an fBData file
 * and calls methods from the SFacebook class in order to mimic a simple version
 * of Facebook. If a P is read from the data file, a new Friend is added to 
 * the SFacebook, F makes two people friends, U unfriends two people, L lists a 
 * person's friends, Q checks whether two people are friends, V lists a person's
 * friends and maybe their friends depending on security level and X terminates 
 * the program. The commands and their method calls are included in try/catch
 * blocks in case the data read is not appropriate and a message is displayed to 
 * the console to handle the exception.</p>
 * 
 * @author Reina Eustache
 */
public class Proj5App 
{
	public static void main(String[] args) throws Exception
	{
		// creates a new Scanner object passing over fBData.txt as the file to be read
		Scanner fileScan = new Scanner(new File("fBData.txt"));
		
		String command; // to store the first token in a line
		String name1; // to store the second token in a line
		String name2; // to store the third token in a line

		SFacebook fb = new SFacebook(); // creates new SFacebook object to process commands on

		while(fileScan.hasNext()) // loops until the scanner has another token in its input
		{
			try // in case the input for a particular command is invalid and the method throws an exception
			{
				command = fileScan.next();

				// check which command was grabbed as the first token of line and stores the following tokens
				// in variables to be passed to the appropriate method depending on the command, a message is
				//then displayed to the console after each command is processed
				if(command.equals("P")) // adds a new Friend to the SFacebook
				{
					name1 = fileScan.next();
					name2 = fileScan.next();
					
					System.out.println("*After processing command " + command + " on " + name1 + " with security level " + name2 + ".");
					
					fb.addToFacebook(name1, Integer.parseInt(name2));
					System.out.println(fb.toString());
				}
				else if(command.equals("F")) // friends two people
				{
					name1 = fileScan.next();
					name2 = fileScan.next();
					
					System.out.println("*After processing command " + command + " on " + name1 + " and " + name2 + ".");
					
					fb.makeFriends(name1, name2);
					System.out.println(fb.toString());
				}
				else if(command.equals("U")) // unfriends two people
				{
					name1 = fileScan.next();
					name2 = fileScan.next();
					
					System.out.println("*After processing command " + command + " on " + name1 + " and " + name2 + ".");
					
					fb.breakFriendship(name1, name2);
					System.out.println(fb.toString());
				}
				else if(command.equals("L")) // lists someone's friends
				{
					name1 = fileScan.next();
					
					System.out.println("*After processing command " + command + " on " + name1 + ".");
					System.out.println(fb.getFriends(name1));
				}
				else if(command.equals("Q")) // checks whether two people are friends 
				{
					name1 = fileScan.next();
					name2 = fileScan.next();
					
					System.out.println("*After processing command " + command + " on " + name1 + " and " + name2 + ".");
					
					if(fb.getFriendStatus(name1, name2))
						System.out.println(name1 + " and " + name2 + " are friends\n");
					else
						System.out.println(name1 + " and " + name2 + " are not friends\n");
				}
				else if(command.equals("V")) // lists a person's friends and maybe their friends depending on security level
				{
					name1 = fileScan.next();
					
					System.out.println("*After processing command " + command + " on " + name1 + ".");
					System.out.println(fb.getFriendsByLevel(name1));
				}
			}
			catch(FriendNotFoundException ex) // handles any exceptions thrown by methods in the SFacebook class if invalid input is received
			{
				System.out.println(ex.getMessage() + "\n");
			}
		}
		fileScan.close(); // closes scanner object used to read the file
	}
}
