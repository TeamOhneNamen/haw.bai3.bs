package schere_stein_papier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Tisch kasino_tisch = new Tisch();
		Spieler thorben = new Spieler(1, kasino_tisch);
		Spieler ferdi = new Spieler(2, kasino_tisch);
		Schiedsrichter schiri = new Schiedsrichter(kasino_tisch, thorben, ferdi);
		Timekeeper t = new Timekeeper(schiri, thorben, ferdi, 300);
		thorben.start();
		ferdi.start();
		schiri.start();
		t.start();
//		try {
//			thorben.join();
//			ferdi.join();
//			schiri.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		Timer timer = new Timer(300, new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				thorben.interrupt();
//				ferdi.interrupt();
//				schiri.interrupt();
//			}
//		});
//		timer.setRepeats(false); // Only execute once
//		timer.start();
//		Thread.sleep(30);
//		schiri.interrupt();
//		thorben.interrupt();
//		ferdi.interrupt();
		

	}

}
