package schere_stein_papier;

public class Tisch {
	private Spieler spieler_1;
	private Spieler spieler_2;
	private Spielobjekt spieler_1_spielobjekt;
	private Spielobjekt spieler_2_spielobjekt;

	public Tisch(Spieler spieler_1, Spieler spieler_2) {
		this.spieler_1 = spieler_1;
		this.spieler_2 = spieler_2;
	}

//	public void spieler_1(Spielobjekt so) {
//		this.spieler_1_spielobjekt = so;
//	}
//
//	public void spieler_2(Spielobjekt so) {
//		this.spieler_2_spielobjekt = so;
//	}
//
	public Spieler getSpieler_1() {
		return spieler_1;
	}

	public Spieler getSpieler_2() {
		return spieler_2;
	}
	
	public void leeren() {
		this.spieler_1.setSpielobjekt(null);
		this.spieler_2.setSpielobjekt(null);
		spieler_1_spielobjekt = null;
		spieler_2_spielobjekt = null;
//		if (spieler_1.getState() != Thread.State.WAITING) {
//			try {
//				spieler_1.wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		if (spieler_2.getState() != Thread.State.WAITING) {
//			try {
//				spieler_2.wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	public void auswertung() {
//		if (spieler_1.getState() == Thread.State.WAITING) {
//			spieler_1.notify();
//		}
//		if (spieler_2.getState() == Thread.State.WAITING) {
//			spieler_2.notify();
//		}
		notifyAll();
		try {
			spieler_1.wait();
			spieler_2.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.spieler_1_spielobjekt = this.spieler_1.getSpielobjekt();
		this.spieler_2_spielobjekt = this.spieler_2.getSpielobjekt();
		switch (this.spieler_1_spielobjekt) {
		case SCHERE:
			switch (this.spieler_2_spielobjekt) {
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
			switch (this.spieler_2_spielobjekt) {
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
			switch (this.spieler_2_spielobjekt) {
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

}
