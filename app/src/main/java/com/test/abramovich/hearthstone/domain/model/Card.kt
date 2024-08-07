package com.test.abramovich.hearthstone.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Card_hearthstone")
data class Card(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    @ColumnInfo(name = "name")
    val name : String?,
    @ColumnInfo(name = "className")
    val className : String?,
    @ColumnInfo(name = "coast")
    val coast : Int?,
    @ColumnInfo(name = "description")
    val description : String?,
    @ColumnInfo(name = "image")
    val image : String?,
    @ColumnInfo(name = "isFavorite")
    var isFavorite : Boolean
)
