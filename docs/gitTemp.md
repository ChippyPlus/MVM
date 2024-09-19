## Getting Started with MVM

This guide will help you get MVM (Micro Virtual Machine) set up and running, so you can start executing assembly code.

### Prerequisites

* **Java Development Kit (JDK):** MVM is written in Kotlin, which requires a JDK. Make sure you have JDK 17 or later
  installed. You can download it
  from [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/).

### Installation

1. **Clone the Repository:**
   ```bash
   git clone --depth 1 https://github.com/ChippyPlus/MVM.git
   ```

2. **Navigate to the Project Directory:**
   ```bash
   cd MVM
   ```

### Building the VM

1. **Build with Gradle:**
   MVM uses Gradle as its build system. To build the VM, run the following command:
   ```bash
   ./gradlew build
   ```
   This will create a self-contained executable JAR file in the `build/libs/` directory.

### Running Assembly Code

You can run your MVM assembly code (`.kar` files) using the following methods:

**Method 1: Using Gradle (Recommended for Development)**

1. **Execute with Gradle:**
   ```bash
   ./gradlew r --args="run <path_to_assembly_file.kar>"
   ```
   Replace `<path_to_assembly_file.kar>` with the actual path to your assembly file. For example:
   ```bash
   ./gradlew r --args="run src/main/resources/main.kar" 
   ```

**Method 2: Using the Executable JAR**

1. **Locate the JAR:** After building the project, find the JAR file (`MVM-1.0-SNAPSHOT.jar` or similar) in the
   `build/libs/` directory.
2. **Run with Java:**
   ```bash
   java -jar build/libs/MVM-1.0-SNAPSHOT.jar run <path_to_assembly_file.kar>
   ```

**Method 3: Using an alias**

1. **Create an alias for method 2**
    ```bash
   alias mvm="java -jar build/libs/MVM-1.0-SNAPSHOT.jar"
   ```
2. **Run with the alias**
    ```bash
   mvm run <path_to_assembly_file.kar>
   ```

### Example

Here is a simple example of an MVM assembly program (`myprogram.kar`):

```assembly
// Example program to print the number 10
LIT G1 10       // Load the value 10 into register G1
PUSH G1         // Push G1 onto the stack
PRINTS          // Print what's on the stack
```

To run this program, you would use one of the following commands:

- **Using Gradle:**  `./gradlew r --args="run myprogram.kar"`
- **Using the JAR:**  `java -jar build/libs/MVM-1.0-SNAPSHOT.jar run myprogram.kar`
- **Using the alias** `mvm run myprogram.kar`
This will execute the assembly code, and the output will be printed to the console.

Now that you have MVM set up and running, you can start writing and executing your own assembly programs!  Refer to
the [Instruction Table](Instruction-Table) for a detailed list of instructions and their syntax. 
