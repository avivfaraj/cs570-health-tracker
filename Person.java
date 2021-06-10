import java.util.ArrayList;
import java.util.Calendar;
/***************************************
 * Person.java
 * Abstract class represent a person
 * with weight, height, name, id, gender,
 * and desired calorie intake
 * @author Aviv Farag
 * @version 6.0 - 06.10.21
 ****************************************/

public abstract class Person {

	// Variables Declaration
	protected int id_num;
	protected String name, gender; 
	protected double desiredCalorieIntake;
	protected double weightKG, heightCm;
	protected ArrayList<Workout> workouts;
	protected ArrayList<DailyConsumption> dailyc;
	
	// Setters
	protected final void setPerson(int id, String name, String gender,
							 double desiredCI, double weightKG, 
							 double heightCm){
		id_num = id;
		this.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();;
		this.gender = gender;
		desiredCalorieIntake = desiredCI;
		this.weightKG = weightKG;
		this.heightCm = heightCm;
		workouts = new ArrayList<Workout>();
		dailyc = new ArrayList<DailyConsumption>();
	}

	protected final void addWorkouts(Workout ... w){
		for (Workout workout : w)
		{
			// Make a copy of each Workout instance in the array
			workouts.add(new Workout(workout.getDuration(),
									 workout.getLocation(),
									 workout.getType(),
									 workout.getCalorie(),
									 workout.getDate()));
		}
	}

	protected final void addWorkouts(ArrayList<Workout> work){

		workouts.addAll(work);
	}

	protected final void addDailyConsumption(DailyConsumption dc){

		// Create a copy of dc instance
		dailyc.add(new DailyConsumption(dc));
	}

	// getters
	public final int getID(){
		return id_num;
	}

	public final String getName(){
		return name;
	}

	public final double getDesiredCalorieIntake(){
		return desiredCalorieIntake;
	}

	public final double getWeightKG(){
		return weightKG;
	}

	public final double getHeightCM(){
		return heightCm;
	}

	public final String getGender(){
		return gender;
	}
	public final String getWorkouts(){
		// Returns a string with all workouts!
		String final_string = "";
		for(Workout workout : workouts)
		{
			final_string += workout.toString();
			final_string += "\n";
		}

		return final_string;
	}

	public final String getWorkouts(Calendar date){
		// Returns a string with all after a specific date
		String final_string = "";
		for(Workout workout : workouts)
		{
			if (date.before(workout.getDate()))
			{
				final_string += workout.toString();
				final_string += "\n";
			}
			
		}

		return final_string;
	}


	public final String getDailyConsumption(String date){
		// Returns a string of Daily Consumption of a specific date
		for(DailyConsumption dc : dailyc)
		{

			if (date.equals(dc.getDate()))
			{
				return dc.toString();
			}
			
		}

		return "Not Found";
	}

	public final boolean addFood(String date, Food f){
		// Add food to a daily consumption in a specific date
		for (DailyConsumption dc : dailyc)
		{
			if (date.equals(dc.getDate()))
			{
				return dc.addFood(f);
			}
		}

		return false;
	}

	public final boolean deleteFood(String date, String name){
		// Delete food from daily consumption in a specific date
		for (DailyConsumption dc : dailyc)
		{
			if (date.equals(dc.getDate()))
			{
				return dc.deleteFood(name);
			}
		}
		
		return false;
	}
	

	// toFile method
	public String toFile(){
		// return fat;
		String toFile = (this.getID() + "," +
						 this.getName() + ","+
						 this.getGender() + ","+
						 this.getDesiredCalorieIntake()+","+
						 this.getWeightKG()+","+
						 this.getHeightCM()+"\n");
		return toFile;
	}

	// toString method
	public String toString(){

		String final_string = ("ID: " + this.getID() +
							   "\nName: "+ this.getName() +
							   "\nGender: "+this.getGender()+ 
							   "\nHeight(cm): "+ this.getHeightCM()+
							   "\nWeight(kg): "+this.getWeightKG()+
							   "\nDesired Calorie Intake: "+this.getDesiredCalorieIntake()+
							   "\n");

		return final_string;
	}
}


