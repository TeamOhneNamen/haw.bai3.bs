package schere_stein_papier;

public class Main {

	public static void main(String[] args) {
		Spieler thorben = new Spieler();
		Spieler ferdi = new Spieler();
		Tisch kasino_tisch = new Tisch(thorben,ferdi);
		Schiedsrichter schiri = new Schiedsrichter(kasino_tisch);
//		kasino_tisch.setSchiri(schiri);
		long dauer =  5*1000; // 60 seconds * 1000 ms/sec
		thorben.start();
		ferdi.start();
		schiri.start();
		
		try {
			Thread.sleep(dauer);
			schiri.interrupt();
			thorben.interrupt();
			ferdi.interrupt();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
