package schere_stein_papier;

public class Main {

	public static void main(String[] args) throws InterruptedException {
//		Tisch kasino_tisch = new TischSynchronized();
		Tisch kasino_tisch = new TischLock();
		Spieler thorben = new Spieler(1, kasino_tisch);
		Spieler ferdi = new Spieler(2, kasino_tisch);
		Schiedsrichter schiri = new Schiedsrichter(kasino_tisch);
		thorben.start();
		ferdi.start();
		schiri.start();

		Thread.sleep(30);
		schiri.interrupt();
		thorben.interrupt();
		ferdi.interrupt();
	}

}
