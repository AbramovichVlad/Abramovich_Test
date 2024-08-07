package com.test.abramovich.hearthstone.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.abramovich.hearthstone.domain.model.Card

@Dao
interface FavoriteCardDao {

    @Query("SELECT * FROM Card_hearthstone WHERE id =:id")
    suspend fun getCardById(id : String) : Card?

    @Query("SELECT * FROM Card_hearthstone")
    suspend fun getCards() : List<Card>

    @Query("DELETE FROM Card_hearthstone WHERE id =:id")
    suspend fun deleteCardById(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card : Card)
}