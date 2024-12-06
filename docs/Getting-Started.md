## Getting Started with MVM

This guide helps you set up and run the Micro Virtual Machine (MVM) to execute assembly code. This guide is for macOS
and Linux systems.

### Prerequisites

* **Java Development Kit (JDK):** MVM is written in Kotlin, which requires a JDK. Ensure you have JDK 17 or later
  installed. You can download it
  from [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/).

### Installation

1. **Clone the Repository:**
   ```bash
   git clone --depth 1 https://github.com/ChippyPlus/micro-vm.git
   ```

2. **Navigate to the Project Directory:**
   ```bash
   cd micro-vm
   ```

### Building the Project

1. **Execute with Gradle:** This builds the project and creates the executable JAR file.
   ```bash
   ./gradlew build
   ```

### Running Assembly Code

You can run your MVM assembly code (`.kar` files) using the following methods:

**Method 1: Using Gradle (Recommended for Development)**

1. **Execute with Gradle:** This runs the MVM from the JAR file, executing a `.kar` file provided as an argument.
   ```bash
   ./gradlew r --args="irun <path_to_assembly_file.kar>"
   ```
   Replace `<path_to_assembly_file.kar>` with the path to your assembly file. For example:
   ```bash
   ./gradlew r --args="irun src/main/resources/programs/helloworld.kar"
   ```

**Method 2: Using the Executable JAR**

1. **Locate the JAR:** After building the project, find the JAR file (`MVM-1.0.jar` or similar) in the `build/libs/`
   directory.
2. **Run with Java:**
   ```bash
   java -jar build/libs/MVM-1.0.jar irun <path_to_assembly_file.kar>
   ```

**Method 3: Using an Alias**

1. **Create an alias:** This creates a shell alias to simplify running the MVM.
   ```bash
   alias mvm="java -jar build/libs/MVM-1.0.jar"
   ```
2. **Run with the alias:**
   ```bash
   mvm irun <path_to_assembly_file.kar>
   ```

### Clean Up

If you do not plan to build the project again, it's recommended to remove the Gradle cache files:

```bash
rm -rf ~/.gradle
```

### Example

Here's a simple MVM assembly program (`myprogram.kar`):

```assembly
// Example HelloWorld program 

STR F1 "Hello, World!"   // Load string into memory and returns pointer to F1
call println             // Prints the string located in F1 to stdout

```

To run this program, use one of the above commands, replacing `<path_to_assembly_file.kar>` with `myprogram.kar`. The
output will be printed to the console.

Now you can start writing and executing your own assembly programs. Refer to
the [Instruction Table](Instruction-Table.md)
for a list of instructions and their syntax.

