// package Helper;

// import java.io.BufferedReader;
import java.io.IOException;
// import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.ArrayList;

public class Wow {


public static void main(String args[]) {

	FoodSearch food = new FoodSearch();
	food.setQuery("Butter");
	try{
		System.out.print(food.getRequest());
	}catch(IOException ioe)
	{
		System.out.print("IOException\n");
	}
	

}
}
