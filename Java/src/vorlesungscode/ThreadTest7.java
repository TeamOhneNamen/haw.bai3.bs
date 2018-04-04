/* ThreadTest7.java
 Version 1.0
 Autor: M. Huebner
 Zweck: Beispiel fuer die Verwendung des Semaphor-Mechanismus zum wechselseitigen Ausschluss
 */

import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest7 {
   /*
    * Beispiel fuer die Verwendung des Semaphor-Mechanismus zum wechselseitigen
    * Ausschluss
    */
   public static void main(String[] args) {
      OutputServer7 outputServer = new OutputServer7();

      MyThread7a threadZahl = new MyThread7a(outputServer);
      threadZahl.setName("Zahl-Thread");

      MyThread7b threadText = new MyThread7b(outputServer);
      threadText.setName("Hallo-Thread");

      outputServer.showOutput("-- Noch nichts passiert!--");


      threadZahl.start();
      threadText.start();

      try {
         /* Main-Thread anhalten */
         Thread.sleep(3000);
      } catch (InterruptedException e) {
         // nichts
      }

      /* Threads beenden */
      threadZahl.interrupt();
      threadText.interrupt();
   }
}

class OutputServer7 {

   /* Mutex (binaeres Semaphor) erzeugen */
   private ReentrantLock mutex = new ReentrantLock();  

   public void showOutput(Object output) {
      /* Output ausgeben. Waehrend der Ausgabe kann kein anderer Thread eine Ausgabe mit dieser Methode
       vornehmen, sondern kommt in die Semaphor-Warteschlange des Mutex.*/
      mutex.lock();   // P(mutex) --> Sperre setzen
      try {
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
      } finally {
         mutex.unlock();   // V(mutex) --> Sperre freigeben
      }
   }

   public void showThreadName() {
      /* Zeige aktuellen Threadnamen an */
      mutex.lock();   // P(mutex) --> Sperre setzen
      System.err.print("Output von " + Thread.currentThread().getName() + ": ");
      mutex.unlock();   // V(mutex) --> Sperre freigeben
   }
}

class MyThread7a extends Thread {
   /* Hochzaehlen und Zahlen ausgeben */
   private OutputServer7 outputServer;

   public MyThread7a(OutputServer7 outputServer) {
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

class MyThread7b extends Thread {
   /* Intelligenten Text ausgeben */

   private OutputServer7 outputServer;

   public MyThread7b(OutputServer7 outputServer) {
      this.outputServer = outputServer;
   }

   public void run() {
      /* Interrupt-Flag abfragen */
      while (!isInterrupted()) {
         outputServer.showOutput("------------ Hallo! --------------");
      }
   }
}
