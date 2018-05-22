package schere_stein_papier;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Spieler extends Thread {
	private int points = 0;
	Spielobjekt[] spielobjekte = { Spielobjekt.SCHERE, Spielobjekt.STEIN, Spielobjekt.PAPIER };
	Random rand = new Random();
	
	public void run() {
		guess();
	}

	public synchronized Spielobjekt guess() {
		return spielobjekte[ThreadLocalRandom.current().nextInt(0, spielobjekte.length)];
	}

	public int getPoints() {
		return this.points;
	}

	public void win() {
		this.points++;
	}
}
