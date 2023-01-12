package com.example.androidallcomponentsample.reddit.repository
import androidx.paging.PagingData
import com.example.androidallcomponentsample.reddit.vo.RedditPost
import kotlinx.coroutines.flow.Flow

interface RedditPostRepository {

fun postsOfSubreddit(
    subreddit : String,
    pageSize : Int,
) : Flow<PagingData<RedditPost>>

enum class Type {
    IN_MEMORY_BY_ITEM,
    IN_MEMORY_BY_PAGE,
    DB
}

}