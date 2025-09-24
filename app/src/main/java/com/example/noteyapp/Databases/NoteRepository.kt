package com.example.noteyapp.Databases

import com.example.noteyapp.models.Note
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class NoteRepository @Inject constructor(private val notedatabasedao: NoteDatabaseDAO) {
    suspend fun insertnote(note: Note){
        return notedatabasedao.insertNote(note)
    }

    suspend fun deletenote(note:Note){
        return notedatabasedao.deleteNote(note)
    }

    fun getallnotes(): Flow<List<Note>> {
        return notedatabasedao.getAllNotes().flowOn(Dispatchers.IO).conflate()
    }

}