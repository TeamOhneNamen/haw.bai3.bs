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
			try {
				while (guesses.containsValue(null)) {
					wait();
				}
				s.setGuesses(this.guesses);
				s.auswertung();
				leeren();
				notifyAll();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}

	@Override
	public synchronized void zugriff(Integer i, Spielobjekt so) {
			try {
				while (guesses.get(i) != null) {
					wait();
				}
				this.guesses.put(i, so);
				notifyAll();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}
	@Override
	public void leeren() {
		this.guesses.put(1, null);
		this.guesses.put(2, null);
	}
}
