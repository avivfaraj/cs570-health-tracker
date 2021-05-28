
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
/***************************************
 * FoodDataset.java
 * 
 * @author Aviv Farag
 * @version 05242021
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
		for (Food item : foods){
			if (item.getName().equalsIgnoreCase(name))
			{
				return true;
			}
		}

		return false;
	}

	public int getIndex(String name){
		int counter = 0;
		for (Food item : foods){
			if (item.getName().equalsIgnoreCase(name))
			{
				return counter;
			}
			counter++;
		}

		// Was not found
		return -1;
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
		boolean added = foods.add(f);
		if (added) count++;
		return added;
	}

	public boolean deleteFood(String name){
		boolean removed = false;
		if (search(name))
		{
			try{
				removed = foods.remove(foods.get(getIndex(name)));
			}
			catch(IndexOutOfBoundsException exce)
			{
				return false;
			}

			if (removed)
			{
				count--;
				return true;
			}
		}

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

