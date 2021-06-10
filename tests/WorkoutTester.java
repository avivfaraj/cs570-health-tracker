import java.util.Calendar;
/***************************************
 * WorkoutTester.java
 * White box for Workout.java
 * @author Aviv Farag
 * @version 6.0 - 06.10.21
 ****************************************/

class WorkoutTester {

   public static void main(String[] args){

   	Calendar date_1 = Calendar.getInstance();
   	Calendar date_2 = Calendar.getInstance();
    date_2.set(2020, 0, 2);

    Workout[] workouts = new Workout[4];

    // Constructor #1
    workouts[0] = new Workout(3600, "Virginia Beach", "Running", 600.0, date_1);
   	workouts[1] = new Workout(1800, "Virginia Beach", "Bike", 150.0, date_2);


    // Constructor #2
    workouts[2] = new Workout(1250, "Norfolk", "Weight lifting", 180.0);
    workouts[3] = new Workout(3500, "Norfolk", "Bike", 300.0);
    

    // Print duration in Hours and Minutes
    for (Workout workout : workouts){
    	System.out.println(workout.getDate().toInstant());
    	System.out.println(workout.getDateString());

    	System.out.println();
    	System.out.println(workout.getDuration());
    	System.out.println(workout.getDurationMinutes());
    	System.out.println(workout.getDurationHours());
    	System.out.println();
    }


    // Print toString and toFile
    for (Workout workout : workouts){
    	System.out.println(workout.toFile());
    	System.out.println();
    	System.out.println(workout.toString());
    	System.out.println();
    }

}
}
