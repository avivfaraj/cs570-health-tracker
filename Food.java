/***************************************
 * Food.java
 * Represents a Food item with nutrients
 * @author Aviv Farag
 * @version 3.0 - 05.29.21
 ****************************************/

public class Food {

	// Variables Declaration
	private String name, type; 
	private double calorie, protein, fat, carbohydrate, sugars, grams; 

	public Food(String n,String t,double grams,double c, 
				double p,double f, double carbs,
				double s){
		name = n;
		type = t;
		calorie = c;
		protein = p;
		fat = f;
		carbohydrate = carbs;
		sugars = s;
		this.grams = grams;
		
	}

	// getters
	public String getName(){
		return name;
	}
	public double getCalorie(){
		return calorie;
	}
	public String getType(){
		return type;
	}
	public double getProtein(){
		return protein;
	}
	public double getFat(){
		return fat;
	}
	public double getCarbs(){
		return carbohydrate;
	}
	public double getSugars(){
		return sugars;
	}
	public double getGrams(){
		return grams;
	}

	// toFile method
	public String toFile(){
		// return fat;
		String toFile = (this.getName() + "," +
						 this.getType() + ","+
						 this.getGrams() + ","+
						 this.getCalorie()+","+
						 this.getProtein()+","+
						 this.getFat()+","+
						 this.getCarbs()+","+
						 this.getSugars()+"\n");
		return toFile;
	}

	// toString method
	public String toString(){
		return ("Food item: " + this.getName() +
			    "\nType: "+ this.getType() +
			    "\nGrams: "+this.getGrams()+ 
			    "\nCalorie: "+ this.getCalorie()+
			    "\nProtein: "+this.getProtein()+
			    "\nFat: "+this.getFat()+
			    "\nCarbohydrate: "+this.getCarbs()+
			    "\nSugars: "+this.getSugars());
	}
}
