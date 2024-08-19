# FlowLogTagger

## Overview

FlowLogTagger is a Java application that processes flow log data based on a lookup table and generates a report containing tag counts and port/protocol combination counts.

## Assumptions

- The flow log data and lookup table are in CSV format.
- The matching of protocol is case-insensitive.
- If a port/protocol combination does not match any entry in the lookup table, it is tagged as "Untagged".
- The application is designed to handle flow log files up to 10 MB in size and lookup tables with up to 10,000 entries.

## How to Compile and Run

### Prerequisites

- Java 8 or higher
- Gradle

### Steps

1. Clone the repository or download the source code.
2. Navigate to the project directory.
3. Compile the project using Gradle:

    ```bash
    gradle build
    ```

4. Run the application:

    ```bash
    gradle run
    ```

5. The output will be generated in the `output.txt` file in the project root.

### Testing

Run the unit tests using the following command:

```bash
gradle test
```

## Analysis

- The program is designed to efficiently handle large files and a substantial number of lookup entries.
- Edge cases handled include missing or extra columns in the input files and case insensitivity in protocol matching.

## Author

Koushik Thirupattur
