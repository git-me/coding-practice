package org.code.problems.multithreading;

public class ProducerCOnsumerUsingWaitNotify {
    public static void main(String[] args) {

        SharedQueue s = new SharedQueue();
        Thread t= new Thread(()->{
            try{

            for (int i = 0; i < 5; i++) {

                    s.produce();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }, "producer") ;
        Thread t2= new Thread(()->{
            try {
            for (int i = 0; i < 5; i++) {

                s.consume();
                Thread.sleep(500);
            }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }, "consumer") ;
        t.start();
        t2.start();

    }
}

 class SharedQueue {
int item =0 ;
boolean hasItem= false;
public synchronized  void produce () throws InterruptedException {
    while(hasItem){
        wait();
    }
    item++;
    System.out.println("Produced: " + item);
    hasItem=true;
    notify();

}
     public synchronized  void consume () throws InterruptedException {
         while(!hasItem){
             wait();
         }
         System.out.println("consumed: " + item);
         hasItem=false;
         notify();

     }

 }
