import java.util.Calendar;
/***************************************
 * Workout.java
 * Represents a workout of specific type,
 * location and duration.
 * @author Aviv Farag
 * @version 6.0 - 06.10.21
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
		setLocation(location);
		this.calories = calories;
		setType(type);
		this.date = (Calendar) date.clone();
	}

	// Constructor #2
	public Workout(int seconds,String location,String type, 
				double calories){
		durationSec = seconds;
		setLocation(location);
		this.calories = calories;
		setType(type);
		date = Calendar.getInstance();
	}

	// getters
	private void setLocation(String location)
	{
		this.location = (location.substring(0,1).toUpperCase() +
						 location.substring(1).toLowerCase());
	}

	private void setType(String type)
	{
		this.type = (type.substring(0,1).toUpperCase() +
						 type.substring(1).toLowerCase());
	}
	public int getDuration(){
		return durationSec;
	}
	public double getDurationMinutes(){
		String temp = String.format("%.02f",durationSec / 60.0);
		return Double.parseDouble(temp);
	}
	public double getDurationHours(){
		String temp = String.format("%.02f",durationSec / 60.0);
		return Double.parseDouble(temp);
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
						 this.getCalorie());
		return toFile;
	}


	// toString method
	public String toString(){
		return ("\nDate: " + this.getDateString()+
			    "\nType: "+ this.getType() +
			    "\nLocation: "+this.getLocation()+
			    "\nDuration: "+this.getDurationMinutes() +" minutes"+
			    "\nCalories: "+ this.getCalorie()+"\n");
	}
}
