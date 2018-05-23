package schere_stein_papier;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Spieler extends Thread {
	Spielobjekt[] spielobjekte = { Spielobjekt.SCHERE, Spielobjekt.STEIN, Spielobjekt.PAPIER };
	Spielobjekt spielobjekt;
	Random rand = new Random();
	private Tisch tisch;
	private int spieler_nummer;

	Spieler(int nummer, Tisch t) {
		this.spieler_nummer = nummer;
		this.tisch = t;
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			guess();
			this.tisch.zugriff(this.getSpielerNummer(), this.getSpielobjekt());
		}

	}

	public void guess() {
		this.spielobjekt = spielobjekte[ThreadLocalRandom.current().nextInt(0, spielobjekte.length)];
	}

	public Spielobjekt getSpielobjekt() {
		return spielobjekt;
	}

	public void setSpielobjekt(Spielobjekt spielobjekt) {
		this.spielobjekt = spielobjekt;
	}

	public Integer getSpielerNummer() {
		return spieler_nummer;
	}
}
