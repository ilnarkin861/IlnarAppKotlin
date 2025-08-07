package ru.ilnarkin.ilnarappkotlin.models


data  class Note (
    var id: String?,
    var title: String?,
    var text: String,
    var noteType: NoteType,
    var date: String,
    var archive: Archive?,
    var tags: MutableList<Tag>
    )