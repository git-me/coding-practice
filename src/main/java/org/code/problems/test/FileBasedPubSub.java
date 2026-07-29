package org.code.problems.test;



// producer -- create a thread to produce some values to the folder
// create a thread using runnable class
// path for producing in producer  classpath://uniken-pub-sub

//consumer :
// multithreaded code for reading the folder in the classpath
// thread can be in any state i.e runnable , waiting,
// thread creation where multiple threads will read the folder but only a single task  to be picked by each thread
// manage race condition
// manage thread starving
// multithreaded consumer


// conditions

//1/ 1 thread 1 task for consumer
//2/ producer maintainign the enough task in folder to keep busy the consumer
//3/ manage exceptions , timeouts  , delays .

import java.io.*;
        import java.nio.channels.*;
        import java.nio.file.*;
        import java.util.*;
        import java.util.concurrent.*;

public class FileBasedPubSub {

    private static final String FOLDER_NAME = "uniken-pub-sub";

    public static void main(String[] args) throws Exception {
        // Create folder inside classpath target/classes
        Path folderPath = Paths.get("target/classes/" + FOLDER_NAME);
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        ExecutorService producerPool = Executors.newFixedThreadPool(2);
        ExecutorService consumerPool = Executors.newFixedThreadPool(3);

        List<Thread> allThreads = new CopyOnWriteArrayList<>();

        // Start producers
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(new ProducerTask(folderPath), "Producer-" + (i + 1));
            allThreads.add(t);
            producerPool.submit(t);
        }

        // Start consumers
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(new ConsumerTask(folderPath), "Consumer-" + (i + 1));
            allThreads.add(t);
            consumerPool.submit(t);
        }

        // Scheduled logger for thread states
        ScheduledExecutorService monitor = Executors.newSingleThreadScheduledExecutor();
        monitor.scheduleAtFixedRate(() -> {
            System.out.println("\n=== THREAD STATE SNAPSHOT ===");
            for (Thread t : allThreads) {
                System.out.println(t.getName() + " -> " + t.getState());
            }
            System.out.println("=============================\n");
        }, 2, 3, TimeUnit.SECONDS);

        // Let system run for 30 sec
        Thread.sleep(30000);

        producerPool.shutdownNow();
        consumerPool.shutdownNow();
        monitor.shutdownNow();
    }

    // ------------------- PRODUCER -------------------
    static class ProducerTask implements Runnable {
        private final Path folderPath;
        private final Random random = new Random();

        ProducerTask(Path folderPath) {
            this.folderPath = folderPath;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(Thread.currentThread().getName());
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String fileName = "task-" + UUID.randomUUID() + ".txt";
                    Path filePath = folderPath.resolve(fileName);

                    try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                        writer.write("Task data: " + new Date());
                    }

                    System.out.println(Thread.currentThread().getName() +
                            " [PRODUCED] -> " + fileName);

                    Thread.sleep(500 + random.nextInt(1000)); // simulate delay

                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted.");
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    System.err.println("Producer error: " + e.getMessage());
                }
            }
        }
    }

    // ------------------- CONSUMER -------------------
    static class ConsumerTask implements Runnable {
        private final Path folderPath;

        ConsumerTask(Path folderPath) {
            this.folderPath = folderPath;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(Thread.currentThread().getName());
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    File[] files = folderPath.toFile().listFiles();
                    if (files == null || files.length == 0) {
                        System.out.println(Thread.currentThread().getName() +
                                " waiting... no tasks available.");
                        Thread.sleep(300); // wait if no task
                        continue;
                    }

                    boolean processed = false;
                    for (File file : files) {
                        if (!file.getName().endsWith(".processing")) {
                            if (tryPickAndProcess(file)) {
                                processed = true;
                                break;
                            }
                        }
                    }

                    if (!processed) {
                        Thread.sleep(200); // avoid busy-spin
                    }

                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted.");
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    System.err.println("Consumer error: " + e.getMessage());
                }
            }
        }

        private boolean tryPickAndProcess(File file) {
            Path source = file.toPath();
            Path processing = source.resolveSibling(file.getName() + ".processing");

            try {
                // Atomic move to mark ownership
                Files.move(source, processing);

                System.out.println(Thread.currentThread().getName() +
                        " picked " + processing.getFileName());

                // Simulate processing
                List<String> lines = Files.readAllLines(processing);
                System.out.println(Thread.currentThread().getName() +
                        " [CONSUMED] -> " + processing.getFileName() +
                        " Data: " + lines);

                // Delete after done
                Files.delete(processing);
                System.out.println(Thread.currentThread().getName() +
                        " deleted " + processing.getFileName());

                return true;

            } catch (NoSuchFileException e) {
                // Another consumer already picked it
            } catch (FileAlreadyExistsException e) {
                // Another consumer moved it first
            } catch (Exception e) {
                System.err.println("Error processing file " + file.getName() + ": " + e.getMessage());
            }
            return false;
        }
    }
    }



