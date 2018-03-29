package vorlesungscode;

/* ThreadTest3.java
 Version 1.0
 Autor: M. Hübner
 Zweck: Beispiel für die sinnvolle Verwendung des Interrupt-Flags
 */


public class ThreadTest3 {
  public static void main(String[] args) {
    MyThread3 testThread = new MyThread3();
    testThread.start();
    try {
      /* Für 2000 ms anhalten */
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // nichts
    }
    /* Thread unterbrechen (Interrupt-Flag setzen) */
    testThread.interrupt();
    System.out.println("Es wurde gestoppt!");
  }
}

class MyThread3 extends Thread {
  /* Hochzählen und Zahlen ausgeben */
  public void run() {
    int i = 0;
    
    /* Interrupt-Flag abfragen */
    while (!isInterrupted()) {
      System.out.println(i++);
    }
    System.err.println("MyThread3 wird beendet!");
  }
}