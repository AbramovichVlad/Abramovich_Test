package com.test.abramovich.hearthstone.domain.model

data class SortType(
    var direction : SortDirection = SortDirection.ASCENDING,
    var field : SortField = SortField.CLASS
)

enum class SortDirection(){
    ASCENDING(), DESCENDING()
}

enum class SortField(){
    COAST(), CLASS()
}
