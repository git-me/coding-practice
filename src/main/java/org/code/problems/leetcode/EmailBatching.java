package org.code.problems.leetcode;

import java.util.*;

public class EmailBatching {
    public static Map<Integer, List<String>> createBatches(List<String> emails) {
        Map<String, Integer> recipientToBatchMap = new HashMap<>();
        Map<Integer, List<String>> batches = new HashMap<>();
        int batchCounter = 1;

        for (String email : emails) {
            String[] recipients = email.split(";"); // Assuming semicolon (;) is the delimiter for recipients

            Set<String> uniqueRecipients = new HashSet<>();
            for (String recipient : recipients) {
                if (!uniqueRecipients.contains(recipient)) {
                    uniqueRecipients.add(recipient);

                    // If recipient is already present in some batch, create a new batch
                    if (recipientToBatchMap.containsKey(recipient)) {
                        batchCounter++;
                    }

                    recipientToBatchMap.put(recipient, batchCounter);
                }
            }

            // Add email to corresponding batch
            for (String recipient : uniqueRecipients) {
                int batchNumber = recipientToBatchMap.get(recipient);
                batches.computeIfAbsent(batchNumber, k -> new ArrayList<>()).add(email);
            }
        }

        return batches;
    }

    public static void main(String[] args) {
        List<String> emails = Arrays.asList(
                "john@example.com;jane@example.com",
                "jane@example.com;mary@example.com",
                "peter@example.com;john@example.com"
        );

        Map<Integer, List<String>> batches = createBatches(emails);

        // Print batches
        for (Map.Entry<Integer, List<String>> entry : batches.entrySet()) {
            System.out.println("Batch " + entry.getKey() + ":");
            for (String email : entry.getValue()) {
                System.out.println("\t" + email);
            }
        }
    }
}
