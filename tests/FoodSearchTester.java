// package Helper;

// import java.io.BufferedReader;
import java.io.IOException;
// import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.ArrayList;

public class FoodSearchTester {


public static void main(String args[]) {

	FoodSearch food = new FoodSearch();
	// food.setQuery("cheese","CITRUS GINGER BELLAVITANO");
	// try{
	// 	System.out.print(food.searchFood("cheese","CITRUS GINGER BELLAVITANO").toString());
	// }catch(IOException ioe)
	// {
	// 	System.out.print("IOException\n");
	// }

	Food new_food = null;
	try{
		new_food = food.searchFood("Olive Oil","Kirkland");
	}catch(IOException ioe)
	{
		System.out.print("IOException\n");
	}

	if(new_food != null)  System.out.println(new_food.toString());


	try{
		new_food = food.searchFood("Avocado","AVEYO");
	}catch(IOException ioe)
	{
		System.out.print("IOException\n");
	}

	if(new_food != null)  System.out.println(new_food.toString());
}
}
