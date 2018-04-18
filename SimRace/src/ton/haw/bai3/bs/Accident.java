package ton.haw.bai3.bs;

import java.util.ArrayList;
import java.util.Random;

public class Accident extends Thread {

	ArrayList<Car> carList;
	boolean isRennenGestartet;
	boolean isUnfall = false;

	public Accident(ArrayList<Car> carList, boolean isRennenGestartet) {

		this.carList = carList;
		this.isRennenGestartet = isRennenGestartet;
		this.isUnfall = true;
	}
	
	public boolean isUnfall(){
		return isUnfall;
	}

	@Override
	public void run() {
		Random rand = new Random();
		int randInt = rand.nextInt(10000);

		try {
			Thread.sleep(randInt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (isRennenGestartet) {
			System.out.println("UNFALL");
			for (int j = 0; j < carList.size(); j++) {
				carList.get(j).interrupt();
			}
		}
	}

}
