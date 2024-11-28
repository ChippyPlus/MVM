package environment.vfs


val myFileSystem: List<Formats.Ventry> = listOf(
	Formats.Ventry(
		name = "home", permissions = Formats.Permissions(directory = true), children = listOf(
			Formats.Ventry(
				name = "user", permissions = Formats.Permissions(directory = true), children = listOf(
					Formats.Ventry(
						name = "file1.txt", content = "This is file 1"
					), Formats.Ventry(
						name = "dir2", permissions = Formats.Permissions(directory = true), children = listOf(
							Formats.Ventry(
								name = "file3.txt", content = "This is file 3"
							)
						)
					)
				)
			)
		)
	), Formats.Ventry(
		name = "docs", permissions = Formats.Permissions(directory = true), children = listOf(
			Formats.Ventry(
				name = "important.txt", content = "This is an important document"
			)
		)
	), Formats.Ventry(
		name = "temp.txt", content = "This is a temporary file"
	)
)

