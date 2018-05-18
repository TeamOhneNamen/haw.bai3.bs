package SimMensa;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Student extends Thread {

	private int ID;
	private Mensa mensa;
	private Kasse kasse;
	
	public Lock lock = new ReentrantLock();
	
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	
	public Kasse getKasse() {
		return kasse;
	}
	public void setKasse(Kasse kasse) {
		this.kasse = kasse;
	}
	
	public Student(Mensa mensa) {
		this.mensa = mensa;
	}
	
	public void run(){
		System.out.println("Student: " + ID + " betritt die Mensa");
		mensa.anstehen(this);
		System.out.println("Student: " + ID + " steht an kasse " + kasse.getKASSENNUMMER());
		
		
	}
	public void bezahlenAufruf(){
		Random rand = new Random();
		int dauerBezahlen = rand.nextInt(1000);
		kasse.bezahlen(dauerBezahlen);
	}
	
	public void essen() {
		Random rand = new Random();
		int dauerBezahlen = rand.nextInt(1000);
		try {
			Thread.sleep(dauerBezahlen);
			mensa.anstehen(this);
			System.out.println("Student: " + getID() + " hat noch hunger");
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
	}
	
	@Override
	public String toString() {
		
		return "Student: " + ID;
	}
	
	
}
