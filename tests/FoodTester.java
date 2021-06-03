/***************************************
 * FoodTeter.java
 * White box for Food.java file
 * @author Aviv Farag
 * @version 05242021
 ****************************************/

class FoodTester {

   public static void main(String[] args){

   	Food f1 = new Food("cheese", "diary","TheBestCheese",100.0 ,100.0, 12.0, 10.0,10.0,3.0);
   	Food f2 = new Food("bURger", "mEat","TheBestMeat",100.0 ,150.0, 24.0, 20.0,5.0,0.0);

   	// toString()
   	System.out.println(f1.toString());
   	System.out.println();
   	System.out.println(f2.toString());
   	System.out.println();
   	
   	// toFile()
   	System.out.println(f1.toFile());
	   System.out.println();
   	System.out.println(f2.toFile());
   	System.out.println();
}
}
