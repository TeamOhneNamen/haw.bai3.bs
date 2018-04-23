package vorlesungscode;

/* ThreadTest8a.java
 Version 1.0
 Autor: M. Huebner
 Zweck: Beispiel fuer die Verwendung des Java-Monitor-Mechanismus 
 * zum wechselseitigen Ausschluss mit abwechselnder Reihenfolge
 */
public class ThreadTest81 {
   /*
    * Beispiel fuer die Verwendung des Java-Monitor-Mechanismus zum
    * wechselseitigen Ausschluss mit abwechselnder Reihenfolge (inkl. wait/notify)
    */
   public static void main(String[] args) {
      OutputServer81 outputServer = new OutputServer81();

      MyThread81a threadZahl = new MyThread81a(outputServer);
      threadZahl.setName("Zahl-Thread");

      MyThread81b threadText = new MyThread81b(outputServer);
      threadText.setName("Hallo-Thread");

      System.out.println("-- Noch nichts passiert!--");

      threadZahl.start();
      threadText.start();

      try {
         /* Main-Thread Sekunden anhalten */
         Thread.sleep(3000);
      } catch (InterruptedException e) {
         // nichts
      }

      /* Threads beenden */
      threadZahl.interrupt();
      threadText.interrupt();
   }
}


class OutputServer81 {

   public synchronized void showOutput(Object output) {
      /* Output ausgeben. Waehrend der Ausgabe kann kein anderer Thread eine Ausgabe mit dieser Methode
       vornehmen, sondern kommt in die Monitor-Warteschlange des OutputServer81-Objekts.*/

      /* 1. Ausgabenteil */
      showThreadName();

      try {
         /* Thread anhalten */
         Thread.sleep(10);

	      /* 2. Ausgabenteil */
	      System.err.println(output);
	      
			/* Partner aufwecken ("Staffelholz übergeben") --> Partner kommt
			 * in die Monitor-Queue ("ready"-Zustand) */
			this.notify();
			/* Monitor freigeben und warten --> selbst ab in die wait-Queue
			 * ("blocked"-Zustand) */
			this.wait();
		} catch (InterruptedException e) {
         Thread.currentThread().interrupt();
		}
   }

   public synchronized void showThreadName() {
      /* Zeige aktuellen Threadnamen an */
      System.err.print("Output von " + Thread.currentThread().getName() + ": ");
   }
}


class MyThread81a extends Thread {
   /* Hochzaehlen und Zahlen ausgeben */
   private OutputServer81 outputServer;

   public MyThread81a(OutputServer81 outputServer) {
      this.outputServer = outputServer;
   }

   public void run() {
      int i = 0;

      /* Interrupt-Flag abfragen */
      while (!isInterrupted()) {
         outputServer.showOutput(i++);
      }
   }
}


class MyThread81b extends Thread {
   /* Intelligenten Text ausgeben */
   private OutputServer81 outputServer;

   public MyThread81b(OutputServer81 outputServer) {
      this.outputServer = outputServer;
   }

   public void run() {
      /* Interrupt-Flag abfragen */
      while (!isInterrupted()) {
         outputServer.showOutput("------------ Hallo! --------------");
      }
   }
}
