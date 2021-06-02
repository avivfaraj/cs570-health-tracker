// Util packages
import java.util.Scanner;
import java.util.Calendar;
import java.util.ArrayList;

// File package
import java.io.File;

// Exceptions packages
import java.io.FileNotFoundException;
import java.io.IOException;

// File writer package
import java.io.FileWriter;

/***************************************
 * HealthTracker.java
 * Tracks the food you eat everyday
 * Tracks workouts done everyday 
 * 
 * @author Aviv Farag
 * @version 4.0 - 05.30.21
 ****************************************/

public class HealthTracker {
  
  // ******************************* Useful Methods **********************************
  // Write to file method 
  public static void writeFile(File file, String str, boolean append_line) throws IOException
  {
    // FileWriter object to write to a file.
    // If append_line is true the line will be added in the end
    // Otherwise a new file will be written and override an existing one
    FileWriter writer = new FileWriter(file,append_line);

    // Write string to the file
    writer.write(str);

    // Close FileWriter object
    writer.close();
  }

  // Reads a file and return lines as ArrayList<String>
  public static ArrayList<String> readFile(File file) throws FileNotFoundException
  {
    // Decalre ArrayList
    ArrayList<String> lines = new ArrayList<String>();
    String line = "";

    // Scanner object to read a file
    Scanner reader = new Scanner(new File(file.getPath()));

    // Iterate over lines
    // Scanner is iterator which means
    // It has a method hasNextLine() that will
    // return the sentinal value for that loop
    while(reader.hasNextLine())
    {
       // Reading line
      line = reader.nextLine();

      // Add this line to ArrayList
      lines.add(line);
    }

    // Close Scanner
    reader.close();

    // Return ArrayList
    return lines;

  }

  public static boolean createNewFile(File file, String headers) throws IOException{
    if (!file.exists())
    {
      writeFile(file, headers, false);
      return true;
    }

    return false;
  }


  public static void insertToFile(Calendar current_date, File file, String line_to_insert, ArrayList<String> arr) throws IOException{

    // Initialize variables
    int counter = 0;
    Calendar another_date = Calendar.getInstance();
    int index = -1;
    String[] line_attr, date_attr;
    String toFile = "";

    // Iterate over lines in arr
    for (String line : arr)
    {

      // Line attributes splitting by comma
      line_attr = line.split(",");

      // Ensure not headers
      if (!line_attr[0].equalsIgnoreCase("id"))
      {

        another_date = stringToDate(line_attr[1]);

        // Ensure another_date is greater than current_date
        if(another_date.after(current_date))
        {
          // Save index
          index = counter;

          // Exit loop!
          break;
        }
      }

      // Increment counter
      counter++;
    }

    // Append line
    if (index == -1)
    {
      // writeFile(file, user.getID() +","+new_workout.toFile()+ "\n", true);
      writeFile(file, line_to_insert+"\n", true);

      // Append line to array list
      arr.add(line_to_insert);

    }

    // Insert in between
    else
    {
      // Insert line to array list in index 
      arr.add(index, line_to_insert);

      // Initialize variables
      // counter = 0;
      toFile = "";

      // Iterate over array list
      for (String w : arr)
      {

        // Add line
        toFile += w + "\n";

        //increment counter
        counter++;
        
      }

      // Write String to file
      writeFile(file, toFile, false);

    }
  }

  public static Calendar stringToDate(String date)
  {
    String[] date_attr = date.split("-");  // Month-Day-Year
    Calendar another_date = Calendar.getInstance();
    another_date.clear();
    another_date.set(Integer.parseInt(date_attr[2]),
                       Integer.parseInt(date_attr[0])-1,
                       Integer.parseInt(date_attr[1]));

    return (Calendar) another_date.clone();
  }
  // *********************************************************************************

  

  // ************************************** Main Method ******************************
  public static void main (String args[]) throws IOException{
    

    //    Calendar date_1 = Calendar.getInstance();
    // Workout w1 = new Workout(3600, "Virginia Beach", "Running", 600.0, date_1);
    // Workout w2 = new Workout(1800, "Virginia Beach", "Bike", 150.0, date_1);
    // writeFile(workoutsFile, w1.toFile(), true);
    // writeFile(workoutsFile, w2.toFile(), true);


    // Variables Declaration
    int index = -1;
    Calendar current_date;
    Calendar another_date;
    boolean run = true;
    Scanner keyboard = new Scanner(System.in);
    int max = -1;
    int counter = 0;
    boolean inaugural_run = false;
    boolean new_user = true;
    int lines_num = -1;
    String choice = "";
    String headers = "date,type,duration,location,calorie\n";
    FoodDataset foodData = new FoodDataset();
    String month;
    String date = "", toFile = "", food_name = "", brand_owner = "";
    String[] date_attr, line_attr;
    FoodSearch fs = new FoodSearch();


    // Define user as Male() with no attributes
    // This is only to avoid an error of no initialization
    Person user = new Male();

    // "Files" directory
    File filesDir = new File('.' + File.separator +"Files");

    // Ensure directory exists
    if (!filesDir.exists())
    {
      // Make a new directory
      filesDir.mkdir();

      // Inaugural run - new user
      inaugural_run = true;
    }

    // Define other files required for that program
    File wFile = new File("Files" +File.separator+ "workouts.csv");
    File uFile = new File("Files" +File.separator+ "users.csv");
    File dcFile = new File("Files" +File.separator+ "dailyConsumption.csv");
    File fdFile = new File("Files" +File.separator+ "foodDataset.csv");
    
    // Array Lists for reading files.
    ArrayList<String> workoutsArrayList = new ArrayList<String>();
    ArrayList<String> usersArrayList = new ArrayList<String>();
    ArrayList<String> fdArrayList = new ArrayList<String>();
    ArrayList<String> dcArrayList = new ArrayList<String>();

      // Try reading users file
      try{
           usersArrayList = readFile(uFile);

           // Get number of lines in file
           lines_num = usersArrayList.size();

      }catch(FileNotFoundException exc)
      {
        // No users in memory - inaugural_run
        System.out.println("Created new users file");
        inaugural_run  = true;

        // Creating required files
        headers = "id,name,gender,height,weight\n";
        createNewFile(uFile, headers);
        usersArrayList.add(headers);

        headers = "name,category,brandOwner,grams,calorie,protein,fat,carbs,sugars\n";
        createNewFile(fdFile, headers);
        fdArrayList.add(headers);

        headers = "id,date,type,duration,location,calorie\n";
        createNewFile(wFile, headers);
        workoutsArrayList.add(headers);

         headers = "id,date,name,category,brandOwner,grams,calorie,protein,fat,carbs,sugars\n";
        createNewFile(dcFile, headers);
        dcArrayList.add(headers);

        // Max id is to make unique id
        max = 1000;
      }

    if (lines_num == 1)
    {
      // Only headers
      inaugural_run = true;

      max = 1000;

    }
    else if (lines_num > 1)
    {
        // Find user
        try{
          workoutsArrayList = readFile(wFile);
        }catch(FileNotFoundException exc)
        {
          headers = "id,date,type,duration,location,calorie\n";
          createNewFile(wFile, headers);
          workoutsArrayList.add(headers);
        }

        try{
          dcArrayList = readFile(dcFile);
        }catch(FileNotFoundException exc)
        {
           headers = "id,date,name,category,brandOwner,grams,calorie,protein,fat,carbs,sugars\n";
          createNewFile(dcFile, headers);
          dcArrayList.add(headers);

        }

        // Ask for ID
        // InputMismatchException
        System.out.print("Enter your ID: ");
        int user_id = keyboard.nextInt();
        // String[] attr;

        // Iterate over users save in file
        for (String line : usersArrayList)
        {

          // Split line into different attributes
          line_attr = line.split(",");

          // Ensure not first line (headers)
          if (!line_attr[0].equalsIgnoreCase("id"))
          {

            // Get id as an integer number
            max = Integer.parseInt(line_attr[0]);

            // Ensure user exist.
            if (max == user_id)
            {
              // Create user's instance based on gender
              if (line_attr[2].equalsIgnoreCase("male"))
              {
                user = new Male(max, line_attr[1],
                                Double.parseDouble(line_attr[4]),
                                Double.parseDouble(line_attr[5]),
                                Double.parseDouble(line_attr[3]));
              }
              else
              {
                user = new Female(max, line_attr[1],
                                Double.parseDouble(line_attr[4]),
                                Double.parseDouble(line_attr[5]),
                                Double.parseDouble(line_attr[3]));
              }

              // Avoid creating new user
              new_user = false;

            }
          }
        }
        
    }

    // Welcome Message
    System.out.print("\n>--------------- Health Tracker -----------------<\n");
    
    // Ensure user is defined
    if (!inaugural_run && !new_user)
    {
      // Welcome message
      System.out.printf("\nWelcome Back %s!\n", user.getName());
      ArrayList<DailyConsumption> dc_temp = new ArrayList<DailyConsumption>();
      ArrayList<Workout> w_temp = new ArrayList<Workout>();
      current_date = Calendar.getInstance();
      another_date = Calendar.getInstance();
      boolean first_line = true;
      // Get workouts and daily consumption data for a specific user
      if (dcArrayList.size() > 1) // First line is headers
      {
        
        for (String line : dcArrayList)
        {
          line_attr = line.split(",");

          // Ensure not headers
          if (counter >= 1)
          {
            //  headers = "id,date,name,category,brandOwner,grams,calorie,protein,fat,carbs,sugars\n";
            int id = Integer.parseInt(line_attr[0]);

            if (id == user.getID())
            {
              another_date = stringToDate(line_attr[1]);

              if (first_line)
              {
                current_date = stringToDate(line_attr[1]);
      
                dc_temp.add(new DailyConsumption(current_date, 
                                                 new ArrayList<Food>()));
                first_line = false;
              }
              else if (another_date.after(current_date))
              {
                current_date.clear();

                // Update current_date with a copy of another_date
                current_date = (Calendar) another_date.clone();

                // Create a new daily_consumption 
                dc_temp.add(new DailyConsumption(current_date, 
                                                 new ArrayList<Food>()));
              }

              if (dc_temp.size() > 0)
              {
                // headers = "id,date,name,category,brandOwner,grams,calorie,protein,fat,carbs,sugars\n";
                Food food_from_file = new Food(line_attr[2],
                                               line_attr[3],
                                               line_attr[4],
                                               Double.parseDouble(line_attr[5]),
                                               Double.parseDouble(line_attr[6]),
                                               Double.parseDouble(line_attr[7]),
                                               Double.parseDouble(line_attr[8]),
                                               Double.parseDouble(line_attr[9]),
                                               Double.parseDouble(line_attr[10])); 
                dc_temp.get(dc_temp.size() - 1).addFood(food_from_file);
              }

              
            }


          }
          counter++;

        }

        // Add all daily consumptions to user array list
        for(DailyConsumption dc : dc_temp) user.addDailyConsumption(dc);

        // Free memory by clearing array list
        dc_temp.clear();
      }
      // System.out.println(user.getDailyConsumption("05-31-2021"));

      if (workoutsArrayList.size() > 1) // First line is headers
      {
        current_date = Calendar.getInstance();
        
        counter = 0;
        for (String line : workoutsArrayList)
        {
          if (counter > 0) // Skip headers
          {
            line_attr = line.split(",");
            if (counter >= 1)
            {

              int id = Integer.parseInt(line_attr[0]);

              if (id == user.getID())
              {
    
                current_date = stringToDate(line_attr[1]);

                w_temp.add(new Workout(Integer.parseInt(line_attr[3]), // Duration in seconds
                                       line_attr[4],                   // Location String
                                       line_attr[2],                   // Type String
                                       Double.parseDouble(line_attr[5]),// Calories double
                                      current_date));              // Date Calendar
              }
            }

          }
          counter++;
        }
        user.addWorkouts(w_temp);

        // Free memory by clearing array list
        w_temp.clear();  
      }
      // System.out.println(user.getWorkouts());

    }
    else  // New user must be created.
    {
      // unique id = max + 1;
      int unique_id = max + 1;

      // Ask for details
      System.out.print("Enter your name: ");
      String user_name = keyboard.next();

      System.out.print("Enter your gender: ");
      String user_gender = keyboard.next();

      System.out.print("Enter your desired calorie intake (If you are not sure enter -1): ");
      double user_dci = keyboard.nextDouble();

      System.out.print("Enter your weight (kg): ");
      double user_weight = keyboard.nextDouble();

      System.out.print("Enter your height: ");
      double user_height = keyboard.nextDouble();

      // System.out.print(user_name +" " + user_gender +" "+
      //                  user_dci +" "+ user_weight +" "+ user_height);

      // Create user's instance
      if (user_gender.equalsIgnoreCase("male"))
      {
        if (user_dci < 0)
          user = new Male(unique_id, user_name, user_weight, user_height);
        else
          user = new Male(unique_id, user_name, user_weight, user_height, user_dci);
      }
      else
      {
        if (user_dci < 0)
          user = new Female(unique_id, user_name, user_weight, user_height);
        else
          user = new Female(unique_id, user_name, user_weight, user_height, user_dci);
      }

      // Welcome message to the new user
      System.out.printf("\nHey %s, Welcome to HealthTracker program!\n",user_name);
      System.out.printf("\nYour unique id is: %d. Make sure to remember it in order to log in.\n",unique_id);

      // Append new user to file
      writeFile(uFile, user.toFile(), true);
    }

    // Read foodDataset.csv file
    try{
      fdArrayList = readFile(fdFile);
    }catch(FileNotFoundException exc)
    {
      headers = "name,category,brandOwner,grams,calorie,protein,fat,carbs,sugars\n";
      createNewFile(fdFile, headers);
      fdArrayList.add(headers);
    }

    counter = 0;

    // Ensure data exists
    if (fdArrayList.size() > 1)
    {
      for(String line : fdArrayList)
      {
        if (counter > 0)
        {
          line_attr = line.split(",");
          foodData.addFood(new Food(line_attr[0], // Name
                                    line_attr[1], // Category
                                    line_attr[2], // Brand owner
                                    Double.parseDouble(line_attr[3]), //Grams
                                    Double.parseDouble(line_attr[4]), //Calories
                                    Double.parseDouble(line_attr[5]), //Protein
                                    Double.parseDouble(line_attr[6]), //Fat
                                    Double.parseDouble(line_attr[7]), //Carbs
                                    Double.parseDouble(line_attr[8])));//Sugars

        }
        counter++;
      }
    }
    // System.out.println(foodData.toString());


    while(run){

      // Menu
      System.out.print("\n>-------------------- Menu ----------------------<\n");
      System.out.print("\nChoose one of the following (enter a number): \n"+
                       "1. Add New Meal \n"+
                       "2. Add New Workout \n"+
                       "3. Daily Consumption \n"+ // Change it
                       "4. Workouts Done This Week \n"+
                       "5. Food Data Set \n"+
                       "6. Add Food to Data Set \n"+
                       "7. Exit\n");

      System.out.print("Your Choice: ");
      choice = keyboard.next();

    //   System.out.print("\n>-----------------------------------------<\n");
      switch(choice){

            case "1":

              boolean added = false;
              Food meal = null, new_meal = null;

              // Get date from user
              System.out.print("Enter date (Format MM-DD-YYYY): ");
              date = keyboard.next();

              current_date = stringToDate(date);

              // Get food from user
              System.out.print("Enter Food: ");
              food_name = keyboard.next(); // NextLine has a bug
              if (keyboard.hasNextLine()) {
                food_name += keyboard.nextLine();
              }

              // Get amount in grams from user
              System.out.print("Enter Grams: ");
              double food_grams = keyboard.nextDouble();

              // Ensure food item exist in data set
              if (foodData.search(food_name))
              {

                // Find the food in data set
                meal = foodData.getFood(food_name);

                // Get its type
                String food_type = meal.getCategory();

                brand_owner = meal.getBrand();

                // Get food's nutrients in double
                double[] nutrients = foodData.getNutrientsDouble(food_name);

                // Iterate over nutrients and change them according to 
                // the grams entered by user
                for (int iter = 0; iter < nutrients.length; iter ++)
                {
                  nutrients[iter] = nutrients[iter] * food_grams / 100;
                }

                // Create a new Food instance.
                new_meal = new Food(food_name,
                                    food_type,
                                    brand_owner,
                                    nutrients[0], // Grams
                                    nutrients[1], // Calorie
                                    nutrients[2], // Protein
                                    nutrients[3], // Fat
                                    nutrients[4], // Carbs
                                    nutrients[5]);// Sugars

                // Add the new meal to the user
                added = user.addFood(date,new_meal);
              }

              // Date not found
              if (!added)
              {
                // Create a new Daily Consumption instance for user.
                user.addDailyConsumption(new DailyConsumption(current_date, new ArrayList<Food>()));

                // Add new meal
                user.addFood(date,new_meal);
              }

              // Write new line to file and add to Array List
              insertToFile(current_date,dcFile,user.getID() + "," + date +","+ new_meal.toFile(), dcArrayList);

              break;

          case "2":

              // Get date from user 
              System.out.print("Enter date (Format MM-DD-YYYY): ");
              date = keyboard.next();
              current_date = stringToDate(date);

              // Get workout type from user
              System.out.print("Enter workout type: ");
              String type = keyboard.next();
              if (keyboard.hasNextLine()) {
                type += keyboard.nextLine();
              }

              // Get duration in minutes from user
              System.out.print("Enter duration in minutes: ");
              double durationMinutes = keyboard.nextDouble();

              // Get location from user
              System.out.print("Enter location: ");
              String location = keyboard.next();
              if (keyboard.hasNextLine()) 
                {
                   location += keyboard.nextLine();
                }
              
              // Get calories burned from user.
              System.out.print("Enter calories burned: ");
              double calorie = keyboard.nextDouble();

              // Create a new workout instance
              Workout new_workout = new Workout((int)durationMinutes * 60, 
                                                location, type, calorie, current_date);

              // Add the new instance to user.
              user.addWorkouts(new_workout);

              // Write new line to file and add to Array List
              insertToFile(current_date,wFile,user.getID() +","+new_workout.toFile(), workoutsArrayList);

              break;

          case "3":

              System.out.print("Enter date (enter -1 for today): ");
              String date_str = keyboard.next();
              if (date_str.equals ("-1"))
              {
                // Calendar new_date = Calendar.getInstance();
                current_date = Calendar.getInstance();
                month = String.format("%02d" ,current_date.get(Calendar.MONTH) + 1);

                date_str = (month + "-" +
                            String.format("%02d",current_date.get(Calendar.DATE)) + "-" +
                            current_date.get(Calendar.YEAR));
              }

              
              String dcString = user.getDailyConsumption(date_str);
              if (!dcString.equalsIgnoreCase("Not found"))
              {
                System.out.print("\n*********** Daily Consumption ***********\n\n");
                System.out.print(dcString);

              }
              else System.out.print("No records for that date!\n");

              break;
              
          case "4":

              // Current date
              current_date = Calendar.getInstance();

              // Substract 7 days
              current_date.add(Calendar.DATE, -7); 
              
              // Print last week workouts
              String workoutsStr = user.getWorkouts(current_date);

              if (!workoutsStr.isEmpty())
              {
                System.out.print("\n*********** Workouts Done This Week ***********\n");
                System.out.print(workoutsStr);
                
              }
              
               
              break;

          case "5":
              System.out.print(foodData.toString());
              System.out.print("\n");

              break;

          case "6":
              // Get food from user
              System.out.print("Enter Food: ");
              food_name = keyboard.next(); // NextLine has a bug
              if (keyboard.hasNextLine()) {
                food_name += keyboard.nextLine();
              }

              // Get food from user
              System.out.print("Enter Brand: ");
              brand_owner = keyboard.next(); // NextLine has a bug
              if (keyboard.hasNextLine()) {
                brand_owner += keyboard.nextLine();
              }

              // System.out.println(food_name);
              // System.out.println(brand_owner);
              Food new_food = null;
              try{
                 new_food = fs.searchFood(food_name,brand_owner);
              }catch(IOException ioe)
              {
                System.out.print("IOException\n");
              }

              if (new_food != null)
              {
                writeFile(fdFile,new_food.toFile()+"\n", true);
                fdArrayList.add(new_food.toFile()+"\n");
                foodData.addFood(new_food);
                System.out.println("Item Successfully added: ");
                System.out.println(new_food.toString());

              }
              else
              {
                System.out.println("Couldn't find this item");
              }

              break;

          case "7":

              // Exit
              System.out.print("\nClosing program... \n");
              run = false;
              break;

          default:
              System.out.print("Wrong choice.\nPlease choose a number between 1 and 7 (inclusive) according to the menu below.\n\n");
              break;
      }

    
      
    }
    // Close Scanner object
    keyboard.close();

    // Inform user 
    // System.out.printf("\nClosed! \n");
    System.out.print("\n>------------------- Closed ---------------------<\n");

  }
} 