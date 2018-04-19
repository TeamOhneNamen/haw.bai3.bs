package SimRace;

import java.util.Random;

public class Car extends Thread implements Comparable<Car>{

	int runden;
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
			try {
				Car.sleep(randInt);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			zeit = zeit + randInt;
			
		}
	}
}
