package schere_stein_papier;

public class Schere extends Spielobjekt {
	
	@Override
	public void fight(Schere so) {
		this.lose();
		so.lose();
	}

	@Override
	public void fight(Stein so) {
		this.lose();
		so.win();
	}

	@Override
	public void fight(Papier so) {
		this.win();
		so.lose();
	}

}
