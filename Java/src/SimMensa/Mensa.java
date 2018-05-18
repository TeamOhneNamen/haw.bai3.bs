package SimMensa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mensa extends Thread {

	// Kanstanten
	private int ANZAHLKASSEN;
	private int ANZAHLSTUDENTEN;
	// Variablen
	private ArrayList<Kasse> kassenListe = new ArrayList<Kasse>();
	private ArrayList<Student> studentListe = new ArrayList<Student>();
	public Lock lock = new ReentrantLock();

	// getter und setter

	// getter studentListe
	public ArrayList<Student> getstudentListe() {
		return studentListe;
	}
	// getter kassenListe
	public ArrayList<Kasse> getkassenListe() {
		return kassenListe;
	}

	// getter ANZAHLKASSEN
	public int getANZAHLKASSEN() {
		return ANZAHLKASSEN;
	}
	// getter ANZAHLSTUDENTEN
	public int getANZAHLSTUDENTEN() {
		return ANZAHLSTUDENTEN;
	}

	// constructor
	public Mensa(int anzahlKassen, int anzahlStudenten) {
		this.ANZAHLKASSEN = anzahlKassen;
		this.ANZAHLSTUDENTEN = anzahlStudenten;
	}

	//run methode
	public void run() {
		kassenEroeffnen();
		studentenSpawn();
	}

	//eröffne alle kassen und speichere sie in KassenArray
	private void kassenEroeffnen() {
		for (int i = 0; i < getANZAHLKASSEN(); i++) {
			Kasse tmpKasse = new Kasse();
			tmpKasse.setKASSENNUMMER(i + 1);
			kassenListe.add(tmpKasse);
			tmpKasse.start();
		}
	}

	//spawne alle studenten und speichere sie in KassenArray
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
				System.out.println("an Kasse " + kassenListe.get(l).getKASSENNUMMER() + " stehen "
						+ kassenListe.get(l).studentenListe.size() + " studenten an");
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void anstehen(Student student) {
		lock.lock();
		ArrayList<Student> studentenListeVonKasse = kassenListe.get(0).studentenListe;
		studentenListeVonKasse.add(studentenListeVonKasse.size(), student);
		student.setKasse(kassenListe.get(0));
		Collections.sort(kassenListe);
		lock.unlock();

	}

	@Override
	public void interrupt() {
		for (int i = 0; i < studentListe.size(); i++) {
			studentListe.get(i).interrupt();
		}
		for (int j = 0; j < kassenListe.size(); j++) {
			kassenListe.get(j).interrupt();
		}
		try {
			for (int i = 0; i < studentListe.size(); i++) {
				studentListe.get(i).join();
			}

			for (int j = 0; j < kassenListe.size(); j++) {
				kassenListe.get(j).join();
				System.out.println("Kasse: " + kassenListe.get(j).getKASSENNUMMER() + " hat jetzt geschlossen!");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		super.interrupt();
	}
}
