package vorlesungscode;

/*
 * Customer.java
 * Version 1.0
 * Autor: M. H�bner
 * Zweck: Simuliert das Verhalten eines Shop-Kunden
 */

public class Customer extends Thread {
	private Shop currentShop;

	public Customer(Shop s) {
		currentShop = s;
	}

	public void run() {
		try {
			while (!isInterrupted()) {
				// Versuche, in das Gesch�ft einzutreten
				System.err
						.println(this.getName() + " wants to enter the shop!");

				currentShop.enter();

				// F�r unbestimmte Zeit schlafen
				enjoyLife();
			}
		} catch (InterruptedException e) {
			System.err.println(this.getName() + " was interrupted!");
		}
	}

	// Customer benutzen diese Methode, um sich zu vergn�gen
	public void enjoyLife() throws InterruptedException {
		int sleepTime = (int) (1000 * Math.random());

		// Thread blockieren
		Thread.sleep(sleepTime);

	}
}
