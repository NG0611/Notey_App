package com.example.noteyapp.Modules

import android.content.Context
import androidx.room.Room
import com.example.noteyapp.Databases.NoteDataBase
import com.example.noteyapp.Databases.NoteDatabaseDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module

object AppModule {
    @Singleton
    @Provides
    fun provideNoteDao(notedatabase: NoteDataBase): NoteDatabaseDAO {
        return notedatabase.noteDao()
    }

    @Singleton
    @Provides
    fun provideNotedatabase(@ApplicationContext context: Context): NoteDataBase{
        return Room.databaseBuilder(
            context,
            NoteDataBase::class.java,
            "notes_db"
        )
            .fallbackToDestructiveMigration().build()
    }
}