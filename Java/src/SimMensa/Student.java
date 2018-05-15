package SimMensa;

public class Student extends Thread {

	private int ID;
	
	
	
	
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}

	
	
	
	public Student() {
	}
	
	
	public void run(){
		
		System.out.println("Student mit der ID: " + ID + " betritt die Mensa");
		
	}
	
}
