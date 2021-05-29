import java.util.Calendar;
/***************************************
 * Workout.java
 * 
 * @author Aviv Farag
 * @version 05242021
 ****************************************/

public class Workout {

	// Variables Declaration
	private String location, type; 
	private double calories;
	private int durationSec; 
	private Calendar date;

	// Constructor #1
	public Workout(int seconds,String location,String type, 
				double calories,Calendar date){
		durationSec = seconds;
		this.location = location;
		this.calories = calories;
		this.type = type;
		this.date = (Calendar) date.clone();
	}

	// Constructor #2
	public Workout(int seconds,String location,String type, 
				double calories){
		durationSec = seconds;
		this.location = location;
		this.calories = calories;
		this.type = type;
		date = Calendar.getInstance();
	}

	// getters
	public int getDuration(){
		return durationSec;
	}
	public double getDurationMinutes(){
		return durationSec / 60.0;
	}
	public double getDurationHours(){
		return durationSec / 3600.0;
	}

	public String getLocation(){
		return location;
	}
	public String getType(){
		return type;
	}
	public Calendar getDate(){
		return date;
	}
	public String getDateString(){
		// Must add 1 to month (0 - January)
		String month = String.format("%02d" ,date.get(Calendar.MONTH) + 1);

		String date_str = (month + "-" +
						   String.format("%02d",date.get(Calendar.DATE)) + "-" +
						   date.get(Calendar.YEAR));

		return date_str;
	}
	public double getCalorie(){
		return calories;
	}

	// toFile method
	public String toFile(){
		// return fat;
		String toFile = (this.getDateString() + "," +
						 this.getType() + ","+
						 this.getDuration()+","+
						 this.getLocation()+","+
						 this.getCalorie()+"\n");
		return toFile;
	}


	// toString method
	public String toString(){
		return ("Workout: " +
				"\nDate: " + this.getDateString()+
			    "\nType: "+ this.getType() +
			    "\nLocation: "+this.getLocation()+
			    "\nDuration: "+this.getDurationMinutes() +" minutes"+
			    "\nCalories: "+ this.getCalorie());
	}
}