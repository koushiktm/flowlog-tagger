package com.example.flowlogtagger;

import java.io.*;
import java.util.*;

public class FlowLogTagger {
    public static void main(String[] args) {
        String lookupTableFile = "src/main/resources/lookup_table.csv";
        String flowLogsFile = "src/test/resources/flowlogs.txt";
        String outputFile = "output.txt";

        Map<String, String> lookupTable = loadLookupTable(lookupTableFile);
        List<String[]> flowLogs = loadFlowLogs(flowLogsFile);

        Map<String, Integer> tagCounts = new HashMap<>();
        Map<String, Integer> combinationCounts = new HashMap<>();

        for (String[] flowLog : flowLogs) {
            String port = flowLog[0];
            String protocol = flowLog[1].toLowerCase();
            String key = port + "," + protocol;
            String tag = lookupTable.getOrDefault(key, "Untagged");

            tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
            combinationCounts.put(key, combinationCounts.getOrDefault(key, 0) + 1);
        }

        writeOutput(outputFile, tagCounts, combinationCounts);
    }

    private static Map<String, String> loadLookupTable(String lookupTableFile) {
        Map<String, String> lookupTable = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(lookupTableFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    lookupTable.put(parts[0] + "," + parts[1].toLowerCase(), parts[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lookupTable;
    }

    private static List<String[]> loadFlowLogs(String flowLogsFile) {
        List<String[]> flowLogs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(flowLogsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                flowLogs.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flowLogs;
    }

    private static void writeOutput(String outputFile, Map<String, Integer> tagCounts, Map<String, Integer> combinationCounts) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputFile))) {
            pw.println("Tag Counts:");
            pw.println("Tag             Count");
            for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
                pw.printf("%-15s %d%n", entry.getKey(), entry.getValue());
            }

            pw.println("
Port/Protocol Combination Counts:");
            pw.println("Port     Protocol  Count");
            for (Map.Entry<String, Integer> entry : combinationCounts.entrySet()) {
                String[] parts = entry.getKey().split(",");
                pw.printf("%-8s %-8s %d%n", parts[0], parts[1], entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
