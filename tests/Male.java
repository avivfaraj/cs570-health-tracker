/***************************************
 * Male.java
 * 
 * @author Aviv Farag
 * @version 05242021
 ****************************************/

public class Male extends Person {

	// No variables - all inherited from Person

	// Constructor #1
	public Male(int id,String name,double weight,
				double height, int dCI){
		setPerson(id, name, "male", dCI, weight, height);
	}

	// Constructor #2
	public Male(int id,String name,double weight,
				double height){

		setPerson(id, name, "male", 2000, weight, height);
	}
}
