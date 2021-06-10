
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
/***************************************
 * FoodDataset.java
 * Hold Food instances as dataset 
 * @author Aviv Farag
 * @version 6.0 - 06.10.21
 ****************************************/

public class FoodDataset implements ManageFood {

	// Variables Declaration
	private ArrayList<Food> foods; 
	private int count;

	// Constructor #1
	public FoodDataset(){
		foods = new ArrayList<Food>();
		count = 0;
	}

	// Constructor #2
	public FoodDataset(ArrayList<Food> data){
		foods = data;
		count = data.size();
	}


	// getters
	public ArrayList<Food> getData(){
		return foods;
	}

	public boolean search(String name){

		// iterate over food items
		for (Food item : foods){

			// Ensure name equals ignoring case
			if (item.getName().equalsIgnoreCase(name))
			{
				// Return true if equals
				return true;
			}
		}

		// Name not found
		return false;
	}

	public int getIndex(String name){

		// Initialize counter
		int counter = 0;

		// Iterate over food items
		for (Food item : foods){

			// Ensure name equals ignoring case
			if (item.getName().equalsIgnoreCase(name))
			{
				// return counter
				return counter;
			}
			// increment counter
			counter++;
		}

		// Was not found
		return -1;
	}

	public Food getFood(String name){

		// Ensure item exist in data set
		if (search(name))
		{
			// Returns item's details as string.
			return foods.get(getIndex(name));
		}

		return null;

	}

	public String getNutrientsString(String name){

		// Ensure item exist in data set
		if (search(name))
		{
			// Returns item's details as string.
			return foods.get(getIndex(name)).toString();
		}

		// Return a message because not found
		return "Item was not found in dataset";
	}

	public double[] getNutrientsDouble(String name){
		double[] nutrients = new double[6];
		// Ensure item exist in data set
		if (search(name))
		{
			// Item was found
			Food item = foods.get(getIndex(name));
			nutrients[0] = item.getGrams();
			nutrients[1] = item.getCalorie();
			nutrients[2] = item.getProtein();
			nutrients[3] = item.getFat();
			nutrients[4] = item.getCarbs();
			nutrients[5] = item.getSugars();
			
 
		}

		// Returns the array
		return nutrients;
	}
	
	public boolean addFood(Food f){

		// Add food item
		boolean added = foods.add(f);

		// Increment count if added
		if (added) count++;

		// Return boolean value added
		return added;
	}

	public boolean deleteFood(String name){

		// Initialize to false
		boolean removed = false;

		// Ensure item exists in dataset
		if (search(name))
		{
			// Try remove food
			try{
				removed = foods.remove(foods.get(getIndex(name)));
			}
			catch(IndexOutOfBoundsException exce)
			{
				// Exception occured -- item was not removed!
				return false;
			}

			// Ensure item removed
			if (removed)
			{
				// Decrement count
				count--;

				// Return true - item removed!
				return true;
			}
		}

		// Item was not removed - return false;
		return false;
	}


	// toFile method
	public String toFile(){

		String toFile = "";
		for (Food item : foods)
		{
			toFile += item.toFile();
		}
		return toFile;
	}


	// toString method
	public String toString(){

		String toString = "";
		for (Food item : foods)
		{
			toString += item.toString();
			toString += "\n\n";
		}
		return toString;
}
}

