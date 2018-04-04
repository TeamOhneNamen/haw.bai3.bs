/* ThreadTest9.java
 Version 2.0 
 Autor: M. H�bner, HAW Hamburg
 Zweck: Kleiner Test f�r Graphik-Ausgaben (paint-Methode)
 Der Ball bleibt im Fenster gefangen, auch wenn die Gr��e ver�ndert wird!
 */

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

public class ThreadTest9 extends JFrame {

	/* ---------------------- Konstanten ---------------------- */
	public static final int BALL_GROESSE = 20;
	public static final int PAUSENLAENGE = 5;
	public static final int X_INC = 3;
	public static final int Y_INC = 3;
	public static Color FARBE = Color.RED;

	/* ---------------------- Variablen ----------------------- */

	/* JPanel f�r die Grafikausgabe */
	Spielwiese spielwiese;

	/* Animations-Thread */
	private Thread anzeigeThread;

	/* Aktuelle Ball-Position */
	private int xPosition = 0;
	private int yPosition = 0;

	/**
	 * Frame erzeugen
	 */
	public void erzeugeFenster() {
		/* Frame initialisieren */
		setTitle("Grafiktest");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setBackground(Color.WHITE);
		erzeugeMenus();
		pack();

		spielwiese = new Spielwiese();
		getContentPane().add(spielwiese);

		/* Hauptfenster sichtbar machen */
		setVisible(true);

	}

	/* --------------- Men�s erzeugen ------------------ */
	private void erzeugeMenus() {
		/* Men�leiste erzeugen */
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		/* Men� erzeugen */
		JMenu myMenu = new JMenu("Klick mich");
		menuBar.add(myMenu);

		/* Farbe wechseln */
		JMenuItem farbeEntry = new JMenuItem("Farbe wechseln");
		farbeEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Neuen Farbwechsel-Thread starten */
				new Thread() {
					public void run() {
						try {
							/* Farbwechsel verz�gern */
							Thread.sleep(2000);
						} catch (InterruptedException e) {
						}
						/* Farbwechsel! */
						if (getFarbe() == Color.RED) {
							setFarbe(Color.GREEN);
						} else {
							setFarbe(Color.RED);
						}
					}
				}.start();
			}
		});
		myMenu.add(farbeEntry);

		/* Ende */
		JMenuItem endeEntry = new JMenuItem("Beenden");
		endeEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anzeigeThread.interrupt();
				setVisible(false);
				dispose();
			}
		});
		myMenu.add(endeEntry);

	}

	private synchronized void setFarbe(Color aktFarbe) {
		/* Synchronisiert die Farbe ver�ndern! */
		FARBE = aktFarbe;
	}

	private synchronized Color getFarbe() {
		/* Synchronisiert die Farbe zur�ckgeben! */
		return FARBE;
	}

	private void starteAnzeigeThread() {
		/* Anonymen Animations-Thread definieren und starten */
		anzeigeThread = new Thread() {
			public void run() {
				try {
					while (true) {
						Thread.sleep(PAUSENLAENGE);
						/*
						 * F�r Swing-Komponenten: Synchronisation �ber
						 * Event-Queue!
						 */
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								/*
								 * Der AWT-Event-Thread ruft sp�ter
								 * paint(Graphics g) auf!
								 */
								spielwiese.repaint();
							}
						});
					}
				} catch (InterruptedException event) {
				}
			}
		};
		anzeigeThread.start();
	}

	/* ------- Innere Klasse --------------- */
	private class Spielwiese extends JPanel {
		/* Komponente f�r die Grafikausgabe */

		private boolean down = true;

		@Override
		public void paint(Graphics grafik) {
			/*
			 * Wird bei jeder Ver�nderung der Komponente automatisch aufgerufen
			 * oder �ber repaint()
			 */

			/* Alten Kreis l�schen */
			grafik.clearRect(xPosition, yPosition, BALL_GROESSE, BALL_GROESSE);

			/* Farbe f�r das Graphics-Objekt (Ball) setzen */
			grafik.setColor(getFarbe());

			// Neue Positionen berechnen lassen
			xPosition = berechneX();
			yPosition = berechneY();

			// Neuen Kreis zeichnen mit Rechteck-Koordinaten: Es wird der gr��te
			// Kreis gezeichnet, der hinein passt!
			grafik.fillOval(xPosition, yPosition, BALL_GROESSE, BALL_GROESSE);
		}

		private int berechneX() {
			/* Liefere den X-Wert (horizontal) */
			return (xPosition + X_INC) % getWidth();
		}

		private int berechneY() {
			/* Liefere den Y-Wert (vertikal) */
			int yPosNeu;
			if (down) {
				/* Abw�rtsrichtung: y wird erh�ht */
				yPosNeu = yPosition + Y_INC;
				if (yPosNeu > getHeight()) {
					down = !down; // Richtung umkehren
					yPosNeu = getHeight() - Y_INC;
				}
			} else {
				/* Aufw�rtsrichtung: y wird erniedrigt */
				yPosNeu = yPosition - Y_INC;
				if (yPosNeu < 0) {
					down = !down; // Richtung umkehren
					yPosNeu = Y_INC;
				}

			}
			return yPosNeu;
		}
	}

	public static void main(String[] args) {
		/* Starte Applikation */
		final ThreadTest9 animation = new ThreadTest9();
		try {
			/* Abwarten, bis Fenster erzeugt wurden */
			EventQueue.invokeAndWait(new Runnable() {
				public void run() {
					animation.erzeugeFenster();
				}
			});
			animation.starteAnzeigeThread();
		} catch (InvocationTargetException | InterruptedException e) {
		}
	}

}
