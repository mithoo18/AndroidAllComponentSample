package com.example.androidallcomponentsample.reddit.repository.inMemory.byItem

import com.example.androidallcomponentsample.reddit.api.RedditApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.androidallcomponentsample.reddit.repository.RedditPostRepository

class InMemoryByItemRepository(private val redditApi: RedditApi) : RedditPostRepository {
    override fun postsOfSubreddit(subReddit: String, pageSize: Int) = Pager(
        PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        )
    ) {
        ItemKeyedSubredditPagingSource(
            redditApi = redditApi,
            subredditName = subReddit
        )
    }.flow
}
