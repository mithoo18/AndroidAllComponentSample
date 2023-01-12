package com.example.androidallcomponentsample.reddit.repository.inDb

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.androidallcomponentsample.reddit.db.RedditDb
import com.example.androidallcomponentsample.reddit.db.RedditPostDao
import com.example.androidallcomponentsample.reddit.db.SubredditRemoteKeyDao
import com.example.androidallcomponentsample.reddit.vo.RedditPost
import androidx.paging.LoadType.*
import com.bumptech.glide.load.HttpException
import com.example.androidallcomponentsample.reddit.vo.SubredditRemoteKey
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)

class PageKeyedRemoteMediator(
    private val db : RedditDb,
    private val subredditName : String
    ) : RemoteMediator<Int, RedditPost>() {
        private val postDao : RedditPostDao = db.posts()
        private val remoteKeyDao : SubredditRemoteKeyDao = db.remoteKeys()

    override suspend fun initialize() : InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RedditPost>
    ): MediatorResult {
        try {
            // Get the closest item from PagingState that we want to load data around.
            val loadKey = when (loadType) {
                REFRESH -> null
                PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                APPEND -> {
                    val remoteKey = db.withTransaction {
                        remoteKeyDao.remoteKeyByPost(subredditName)
                    }
                    if (remoteKey.nextPageKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    remoteKey.nextPageKey
                }
            }

            val data = redditApi.getTop(
                subreddit = subredditName,
                after = loadKey,
                before = null,
                limit = when (loadType) {
                    REFRESH -> state.config.initialLoadSize
                    else -> state.config.pageSize
                }
            ).data

            val items = data.children.map { it.data }

            db.withTransaction {
                if (loadType == REFRESH) {
                    postDao.deleteBySubreddit(subredditName)
                    remoteKeyDao.deleteBySubreddit(subredditName)
                }

                remoteKeyDao.insert(SubredditRemoteKey(subredditName, data.after))
                postDao.insertAll(items)
            }

            return MediatorResult.Success(endOfPaginationReached = items.isEmpty())
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}
