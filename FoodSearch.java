// net packages
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;

// Lang Package
import java.lang.StringBuffer;

// IO Packages
import java.io.BufferedReader;
import java.io.InputStreamReader;

// Exceptions packages
import java.io.IOException;
/***************************************
 * FoodSearch.java
 * Represents a Food item with nutrients
 * @author Aviv Farag
 * @version 3.0 - 05.29.21
 ****************************************/

public class FoodSearch {

	// Variables Declaration
	private String api_key, query, response, api_url;

	public FoodSearch(){
		setKey();
		query = "";
		response = "";
		api_url = "";
	}


	// getters
	public void setKey()
	{
		api_key = "g6jZti35lOsp8A6Jg0gSfeAWvbtdfyEh9Cfp0Cht";
	}
	public void setQuery(String query){
		this.query = query;
		api_url = "https://api.nal.usda.gov/fdc/v1/foods/search?query="+this.query+"&api_key="+api_key;
	}
	public String getQuery(){
		return query;
	}

	public String getRequest() throws IOException
	{
		
		try{

		// Citing Code
		/************************************************************
		*    Title: Do a Simple HTTP Request in Java
		*    Author: baeldung
		*    Date: January 30, 2021
		*    Code version: Not available
		*    Availability: https://www.baeldung.com/java-http-request
		*************************************************************/

			// URL for API request. Contains key and base (USD)
			URL url = new URL(api_url);

			// Open connection
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			// Set method to GET
			con.setRequestMethod("GET");

			// Response Code 
			int status = con.getResponseCode();
			System.out.print(status+"\n");

			// Ensure got a repsonse.
			// To learn more about error codes go to API docs:
			if (status == 200)
			{
				// Reading response using an input stream
				BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));

				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
				    content.append(inputLine);
				}

				// Closing buffer
				in.close();

				// Disconnecting from api
				con.disconnect();

				// Update private variables.
				// System.out.println(content.toString());
				response = content.toString();

			}
			else
			{
				response = "Error: Code is not 200\n";
			}
			

		}catch(UnknownHostException uhe)
		{
			// No internet Error
			response = "Error: ** No internet connection **\n";

		}
		finally
		{
			return response;
		}
		
	}

	// // toFile method
	// public String toFile(){
	// 	// return fat;
	// 	String toFile = (this.getName() + "," +
	// 					 this.getType() + ","+
	// 					 this.getGrams() + ","+
	// 					 this.getCalorie()+","+
	// 					 this.getProtein()+","+
	// 					 this.getFat()+","+
	// 					 this.getCarbs()+","+
	// 					 this.getSugars()+"\n");
	// 	return toFile;
	// }

	// // toString method
	// public String toString(){
	// 	return ("Food item: " + this.getName() +
	// 		    "\nType: "+ this.getType() +
	// 		    "\nGrams: "+this.getGrams()+ 
	// 		    "\nCalorie: "+ this.getCalorie()+
	// 		    "\nProtein: "+this.getProtein()+
	// 		    "\nFat: "+this.getFat()+
	// 		    "\nCarbohydrate: "+this.getCarbs()+
	// 		    "\nSugars: "+this.getSugars());
	// }
}
