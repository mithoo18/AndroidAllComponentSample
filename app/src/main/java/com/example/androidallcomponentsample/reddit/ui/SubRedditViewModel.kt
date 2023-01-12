package com.example.androidallcomponentsample.reddit.ui

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidallcomponentsample.reddit.repository.inDb.DbRedditPostRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

class SubRedditViewModel(
    private val repository: RedditPostRepository,
    private val savedStateHandle: SavedStateHandle
)  : ViewModel(){
    companion object{
        const val KEY_SUBREDDIT =  "subreddit"
        const val DEFAULT_SUBREDDIT = "androiddev"
    }

    init{
        if(!savedStateHandle.contains(KEY_SUBREDDIT)){
            savedStateHandle.set(KEY_SUBREDDIT, DEFAULT_SUBREDDIT)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val posts = savedStateHandle.getLiveData<String>(KEY_SUBREDDIT)
        .asFlow()
        .flatMapLatest{ repository.postsOfSubreddit(it,30)}
        .cachedIn(viewModelScope)


    fun showSubreddit(subreddit : String){
        if(!shouldShowSubreddit(subreddit)) return
        savedStateHandle.set(KEY_SUBREDDIT,subreddit)
    }

}
