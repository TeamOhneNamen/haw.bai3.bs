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
			this.tisch.zugriff(this.getSpielerNummer(), spielobjekt);
		}
		System.out.println("Spieler: "+ getSpielerNummer().toString()+ " beendet");
	}

	public void guess() {
		this.spielobjekt = spielobjekte[ThreadLocalRandom.current().nextInt(0, spielobjekte.length)];
	}

	public Integer getSpielerNummer() {
		return spieler_nummer;
	}
	
	@Override
	public void interrupt(){
		super.interrupt();
		
	}
}
