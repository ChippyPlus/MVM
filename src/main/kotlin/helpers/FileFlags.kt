package helpers

enum class FileFlags {
    READ_ONLY,
    WRITE_ONLY,
    READ_WRITE,
    APPEND,
    TRUNCATE,
    CREATE
}

val fileFlags: Map<Int, FileFlags> = mapOf(
    1 to FileFlags.READ_ONLY,
    2 to FileFlags.WRITE_ONLY,
    3 to FileFlags.READ_WRITE,
    4 to FileFlags.APPEND,
    5 to FileFlags.TRUNCATE,
    6 to FileFlags.CREATE
)