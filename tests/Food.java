/***************************************
 * Food.java
 * Represents a Food item with nutrients
 * @author Aviv Farag
 * @version 3.0 - 05.29.21
 ****************************************/

public class Food {

	// Variables Declaration
	private String name, category, brandOwner;
	private double calorie, protein, fat, carbohydrate, sugars, grams; 

	public Food(String n,String category,String brandOwner ,double grams,double c, 
				double p,double f, double carbs,
				double s){
		name = n.substring(0,1).toUpperCase() +  n.substring(1).toLowerCase();
		this.category = category.substring(0,1).toUpperCase() + category.substring(1).toLowerCase();
		this.brandOwner = brandOwner;
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

	public String getBrand(){
		return brandOwner;
	}
	public double getCalorie(){
		return calorie;
	}
	public String getCategory(){
		return category;
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
						 this.getCategory() + ","+
						 this.getBrand() + "," +
						 this.getGrams() + "," +
						 this.getCalorie() + "," +
						 this.getProtein() + "," +
						 this.getFat() + "," +
						 this.getCarbs() + "," +
						 this.getSugars());
		return toFile;
	}

	// toString method
	public String toString(){
		return ("Food item: " + this.getName() +
			    "\nCategory: "+ this.getCategory() +
			    "\nBrand: " + this.getBrand() +
			    "\nGrams: "+this.getGrams() + 
			    "\nCalorie: "+ this.getCalorie() +
			    "\nProtein: "+this.getProtein() +
			    "\nFat: "+this.getFat() +
			    "\nCarbohydrate: "+this.getCarbs() +
			    "\nSugars: "+this.getSugars());
	}
}
