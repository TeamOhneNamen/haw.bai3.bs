package vorlesungscode;

/* ThreadTest6fehler7.java
 Version 1.0
 Autor: M. Hübner, Tim Tiedemann
 Zweck: Beispiel für Probleme bei fehlender Synchronisation, Vorbereitung fuer ThreadTest7
 */


public class ThreadTest6fehler7 {
  /* Beispiel für die Verwendung der setPriority-Methode */
  public static void main(String[] args) {
    MyThread6af7 threadZahl = new MyThread6af7();
    MyThread6bf7 threadText = new MyThread6bf7();
    System.err.println("-- Noch nichts passiert!--");

    /* Priorität des main-Threads auf Maximum setzen */
    Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

    /* Priorität des Zahl-Threads auf Minimum setzen und starten */
    threadZahl.setPriority(Thread.MIN_PRIORITY);
    threadZahl.start();

    /* Priorität des Text-Threads nahezu auf Maximum setzen und starten */
    threadText.setPriority(Thread.MAX_PRIORITY - 1);
    threadText.start();

    try {
      /* Main-Thread für 3 Sekunden anhalten */
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      // nichts
    }

    /* Threads beenden */
    threadZahl.interrupt();
    threadText.interrupt();
  }
}

class MyThread6af7 extends Thread {
  /* Hochzählen und Zahlen ausgeben */
  public void run() {
    int i = 0;
    int cnt=0;
    /* Interrupt-Flag abfragen */
    while (!isInterrupted()) {
        cnt++;
    	System.err.print("Thread 6a-"+cnt+": ");
    	try {
    		Thread.sleep(10);
    	} catch (InterruptedException e) {
    		Thread.currentThread().interrupt();
    	}
        System.err.println(cnt+"-"+i++);
    }
  }
}

class MyThread6bf7 extends Thread {
  /* Intelligenten Text ausgeben */
  public void run() {
	int cnt=0;
    /* Interrupt-Flag abfragen */
    while (!isInterrupted()) {
        cnt++;
    	System.err.print("Thread 6b-"+cnt+": ");
    	try {
    		Thread.sleep(10);
    	} catch (InterruptedException e) {
    		Thread.currentThread().interrupt();
    	}
        System.err.println(cnt+"------------ Hallo! --------------");
    }
  }
}
