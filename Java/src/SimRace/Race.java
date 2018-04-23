package SimRace;

import java.util.ArrayList;
import java.util.Collections;

public class Race {

	static int ANZAHLCAR = 10;
	static int ANZAHLRUNDEN = 10;

	static boolean isRennenGestartet = false;

	static ArrayList<Car> carList = new ArrayList<Car>();

	public static void main(String[] args) {
		isRennenGestartet = true;
		for (int i = 0; i < ANZAHLCAR; i++) {
			Car newCar = new Car(ANZAHLRUNDEN);
			newCar.setCarName("Wagen " + i);
			carList.add(newCar);
			newCar.start();
		}

		Accident acc = new Accident(carList, isRennenGestartet);
		acc.start();

		for (int j = 0; j < carList.size(); j++) {

			if (acc.isInterrupted()) {
				break;
			}
			try {
				carList.get(j).join();
			} catch (InterruptedException e) {
			}

		}
		isRennenGestartet = false;

		if (!acc.isUnfall()) {
			acc.interrupt();

			System.out.println("**** Endstand ****");
			Collections.sort(carList);
			for (int k = 0; k < carList.size(); k++) {
				carList.get(k).printWinner(k);
			}
		}
	}

}
