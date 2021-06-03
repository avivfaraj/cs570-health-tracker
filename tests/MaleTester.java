import java.util.Calendar;
import java.util.ArrayList;
/***************************************
 * MaleTester.java
 * 
 * @author Aviv Farag
 * @version 05242021
 ****************************************/

class MaleTester {

   public static void main(String[] args){

   Person sean = new Male(1001, "Sean", 100, 178, 1750);
   Person michael = new Male(1002, "Micheal", 90, 190);


   System.out.println(sean.toString());
   System.out.println();

   System.out.println(michael.toString());

   ArrayList<Workout> wArr = new ArrayList<Workout>(3);
   Calendar date_1 = Calendar.getInstance();
    wArr.add(new Workout(3600, "Virginia Beach", "Running", 600.0, date_1));
   	wArr.add(new Workout(1800, "Virginia Beach", "Bike", 150.0, date_1));
   	wArr.add(new Workout(1250, "Norfolk", "Weight lifting", 180.0));

   	sean.addWorkouts(wArr);

   	System.out.println(sean.getWorkouts());
   	System.out.println();

   System.out.println(michael.toFile());



   ArrayList<Food> arrList =new ArrayList<Food>();
   arrList.add(new Food("YOGURT","Dairy","The best Cheese",100,73.0,2.67,2.67,8.67,5.33));
   arrList.add(new Food("DARK CHOCOLATE","Dairy","The best Cheese",100,581.0,6.98,37.2,51.2,30.2));
   
   DailyConsumption dc_1 = new DailyConsumption(date_1, arrList);


   arrList.clear();
   arrList.add(new Food("Butter","Dairy","The best Cheese",100,714.0,0.0,78.6,0.0,7.14));
   arrList.add(new Food("FONTINA CHEESE","Dairy","Cheese Factory",100,393.0,21.4,32.1,3.57,0.0));
   arrList.add(new Food("BURRATA CHEESE","Dairy","Cheese Factory",100,214.0,17.9,17.9,0.0,0.0));
   // Calendar date_2 = Calendar.getInstance();
   date_1.set(2021, 4, 26);
   DailyConsumption dc_2 = new DailyConsumption(date_1, arrList);

   michael.addDailyConsumption(dc_1);
   michael.addDailyConsumption(dc_2);

   System.out.println(michael.getDailyConsumption("05-26-2021"));


  System.out.println(michael.addFood("05-26-2021",new Food("Meat","Meat","Meat Factory",100,214.0,17.9,17.9,0.0,0.0)));
  System.out.println(michael.getDailyConsumption("05-26-2021"));

  System.out.println(michael.deleteFood("05-26-2021","BURRATA CHEESE"));
  System.out.println(michael.getDailyConsumption("05-26-2021"));



}
}
