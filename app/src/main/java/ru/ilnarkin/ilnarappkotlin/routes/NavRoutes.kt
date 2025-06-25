package ru.ilnarkin.ilnarappkotlin.routes

sealed class NavRoutes(val route: String) {
    object NotesScreen : NavRoutes("notes")
    object NoteAddScreen : NavRoutes("notes/add")
    object NoteViewScreen : NavRoutes("notes/view")
    object TagsScreen : NavRoutes("tags")
    object ArchiveScreen : NavRoutes("archive")
    object SearchScreen : NavRoutes("search")
    object SettingsScreen : NavRoutes("settings")
}