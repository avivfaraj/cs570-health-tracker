import java.util.ArrayList;
/***************************************
 * FoodTeter.java
 * White box for FoodDataset.java file
 * @author Aviv Farag
 * @version 6.0 - 06.10.21
 ****************************************/

class FoodDatasetTester {

   public static void main(String[] args){

   	Food f1 = new Food("Cheese", "Diary","The Best Factory",100.0 ,100.0, 12.0, 10.0,10.0,3.0);
   	Food f2 = new Food("Burger", "Meat","Meat Inc.",100.0 ,150.0, 24.0, 20.0,5.0,0.0);
      Food f3 = new Food("Blue Cheese", "Diary","Special Cheese",100.0 ,100.0, 7.0, 5.0,10.0,3.0);

      ArrayList<Food> arrList =new ArrayList<Food>();
      arrList.add(new Food("Chocolate", "Diary","Cheese Factory",100.0 ,180.0, 5.0, 10.2,8.1,6.2));
      arrList.add(new Food("Olives", "fruit","Agri inc.",100.0 ,300.0, 6.2, 8.4,8.1,6.2));


      // Constructor #1
      FoodDataset dataset = new FoodDataset();

      //Constructor #2
      FoodDataset dataset2 = new FoodDataset(arrList);


      // Dataset
      // Add food
      dataset.addFood(f1);
      dataset.addFood(f2);
      dataset.addFood(f3);

      // toFile
      System.out.println(dataset.toFile());

      //Delete food
      dataset.deleteFood("Cheese");

      // toString
      System.out.println(dataset.toString());

      // Exist
      System.out.println(dataset.getNutrientsString("Blue Cheese"));
      System.out.println();

      // Does not exist
      System.out.println(dataset.getNutrientsString("Cheese X"));
      System.out.println();

      double[] a = dataset.getNutrientsDouble("Burger");
      for (double i : a){
         System.out.println(i);
      }


      //Dataset2:
      System.out.println(dataset2.addFood(f1));
      dataset2.addFood(f2);

      System.out.println();
      System.out.println(dataset2.deleteFood("Cheese"));

      System.out.println(dataset2.toFile());
      System.out.println();

      System.out.println(dataset2.search("Chocolate"));
      System.out.println(dataset2.getIndex("Chocolate"));
      System.out.println(dataset2.getIndex("Cheese"));

      System.out.println();
      Food d = dataset2.getFood("Chocolate");
      System.out.println(d.toString());

      System.out.println();
      System.out.println("Dataset Data");
      ArrayList<Food> data = dataset.getData();
      for(Food item : data)
      {
            System.out.println(item.toString());
            System.out.println();
      }




}
}
