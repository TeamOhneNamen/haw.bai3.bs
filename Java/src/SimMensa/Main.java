package SimMensa;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		Mensa mensa = new Mensa(3, 20);
		mensa.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mensa.interrupt();
		try {
			mensa.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Kasse> kassenliste = mensa.getkassenListe();
		for(int i = 0; i<kassenliste.size(); i++) {
			System.out.println("an kasse " + kassenliste.get(i).getKASSENNUMMER() + " wurden " +kassenliste.get(i).getBezahlvorgaenge() + " Bezahlvorgänge abgeschlossen!");
		}
	}

}
