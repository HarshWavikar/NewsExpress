package com.codewithharsh.newsexpress.presentation.home

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.codewithharsh.newsexpress.domain.usecases.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    val news = newsUseCase.getNews(
        sources = listOf("bbc-news", "the-times-of-india", "abc-news")
    ).cachedIn(viewModelScope)   // to save from configuration changes

//    private val _state = mutableStateOf(MainScreenState())
//    val state: State<MainScreenState> = _state
//    private  var  textToSpeech: TextToSpeech? = null



//    fun speakText(text:String){
//        _state.value = state.value.copy(
//            text = text
//        )
//    }

//    fun textToSpeech(context: Context){
//        _state.value = state.value.copy(
//            isButtonEnabled = false
//        )
//        textToSpeech = TextToSpeech(
//            context
//        ) {
//            if (it == TextToSpeech.SUCCESS) {
//                textToSpeech?.let { txtToSpeech ->
//                    txtToSpeech.language = Locale.US
//                    txtToSpeech.setSpeechRate(1.0f)
//                    txtToSpeech.speak(
//                        _state.value.text,
//                        TextToSpeech.QUEUE_ADD,
//                        null,
//                        null
//                    )
//                }
//            }
//            _state.value = state.value.copy(
//                isButtonEnabled = true
//            )
//        }
//    }
}



//data class MainScreenState(
//    val isButtonEnabled:Boolean = true,
//    val text:String = ""
//)