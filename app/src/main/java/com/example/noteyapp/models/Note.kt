package com.example.noteyapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDate
import java.util.Date

@Entity(tableName="Notes")
data class Note (
    @PrimaryKey(autoGenerate = true)
    var Id:Int=0,

    @ColumnInfo(name="Title")
    var Title: String,
    @ColumnInfo(name="Description")
    var Description:String,
    @ColumnInfo(name="EntryDate")
    val entryDate: Date = Date.from(Instant.now())
)
