/***************************************
 * Female.java
 * Represents Female user. 
 * @author Aviv Farag
 * @version 05242021
 ****************************************/

public class Female extends Person {

	// No variables - all inherited from Person

	// Constructor #1
	public Female(){
		setPerson(-1, "Example", "Female", 1500, 130, 170);
	}

	// Constructor #2
	public Female(int id,String name,double weight,
				double height, double dCI){
		setPerson(id, name, "Female", dCI, weight, height);
	}

	// Constructor #3
	public Female(int id,String name,double weight,
				double height){

		setPerson(id, name, "Female", 1500, weight, height);
	}


}
