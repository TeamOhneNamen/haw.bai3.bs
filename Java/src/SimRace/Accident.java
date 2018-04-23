package SimRace;

import java.util.ArrayList;
import java.util.Random;

public class Accident extends Thread {

	ArrayList<Car> carList;
	boolean isRennenGestartet;
	boolean isUnfall = false;

	public Accident(ArrayList<Car> carList, boolean isRennenGestartet) {

		this.carList = carList;
		this.isRennenGestartet = isRennenGestartet;
		this.isUnfall = false;
	}

	public boolean isUnfall() {
		return isUnfall;
	}

	@Override
	public void run() {
		Random rand = new Random();
		int randInt = rand.nextInt(1000);

		try {
			Thread.sleep(randInt);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		if (!Thread.currentThread().isInterrupted()) {
			isUnfall=true;
			for (int j = 0; j < carList.size(); j++) {
				carList.get(j).interrupt();
			}
		}		
	}
}
