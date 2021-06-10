/***************************************
 * Female.java
 * Represents a Female user. 
 * @author Aviv Farag
 * @version 6.0 - 06.10.21
 ****************************************/

public class Female extends Person {

	// No variables - all inherited from Person

	// Constructor #1
	public Female(int id,String name,double weight,
				double height, double dCI){
		setPerson(id, name, "Female", dCI, weight, height);
	}

	// Constructor #2
	public Female(int id,String name,double weight,
				double height){

		setPerson(id, name, "Female", 1500, weight, height);
	}


}
