package schere_stein_papier;

import java.util.HashMap;

public class Tisch {
	private HashMap<Integer, Spielobjekt> guesses;
	private HashMap<String, Integer> spieler_1_stats = new HashMap<String, Integer>();
	private HashMap<String, Integer> spieler_2_stats = new HashMap<String, Integer>();

	public Tisch() {
		this.guesses = new HashMap<Integer, Spielobjekt>();
		this.guesses.put(1, null);
		this.guesses.put(2, null);
		this.spieler_1_stats.put("gewonnen", 0);
		this.spieler_1_stats.put("unentschieden", 0);
		this.spieler_1_stats.put("verloren", 0);
		this.spieler_2_stats.put("gewonnen", 0);
		this.spieler_2_stats.put("unentschieden", 0);
		this.spieler_2_stats.put("verloren", 0);
	}

	public synchronized void zugriff(Schiedsrichter s) {
		auswertung(s);
		leeren();
	}

	public synchronized void zugriff(Spieler sp) {
		System.out.println(sp.getSpielerNummer().toString() + " hat gespielt:");
		System.out.println(sp.getSpielobjekt().toString());
		this.guesses.put(sp.getSpielerNummer(), sp.getSpielobjekt());
	}

	public synchronized void zugriff(Integer i, Spielobjekt so) {
		// System.out.println(i.toString() + " hat gespielt:");
		// System.out.println(so.toString());
		this.guesses.put(i, so);
		notifyAll();
		while (!guesses.containsValue(null)) {
			try {
				// System.out.println("beide haben gespielt");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}

	}

	public synchronized void unentschieden() {
		spieler_1_stats.put("unentschieden", this.spieler_1_stats.get("unentschieden") + 1);
		spieler_2_stats.put("unentschieden", this.spieler_2_stats.get("unentschieden") + 1);
	}

	public synchronized void gewonnen(HashMap<String, Integer> gewinner, HashMap<String, Integer> verlierer) {
		gewinner.put("gewonnen", gewinner.get("gewonnen") + 1);
		verlierer.put("verloren", verlierer.get("verloren") + 1);
	}

	public synchronized void leeren() {
		this.guesses.put(1, null);
		this.guesses.put(2, null);
	}

	public synchronized void auswertung(Schiedsrichter s) {
		notifyAll();

		while (guesses.containsValue(null)) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

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
		System.out.println(this.spieler_1_stats.toString());
		System.out.println(this.spieler_2_stats.toString());
	}

}
