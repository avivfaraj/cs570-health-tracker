// package Helper;
import java.util.Calendar;
import java.util.ArrayList;
/***************************************
 * FoodSearchTester.java
 * Whitebox for FoodSearch.java
 * @author Aviv Farag
 * @version 6.0 - 06.10.21
 ****************************************/

public class FoodSearchTester {


public static void main(String args[]) {

	FoodSearch food = new FoodSearch();
	// food.setQuery("cheese","CITRUS GINGER BELLAVITANO");
	System.out.println(food.searchFood("cheese","CITRUS GINGER BELLAVITANO").toString());
	System.out.println();
	System.out.println(food.getQuery());

	System.out.println();
	Food new_food = food.searchFood("Greek Yogurt","Fage");
	System.out.println(new_food.toString());

	System.out.println();
	new_food = food.searchFood("Avocado","AVEYO");
	System.out.println(new_food.toString());
	
}
}
