package schere_stein_papier;

public class Main {

	public static void main(String[] args) {
		Spieler thorben = new Spieler();
		Spieler ferdi = new Spieler();
		Tisch kasino_tisch = new Tisch(thorben,ferdi);
		Schiedsrichter schiri = new Schiedsrichter(kasino_tisch);
		long start = System.currentTimeMillis();
		long end = start + 5*1000; // 60 seconds * 1000 ms/sec
		thorben.start();
		ferdi.start();
		schiri.start();
//		try {
//			thorben.join();
//			ferdi.join();
//			schiri.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
