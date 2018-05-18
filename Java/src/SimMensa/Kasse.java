package SimMensa;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Kasse extends Thread implements Comparable<Kasse> {

	private int KASSENNUMMER;
	private int bezahlvorgaenge;
	public ArrayList<Student> studentenListe = new ArrayList<Student>();
	private boolean kasseOffen = true;

	private Lock lock = new ReentrantLock();

	public int getBezahlvorgaenge() {
		return bezahlvorgaenge;
	}

	public Integer getStudentenListeSice() {
		return studentenListe.size();
	}

	public int getKASSENNUMMER() {
		return KASSENNUMMER;
	}

	public void setKASSENNUMMER(int kassennummer) {
		KASSENNUMMER = kassennummer;
	}

	@Override
	public int compareTo(Kasse other) {
		return this.getStudentenListeSice().compareTo(other.getStudentenListeSice());
	}

	public Kasse() {
		bezahlvorgaenge = 0;
	}

	public void run() {
		System.out.println("Kasse nummer: " + KASSENNUMMER + " ist jetzt Eröffnet");
		
		arbeiten();
	}
	
	private void arbeiten() {
		
		while(kasseOffen) {
			if (!studentenListe.isEmpty()) {
				studentenListe.get(0).bezahlenAufruf();
			}
		}
	}

	public void bezahlen(int dauerBezahlen) {
		lock.lock();
		try {
			Thread.sleep(dauerBezahlen);
		} catch (InterruptedException e) {
			this.interrupt();
		}
		bezahlvorgaenge++;
		System.out.println("Student: " + studentenListe.get(0).getID() + " hat bezahlt");
		studentenListe.get(0).essen();
		studentenListe.remove(0);

		lock.unlock();
	}
	@Override
	public void interrupt() {
		kasseOffen = false;
		super.interrupt();
	}

}
