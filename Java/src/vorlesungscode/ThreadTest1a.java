package vorlesungscode;

/* ThreadTest1a.java
 Version 1.0
 Autor: M. H�bner
 Zweck: Beispiel f�r das Starten von mehreren Threads
 mit Threaderzeugung �ber Ableitung aus der Thread-Klasse
 */

public class ThreadTest1a {
  /* Beispiel f�r das Starten von mehreren Threads */

  public static void main(String[] args) {
    /* Main: wird vom Hauptthread ausgef�hrt */

    /* Erzeuge Thread-Objekte (nur Java-Objekte) */
    MyThreadZahl threadZahl = new MyThreadZahl("Zahlthread");
    MyThreadText threadText = new MyThreadText("Textthread");
    System.err.println("-- Noch nichts passiert!--");

    /* Starte Threads */
    threadZahl.start();
    threadText.start();

    System.err.println("-- Hauptthread wird beendet!--");
  }
}

/* Eigene Klasse */
class MyThreadZahl extends Thread {
	private String myName;
	
	public MyThreadZahl(String myName) {
      this.myName = myName;
   }
   
  /* Hochz�hlen und Zahlen ausgeben */
  public void run() {
    for (int i = 0; i < 4000; i++) {
      System.err.println(myName + ": " + i);
    }
  }
}

/* Eigene Klasse */
class MyThreadText extends Thread {
	private String myName;
	
	public MyThreadText(String myName) {
      this.myName = myName;
   }
   	
  /* Intelligenten Text ausgeben */
  public void run() {
    for (int i = 0; i < 4000; i++) {
      System.err.println(myName + ": ------------ Ich bin auch noch da! ");
    }
  }
}
