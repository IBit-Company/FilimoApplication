package ir.mahdi.filimoapplication.feature_search.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.mahdi.filimoapplication.feature_search.data.network.SearchApi
import ir.mahdi.filimoapplication.feature_search.data.repository.SearchRepositoryImpl
import ir.mahdi.filimoapplication.feature_search.domain.repository.SearchRepository
import ir.mahdi.filimoapplication.feature_search.domain.use_case.SearchMovie
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
object SearchModule {

    @Provides
    @Singleton
    fun provideSearchMovieUseCase(searchRepository: SearchRepository): SearchMovie {
        return SearchMovie(searchRepository)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(searchApi: SearchApi): SearchRepository {
        return SearchRepositoryImpl(searchApi)
    }

    @Provides
    @Singleton
    fun provideSearchApi(): SearchApi {
        return SearchApi()
    }
}