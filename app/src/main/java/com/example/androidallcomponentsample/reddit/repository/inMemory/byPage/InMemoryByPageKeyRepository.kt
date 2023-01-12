package com.example.androidallcomponentsample.reddit.repository.inMemory.byPage

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.androidallcomponentsample.reddit.api.RedditApi
import com.example.androidallcomponentsample.reddit.repository.RedditPostRepository
import com.example.androidallcomponentsample.reddit.vo.RedditPost
import kotlinx.coroutines.flow.Flow

class InMemoryByPageKeyRepository(private val redditApi: RedditApi) : RedditPostRepository {
    override fun postsOfSubreddit(subReddit: String, pageSize: Int) = Pager(
        PagingConfig(pageSize)
    ) {
        PageKeyedSubredditPagingSource(
            redditApi = redditApi,
            subredditName = subReddit
        )
    }.flow
}