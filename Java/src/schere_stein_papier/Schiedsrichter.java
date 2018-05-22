package schere_stein_papier;

public class Schiedsrichter extends Thread {
	private Tisch tisch;
	private Spieler spieler_1;
	private Spieler spieler_2;
	int runde = 1;
	boolean vorbei = false;

	public Schiedsrichter(Tisch t) {
		this.tisch = t;
		this.spieler_1 = this.tisch.getSpieler_1();
		this.spieler_2 = this.tisch.getSpieler_2();
		
	}

	public void run() {
		
		while (!vorbei) {

			tisch.haendeAufDenTisch();
			auswertung();
			ausgabe();
			tisch.leeren();
			runde++;
		}
	}
	

	private void auswertung() {
		switch (tisch.getSpieler_1_spielobjekt()) {
		case SCHERE:
			switch (tisch.getSpieler_2_spielobjekt()) {
			case SCHERE:
				break;
			case STEIN:
				this.spieler_2.win();
				break;
			case PAPIER:
				this.spieler_1.win();
				break;
			}
			break;
		case STEIN:
			switch (tisch.getSpieler_2_spielobjekt()) {
			case SCHERE:
				this.spieler_1.win();
				break;
			case STEIN:
				break;
			case PAPIER:
				this.spieler_2.win();
				break;
			}
			break;
		case PAPIER:
			switch (tisch.getSpieler_2_spielobjekt()) {
			case SCHERE:
				this.spieler_2.win();
				break;
			case STEIN:
				this.spieler_1.win();
				break;
			case PAPIER:
				break;
			}
			break;
		}

	}
	
	private void ausgabe() {
		System.out.println("-----------------");
		System.out.println("round:  " + ((Integer)runde).toString());
		System.out.println(tisch.getSpieler_1_spielobjekt().toString() + " vs. " + tisch.getSpieler_2_spielobjekt().toString());
		System.out.println(((Integer) spieler_1.getPoints()).toString() + " : "
				+ ((Integer) spieler_2.getPoints()).toString());
	}
	
	
	@Override
	public void interrupt() {
		vorbei = true;
		super.interrupt();
	}
	
}
