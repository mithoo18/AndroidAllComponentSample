package com.example.androidallcomponentsample.reddit

import android.app.Application
import android.content.Context
import androidx.annotation.VisibleForTesting
import com.example.androidallcomponentsample.reddit.api.RedditApi
import com.example.androidallcomponentsample.reddit.repository.RedditPostRepository
import com.example.androidallcomponentsample.reddit.repository.inDb.DbRedditPostRepository
import com.example.androidallcomponentsample.reddit.repository.inMemory.byItem.InMemoryByItemRepository
import com.example.androidallcomponentsample.reddit.repository.inMemory.byPage.InMemoryByPageKeyRepository

interface ServiceLocator {
    companion object{
        private val LOCK = Any()
        private var instance : ServiceLocator? = null

        fun instance(context : Context) : ServiceLocator{
            synchronized(LOCK){
                if(instance == null){
                    instance = DefaultServiceLocator(
                        app = context.applicationContext as Application,
                        useInMemoryDb = false
                    )
                }
                return instance!!
            }
        }

        @VisibleForTesting
        fun swap(locator: ServiceLocator){
            instance = locator
        }


        fun getRepository(type: RedditPostRepository.Type): RedditPostRepository

        fun getRedditApi(): RedditApi
    }


    open class DefaultServiceLocator(
        val app : Application,
        val useInMemoryDb : Boolean
    ) : ServiceLocator {

        private val api by lazy {
            RedditApi.create()
        }


        override fun getRepository(type: RedditPostRepository.Type): RedditPostRepository {
            return when (type) {
                RedditPostRepository.Type.IN_MEMORY_BY_ITEM -> InMemoryByItemRepository(
                    redditApi = getRedditApi()
                )
                RedditPostRepository.Type.IN_MEMORY_BY_PAGE -> InMemoryByPageKeyRepository(
                    redditApi = getRedditApi()
                )
                RedditPostRepository.Type.DB -> DbRedditPostRepository(
                    db = db,
                    redditApi = getRedditApi()
                )
            }
        }

        override fun getRedditApi(): RedditApi = api
    }