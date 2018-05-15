package SimMensa;

import java.util.ArrayList;

public class Mensa extends Thread {

	private int ANZAHLKASSEN;
	private int ANZAHLSTUDENTEN;
	private ArrayList<Kasse> kassenListe = new ArrayList<Kasse>();
	private ArrayList<Student> studentListe = new ArrayList<Student>();
	
	
	
	public int getANZAHLKASSEN() {
		return ANZAHLKASSEN;
	}

	public void setANZAHLKASSEN(int anzahlKassen) {
		ANZAHLKASSEN = anzahlKassen;
	}
	public int getANZAHLSTUDENTEN() {
		return ANZAHLSTUDENTEN;
	}

	public void setANZAHLSTUDENTEN(int anzahlStudenten) {
		ANZAHLSTUDENTEN = anzahlStudenten;
	}
	
	//constructor
	public Mensa(int anzahlKassen, int anzahlStudenten) {
		this.ANZAHLKASSEN = anzahlKassen;
		this.ANZAHLSTUDENTEN = anzahlStudenten;
	}
	
	public void run(){
		kassenEroeffnen();
	}
	
	private void kassenEroeffnen() {
		for(int i = 0; i<getANZAHLKASSEN(); i++) {
			Kasse tmpKasse = new Kasse();
			tmpKasse.setKASSENNUMMER(i+1);
			kassenListe.add(tmpKasse);
			tmpKasse.start();
		}
		for(int j = 0; j<getANZAHLSTUDENTEN(); j++) {
			Student tmpStudent = new Student();
			tmpStudent.setID(j+1);
			studentListe.add(tmpStudent);
			tmpStudent.start();
		}
	}
	
}
