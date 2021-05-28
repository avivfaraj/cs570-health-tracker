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
 *
 * @author Aviv Farag
 * @version 1.0 - 05.25.21
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
  // *********************************************************************************

  

  // ************************************** Main Method ******************************
  public static void main (String args[]) throws IOException{
    

  	// Variables Declaration
   //  double curr_balance;
  	// String comment, date, time, trans, month_num, choice, am_pm = "";
  	// boolean run = true;
      // Array lists
    // ArrayList<String> lines = new ArrayList<String>();
   //  int counter = 0, month;
   //  Calendar calendar;


    //    Calendar date_1 = Calendar.getInstance();
    // Workout w1 = new Workout(3600, "Virginia Beach", "Running", 600.0, date_1);
    // Workout w2 = new Workout(1800, "Virginia Beach", "Bike", 150.0, date_1);


    // Variables Declaration
    Scanner keyboard = new Scanner(System.in);
    int max = -1;
    boolean inaugural_run = false;
    boolean new_user = true;
    int lines_num = -1;
    String headers = "date,type,duration,location,calorie\n";

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

        headers = "name,type,grams,calorie,protein,fat,carbs,sugars\n";
        createNewFile(fdFile, headers);

        headers = "id,date,type,duration,location,calorie\n";
        createNewFile(wFile, headers);

        headers = "id,date,name,type,grams,calorie,protein,fat,carbs,sugars\n";
        createNewFile(dcFile, headers);

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
        }

        try{
          fdArrayList = readFile(fdFile);
        }catch(FileNotFoundException exc)
        {
          headers = "name,type,grams,calorie,protein,fat,carbs,sugars\n";
          createNewFile(fdFile, headers);
        }

        try{
          dcArrayList = readFile(dcFile);
        }catch(FileNotFoundException exc)
        {
          headers = "id,date,name,type,grams,calorie,protein,fat,carbs,sugars\n";
          createNewFile(dcFile, headers);
        }

        // Ask for ID
        // InputMismatchException
        System.out.print("Enter your ID: ");
        int user_id = keyboard.nextInt();
        String[] attr;

        // Iterate over users save in file
        for (String line : usersArrayList)
        {

          // Split line into different attributes
          attr = line.split(",");

          // Ensure not first line (headers)
          if (!attr[0].equalsIgnoreCase("id"))
          {

            // Get id as an integer number
            max = Integer.parseInt(attr[0]);

            // Ensure user exist.
            if (max == user_id)
            {
              // Create user's instance based on gender
              if (attr[2].equalsIgnoreCase("male"))
              {
                user = new Male(max, attr[1],
                                Double.parseDouble(attr[4]),
                                Double.parseDouble(attr[5]),
                                Double.parseDouble(attr[3]));
              }
              else
              {
                user = new Female(max, attr[1],
                                Double.parseDouble(attr[4]),
                                Double.parseDouble(attr[5]),
                                Double.parseDouble(attr[3]));
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
      String[] attr;
      String[] date_att;
      int counter = 0;
      Calendar current_date = Calendar.getInstance();
      Calendar another_date = Calendar.getInstance();
      // Get workouts and daily consumption data for a specific user
      if (dcArrayList.size() > 1) // First line is headers
      {
        
        for (String line : dcArrayList)
        {
          attr = line.split(",");

          // Ensure not headers
          if (counter >= 1)
          {
            // headers = "id,date,name,type,grams,calorie,protein,fat,carbs,sugars\n";
            int id = Integer.parseInt(attr[0]);

            if (id == user.getID())
            {
              date_att = attr[1].split("-");  // Month-Day-Year
              another_date.clear();
              another_date.set(Integer.parseInt(date_att[2]),
                                 Integer.parseInt(date_att[0])-1,
                                 Integer.parseInt(date_att[1]));

              if (counter == 1)
              {
                current_date.clear();
                current_date.set(Integer.parseInt(date_att[2]),
                                 Integer.parseInt(date_att[0])-1,
                                 Integer.parseInt(date_att[1]));

                dc_temp.add(new DailyConsumption(current_date, 
                                                 new ArrayList<Food>()));
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
                // headers = "id,date,name,type,grams,calorie,protein,fat,carbs,sugars\n";
                Food food_from_file = new Food(attr[2],attr[3],
                                               Double.parseDouble(attr[4]),
                                               Double.parseDouble(attr[5]),
                                               Double.parseDouble(attr[6]),
                                               Double.parseDouble(attr[7]),
                                               Double.parseDouble(attr[8]),
                                               Double.parseDouble(attr[9])); 
                dc_temp.get(dc_temp.size() - 1).addFood(food_from_file);
              }

              
            }


          }
          counter++;

        }

        // Add all daily consumptions to user array list
        for(DailyConsumption dc : dc_temp) user.addDailyConsumption(dc);
      }
      // System.out.println(user.getDailyConsumption("05-27-2021"));

      if (workoutsArrayList.size() > 1) // First line is headers
      {
        current_date = Calendar.getInstance();
        
        counter = 0;
        for (String line : workoutsArrayList)
        {
          if (counter > 0) // Skip headers
          {
            attr = line.split(",");
            if (counter >= 1)
            {
              // headers = "id,date,type,duration,location,calorie\n";
              int id = Integer.parseInt(attr[0]);

              if (id == user.getID())
              {
                date_att = attr[1].split("-");
                current_date.clear();
                current_date.set(Integer.parseInt(date_att[2]),
                                 Integer.parseInt(date_att[0])-1,
                                 Integer.parseInt(date_att[1]));

                w_temp.add(new Workout(Integer.parseInt(attr[3]), // Duration in seconds
                                       attr[4],                   // Location String
                                       attr[2],                   // Type String
                                       Double.parseDouble(attr[5]),// Calories double
                                      current_date));              // Date Calendar
              }
            }

          }
          counter++;
        }
        user.addWorkouts(w_temp);  
      }
      System.out.println(user.getWorkouts());

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




    // writeFile(workoutsFile, w1.toFile(), true);
    // writeFile(workoutsFile, w2.toFile(), true);


    // while(run){

    //   // Menu
    //   System.out.print("\nChoose one of the following (enter a number): \n"+
    //                    "1. Convert USD(\u0024) to NIS(\u20AA) \n"+
    //                    "2. Make A Transaction \n"+
    //                    "3. Print Current Balance \n"+
    //                    "4. Print Transaction History \n"+
    //                    "5. Exit \n\n");

    //   System.out.print("Your Choice: ");
    //   choice = keyboard.next();

    //   System.out.print("\n>-----------------------------------------<\n");
    //   switch(choice){

    //       case "1":

    //           break;

    //       case "2":

    //           break;

    //       case "3":

    //           break;

    //       case "4":
                
               
    //           break;

    //       case "7":

    //           // Exit
    //           System.out.print("\nClosing program... \n");
    //           run = false;
    //           break;

    //       default:
    //           System.out.print("Wrong choice.\nPlease choose a number between 1 and 5 according to the menu below.\n\n");
    //           break;
    //   }

    
      
    // }
    // Close Scanner object
    keyboard.close();

    // Inform user 
    // System.out.printf("\nClosed! \n");
    System.out.print("\n>------------------- Closed ---------------------<\n");

  }
} 