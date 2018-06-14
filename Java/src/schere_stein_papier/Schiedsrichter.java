package schere_stein_papier;

import java.util.HashMap;

public class Schiedsrichter extends Thread {
	private Tisch tisch;
	private HashMap<String, Integer> spieler_1_stats = new HashMap<String, Integer>();
	private HashMap<String, Integer> spieler_2_stats = new HashMap<String, Integer>();
	private HashMap<Integer, Spielobjekt> guesses = new HashMap<Integer, Spielobjekt>();
	int runde = 1;
	public Schiedsrichter(Tisch t) {
		this.tisch = t;
		this.spieler_1_stats.put("gewonnen", 0);
		this.spieler_1_stats.put("unentschieden", 0);
		this.spieler_1_stats.put("verloren", 0);
		this.spieler_2_stats.put("gewonnen", 0);
		this.spieler_2_stats.put("unentschieden", 0);
		this.spieler_2_stats.put("verloren", 0);
		this.guesses.put(1, null);
		this.guesses.put(2, null);

	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			System.out.println("Runde:  " + ((Integer) runde).toString());
			tisch.zugriff(this);
			runde++;
		}
		System.out.println("Schiri beendet");
	}

	public void setGuesses(HashMap<Integer, Spielobjekt> new_guesses) {
		this.guesses = new_guesses;
	}

	public synchronized void unentschieden() {
		spieler_1_stats.put("unentschieden", this.spieler_1_stats.get("unentschieden") + 1);
		spieler_2_stats.put("unentschieden", this.spieler_2_stats.get("unentschieden") + 1);
	}

	public synchronized void gewonnen(HashMap<String, Integer> gewinner, HashMap<String, Integer> verlierer) {
		gewinner.put("gewonnen", gewinner.get("gewonnen") + 1);
		verlierer.put("verloren", verlierer.get("verloren") + 1);
	}

	public synchronized void auswertung() {
		if (guesses.containsValue(null)) {
			System.out.println("Runde: " + runde + " nicht beendet");
		} else {
			System.out.println(this.guesses.get(1).toString() + " vs. " + this.guesses.get(2).toString());
			switch (this.guesses.get(1)) {
			case SCHERE:
				switch (this.guesses.get(2)) {
				case SCHERE:
					this.unentschieden();
					break;
				case STEIN:
					this.gewonnen(spieler_2_stats, spieler_1_stats);
					break;
				case PAPIER:
					this.gewonnen(spieler_1_stats, spieler_2_stats);
					break;
				}
				break;
			case STEIN:
				switch (this.guesses.get(2)) {
				case SCHERE:
					this.gewonnen(spieler_1_stats, spieler_2_stats);
					break;
				case STEIN:
					this.unentschieden();
					break;
				case PAPIER:
					this.gewonnen(spieler_2_stats, spieler_1_stats);
					break;
				}
				break;
			case PAPIER:
				switch (this.guesses.get(2)) {
				case SCHERE:
					this.gewonnen(spieler_2_stats, spieler_1_stats);
					break;
				case STEIN:
					this.gewonnen(spieler_1_stats, spieler_2_stats);
					break;
				case PAPIER:
					this.unentschieden();
					break;
				}
				break;
			}
		}
		this.print();
	}

	private void print() {
		System.out.println("Spieler" + "\t" + "Gew." + "\t" + "Une." + "\t" + "Ver.");
		System.out.println("1" + "\t" + this.spieler_1_stats.get("gewonnen") + "\t"
				+ this.spieler_1_stats.get("unentschieden") + "\t" + this.spieler_1_stats.get("verloren"));
		System.out.println("2" + "\t" + this.spieler_2_stats.get("gewonnen") + "\t"
				+ this.spieler_2_stats.get("unentschieden") + "\t" + this.spieler_2_stats.get("verloren"));
	}
	@Override
	public void interrupt(){
		super.interrupt();
		
	}
}
