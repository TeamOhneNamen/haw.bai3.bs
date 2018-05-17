package schere_stein_papier;

public class Schiedsrichter extends Thread  {
	
	private Spielobjekt spieler_1_spielobjekt;
	private Spielobjekt spieler_2_spielobjekt;
	
	public Schiedsrichter(Spieler spieler_1, Spieler spieler_2) {
		spieler_1_spielobjekt = spieler_1.getSpielobjekt();
		spieler_2_spielobjekt = spieler_2.getSpielobjekt();
	}

	public void run(){
		auswertung();
	}
	
	private void auswertung() {
		if (spieler_1_spielobjekt == Spielobjekt.SCHERE) {
			if (spieler_1_spielobjekt == Spielobjekt.SCHERE) {
				
			}
		}

	}
}
