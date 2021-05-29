
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import java.util.Calendar;
/***************************************
 * DailyConsumption.java
 * Represents consumption for a specific date
 * Counts calorie, protein, carbs, sugars and fat.
 * @author Aviv Farag
 * @version 3.0 - 05.29.21
 ****************************************/

public class DailyConsumption implements ManageFood {

	// Variables Declaration
	private Calendar date;
	private ArrayList<Food> foodConsumed; 
	private double calorieIntake, totalProtein, totalFat;
	private double totalCarbs, totalSugars;

	// Constructor #1
	public DailyConsumption(){
		foodConsumed = new ArrayList<Food>();
		calorieIntake = 0;
		totalProtein = 0;
		totalFat = 0;
		totalSugars = 0;
		totalCarbs = 0;
		date = Calendar.getInstance();
	}

	// Constructor #2
	public DailyConsumption(Calendar date, 
							ArrayList<Food> data){
		foodConsumed = new ArrayList<Food>(data.size());

		// Must use addAll in order to make a copy
		foodConsumed.addAll(data);

		// Must clone to make a different reference
		this.date = (Calendar) date.clone();
		for (Food item : foodConsumed)
		{
			calorieIntake += item.getCalorie();
			totalProtein += item.getProtein();
			totalFat += item.getFat();
			totalSugars += item.getSugars();
			totalCarbs += item.getCarbs();
		}
	}

	// Constructor #3
	// This constructor only copies an instance. 
	// The same idea as clone
	public DailyConsumption(DailyConsumption new_dc){
		foodConsumed = new ArrayList<Food>(new_dc.foodConsumed.size());

		// Must use addAll in order to make a copy
		foodConsumed.addAll(new_dc.foodConsumed);

		// Must clone to make a different reference
		this.date = (Calendar) new_dc.date.clone();
		for (Food item : foodConsumed)
		{
			calorieIntake += item.getCalorie();
			totalProtein += item.getProtein();
			totalFat += item.getFat();
			totalSugars += item.getSugars();
			totalCarbs += item.getCarbs();
		}
	}


	// getters

	public String getDate()
	{
		// Must add 1 to month (0 - January)
		String month = String.format("%02d" ,date.get(Calendar.MONTH) + 1);

		String date_str = (month + "-" +
						   String.format("%02d",date.get(Calendar.DATE)) + "-" +
						   date.get(Calendar.YEAR));
		return date_str; 

	}

	public double getCalorieIntake(){
		return calorieIntake;
	}

	public double getTotalProtein(){
		return totalProtein;
	}
	public double getTotalFat(){
		return totalFat;
	}
	public double getTotalCarbs(){
		return totalCarbs;
	}
	public double getTotalSugars(){
		return totalSugars;
	}

	public String getFoodConsumed()
	{
		String fcString = "";
		for (Food item : foodConsumed)
		{
			fcString += item.toString();
			fcString += "\n";
		} 

		return fcString;
	}


	public boolean addFood(Food f)
	{
		boolean added = foodConsumed.add(f);
		if (added)
		{
			calorieIntake += f.getCalorie();
			totalProtein += f.getProtein();
			totalFat += f.getFat();
			totalSugars += f.getSugars();
			totalCarbs += f.getCarbs();

			return true;
		}
		return false;
		
	}

	public boolean deleteFood(String n)
	{ 
		int index = -1, counter = 0;
		for (Food item : foodConsumed)
		{
			if (item.getName().equalsIgnoreCase(n))
			{
				index = counter;
			}
			counter++;
		}

		if (index != -1)
		{
			Food removed = foodConsumed.remove(index);
			calorieIntake -= removed.getCalorie();
			totalProtein -= removed.getProtein();
			totalFat -= removed.getFat();
			totalSugars -= removed.getSugars();
			totalCarbs -= removed.getCarbs();
			return true;
		}

		return false;
	}

	// toFile method
	public String toFile(){

		String toFile = "";
		for (Food item : foodConsumed)
		{
			toFile += getDate()+","+item.toFile();
		}

		return toFile;
	}


	// toString method
	public String toString(){

		String toString = ("Date: "+getDate()+
						   "\nCalorie Intake: "+ String.format("%.2f",getCalorieIntake())+
						   "\nTotal Protein: "+ String.format("%.2f",getTotalProtein()) + 
						   "\nTotal Fat: "+ String.format("%.2f",getTotalFat())+
						   "\nTotal Carbohydrates: "+String.format("%.2f",getTotalCarbs())+
						   "\nTotal Sugars: " + String.format("%.2f",getTotalSugars())+
						   "\n\nList of food consumed: \n");
		for (Food item : foodConsumed)
		{
			toString += item.toString();
			toString += "\n\n";
		}
		return toString;
}
}

