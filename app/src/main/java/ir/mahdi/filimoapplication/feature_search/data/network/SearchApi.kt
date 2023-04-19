package ir.mahdi.filimoapplication.feature_search.data.network

import io.ktor.client.request.get
import io.ktor.client.request.header
import ir.mahdi.filimoapplication.core.util.Networking.HttpHelper
import ir.mahdi.filimoapplication.feature_search.data.dto.MovieListDto

class SearchApi {

    suspend fun search(query: String): MovieListDto =
        HttpHelper.ktorHttpClient.get(urlString = "${HttpHelper.baseUrl}movie/movie/list/tagid/1000300/text/{$query}/sug/on"){
            header("jsonType" , "simple")
        }
}