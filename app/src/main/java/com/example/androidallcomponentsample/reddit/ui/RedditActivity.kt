package com.example.androidallcomponentsample.reddit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.androidallcomponentsample.R
import com.example.androidallcomponentsample.databinding.ActivityRedditBinding
import com.example.androidallcomponentsample.reddit.ServiceLocator

class RedditActivity : AppCompatActivity() {
    lateinit var binding : ActivityRedditBinding
    private set


    private val model : SubRedditViewModel by viewModels {
        object : AbstractSavedStateViewModelFactory(this,null){
            override fun <T : ViewModel?> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                val repoTypeParam = intent.getIntExtra(KEY_REPOSITORY_TYPE,0)
                val repoType = RedditPostRepository.Type.values()[repoTypeParam]
                val repo = ServiceLocator.instance(this@RedditActivity).getRepository(repoType)

                @Suppress("UNCHECKED_CAST")
                return SubRedditViewModel(repo,handle) as T
            }
        }
    }


    private lateinit var adapter : PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRedditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initSwipeToRefresh()
        initSearch()
    }

    private fun initAdapter(){
        val glide = GlideApp.with(this)
        adapter = PostsAdapter(glide)
        binding.list.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PostsLoadStateAdapter(adapter),
            footer = PostsLoadStateAdaprer(adapter)
        )


        lifecycleScope.launchWhenCreated{
            model.posts.collectLatest{
                adapter.submitData(it)
            }
        }

        lifecycleScope.lauchWhenCreated{
            adapter.loadStateFlow
                .asMergedLoadStates()
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect{ binding.list.scrollToPosition(0) }
        }

    }

    private fun initSwipeToRefresh(){
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }

    }

    private fun initSearch(){
        binding.input.setOnEditorActionListener{
            _,actionId,_ ->
            if(actionId == EditorInfo.IME_ACTION_GO){
                updateSubredditFromInput()
                true
            }
            else{
                false
            }
        }


        binding.input.setOnKeyListener{
            _,keycode,event ->
        }

    }
}