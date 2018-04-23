package SimRace;

import java.util.Random;

public class Car extends Thread implements Comparable<Car>{

	int runden;
	int rundengefahren;
	int zeit = 0;
	String carName;
	
	public Car(int runden) {
		this.runden = runden;
	}
	
	public Integer getZeit() {
		return Integer.valueOf(zeit);
	}
	
	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	@Override
    public int compareTo(Car other) {
    	return this.getZeit().compareTo(other.getZeit());
    }
	
	public void run(){
		for (int i = 0; i < runden; i++) {
			Random rand = new Random();
			int randInt = rand.nextInt(100);
			if(!Thread.currentThread().isInterrupted()) {
				try {
					Car.sleep(randInt);
				} catch (InterruptedException e) {	
					Thread.currentThread().interrupt();
				}
				if (this.isInterrupted()) {
					System.out.println(carName + ": hatte einen Unfall");
				}
				zeit = zeit + randInt;
				rundengefahren=i+1;
			}else {
			Thread.currentThread().interrupt();
			}
		}
	}
	public void printWinner(int k) {
		if (!Thread.currentThread().isInterrupted()) {
			System.out.println((k + 1) + ". Platz: " + carName + " Zeit: " + zeit + " Runden: " + rundengefahren);	
		}
	}
}
