package kvmInternals.systemCalls

data class Read(val fd: Int, val buffer: Long, val count: Int)
data class Write(val fd: Int, val buffer: Long, val count: Int)
data class Open(val path: String, val flags: Int, val mode: Int)
data class Close(val fd: Int)
data class Fork(val nothing: Nothing? = null)
data class Exit(val status: Int)
data class Wait(val pid: Int, val status: Long)
data class ReadSock(val fd: Int, val buffer: Long, val count: Int)
data class WriteSock(val fd: Int, val buffer: Long, val count: Int)
data class Socket(val domain: Int, val type: Int, val protocol: Int)
data class Bind(val fd: Int, val address: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bind

        if (fd != other.fd) return false
        if (!address.contentEquals(other.address)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fd
        result = 31 * result + address.contentHashCode()
        return result
    }
}
data class Listen(val fd: Int, val backlog: Int)
data class Accept(val fd: Int, val address: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Accept

        if (fd != other.fd) return false
        if (!address.contentEquals(other.address)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fd
        result = 31 * result + address.contentHashCode()
        return result
    }
}
data class Connect(val fd: Int, val address: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Connect

        if (fd != other.fd) return false
        if (!address.contentEquals(other.address)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fd
        result = 31 * result + address.contentHashCode()
        return result
    }
}
data class GetTimeOfDay(val tv: Long)
data class Execve(val path: String, val argv: Long, val envp: Long)
data class GetPid(val nothing: Nothing? = null)
data class GetUid(val nothing: Nothing? = null)
data class GetCwd(val nothing: Nothing? = null)
data class Chdir(val path: String)
data class Mkdir(val path: String)
data class Rmdir(val path: String)
data class Remove(val path: String)
data class Create(val path: String)