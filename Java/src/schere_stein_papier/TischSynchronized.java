package schere_stein_papier;

import java.util.HashMap;

public class TischSynchronized extends Tisch {
	private HashMap<Integer, Spielobjekt> guesses;

	public TischSynchronized() {
		this.guesses = new HashMap<Integer, Spielobjekt>();
		this.guesses.put(1, null);
		this.guesses.put(2, null);
	}

	@Override
	public synchronized void zugriff(Schiedsrichter s) {
		notifyAll();
		while (guesses.containsValue(null)) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}
		s.setGuesses(this.guesses);
		s.auswertung();
		leeren();
	}

	@Override
	public synchronized void zugriff(Integer i, Spielobjekt so) {
		this.guesses.put(i, so);
		notifyAll();
		while (!guesses.containsValue(null)) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}
	}
	@Override
	public void leeren() {
		this.guesses.put(1, null);
		this.guesses.put(2, null);
	}
}
