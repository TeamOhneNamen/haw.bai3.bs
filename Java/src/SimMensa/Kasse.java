package SimMensa;

import java.util.ArrayList;

public class Kasse extends Thread {

	private int KASSENNUMMER;
	public ArrayList<Student> studentenListe = new ArrayList<Student>(); 
	
	
	
	public int getKASSENNUMMER() {
		return KASSENNUMMER;
	}

	public void setKASSENNUMMER(int kassenNummer) {
		KASSENNUMMER = kassenNummer;
	}
	
	public Kasse() {
	}
	
	public void run(){
		
		System.out.println("Kasse nummer: " + KASSENNUMMER + " ist jetzt Eröffnet");
		
	}
	
}
