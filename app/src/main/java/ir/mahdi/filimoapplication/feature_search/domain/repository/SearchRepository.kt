package ir.mahdi.filimoapplication.feature_search.domain.repository

import ir.mahdi.filimoapplication.core.util.Resource
import ir.mahdi.filimoapplication.feature_search.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun search(query: String): Flow<Resource<List<Movie>>>
}