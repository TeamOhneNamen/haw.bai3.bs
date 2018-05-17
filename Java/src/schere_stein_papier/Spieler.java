package schere_stein_papier;

import java.util.Random;

public class Spieler extends Thread  {
	Spielobjekt[] spielobjekte = {Spielobjekt.SCHERE, Spielobjekt.STEIN, Spielobjekt.PAPIER};
	Spielobjekt spielobjekt;
	Random rand = new Random();
	
	public void run(){
		spielobjekt = spielobjekte[rand.nextInt(spielobjekte.length)];
	}
	
	public Spielobjekt getSpielobjekt() {
		return spielobjekt;
	}
}
