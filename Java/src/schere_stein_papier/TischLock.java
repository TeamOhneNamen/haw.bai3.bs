package schere_stein_papier;

import java.util.HashMap;
import java.util.concurrent.locks.*;

public class TischLock extends Tisch {
	private HashMap<Integer, Spielobjekt> guesses;
	private Lock lock = new ReentrantLock();
	private Condition schiedsrichter_cond = lock.newCondition();
	private Condition spieler_cond = lock.newCondition();

	public TischLock() {
		this.guesses = new HashMap<Integer, Spielobjekt>();
		this.guesses.put(1, null);
		this.guesses.put(2, null);
	}

	@Override
	public void zugriff(Schiedsrichter s) {
		lock.lock();
		
		try {
			while (guesses.containsValue(null)) {
				schiedsrichter_cond.await();
			}
			s.setGuesses(this.guesses);
			s.auswertung();
			leeren();
			spieler_cond.signalAll();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		} finally {
			lock.unlock();
		}

	}

	@Override
	public void zugriff(Integer i, Spielobjekt so) {
		lock.lock();

		try {
			while (guesses.get(i) != null) {
				spieler_cond.await();
			}
			this.guesses.put(i, so);
			schiedsrichter_cond.signal();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void leeren() {
		this.guesses.put(1, null);
		this.guesses.put(2, null);
	}
}
