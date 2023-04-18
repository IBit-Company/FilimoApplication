package ir.mahdi.filimoapplication.feature_search.presentation

import ir.mahdi.filimoapplication.feature_search.domain.model.Movie

data class MovieSearchState (
    val movieList: List<Movie> = emptyList(),
    val isLoading: Boolean = false
)