/*****************************
 * ManageFood.java
 * Interface for adding and 
 * deleting food.
 * @author Aviv Farag
 * @version 05242021
 ***************************/
public interface ManageFood {
  public boolean addFood(Food f);
  public boolean deleteFood(String name);
}