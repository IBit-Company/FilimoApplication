package ir.mahdi.filimoapplication.feature_search.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mahdi.filimoapplication.core.util.Resource
import ir.mahdi.filimoapplication.feature_search.domain.use_case.SearchMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMovie: SearchMovie
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery as State<String>

    private val _state = mutableStateOf(MovieSearchState())
    val state:State<MovieSearchState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String){
        searchJob?.cancel()
        _searchQuery.value = query
        searchJob = viewModelScope.launch(Dispatchers.IO){
            delay(500)
            searchMovie(query).onEach {
                when(it){
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            movieList = it.data?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            movieList = emptyList(),
                            isLoading = true
                        )
                    }
                    is Resource.Fail -> {
                        _state.value = state.value.copy(
                            movieList = emptyList(),
                            isLoading = false
                        )

                        _eventFlow.emit(UIEvent.ShowToast(it.message?: "there is an error"))
                    }
                }
            }.launchIn(this)
        }
    }
    sealed class UIEvent {
        data class ShowToast(val message: String): UIEvent()
    }
}

