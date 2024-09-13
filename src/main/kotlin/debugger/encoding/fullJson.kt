package org.example.internals.encoding

import kotlinx.serialization.Serializable





@Serializable
data class DebugFile(
    val lineSpecific: Map<String, String>, val eachIteration: List<String>
)