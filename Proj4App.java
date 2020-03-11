package proj4sp18;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;
import java.text.DecimalFormat;

/**
 * <p>Title: Project4App.java: Project 4 Application class</p>
 * 
 * <p>Description: This class reads data from the "NYPD_Motor_Vehicle_Collisions.csv"
 * file and stores specific fields from each row in a MotorVehicleCollision object.
 * If an IllegalArgumentException is thrown by the constructor or an InputMismatchException
 * is encountered due to malformed rows, the Exception catch block handles these by reading
 * the rest of the row and moving on. Once the data has been parsed, the user is presented 
 * with a menu with four types of analysis that can be performed by the program. The result
 * for the number of people killed for each borough, the type of car that is most likely to
 * have an accident in a particular borough, the number of people killed per contributing
 * factor and the percentage for each contributing factor can be displayed in the dialog 
 * box. The field(s) of data used from each object to determine the result is displayed in 
 * the console.</p>
 * 
 * @author Reina Eustache
 */
public class Proj4App 
{
	public static void main (String[] args) throws Exception
	{
		// a new Scanner object is instantiated to read data from the NYPD_Motor_Vehicle_Collisions.csv file
		Scanner fileScan = new Scanner(new File("NYPD_Motor_Vehicle_Collisions.csv"));
		fileScan.useDelimiter(","); // defines where a token start and ends 

		// instantiates an Array unordered list to store MotorVehicleCollision objects read from the file
		ArrayUnorderedList<MotorVehicleCollision> collisionList = new ArrayUnorderedList<MotorVehicleCollision>();

		// variables to store data read from file to be passed to the unordered list's parameterized constructor
		String hour, city, probableCause, carType; 
		int personsDead, key;

		// reads the first line from the file which contains the title for each column and discards it since it is not needed 
		fileScan.nextLine();

		// loop indicating how many rows of data to attempt to retrieve from the file
		for(int i = 0; i < 1672; i++) 
		{
			try
			{
				fileScan.next(); // discards the first column since it will not be used

				// stores the next two tokens in the appropriate variables to be passed to the constructor
				hour = fileScan.next();
				city = fileScan.next();

				// loops through 8 tokens of data and discards them since they are not needed
				for(int j = 0; j < 8; j++)
				{
					fileScan.next();
				}

				// retrieves and converts this token to an integer and store it personsDead
				personsDead = Integer.parseInt(fileScan.next());

				// loops through 7 tokens of data and discards them since they are not needed
				for(int j = 0; j < 7; j++)
				{
					fileScan.next();
				}

				// retrieves the next token and stores it
				probableCause = fileScan.next();

				// loops through 4 tokens of data and discards them since they are not needed
				for(int j = 0; j < 4; j++)
				{
					fileScan.next();
				}

				// retrieves the next two tokens and stores them
				key = Integer.parseInt(fileScan.next());
				carType = fileScan.next();

				// loops through 4 tokens of data and discards them since they are not needed
				for(int j = 0; j < 4; j++)
				{
					fileScan.next();
				}

				// the specified fields that were stored from the row are passed to the constructor and a new
				// MotorVehicleCollision object is added to the rear of the list
				collisionList.addToRear(new MotorVehicleCollision(hour, city, personsDead, probableCause, key, carType));
			}
			catch(Exception ex) // handles all Exceptions such as IllegalArgument and InputTypeMismatch exceptions by reading and discarding the rest of a row
			{
				fileScan.nextLine();
			}
		}

		fileScan.close(); //closes the scanner file used to read the data

		int choice; // variable used to store the users choice from the JOptionPane

		do
		{
			// displays a menu to the user allowing them to choose an analysis and stores the integer returned for their choice
			String[] choices = {"Persons Killed", "Percent Contributing Factor", "Vehicle Type", "Killed Per Factor", "Quit"};
			choice = JOptionPane.showOptionDialog(null, "What would you like to analyze the motor vehicle collisions by......?", "Main Menu", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

			// String to make up the difference in indentation to lined up data in the console into columns
			String spaces = "                    ";

			if (choice == 0) // if user chooses to analyze by "Persons Killed"
			{
				// local variables to store the number of persons killed for each borough
				int numKilledBronx = 0, numKilledBrooklyn = 0, numKilledManhattan = 0, numKilledStatenIsland = 0, numKilledQueens = 0;

				// loops through list and adds to the appropriate number of people killed if the borough for that object is equal to the one specified
				System.out.println("\nThe following data was used to analyze by persons killed: ");
				for(int i = 0; i < collisionList.size(); i++)
				{
					if(collisionList.get(i).getBorough().equals("BRONX"))
						numKilledBronx += collisionList.get(i).getNumKilled();
					else if(collisionList.get(i).getBorough().equals("BROOKLYN"))
						numKilledBrooklyn += collisionList.get(i).getNumKilled();
					else if(collisionList.get(i).getBorough().equals("MANHATTAN"))
						numKilledManhattan += collisionList.get(i).getNumKilled();
					else if(collisionList.get(i).getBorough().equals("QUEENS"))
						numKilledQueens += collisionList.get(i).getNumKilled();
					else
						numKilledStatenIsland += collisionList.get(i).getNumKilled();

					// displays the data used for this analysis to the console
					System.out.println("Borough: " + collisionList.get(i).getBorough() + spaces.substring(0, spaces.length() - collisionList.get(i).getBorough().length()) 
					+ " Number of persons killed: " + collisionList.get(i).getNumKilled());
				}

				// variables to store the maximum and minimum values and boroughs
				int greatestNum = 0, leastNum = 0;
				String greatestBorough = "", leastBorough = "";

				// compares each of the number of people killed to see which among them is the greatest and sets greatestNum and greatesBorough accordingly
				if(numKilledBronx >= numKilledBrooklyn && numKilledBronx >= numKilledManhattan && numKilledBronx >= numKilledStatenIsland && numKilledBronx >= numKilledQueens)
				{
					greatestNum = numKilledBronx;
					greatestBorough = "BRONX";
				}
				else if(numKilledBrooklyn >= numKilledBronx && numKilledBrooklyn >= numKilledManhattan && numKilledBrooklyn >= numKilledStatenIsland && numKilledBrooklyn >= numKilledQueens)
				{
					greatestNum = numKilledBrooklyn;
					greatestBorough = "BROOKLYN";
				}
				else if(numKilledManhattan >= numKilledBronx && numKilledManhattan >= numKilledBrooklyn && numKilledManhattan >= numKilledStatenIsland && numKilledManhattan >= numKilledQueens)
				{
					greatestNum = numKilledManhattan;
					greatestBorough = "MANHATTAN";
				}
				else if(numKilledStatenIsland >= numKilledBronx && numKilledStatenIsland >= numKilledBrooklyn && numKilledStatenIsland >= numKilledManhattan && numKilledStatenIsland >= numKilledQueens)
				{
					greatestNum = numKilledStatenIsland;
					greatestBorough = "STATEN ISLAND";
				}
				else
				{
					greatestNum = numKilledQueens;
					greatestBorough = "QUEENS";
				}

				// compares each of the number of people killed to see which among them is the least and sets leastNum and leastBorough accordingly
				if(numKilledBronx <= numKilledBrooklyn && numKilledBronx <= numKilledManhattan && numKilledBronx <= numKilledStatenIsland && numKilledBronx <= numKilledQueens)
				{
					leastNum = numKilledBronx;
					leastBorough = "BRONX";
				}
				else if(numKilledBrooklyn <= numKilledBronx && numKilledBrooklyn <= numKilledManhattan && numKilledBrooklyn <= numKilledStatenIsland && numKilledBrooklyn <= numKilledQueens)
				{
					leastNum = numKilledBrooklyn;
					leastBorough = "BROOKLYN";
				}
				else if(numKilledManhattan <= numKilledBronx && numKilledManhattan <= numKilledBrooklyn && numKilledManhattan <= numKilledStatenIsland && numKilledManhattan <= numKilledQueens)
				{
					leastNum = numKilledManhattan;
					leastBorough = "MANHATTAN";
				}
				else if(numKilledStatenIsland <= numKilledBronx && numKilledStatenIsland <= numKilledBrooklyn && numKilledStatenIsland <= numKilledManhattan && numKilledStatenIsland <= numKilledQueens)
				{
					leastNum = numKilledStatenIsland;
					leastBorough = "STATEN ISLAND";
				}
				else
				{
					leastNum = numKilledQueens;
					leastBorough = "QUEENS";
				}

				// displays the result of the analysis in a message dialog showing the number of people killed overall per borough as well as which borough
				// killed the most people per day and which borough killed the least people per day
				JOptionPane.showMessageDialog(null, "Over a 4 day period, the total number of people killed in car accidents in the 5 boroughs for:\n\nQueens was: " + numKilledQueens
						+ " people\nBrooklyn was " + numKilledBrooklyn + " people\nBronx was: " + numKilledBronx + " people\nManhattan was: " + numKilledManhattan 
						+ " people\nStanten Island was: " + numKilledStatenIsland + " people\n\nCar accidents that happened in " + greatestBorough + " led to the more deaths than any of"
						+ " the other 4 boroughs, with an average of " + (greatestNum / 4) + " killed per day. "
						+ "\nWhile on the other hand " + leastBorough + " only had " + (leastNum / 4) + " death per day due to car accidents.\n");
			}

			else if (choice == 1) // if user chooses to analyze by "Percent Contributing Factor"
			{
				// displays the data used for this analysis to the console
				System.out.println("\nThe following data was used to analyze by contributing factor: ");
				for(int i = 0; i < collisionList.size(); i++)
				{
					System.out.println("Contributing Factor: " + collisionList.get(i).getContributingFactor());
				}

				// the result of the analysis displayed in a message dialog by calling the static getPercentStr method and passing the collisionList
				// and a specific contributing factor to look for to the method to get the overall percentage for that contributing factor among
				// the 5 boroughs
				JOptionPane.showMessageDialog(null, "Among the 5 boroughs the percentage of car accidents based on contributing factor was:\n\n"
						+ getPercentStr(collisionList, "Driver Inattention/Distraction") + " of accidents were due to Driver Inattention/Distraction\n"
						+ getPercentStr(collisionList, "Failure to Yield Right-of-Way") + " of accidents were due to Failure to Yield Right-of-Way\n"
						+ getPercentStr(collisionList, "Backing Unsafely") + " of accidents were due to Backing Unsafely\n"
						+ getPercentStr(collisionList, "Following Too Closely") + " of accidents were due to Following Too Closely\n"
						+ getPercentStr(collisionList, "Passing or Lane Usage Improper") + " of accidents were due to Passing or Lane Usage Improper\n"
						+ getPercentStr(collisionList, "Unsafe Lane Changing") + " of accidents were due to Unsafe Lane Changing\n"
						+ getPercentStr(collisionList, "Turning Improperly") + " of accidents were due to Turning Improperly\n"
						+ getPercentStr(collisionList, "Other Vehicular") + " of accidents were due to Other Vehicular\n"
						+ getPercentStr(collisionList, "Traffic Control Disregarded") + " of accidents were due to Traffic Control Disregarded\n"
						+ getPercentStr(collisionList, "Driver Inexperience") + " of accidents were due to Driver Inexperience\n"
						+ getPercentStr(collisionList, "Reaction to Other Uninvolved Vehicle") + " of accidents were due to Reaction to Other Uninvolved Vehicle\n"
						+ getPercentStr(collisionList, "Unsafe Speed") + " of accidents were due to Unsafe Speed\n"
						+ getPercentStr(collisionList, "View Obstructed/Limited") + " of accidents were due to View Obstructed/Limited\n"
						+ getPercentStr(collisionList, "Pedestrian/Bicyclist/Other Pedestrian Error/Confusion") + " of accidents were due to Pedestrian/Bicyclist/Other Pedestrian Error/Confusion\n"
						+ getPercentStr(collisionList, "Alcohol Involvement") + " of accidents were due to Alcohol Involvement\n"
						+ getPercentStr(collisionList, "Oversized Vehicle") + " of accidents were due to Oversized Vehicle\n"
						+ getPercentStr(collisionList, "Passenger Distraction") + " of accidents were due to Passenger Distraction\n"
						+ getPercentStr(collisionList, "Aggressive Driving/Road Rage") + " of accidents were due to Aggressive Driving/Road Rage\n"
						+ getPercentStr(collisionList, "Tire Failure/Inadequate") + " of accidents were due to Tire Failure/Inadequate\n"
						+ getPercentStr(collisionList, "Failure to Keep Right") + " of accidents were due to Failure to Keep Right\n"
						+ getPercentStr(collisionList, "Glare") + " of accidents were due to Glare\n"
						+ getPercentStr(collisionList, "Pavement Slippery") + " of accidents were due to Pavement Slippery\n"
						+ getPercentStr(collisionList, "Fatigued/Drowsy") + " of accidents were due to Fatigued/Drowsy\n"
						+ getPercentStr(collisionList, "Animals Action") + " of accidents were due to Animals Action\n");           
			}

			else if (choice == 2) // if user chooses to analyze by "Vehicle Type"
			{
				// displays the data used for this analysis to the console
				System.out.println("\nThe following data was used to analyze by vehicle type: ");
				for(int i = 0; i < collisionList.size(); i++)
				{
					System.out.println("Borough: " + collisionList.get(i).getBorough() + spaces.substring(0, spaces.length() - collisionList.get(i).getBorough().length()) 
					+ " Vehicle Type: " + collisionList.get(i).getVehicleType());
				}

				// the result of the analysis displayed in a message dialog by calling the static typePerBorough method and passing the collisionList
				// and a specific vehicle type to look for to the method to get which borough this type of car was most likely to have an accident in
				JOptionPane.showMessageDialog(null, "These are the results for which vehicle type was most likely to have an accident in a particular borough:\n\n" + typePerBorough(collisionList, "TAXI") + "\n" + typePerBorough(collisionList, "PASSENGER VEHICLE") 
				+ "\n" + typePerBorough(collisionList, "SPORT UTILITY / STATION WAGON") + "\n" + typePerBorough(collisionList, "PICK-UP TRUCK")
				+ "\n" + typePerBorough(collisionList, "BICYCLE") + "\n" + typePerBorough(collisionList, "MOTORCYCLE") + "\n" + typePerBorough(collisionList, "TK")
				+ "\n" + typePerBorough(collisionList, "BU") + "\n" + typePerBorough(collisionList, "VN") + "\n" + typePerBorough(collisionList, "DS")
				+ "\n" + typePerBorough(collisionList, "AR") + "\n" + typePerBorough(collisionList, "TT") + "\n" + typePerBorough(collisionList, "CONV")
				+ "\n" + typePerBorough(collisionList, "DP") + "\n" + typePerBorough(collisionList, "FB") + "\n");
			}

			else if(choice == 3) // if user chooses to analyze by "Killed Per Factor"
			{
				// displays the data used for this analysis to the console
				System.out.println("\nThe following data was used to analyze by persons killed per contributing factor: ");
				for(int i = 0; i < collisionList.size(); i++)
				{
					System.out.println("Number of persons killed: " + collisionList.get(i).getNumKilled()
							+ " \t\tContributing Factor: " + collisionList.get(i).getContributingFactor());
				}

				// the result of the analysis displayed in a message dialog by calling the static killedPerFactor method and passing the collisionList
				// and a specific contributing factor to look for to the method to get how many people were killed for a particular type of accident
				JOptionPane.showMessageDialog(null, "Among the 5 boroughs the number of people who lost their lives based on contributing factor was:\n\n"
						+ killedPerFactor(collisionList, "Driver Inattention/Distraction") + " people lost their lives in accidents due to  Driver Inattention/Distraction\n"
						+ killedPerFactor(collisionList, "Failure to Yield Right-of-Way") + " people lost their lives in accidents due to  Failure to Yield Right-of-Way\n"
						+ killedPerFactor(collisionList, "Backing Unsafely") + " people lost their lives in accidents due to  Backing Unsafely\n"
						+ killedPerFactor(collisionList, "Following Too Closely") + " people lost their lives in accidents due to  Following Too Closely\n"
						+ killedPerFactor(collisionList, "Passing or Lane Usage Improper") + " people lost their lives in accidents due to  Passing or Lane Usage Improper\n"
						+ killedPerFactor(collisionList, "Unsafe Lane Changing") + " people lost their lives in accidents due to  Unsafe Lane Changing\n"
						+ killedPerFactor(collisionList, "Turning Improperly") + " people lost their lives in accidents due to  Turning Improperly\n"
						+ killedPerFactor(collisionList, "Other Vehicular") + " people lost their lives in accidents due to  Other Vehicular\n"
						+ killedPerFactor(collisionList, "Traffic Control Disregarded") + " people lost their lives in accidents due to  Traffic Control Disregarded\n"
						+ killedPerFactor(collisionList, "Driver Inexperience") + " people lost their lives in accidents due to  Driver Inexperience\n"
						+ killedPerFactor(collisionList, "Reaction to Other Uninvolved Vehicle") + " people lost their lives in accidents due to  Reaction to Other Uninvolved Vehicle\n"
						+ killedPerFactor(collisionList, "Unsafe Speed") + " people lost their lives in accidents due to  Unsafe Speed\n"
						+ killedPerFactor(collisionList, "View Obstructed/Limited") + " people lost their lives in accidents due to  View Obstructed/Limited\n"
						+ killedPerFactor(collisionList, "Pedestrian/Bicyclist/Other Pedestrian Error/Confusion") + " people lost their lives in accidents due to  Pedestrian/Bicyclist/Other Pedestrian Error/Confusion\n"
						+ killedPerFactor(collisionList, "Alcohol Involvement") + " people lost their lives in accidents due to  Alcohol Involvement\n"
						+ killedPerFactor(collisionList, "Oversized Vehicle") + " people lost their lives in accidents due to  Oversized Vehicle\n"
						+ killedPerFactor(collisionList, "Passenger Distraction") + " people lost their lives in accidents due to  Passenger Distraction\n"
						+ killedPerFactor(collisionList, "Aggressive Driving/Road Rage") + " people lost their lives in accidents due to  Aggressive Driving/Road Rage\n"
						+ killedPerFactor(collisionList, "Tire Failure/Inadequate") + " people lost their lives in accidents due to  Tire Failure/Inadequate\n"
						+ killedPerFactor(collisionList, "Failure to Keep Right") + " people lost their lives in accidents due to  Failure to Keep Right\n"
						+ killedPerFactor(collisionList, "Glare") + " people lost their lives in accidents due to  Glare\n"
						+ killedPerFactor(collisionList, "Pavement Slippery") + " people lost their lives in accidents due to  Pavement Slippery\n"
						+ killedPerFactor(collisionList, "Fatigued/Drowsy") + " people lost their lives in accidents due to  Fatigued/Drowsy\n"
						+ killedPerFactor(collisionList, "Animals Action") + " people lost their lives in accidents due to  Animals Action\n");
			}
		}while(choice != 4); // loops until the user chooses to quit
	}

	/**
	 * getPercentStr --
	 * calculates the total number of a specific contributing factor, loops through the 
	 * unordered list object passed to list and compares each object's contributing factor 
	 * to the factor passed to causeStr and if they are equal, increments the number of that
	 * collision type. After retrieving the total, it is then divided but the number of 
	 * objects in the list and multiplied by 100 to obtain the percentage
	 * @param list the ArrayUnorderedList<MotorVehicleCollision> object to search through
	 * @param causeStr the target contributing factor to look for
	 * @return the percentage of a particular collision type
	 */
	private static String getPercentStr(ArrayUnorderedList<MotorVehicleCollision> list, String causeStr)
	{
		double numCollisionType = 0; // calculates the number of collisions with that contributing factor

		// loops through list and adds to numCollisionType if the contributing factor for that object are equal
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getContributingFactor().equals(causeStr))
				numCollisionType++;
		}

		// instantiates a new DecimalFormat object to round to two decimal places 
		DecimalFormat roundHundredth = new DecimalFormat("0.00");

		// calculates the percentage of that contributing factor out of the whole list and returns it
		return roundHundredth.format((numCollisionType / list.size()) * 100) + "%";
	}

	/**
	 * typePerBorough --
	 * calculates how many of a particular car type was involved in an accident for each borough,
	 * loops through the unordered list object passed to list and compares each object's
	 * car type to type and as well as the borough and if they are equal, adds to the variable 
	 * containing the total for that borough. The final total for each borough are then compared
	 * and a String containing which car type was most likely to have an accident in a borough
	 * is returned 
	 * @param list the ArrayUnorderedList<MotorVehicleCollision> object to search through
	 * @param type the type of car to look for
	 * @return a String containing which borough a car type was most popular in
	 */
	private static String typePerBorough(ArrayUnorderedList<MotorVehicleCollision> list, String type)
	{
		// variables to store a number of a car type for each borough
		int numOfBronx = 0, numOfBrooklyn = 0, numOfManhattan = 0, numOfStaten = 0, numOfQueens = 0;

		// loops through list and adds to the appropriate local variable if the vehicle type and borough for that object are equal
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getBorough().equals("BRONX") && list.get(i).getVehicleType().equals(type))
				numOfBronx++;
			else if(list.get(i).getBorough().equals("BROOKLYN") && list.get(i).getVehicleType().equals(type))
				numOfBrooklyn++;
			else if(list.get(i).getBorough().equals("MANHATTAN") && list.get(i).getVehicleType().equals(type))
				numOfManhattan++;
			else if(list.get(i).getBorough().equals("STATEN ISLAND") && list.get(i).getVehicleType().equals(type))
				numOfStaten++;
			else if(list.get(i).getBorough().equals("QUEENS") && list.get(i).getVehicleType().equals(type))
				numOfQueens++;
		}

		// compares each number and checks which among the five boroughs was most likely to have an accident for this car type and returns the appropriate String
		if(numOfBronx >= numOfBrooklyn && numOfBronx >= numOfManhattan && numOfBronx >= numOfStaten && numOfBronx >= numOfQueens)
			return "Accidents with " + type + "s were more likely to happen in BRONX. There were " + numOfBronx + " accidents caused by that vehicle type.";
		else if(numOfBrooklyn >= numOfBronx && numOfBrooklyn >= numOfManhattan && numOfBrooklyn >= numOfStaten && numOfBrooklyn >= numOfQueens)
			return "Accidents with " + type + "s were more likely to happen in BROOKLYN. There were " + numOfBrooklyn + " accidents caused by that vehicle type.";
		else if(numOfManhattan >= numOfBronx && numOfManhattan >= numOfBrooklyn && numOfManhattan >= numOfStaten && numOfManhattan >= numOfQueens)
			return "Accidents with " + type + "s were more likely to happen in MANHATTAN. There were " + numOfManhattan + " accidents caused by that vehicle type.";
		else if(numOfStaten >= numOfBronx && numOfStaten >= numOfBrooklyn && numOfStaten >= numOfManhattan && numOfStaten >= numOfQueens)
			return "Accidents with " + type + "s were more likely to happen in STATEN ISLAND. There were " + numOfStaten + " accidents caused by that vehicle type.";
		else
			return "Accidents with " + type + "s were more likely to happen in QUEENS. There were " + numOfQueens + " accidents caused by that vehicle type.";
	}

	/**
	 * killedPerFactor --
	 * calculates the total number of people killed for a specific contributing factor,
	 * loops through the unordered list object passed to list and compares each object's
	 * contributing factor to the factor passed to factorStr and if they are equal, adds
	 * the number of people killed for that object to the existing total 
	 * @param list the ArrayUnorderedList<MotorVehicleCollision> object to search through
	 * @param factorStr the target contributing factor to look for
	 * @return the total number of persons killed for a contributing factor to an accident
	 */
	private static int killedPerFactor(ArrayUnorderedList<MotorVehicleCollision> list, String factorStr)
	{
		int pplKilled = 0; // stores the total number of people killed

		// loops through list and adds to pplKilled if the contributing factor for that object are equal
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getContributingFactor().equals(factorStr))
				pplKilled += list.get(i).getNumKilled();
		}

		return pplKilled;
	}

}