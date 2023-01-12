package com.example.androidallcomponentsample.reddit.repository.inDb

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bumptech.glide.load.engine.Initializable
import com.example.androidallcomponentsample.reddit.db.RedditDb
import com.example.androidallcomponentsample.reddit.db.RedditPostDao
import com.example.androidallcomponentsample.reddit.db.SubredditRemoteKeyDao
import com.example.androidallcomponentsample.reddit.vo.RedditPost


@OptIn(ExperimentalPagingApi::class)
class PageKeyedRemoteMediator(
    private val db : RedditDb,
    private val subredditName : String
    ) : RemoteMediator<Int, RedditPost>() {
        private val postDao : RedditPostDao = db.post()
        private val remoteKeyDao : SubredditRemoteKeyDao = db.remoteKey()

    override suspend fun initialize() : InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state : PagingState<Int,RedditPost>
    ) : MediatorResult {
     try{
         val loadKey = when(loadType){
             REFRESH  -> null
             PREPEND -> return MediatorResult.Success(
                 endOfPaginationReached = true
             )
             APPEND ->{
                 val remoteKey = db.withTransaction{
                     remoteKeyDao.remote
                 }

             }

         }
     }catch (e: Exception){

     }

    }
    }