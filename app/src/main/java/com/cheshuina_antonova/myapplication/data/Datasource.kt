package com.cheshuina_antonova.myapplication.data

import com.cheshuina_antonova.myapplication.R
import com.cheshuina_antonova.myapplication.model.Movie


class Datasource {
    fun loadMovieList(): List<Movie> {
        return listOf(
            Movie(R.string.item1, R.string.item1_description, R.drawable.item1),
            Movie(R.string.item2, R.string.item2_description, R.drawable.item2),
            Movie(R.string.item3, R.string.item3_description, R.drawable.item3),
            Movie(R.string.item4, R.string.item4_description, R.drawable.item4),
            Movie(R.string.item5, R.string.item5_description, R.drawable.item5),
            Movie(R.string.item6, R.string.item6_description, R.drawable.item6),
            Movie(R.string.item7, R.string.item7_description, R.drawable.item7),
            Movie(R.string.item8, R.string.item8_description, R.drawable.item8),
            Movie(R.string.item9, R.string.item9_description, R.drawable.item9),
            Movie(R.string.item10, R.string.item10_description, R.drawable.item10)
        )
    }
}