package com.example.noteyapp.Databases

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.noteyapp.models.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDatabaseDAO {
    @Query("Select * from Notes")
    fun getAllNotes(): Flow<List<Note>>

    @Insert()
    suspend fun insertNote(note:Note)

    @Delete()
    suspend fun deleteNote(note:Note)


}