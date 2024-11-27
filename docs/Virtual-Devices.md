# MVM Virtual Devices

The MVM (Micro Virtual Machine) supports virtual devices that provide an interface between user-space programs and the
operating system (kernel). These devices are implemented as drivers within the kernel and are accessed via system calls.

## Virtual File System (VFS)

**Keynote!!!**
This is only in a specific branch in [vfs-v2](https://github.com/ChippyPlus/micro-vm/tree/vfs-v2).
And all of these features aren’t yet available in master

The virtual file system (VFS) is a crucial virtual device, providing file system functionality within the MVM. The VFS
is implemented as a driver within the kernel (`os/drivers/VFS.kt`). It handles file and directory operations. The VFS is
persistent, using a file (`vfs.fs`) to save and load its state. System calls provide access to the VFS.

The VFS supports:

- **File Creation:**  Creating new files using `newFile`.
- **File Deletion:** Deleting files and directories using `deleteFile`.
- **Reading Files:** Reading data from files into memory (`readFile`).
- **Writing Files:** Writing data from memory to files (`writeFile`).
- **Listing Files:** Listing files within directories (`listFiles`).
- **Directory Creation:**  Creating directories using `newDir`.

### VFS Data Structures

The VFS uses the following data structures:

- **`Ventry`:** A generic entry (file or directory) in the VFS. It contains the file/directory name, content (if a
  file), permissions, metadata (size, creation time), and a list of child `Ventry` objects (if it's a directory).
- **`Permissions`:** This structure defines the access permissions for a VFS entry (read, write).
- **`Meta`:** Contains metadata for a file or directory.

### VFS System Calls

The VFS is accessed using the following system calls:

- `newFile`: Creates a new file.
- `readFile`: Reads data from a file.
- `writeFile`: Writes data to a file.
- `deleteFile`: Deletes a file or directory.
- `listFiles`: Lists files and directories.
- `newDir`: Creates a new directory.

The function arguments, return values, and error handling for the VFS system calls are documented in
the [System Call Table](System-Calls-Table).

## Other Virtual Devices

In addition to the VFS, the MVM kernel supports several other virtual devices, providing various functionalities within
the VM's emulated environment.
These devices are implemented as drivers in Kotlin:

### Console I/O

The console I/O driver provides system calls for interacting with a virtual console or terminal:

- `writeIo`: Writes data to the console.
- `readIo`: Reads input from the console.

### Timer

The MVM's scheduler uses a timer (not implemented as a virtual device) that counts instruction cycles. The timer doesn’t
have any system calls. Instead, the timer's counter is incremented after every instruction's execution. This counter
triggers a context switch once it reaches the maximum value set in the configuration file.

### Inter-Process Communication (IPC)

The kernel manages IPC using mailboxes.
These are implemented using stacks (`os_package.Ipc.MessagePassing.mailBoxes`).
The following system calls provide access to the IPC:

- `link`: Establishes a link (connection) between two processes.
- `send`: Sends a message to another process.
- `receive`: Receives a message from another process.

This document provides an overview of virtual devices in the MVM. For more detailed information, refer to
the [System Call Table](System-Calls-Table) and the specific documentation for each virtual device.


