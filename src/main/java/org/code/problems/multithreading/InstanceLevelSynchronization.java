package org.code.problems.multithreading;

 class InstanceLevelSynchronization {

    public  synchronized void  instanceMethod() {
//      block level synchronization  // synchronized (this) {
            for (int i = 0; i < 9; i++) {
                System.out.println(Thread.currentThread().getName() + "  thread : " + i);
                System.out.println(Thread.currentThread().getPriority());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


     public static void main(String[] args) {
         InstanceLevelSynchronization s= new InstanceLevelSynchronization();
         Thread t1 = new Thread(s::instanceMethod,"thread-1");
         Thread t2 = new Thread(s::instanceMethod,"thread-2");
         t2.start();
         t1.start();
     }
}
