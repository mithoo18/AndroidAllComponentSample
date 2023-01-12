package com.example.androidallcomponentsample.reddit.repository.inMemory.byItem

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.androidallcomponentsample.reddit.api.RedditApi
import com.example.androidallcomponentsample.reddit.vo.RedditPost
import java.io.IOException
import androidx.paging.PagingSource.LoadParams.Append
import androidx.paging.PagingSource.LoadParams.Prepend
import androidx.paging.PagingSource.LoadResult.Page

class ItemKeyedSubredditPagingSource(
    private val redditApi: RedditApi,
    private val subredditName: String
) : PagingSource<String, RedditPost>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditPost> {
        return try {
            val items = redditApi.getTop(
                subreddit = subredditName,
                after = if (params is Append) params.key else null,
                before = if (params is Prepend) params.key else null,
                limit = params.loadSize
            ).data.children.map { it.data }

            Page(
                data = items,
                prevKey = items.firstOrNull()?.name,
                nextKey = items.lastOrNull()?.name
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, RedditPost>): String? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestItemToPosition(anchorPosition)?.name
        }
    }
}