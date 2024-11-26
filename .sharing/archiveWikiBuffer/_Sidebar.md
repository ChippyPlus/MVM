### Micro Virtual Machine (MVM) Documentation

**Getting Started**
* [Home](Home)
* [Installation and Setup](Getting-Started)

**Assembly Language**
* [Introduction to the Assembly](Assembly-Intro)
* [Instruction Set](Instruction-Set)
    - [Instruction Set Table](Instruction-Table)
* [Registers](Registers)
    - [General Purpose Registers](Registers#general-purpose-registers-g1-g10)
    - [System Registers](Registers#system-registers-s1-s4)
    - [Return Registers](Registers#return-registers-r1-r10)
    - [Function Argument Registers](Registers#function-argument-registers-f1-f10)
    - [Floating-Point Registers](Registers#floating-point-registers-x1-x10)
    - [Intel Registers](Registers#intel-registers-i1-i10)
* [Data Types](DataTypes)
* [Memory Model](Memory-Model)
    - [Memory Addressing](Memory-Model#memory-addressing)
    - [String Storage](Memory-Model#string-storage)
    - [Array Storage](Memory-Model#array-storage)


**Standard Library**
* [Standard Library Overview](Standard-Library)
    - [Standard Library Table](Standard-Library-Table)
* [String Functions](Standard-Library#string-functions)
* [Array Functions](Standard-Library#array-functions)
* [Maths Functions](Standard-Library#maths-functions)
* [Clean Functions](Standard-Library#clean-functions)
* [I/O Functions](Standard-Library#io-functions)
* [System Functions](Standard-Library#system-functions)
* [Conversion Functions](Standard-Library#conversion-functions)

**System Calls**
* [System Call Overview](System-Calls)
    - [System Call Table](System-Calls-Table)
* [File System Calls](System-Calls#file-system-calls)
* [Process Management Calls](System-Calls#process-management-calls)
* [IPC Calls](System-Calls#ipc-calls)
* [Host OS Calls](System-Calls#host-os-calls)
* [Other System Calls](System-Calls#other-system-calls)

**Kernel + OS**
* [Kernel Overview](Architecture-Overview)
* [Scheduler](Scheduler)
    - [Scheduling Algorithms](Scheduler#scheduler-algorithm)
* [Process Management](Process-Management)
    - [Process Control Block (KProcess)](Process-Management#Process-Control-Block-kprocess)
* [Virtual Devices](Virtual-Devices)
    - [Virtual File System (VFS)](Virtual-Devices#virtual-file-system-vfs)
    - [Other Virtual Devices](Virtual-Devices#other-virtual-devices)
* [System Call Implementation](System-Call-Implementation)

**Error Handling**
* [Error Codes](Error-Codes)
* [Error Register Table](Informational-Registers) 

**Advanced Topics**
* [Future Enhancements](Future-Plans)

**Appendix**
* [ASCII Table](ASCII-Table)
* [Instruction Set Table](Instruction-Table)
* [Informational Registers](Informational-Registers)
* [System Call Table](System-Calls-Table)
* [Standard Library Table](Standard-Library-Table)


**Project Information**
* [GitHub Repository](https://github.com/ChippyPlus/micro-vm)
* [License](https://github.com/ChippyPlus/micro-vm/blob/master/LICENSE)