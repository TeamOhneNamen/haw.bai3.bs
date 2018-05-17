package schere_stein_papier;

public class Stein extends Spielobjekt {
	@Override
	public void fight(Schere so) {
		this.win();
		so.lose();
	}

	@Override
	public void fight(Stein so) {
		this.lose();
		so.lose();
	}

	@Override
	public void fight(Papier so) {
		this.lose();
		so.win();
	}
}
