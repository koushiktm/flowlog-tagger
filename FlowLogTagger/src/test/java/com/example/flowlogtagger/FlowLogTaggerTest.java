package com.example.flowlogtagger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

class FlowLogTaggerTest {

    @Test
    void testFlowLogTagging() {
        FlowLogTagger.main(new String[]{});

        File outputFile = new File("output.txt");
        assertTrue(outputFile.exists(), "Output file should exist");

        try (BufferedReader br = new BufferedReader(new FileReader(outputFile))) {
            String line = br.readLine();
            assertEquals("Tag Counts:", line.trim());

            line = br.readLine(); // Skip header
            line = br.readLine();
            assertTrue(line.contains("Untagged"), "Output should contain Untagged");

            line = br.readLine();
            assertTrue(line.contains("sv_P1"), "Output should contain sv_P1");

            line = br.readLine();
            assertTrue(line.contains("sv_P2"), "Output should contain sv_P2");

            line = br.readLine();
            assertTrue(line.contains("SV_P3"), "Output should contain SV_P3");

        } catch (IOException e) {
            fail("Failed to read the output file");
        }
    }
}
