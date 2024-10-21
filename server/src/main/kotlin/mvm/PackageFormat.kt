package mvm

import kotlinx.serialization.Serializable


@Serializable
data class RepositoryFormat(
	var repository: String,
	var modules: List<ModuleFormat>,
)


@Serializable
data class ModuleFormat(
	var module: String,
	var packages: List<PackageFormat>,
)


@Serializable
data class PackageFormat(
	var name: String,
	var code: String,
	var version: String,
	var help: HelpFormat,
	var dependencies: List<String>,
)


@Serializable
data class HelpFormat(
	var name: String,
	var arguments: List<ArgumentsFormat>,
	var info: String,
)

@Serializable
data class ArgumentsFormat(
	var name: String,
	var info: String,
)
