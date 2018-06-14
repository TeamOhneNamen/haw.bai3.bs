package schere_stein_papier;

import java.util.HashMap;
import java.util.concurrent.locks.*;

public class TischLock extends Tisch {
	private HashMap<Integer, Spielobjekt> guesses;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();

	public TischLock() {
		this.guesses = new HashMap<Integer, Spielobjekt>();
		this.guesses.put(1, null);
		this.guesses.put(2, null);
	}

	@Override
	public void zugriff(Schiedsrichter s) {
		lock.lock();
		cond.signalAll();
		try {
			while (guesses.containsValue(null)) {
				cond.await();
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		} finally {
			lock.unlock();
		}
		s.setGuesses(this.guesses);
		s.auswertung();
		leeren();
	}

	@Override
	public void zugriff(Integer i, Spielobjekt so) {
		lock.lock();
		this.guesses.put(i, so);
		cond.signalAll();
		try {
			while (!guesses.containsValue(null)) {
				cond.await();
			}
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
