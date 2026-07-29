package org.code.problems.multithreading;

class ClassLevelSynchronization {
 public static synchronized void classLevellocking (){
     for (int i = 0; i < 5; i++) {
         System.out.println(Thread.currentThread().getName()+ "thread-Name: "+ i );
     try {
         Thread.sleep(500);
     }catch (InterruptedException e) {
         e.printStackTrace();
     }
     }
 }

    public static void main(String[] args) {
        Thread t1= new Thread(ClassLevelSynchronization::classLevellocking, "Thread-1");
        Thread t2 = new Thread(ClassLevelSynchronization::classLevellocking, "Thread-2");

        t1.start();
        t2.start();
    }

}
