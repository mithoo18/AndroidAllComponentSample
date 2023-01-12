package com.example.androidallcomponentsample.reddit.repository.inMemory.byItem

import com.example.androidallcomponentsample.reddit.api.RedditApi
import com.example.androidallcomponentsample.reddit.db.RedditPostDao

class ItemKeyedSubredditPagingSource (
    private val redditApi : RedditApi,
    private val subredditName : String
) : PaginSource<String,RedditPost>(){
    override suspend fun load(params: LoadParams<String>) :
    LoadResult<String,RedditPost> {
        return try{
            val items = redditApi.getTop(
                subreddit = subredditName,
                after = if(params is Append) params.key else null,
                before = if(params is Prepend) params.key else null,
                limit = params.loadSize,
            ).data.children.map {
                it.data
            }

            Page(

            )

        }
    }

}