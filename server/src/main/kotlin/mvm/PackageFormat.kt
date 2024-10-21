package mvm

import kotlinx.serialization.Serializable


@Serializable
data class RepositoryFormat(
	val repository: String,
	val modules: List<ModuleFormat>,
)


@Serializable
data class ModuleFormat(
	val module: String,
	val packages: List<PackageFormat>,
)


@Serializable
data class PackageFormat(
	val name: String,
	val code: String,
	val version: String,
	val help: HelpFormat,
	val dependencies: List<String>,
)


@Serializable
data class HelpFormat(
	val name: String,
	val arguments: List<ArgumentsFormat>,
	val info: String,
)

@Serializable
data class ArgumentsFormat(
	val name: String,
	val info: String,
)
