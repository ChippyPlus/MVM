### Micro Virtual Machine (MVM) Documentation

**Getting Started**

* [Home](Home.md)
* [Installation and Setup](Getting-Started.md)

**Assembly Language**

* [Introduction to the Assembly](Assembly-Intro.md)
* [Instruction Set](Instruction-Set.md)
	- [Instruction Set Table](Instruction-Table.md)
* [Registers](Registers.md)
	- [General Purpose Registers](Registers.md#general-purpose-registers-g1-g10)
	- [System Registers](Registers.md#system-registers-s1-s4)
	- [Return Registers](Registers.md#return-registers-r1-r10)
	- [Function Argument Registers](Registers.md#function-argument-registers-f1-f10)
	- [Floating-Point Registers](Registers.md#floating-point-registers-x1-x10)
	- [Intel Registers](Registers.md#intel-registers-i1-i10)
* [Data Types](DataTypes.md)
* [Memory Model](Memory-Model.md)
	- [Memory Addressing](Memory-Model.md#memory-addressing)
	- [String Storage](Memory-Model.md#string-storage)
	- [Array Storage](Memory-Model.md#array-storage)

**Standard Library**

* [Standard Library Overview](Standard-Library.md)
	- [Standard Library Table](Standard-Library-Table.md)
* [String Functions](Standard-Library.md#string-functions)
* [Array Functions](Standard-Library.md#array-functions)
* [Maths Functions](Standard-Library.md#maths-functions)
* [Clean Functions](Standard-Library.md#clean-functions)
* [I/O Functions](Standard-Library.md#io-functions)
* [System Functions](Standard-Library.md#system-functions)
* [Conversion Functions](Standard-Library.md#conversion-functions)

**System Calls**

* [System Call Overview](System-Calls.md)
	- [System Call Table](System-Calls-Table.md)
* [File System Calls](System-Calls.md#file-system-calls)
* [Process Management Calls](System-Calls.md#process-management-calls)
* [IPC Calls](System-Calls.md#ipc-calls)
* [Host OS Calls](System-Calls.md#host-os-calls)
* [Other System Calls](System-Calls.md#other-system-calls)

**Kernel + OS**

* [Kernel Overview](Architecture-Overview.md)
* [Scheduler](Scheduler.md)
	- [Scheduling Algorithms](Scheduler.md#scheduler-algorithm)
* [Process Management](Process-Management.md)
	- [Process Control Block (KProcess)](Process-Management.md#Process-Control-Block-kprocess)
* [Virtual Devices](Virtual-Devices.md)
	- [Virtual File System (VFS)](Virtual-Devices.md#virtual-file-system-vfs)
	- [Other Virtual Devices](Virtual-Devices.md#other-virtual-devices)
* [System Call Implementation](System-Call-Implementation.md)

**Error Handling**

* [Error Codes](Error-Codes.md)
* [Error Register Table](Informational-Registers.md)

**Advanced Topics**

* [Future Enhancements](Future-Plans.md)

**Appendix**

* [ASCII Table](ASCII-Table.md)
* [Instruction Set Table](Instruction-Table.md)
* [Informational Registers](Informational-Registers.md)
* [System Call Table](System-Calls-Table.md)
* [Standard Library Table](Standard-Library-Table.md)

**Project Information**

* [GitHub Repository](https://github.com/ChippyPlus/micro-vm)
* [License](https://github.com/ChippyPlus/micro-vm/blob/master/LICENSE)