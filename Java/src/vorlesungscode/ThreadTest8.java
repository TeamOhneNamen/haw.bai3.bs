package vorlesungscode;

/* ThreadTest8.java
 Version 1.0
 Autor: M. Huebner
 Zweck: Beispiel fuer die Verwendung des Java-Monitor-Mechanismus zum wechselseitigen Ausschluss
 */
public class ThreadTest8 {
   /*
    * Beispiel fuer die Verwendung des Java-Monitor-Mechanismus zum
    * wechselseitigen Ausschluss
    */
   public static void main(String[] args) {
      OutputServer8 outputServer = new OutputServer8();

      MyThread8a threadZahl = new MyThread8a(outputServer);
      threadZahl.setName("Zahl-Thread");

      MyThread8b threadText = new MyThread8b(outputServer);
      threadText.setName("Hallo-Thread");

      outputServer.showOutput("-- Noch nichts passiert!--");

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


class OutputServer8 {

   public synchronized void showOutput(Object output) {
      /* Output ausgeben. Waehrend der Ausgabe kann kein anderer Thread eine Ausgabe mit dieser Methode
       vornehmen, sondern kommt in die Monitor-Warteschlange des OutputServer8-Objekts.*/

      /* 1. Ausgabenteil */
      showThreadName();

      try {
         /* Thread anhalten */
         Thread.sleep(10);
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
      }

      /* 2. Ausgabenteil */
      System.err.println(output);
   }

   public synchronized void showThreadName() {
      /* Zeige aktuellen Threadnamen an */
      System.err.print("Output von " + Thread.currentThread().getName() + ": ");
   }
}


class MyThread8a extends Thread {
   /* Hochzaehlen und Zahlen ausgeben */
   private OutputServer8 outputServer;

   public MyThread8a(OutputServer8 outputServer) {
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


class MyThread8b extends Thread {
   /* Intelligenten Text ausgeben */
   private OutputServer8 outputServer;

   public MyThread8b(OutputServer8 outputServer) {
      this.outputServer = outputServer;
   }

   public void run() {
      /* Interrupt-Flag abfragen */
      while (!isInterrupted()) {
         outputServer.showOutput("------------ Hallo! --------------");
      }
   }
}
