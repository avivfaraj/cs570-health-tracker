/*****************************
 * ManageFood.java
 * Interface for adding and 
 * deleting food.
 * @author Aviv Farag
 * @version 6.0 - 06.10.21
 ***************************/
public interface ManageFood {
  public boolean addFood(Food f);
  public boolean deleteFood(String name);
}