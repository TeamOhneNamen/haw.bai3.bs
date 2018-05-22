package schere_stein_papier.locks;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Spieler extends Thread {
	private int points = 0;
	public boolean guessing = false;
	Spielobjekt[] spielobjekte = { Spielobjekt.SCHERE, Spielobjekt.STEIN, Spielobjekt.PAPIER };
	Random rand = new Random();
	
	public void run() {
		guessing = true;
		guess();
		guessing = false;
	}

	public Spielobjekt guess() {
		return spielobjekte[ThreadLocalRandom.current().nextInt(0, spielobjekte.length)];
	}

	public int getPoints() {
		return this.points;
	}

	public void win() {
		this.points++;
	}
}
