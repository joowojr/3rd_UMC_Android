package com.example.week8

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content : String,
    @ColumnInfo(name = "bookmark") var bookmark: Boolean,
    @ColumnInfo(name = "like") val like: Boolean
        )
