package com.example.noteyapp.Databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.example.noteyapp.models.DateConverter
import com.example.noteyapp.models.Note


@Database(entities=[Note::class], version=1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NoteDataBase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDAO

}