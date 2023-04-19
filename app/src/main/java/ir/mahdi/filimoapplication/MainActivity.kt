package ir.mahdi.filimoapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ir.mahdi.filimoapplication.feature_search.presentation.SearchViewModel
import ir.mahdi.filimoapplication.feature_search.presentation.components.MovieItem
import ir.mahdi.filimoapplication.ui.theme.FilimoApplicationTheme
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilimoApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray
                ) {
                    val viewModel: SearchViewModel = hiltViewModel()
                    val state = viewModel.state.value

                    LaunchedEffect(key1 = true){
                        viewModel.eventFlow.collectLatest { event ->
                            when(event){
                                is SearchViewModel.UIEvent.ShowToast -> {
                                    Toast.makeText(this@MainActivity , event.message , Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }

                    Column(Modifier.fillMaxSize()) {
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = viewModel.searchQuery.value,
                            onValueChange = viewModel::onSearch,
                            placeholder = {
                                Text(text = "جستجو..." , modifier = Modifier.fillMaxWidth())
                            })

                        Spacer(modifier = Modifier.size(16.dp))

                        LazyColumn(Modifier.fillMaxWidth()){
                            items(state.movieList.size){ index ->
                                val data = state.movieList[index]
                                MovieItem(
                                    movieTitle = data.movieTitle,
                                    description = data.description,
                                    cover = data.cover,
                                    rate = data.rate,
                                    duration = data.duration
                                )
                            }
                        }

                        if(state.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FilimoApplicationTheme {
        Greeting("Android")
    }
}