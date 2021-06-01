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
	try{
		System.out.print(food.searchFood("cheese","CITRUS GINGER BELLAVITANO").toString());
	}catch(IOException ioe)
	{
		System.out.print("IOException\n");
	}


	try{
		System.out.print(food.searchFood("Avocado","AVEYO").toString());
	}catch(IOException ioe)
	{
		System.out.print("IOException\n");
	}
	

}
}
