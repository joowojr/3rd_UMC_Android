package com.example.week8

import androidx.room.*

@Dao
interface MemoDao {
    @Insert
    fun insert(memo: Memo)

    @Delete
    fun delete(memo: Memo?)

    @Update
    fun update(memo: Memo)

    @Query("SELECT * FROM Memo")
    fun selectAll(): List<Memo>

    @Query("SELECT * FROM Memo WHERE bookmark = :bookmark")
    fun selectByBookmark(bookmark: Boolean): List<Memo>

    @Query("SELECT * FROM Memo WHERE id = :id")
    fun selectById(id: Int): Memo

    @Query("UPDATE Memo SET `bookmark` = :bookmark WHERE id = :id")
    fun updateBmById(id: Int, bookmark: Boolean)
}