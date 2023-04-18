package ir.mahdi.filimoapplication.feature_search.data.repository

import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.ServerResponseException
import ir.mahdi.filimoapplication.core.util.Resource
import ir.mahdi.filimoapplication.feature_search.data.network.SearchApi
import ir.mahdi.filimoapplication.feature_search.domain.model.Movie
import ir.mahdi.filimoapplication.feature_search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(
    private val searchApi: SearchApi
): SearchRepository {

    override fun search(query: String): Flow<Resource<List<Movie>>> = flow{
        emit(Resource.Loading())

        try {
            val movieList = searchApi.search(query).toMovieList()
            emit(Resource.Success(movieList))
        } catch (e: ServerResponseException){
            emit( Resource.Fail(
                message = "Couldn't reach the server, check your internet connection." ,
                data = null
            ))
        } catch (e: ClientRequestException){
            emit(Resource.Fail(
                message = when (e.response.status.value){
                    400 -> "Http Error BadRequest"
                    401 -> "Http Error Unauthorized"
                    403 -> "Http Error Forbidden"
                    404 -> "Http Error NotFound"
                    else -> "HttpError"
                },
                data = null
            ))
        }
    }
}