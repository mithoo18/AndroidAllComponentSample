package com.example.androidallcomponentsample.reddit.repository.inDb

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.androidallcomponentsample.reddit.api.RedditApi
import com.example.androidallcomponentsample.reddit.db.RedditDb

class DbRedditPostRepository(val db : RedditDb,
val redditApi: RedditApi
) {
    @OptIn(ExperimentalPagingApi::class)
    override fun postsOfSubreddit(subReddit: String, pageSize: Int) = Pager(
        config = PagingConfig(pageSize),
        remoteMediator = PageKeyedRemoteMediator(db, redditApi, subReddit)
    ) {
        db.post().postsBySubreddit(subReddit)
    }.flow
}
