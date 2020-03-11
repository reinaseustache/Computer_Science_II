package proj2sp18;
import java.util.Random;

/**
 * <p>Title: River Class </p>
 * 
 * <p>Description: This class creates a 2D array that can store a user
 * specified number of references to animal objects in half of the River.
 * It contains a move method that randomly moves an animal either, up,
 * down, right or left and returns a String with the fate of that animal
 * whether they died, fought another animal, created an offspring or moved
 * to an empty location. A toString method can also return the state of 
 * the River.</p>
 * 
 * @author Reina Eustache
 */
public class River 
{
	private Animal[][] animals; // instance variable to store the reference to a 2D array of animals

	/**
	 * parameterized constructor --
	 * Instantiates the animal array with a size based on what the user 
	 * enters, then populates half of the array by placing animals at
	 * random locations with either Bear objects or Fish objects.
	 * @param inputRows the number of rows for the 2D animal array
	 * @param inputColumns the number of columns for the 2D animal array
	 */
	public River(int inputRows, int inputColumns)
	{
		animals = new Animal[inputRows][inputColumns];

		Random rand = new Random();

		// loops through half of the number of locations in the array
		for (int i = 0; i < (animals.length * animals[0].length) / 2 ; i++)
		{
			// generates two random numbers, one for the indexes of each row
			// and the other for the columns
			int num = rand.nextInt(animals.length);
			int num2 = rand.nextInt(animals[0].length);

			// if that location already has an animal, new numbers are generated
			// until a location that is not empty is found
			while (animals[num][num2] != null)
			{
				num = rand.nextInt(animals.length);
				num2 = rand.nextInt(animals[0].length);
			}

			// generates a random 0 or 1 to randomly choose whether to populate that
			// location with a Bear object or a Fish object
			int num3 = rand.nextInt(2);
			if (num3 == 0)
				animals[num][num2] = new Bear();
			else
				animals[num][num2] = new Fish();
		}
	}

	/**
	 * move method --
	 * This method first generates random numbers to choose between the 4 choices
	 * of moving up, down, right or left. To ensure that no animals move beyond the
	 * bounds of the array, for example if the random movement generated it up, row
	 * 1 (the number 0) is excluded from the random numbers that can be generated for
	 * first index that refers to the rows and the same goes for the other movements
	 * accordingly. After a random movement has been decided, a various condition are
	 * checked to see if the animal at the next location is empty, has an animal of the 
	 * same sex, opposite sex or if it is a differently animal. If two animals of the 
	 * same type and gender, the one with the larger strength survives and its strength
	 * increases. If two animals of the same type but different genders, they create a
	 * new instance of that type of animal at a random location. If a bear and a fish 
	 * meet, the fish gets eaten and dies. A string is returned describing what happened
	 * as stated above and and what locations they occurred.
	 * @return a String describing the fate of the animals after each movement in the array
	 */
	public String move()
	{
		Random rand = new Random();
		String str = ""; // variable to store the String containing the fate of the animals
		String movement; // variable to the store the direction of the movement
		int num, num2, firstIndex, secondIndex; // variables to store the random location chosen and the location to move to

		int randomMove = rand.nextInt(4); // a random number is generated to decide whether to move up, down, right or left
		if (randomMove == 0)
		{
			// if the decision is to move up, this ensures that the random number generated for the row is not 0 (first row)
			// so as to not move outside the bounds of the array. Any number from 0 to the length of the columns
			// can be chosen since they do not affect an upward movement
			num = rand.nextInt(animals.length - 1) + 1;
			num2 = rand.nextInt(animals[0].length);

			// if that location already has an animal, new numbers are generated
			// until a location that is not empty is found
			while (animals[num][num2] == null)
			{
				num = rand.nextInt(animals.length - 1) + 1;
				num2 = rand.nextInt(animals[0].length);
			}

			firstIndex = num - 1; // sets the row of the location to move to to the row above it
			secondIndex = num2; // the column number stays the same since it does not affect an upward movement
			movement = "up"; // indicates with movement to concatenate to the String that describes the animal's fate
		}
		else if (randomMove == 1)
		{
			// if the decision is to move down, this ensures that the random number generated for the row is not the last row
			// so as to not move outside the bounds of the array. Any number from 0 to the length of the columns
			// can be chosen since they do not affect an upward movement
			num = rand.nextInt(animals.length - 1);
			num2 = rand.nextInt(animals[0].length);

			// if that location already has an animal, new numbers are generated
			// until a location that is not empty is found
			while (animals[num][num2] == null)
			{
				num = rand.nextInt(animals.length - 1);
				num2 = rand.nextInt(animals[0].length);
			}

			firstIndex = num + 1; // sets the row of the location to move to to the row below it
			secondIndex = num2; // the column number stays the same since it does not affect a downward movement
			movement = "down"; // indicates with movement to concatenate to the String that describes the animal's fate
		}
		else if (randomMove == 2)
		{
			// if the decision is to move right, this ensures that the random number generated for the column is the last column
			// so as to not move outside the bounds of the array. Any number from 0 to the length of the rows
			// can be chosen since they do not affect a movement to the right
			num = rand.nextInt(animals.length);
			num2 = rand.nextInt(animals[0].length - 1);

			// if that location already has an animal, new numbers are generated
			// until a location that is not empty is found
			while (animals[num][num2] == null)
			{
				num = rand.nextInt(animals.length);
				num2 = rand.nextInt(animals[0].length - 1);
			}

			firstIndex = num; // the row number stays the same since it does not affect a movement to the right
			secondIndex = num2 + 1; // sets the column of the location to move to to the column to the right of it
			movement = "right"; // indicates with movement to concatenate to the String that describes the animal's fate
		}
		else
		{
			// if the decision is to move right, this ensures that the random number generated for the column is not 0 (first column)
			// so as to not move outside the bounds of the array. Any number from 0 to the length of the rows
			// can be chosen since they do not affect a movement to the left
			num = rand.nextInt(animals.length);
			num2 = rand.nextInt(animals[0].length - 1) + 1;

			// if that location already has an animal, new numbers are generated
			// until a location that is not empty is found
			while (animals[num][num2] == null)
			{
				num = rand.nextInt(animals.length);
				num2 = rand.nextInt(animals[0].length - 1) + 1;
			}

			firstIndex = num; // the row number stays the same since it does not affect a movement to the left
			secondIndex = num2 - 1; // sets the column of the location to move to to the column to the left of it
			movement = "left"; // indicates with movement to concatenate to the String that describes the animal's fate
		}
		if (animals[firstIndex][secondIndex] == null) // executes if the location the animal is trying to move to is empty
		{
			// String returned by the method describing the fate of the animal/its move
			str = "The " + animals[num][num2].animalType() + " that was at position [" + num + "][" + num2 + "] moved " + movement + " to "
					+ "empty location [" + firstIndex + "][" + secondIndex + "].\n\tThat " + animals[num][num2].animalType() + " is "
					+ "now at location [" + firstIndex + "][" + secondIndex + "].";

			animals[firstIndex][secondIndex] = animals[num][num2]; // the animal moves to that empty location
			animals[num][num2] = null; // the location where the animal was previously is now empty
		}
		// executes if the animals at the two locations are the same type but not the same gender
		else if (animals[firstIndex][secondIndex].isSameType(animals[num][num2]) && !(animals[firstIndex][secondIndex].isSameGender(animals[num][num2])))
		{
			// generates a random location to add a new instance of that animal
			int num3 = rand.nextInt(animals.length);
			int num4 = rand.nextInt(animals[0].length);

			// if that location already has an animal, new numbers are generated
			// until a location that is not empty is found
			while (animals[num3][num4] != null)
			{
				num3 = rand.nextInt(animals.length);
				num4 = rand.nextInt(animals[0].length);
			}

			// if the animals that collided were bears, a new Bear is placed at that random location, same goes for Fish
			if (animals[num][num2].animalType().equals("Bear"))
				animals[num3][num4] = new Bear();
			else
				animals[num3][num4] = new Fish();

			// String returned by the method describing the fate of the animal/its move
			str = "The " + animals[num][num2].animalType() + " at position [" + num + "][" + num2 + "] moved " + movement
					+ " and mated with the " + animals[firstIndex][secondIndex].animalType() + " at position [" + firstIndex + "][" + secondIndex + "] to"
					+ " create a new offspring.\n\tThat new " + animals[num][num2].animalType() + " offspring was placed at empty location at ["
					+ num3 + "][" + num4 + "] and its parents are at their original locations.";
		}
		// executes if the animals at the two locations are the same type and the same gender
		else if (animals[firstIndex][secondIndex].isSameType(animals[num][num2]) && (animals[firstIndex][secondIndex].isSameGender(animals[num][num2])))
		{
			// executes if the random animal chosen has a greater strength than the one at the location its trying to move to
			if (animals[num][num2].hasGreaterStrength(animals[firstIndex][secondIndex]))
			{
				// String returned by the method describing the fate of the animal/its move
				str = "The " + animals[num][num2].animalType() + " at position [" + num + "][" + num2 + "] moved " + movement + " and fought with the "
						+ animals[firstIndex][secondIndex].animalType() + " at position [" + firstIndex + "][" + secondIndex + "].\n\tThe " + animals[num][num2].animalType() 
						+ " at position [" + num + "][" + num2 + "] won and is now at position [" + firstIndex + "][" + secondIndex + "], "
						+ "its strength increased by 3.";

				animals[firstIndex][secondIndex] = animals[num][num2]; // the animal successfully moves
				animals[num][num2] = null; // the location where the winning animal was is now empty after it moves
				animals[firstIndex][secondIndex].increaseStrength(); // the winning animal's strength increases
			}
			else // executes if the animal at the location another animal is trying to move to has the greater strength
			{  
				// String returned by the method describing the fate of the animal/its move
				str = "The " + animals[num][num2].animalType() + " at position [" + num + "][" + num2 + "] moved " + movement + " and fought with the "
						+ animals[firstIndex][secondIndex].animalType() + " at position [" + firstIndex + "][" + secondIndex + "].\n\tThe " + animals[num][num2].animalType() 
						+ " at position [" + firstIndex + "][" + secondIndex + "] won and stayed at position [" + firstIndex + "][" + secondIndex + "], "
						+ "its strength increased by 3.";

				animals[num][num2] = null; // the location of the animal that was trying to move is now empty since it lost
				animals[firstIndex][secondIndex].increaseStrength(); // the winning animal's strength increases
			}

		}
		// executes if the animals are not the same type
		else if (!animals[firstIndex][secondIndex].isSameType(animals[num][num2]))
		{
			// checks to see which animal is where to see if its a Bear moving towards a Fish or the other way around
			// executes if it is a Bear moving to a location where a fish is
			if (animals[num][num2].animalType().equals("Bear") && animals[firstIndex][secondIndex].animalType().equals("Fish"))
			{
				// String returned by the method describing the fate of the animal/its move
				str = "The " + animals[num][num2].animalType() + " at position [" + num + "][" + num2 + "] moved " + 
						movement + " and ate the " + animals[firstIndex][secondIndex].animalType() + " at position [" + firstIndex + "][" + secondIndex + "]."
						+ "\n\t The " + animals[firstIndex][secondIndex].animalType() + " died and the " + animals[num][num2].animalType() + " is now at position ["
						+ firstIndex + "][" + secondIndex + "].";

				animals[firstIndex][secondIndex] = animals[num][num2]; // the Bear moves to the location where the Fish was 
				animals[num][num2] = null; // the location where the Bear was previously is not empty
			}
			else // executes if its a Fish moving to a location where a Bear is
			{
				// String returned by the method describing the fate of the animal/its move
				str = "The " + animals[num][num2].animalType() + " at position [" + num + "][" + num2 + "] moved " + movement + " to the " 
						+ animals[firstIndex][secondIndex].animalType() + " at position [" + firstIndex + "][" + secondIndex + "]."
						+ "\n\tThe " + animals[firstIndex][secondIndex].animalType() + " at position [" + firstIndex + "][" + secondIndex + "] ate the "
						+ animals[num][num2].animalType() + " that was at position [" + num + "][" + num2 + "] and it died. The " 
						+ animals[firstIndex][secondIndex].animalType() + " is still at position [" + firstIndex + "][" + secondIndex + "].";

				animals[num][num2] = null; // the location where the fish was is now empty (it died)
			}
		}
		return str; 
	}

	/**
	 * toString method --
	 * Returns a String representing the state of the River 
	 * with the index of where the animal object is located in
	 * the array row by row. Tabs and spaces are concatenated
	 * to ensure the columns lines up appropriately in the 
	 * console.
	 * @return a reference to a String containing the state of
	 * the River
	 */
	public String toString()
	{
		String str = new String();

		// concatenates the animals at each location row major
		for (int row = 0; row < animals.length; row++)
		{
			// loops through each column for that row
			for (int col = 0; col < animals[0].length; col++)
			{
				if (animals[row][col] == null)
					str += "[" + row + "][" + col + "]                          \t";
				else
					str += "[" + row + "][" + col + "] " + animals[row][col].toString() + "\t";
			}
			str += "\n"; // each row is concatenated on a new separate line
		}
		return str;
	}
}