package schere_stein_papier;

public class Schiedsrichter extends Thread {
	private Tisch tisch;
	private Spieler spieler_1;
	private Spieler spieler_2;
	private int RUNDEN = 20;

	public Schiedsrichter(Tisch t, Spieler s1, Spieler s2) {
		this.tisch = t;
		this.spieler_1 = s1;
		this.spieler_2 = s2;

	}
	@Override
	public void run() {
		int runde = 1;
		 while (runde <= RUNDEN) {
//		while (true) {
			System.out.println("round:  " + ((Integer) runde).toString());
			System.out.println("Auswertung: ");
			this.tisch.auswertung(this);
			System.out.println(spieler_1.getSpielobjekt().toString() + " vs. " + spieler_2.getSpielobjekt().toString());
			this.tisch.zugriff(this);
			this.tisch.leeren();
			runde++;
		}
	}
}
