/***************************************
 * Male.java
 * Represents Male user.
 * @author Aviv Farag
 * @version 6.0 - 06.10.21
 ****************************************/

public class Male extends Person {

	// No variables - all inherited from Person

	// Constructor #1
	public Male(int id,String name,double weight,
				double height, double dCI){
		setPerson(id, name, "Male", dCI, weight, height);
	}

	// Constructor #2
	public Male(int id,String name,double weight,
				double height){

		setPerson(id, name, "Male", 2000, weight, height);
	}
}
