package org.example.debuger.jsonInfo

import kotlinx.serialization.Serializable


@Serializable
data class BreakPoints(
    val line: Int, val enabled: Boolean
)


@Serializable
data class DebugFile(
    val breakPoints: List<BreakPoints>, val lineSpecific: Map<String, String>, val eachIteration: List<String>
)