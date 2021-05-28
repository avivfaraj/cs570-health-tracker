import java.util.ArrayList;
import java.util.Calendar; 
/***************************************
 * DailyConsumptionTester.java
 * White box for DailyConsumptionTester.java file
 * @author Aviv Farag
 * @version 05242021
 ****************************************/

class DailyConsumptionTester {

   public static void main(String[] args){

      Calendar date = Calendar.getInstance();
      date.set(2020, 5, 20);
   	Food f1 = new Food("Butter","Dairy",100,714.0,0.0,78.6,0.0,7.14);
   	Food f2 = new Food("FONTINA CHEESE","Dairy",100,393.0,21.4,32.1,3.57,0.0);
      Food f3 = new Food("BURRATA CHEESE","Dairy",100,214.0,17.9,17.9,0.0,0.0);

      ArrayList<Food> arrList =new ArrayList<Food>();
      arrList.add(new Food("YOGURT","Dairy",100,73.0,2.67,2.67,8.67,5.33));
      arrList.add(new Food("DARK CHOCOLATE","Dairy",100,581.0,6.98,37.2,51.2,30.2));


      // Constructor #1
      DailyConsumption dc_1 = new DailyConsumption();

      //Constructor #2
      DailyConsumption dc_2 = new DailyConsumption(date, arrList);

      System.out.println("dc_1:\n"+dc_1.toString());
      System.out.println();
      System.out.println("dc_2:\n"+dc_2.toString());

      dc_1.addFood(f1);
      dc_1.addFood(f2);
      System.out.println("After adding to dc_1:\n"+ dc_1.toString());

      dc_2.deleteFood("YOGURT");
      System.out.println("After removing Yogurt from dc_2:\n"+ dc_2.toString());

      System.out.println();
      System.out.println();
      System.out.println("To File");
      System.out.println("dc_1:\n"+ dc_1.toFile());

      System.out.println();
      System.out.println("dc_2:\n"+ dc_2.toFile());
}
}
