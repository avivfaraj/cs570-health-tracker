/***************************************
 * Male.java
 * 
 * @author Aviv Farag
 * @version 05242021
 ****************************************/

public class Male extends Person {

	// No variables - all inherited from Person

	// Constructor #1
	public Male(){
		setPerson(-1, "Example", "Male", 1500, 130, 170);
	}

	// Constructor #2
	public Male(int id,String name,double weight,
				double height, double dCI){
		setPerson(id, name, "Male", dCI, weight, height);
	}

	// Constructor #3
	public Male(int id,String name,double weight,
				double height){

		setPerson(id, name, "Male", 2000, weight, height);
	}
}
