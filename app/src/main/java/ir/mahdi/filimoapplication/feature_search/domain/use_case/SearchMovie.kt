package ir.mahdi.filimoapplication.feature_search.domain.use_case

import ir.mahdi.filimoapplication.core.util.Resource
import ir.mahdi.filimoapplication.feature_search.domain.model.Movie
import ir.mahdi.filimoapplication.feature_search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchMovie(
    private val repository: SearchRepository
) {

    operator fun invoke(query: String): Flow<Resource<List<Movie>>> {
        if (query.isBlank() || query.length < 4)
            return flow {  }

        return repository.search(query)
    }
}