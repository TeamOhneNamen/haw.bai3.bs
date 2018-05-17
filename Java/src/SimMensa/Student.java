package SimMensa;

public class Student extends Thread {

	private int ID;
	private Mensa mensa;
	
	
	
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	
	
	public Student(Mensa mensa) {
		this.mensa = mensa;
	}
	
	public void run(){
		System.out.println("Student mit der ID: " + ID + " betritt die Mensa");
		int kassenNummer = mensa.anstehen(this);
		System.out.println("Student " + ID + " steht an Kasse " + kassenNummer);
	}
	
}
