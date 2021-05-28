import java.util.ArrayList;
/***************************************
 * Person.java
 * 
 * @author Aviv Farag
 * @version 05242021
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
		this.name = name;
		this.gender = gender;
		desiredCalorieIntake = desiredCI;
		this.weightKG = weightKG;
		this.heightCm = heightCm;
		workouts = new ArrayList<Workout>();
		dailyc = new ArrayList<DailyConsumption>();
	}

	// protected final void addWorkouts(Workout ... w){
	// 	for (Workout workout : w)
	// 	{
	// 		workouts.add(workout);
	// 	}
	// }

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

		/**** NOTE: Consider printing workouts 
			        for a specific date!
		**/
		String final_string = "";
		for(Workout workout : workouts)
		{
			final_string += workout.toString();
			final_string += "\n";
		}

		return final_string;
	}


	public final String getDailyConsumption(String date){

		/**** NOTE: Deal with MM/DD/YYYY and M/D/YYYY
		**/
		for(DailyConsumption dc : dailyc)
		{
			// System.out.println(dc.getDate()+"  "+ date);


			if (date.equals(dc.getDate()))
			{
				return dc.toString();
			}
			
		}

		return "Not Found";
	}

	public final boolean addFood(String date, Food f){
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

		/**** NOTE: Add Workouts and DailyCOnsumptions to toString
		**/
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


