package schere_stein_papier;

public abstract class Spielobjekt {
	private boolean win;
	public void fight(Schere so) {};
	public void fight(Stein so) {};
	public void fight(Papier so) {};

	public void win() {
		win = true;
	}

	public boolean getWin() {
		return win;
	}

	public void lose() {
		win = false;
	}
}
