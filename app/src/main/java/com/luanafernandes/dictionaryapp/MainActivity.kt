package com.luanafernandes.dictionaryapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.luanafernandes.dictionaryapp.feature_dictionary.presentation.WordInfoItem
import com.luanafernandes.dictionaryapp.feature_dictionary.presentation.WordInfoViewModel
import com.luanafernandes.dictionaryapp.ui.theme.DictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryAppTheme {
                val viewModel: WordInfoViewModel = hiltViewModel()
                val state = viewModel.state.value
                val scaffoldState = rememberScaffoldState()

                LaunchedEffect(key1 = true) {
                    viewModel.eventFlow.collectLatest { event ->
                        when (event) {
                            is WordInfoViewModel.UIEvent.ShowSnackbar -> {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                }

                Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    Box(
                        modifier = Modifier.background(MaterialTheme.colors.background)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Text(text = "Dictionary App",
                                fontSize = 26.sp,
                                modifier = Modifier.padding(vertical = 20.dp)
                                )

                            TextField(
                                value = viewModel.searchQuery.value,
                                onValueChange = viewModel::onSearch,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp)),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.LightGray,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    errorIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                ),
                                placeholder = {
                                    Text(text = "Search...")
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                                LazyColumn(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    items(state.wordInfoItems.size) { i ->
                                        val wordInfo = state.wordInfoItems[i]
                                        if (i > 0) {
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }
                                        WordInfoItem(wordInfo = wordInfo)
                                        if (i < state.wordInfoItems.size - 1) {
                                            Divider()
                                        }
                                    }
                                }

                        }
                        if (state.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }

            }
        }


    }
}

