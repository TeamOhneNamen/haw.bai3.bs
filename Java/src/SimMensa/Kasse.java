package SimMensa;

import java.util.ArrayList;

public class Kasse extends Thread  implements Comparable<Kasse>{

	private int KASSENNUMMER;
	public ArrayList<Student> studentenListe = new ArrayList<Student>(); 
	
	public Integer getStudentenListeSice() {
		return studentenListe.size();
	}
	
	public int getKASSENNUMMER() {
		return KASSENNUMMER;
	}

	public void setKASSENNUMMER(int kassenNummer) {
		KASSENNUMMER = kassenNummer;
	}
	
	@Override
    public int compareTo(Kasse other) {
    	return this.getStudentenListeSice().compareTo(other.getStudentenListeSice());
    }
	
	public Kasse() {
	}
	
	public void run(){
		
		System.out.println("Kasse nummer: " + KASSENNUMMER + " ist jetzt Eröffnet");
		
	}
	
}
