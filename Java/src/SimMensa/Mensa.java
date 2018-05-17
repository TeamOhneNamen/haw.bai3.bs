package SimMensa;

import java.util.ArrayList;
import java.util.Collections;

public class Mensa extends Thread {

	// Kanstanten
	private int ANZAHLKASSEN;
	private int ANZAHLSTUDENTEN;
	// Variablen
	private ArrayList<Kasse> kassenListe = new ArrayList<Kasse>();
	private ArrayList<Student> studentListe = new ArrayList<Student>();

	// getter und setter

	// getter und setter studentListe
	public ArrayList<Student> getstudentListe() {
		return studentListe;
	}

	// getter und setter ANZAHLKASSEN
	public int getANZAHLKASSEN() {
		return ANZAHLKASSEN;
	}

	public void setANZAHLKASSEN(int anzahlKassen) {
		ANZAHLKASSEN = anzahlKassen;
	}

	// getter und setter ANZAHLSTUDENTEN
	public int getANZAHLSTUDENTEN() {
		return ANZAHLSTUDENTEN;
	}

	public void setANZAHLSTUDENTEN(int anzahlStudenten) {
		ANZAHLSTUDENTEN = anzahlStudenten;
	}

	// constructor
	public Mensa(int anzahlKassen, int anzahlStudenten) {
		this.ANZAHLKASSEN = anzahlKassen;
		this.ANZAHLSTUDENTEN = anzahlStudenten;
	}

	public void run() {
		kassenEroeffnen();
		studentenSpawn();
	}

	private void kassenEroeffnen() {
		for (int i = 0; i < getANZAHLKASSEN(); i++) {
			Kasse tmpKasse = new Kasse();
			tmpKasse.setKASSENNUMMER(i + 1);
			kassenListe.add(tmpKasse);
			tmpKasse.start();
		}
	}

	private void studentenSpawn() {
		for (int j = 0; j < getANZAHLSTUDENTEN(); j++) {
			Student tmpStudent = new Student(this);
			tmpStudent.setID(j + 1);
			studentListe.add(tmpStudent);
			tmpStudent.start();
		}
		try {
			for (int k = 0; k < getANZAHLSTUDENTEN(); k++) {
				studentListe.get(k).join();
				
			}
			for (int l = 0; l < getANZAHLKASSEN(); l++) {
				System.out.println("an Kasse " + kassenListe.get(l).getKASSENNUMMER() + " stehen " + kassenListe.get(l).studentenListe.size() + " studenten an");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public synchronized int anstehen(Student student) {
		
		ArrayList<Student> studentenListeVonKasse = kassenListe.get(0).studentenListe;
		studentenListeVonKasse.add(studentenListeVonKasse.size(), student);
		Collections.sort(kassenListe);
		return kassenListe.get(0).getKASSENNUMMER();
	}
}
