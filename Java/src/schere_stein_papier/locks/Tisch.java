package schere_stein_papier.locks;

public class Tisch {
//	private Schiedsrichter schiri;
	private Spieler spieler_1;
	private Spieler spieler_2;
	private Spielobjekt spieler_1_spielobjekt;
	private Spielobjekt spieler_2_spielobjekt;

//	public void setSchiri(Schiedsrichter schiri) {
//		this.schiri = schiri;
//	}

	public Spielobjekt getSpieler_1_spielobjekt() {
		return spieler_1_spielobjekt;
	}

	public Spielobjekt getSpieler_2_spielobjekt() {
		return spieler_2_spielobjekt;
	}

	public Tisch(Spieler spieler_1, Spieler spieler_2) {
		this.spieler_1 = spieler_1;
		this.spieler_2 = spieler_2;
	}

	public Spieler getSpieler_1() {
		return spieler_1;
	}

	public Spieler getSpieler_2() {
		return spieler_2;
	}

	public void leeren() {
		this.spieler_1_spielobjekt = null;
		this.spieler_1_spielobjekt = null;
	}

	public void haendeAufDenTisch() {

		this.spieler_1_spielobjekt = spieler_1.guess();
		this.spieler_2_spielobjekt = spieler_2.guess();
	}

}
