package vorlesungscode;

/* ThreadTest1b.java
 Version 1.0
 Autor: M. H�bner
 Zweck: Beispiel f�r das Starten von mehreren Threads 
 mit Threaderzeugung �ber Runnable-Interface
 */

public class ThreadTest1b {
  /* Beispiel f�r das Starten von mehreren Threads */

  public static void main(String[] args) {
    /* Main: wird vom Hauptthread ausgef�hrt */

    /* Erzeuge Thread-Objekte (nur Java-Objekte) */
    Thread threadZahl = new Thread(new MyRunnableZahl("Runnable Zahl"));
    Thread threadText = new Thread(new MyRunnableText("Runnable Text"));
    System.err.println("-- Noch nichts passiert!--");

    /* Starte Threads */
    threadZahl.start();
    threadText.start();

    System.err.println("-- Hauptthread wird beendet!--");
  }
}

/* Eigene Klasse */
class MyRunnableZahl implements Runnable {
	private String myName;
	
	public MyRunnableZahl(String myName) {
      this.myName = myName;
   }
   	
  /* Hochz�hlen und Zahlen ausgeben */
  public void run() {
    for (int i = 0; i < 4000; i++) {
      System.err.println(myName + ": " + i);    }
  }
}

/* Eigene Klasse */
class MyRunnableText implements Runnable {
	private String myName;
	
	public MyRunnableText(String myName) {
      this.myName = myName;
   }
   	
  /* Intelligenten Text ausgeben */
  public void run() {
    for (int i = 0; i < 4000; i++) {
      System.err.println(myName + ": ------------ Ich bin auch noch da! ");
    }
  }
}
