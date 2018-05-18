package schere_stein_papier;

public class Schiedsrichter extends Thread {
	private Tisch tisch;
	private Spieler spieler_1;
	private Spieler spieler_2;
	private int RUNDEN = 20;

	public Schiedsrichter(Tisch t) {
		this.tisch = t;
		this.spieler_1 = this.tisch.getSpieler_1();
		this.spieler_2 = this.tisch.getSpieler_2();
		
	}

	public void run() {
		int runde = 1;
		while (runde <= RUNDEN) {
//		while (true) {
			System.out.println("round:  " + ((Integer)runde).toString());
			
			this.tisch.auswertung();
			System.out.println(spieler_1.getSpielobjekt().toString() + " vs. " + spieler_2.getSpielobjekt().toString());
			System.out.println(((Integer) spieler_1.getPoints()).toString() + " : "
					+ ((Integer) spieler_2.getPoints()).toString());
			this.tisch.leeren();
			runde++;
		}
	}
}
