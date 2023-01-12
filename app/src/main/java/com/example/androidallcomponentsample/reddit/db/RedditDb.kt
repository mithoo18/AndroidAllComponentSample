package com.example.androidallcomponentsample.reddit.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidallcomponentsample.reddit.vo.RedditPost
import com.example.androidallcomponentsample.reddit.vo.SubredditRemoteKey

@Database(
    entities = [RedditPost::class, SubredditRemoteKey::class],
    version = 1,
    exportSchema = false
)

abstract class RedditDb : RoomDatabase() {
    companion object {
        fun create(context: Context, useInMemory: Boolean): RedditDb {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, RedditDb::class.java)
            } else {
                Room.databaseBuilder(context, RedditDb::class.java, "reddit.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()

        }
    }
        abstract fun post(): RedditPostDao

        abstract fun remoteKey() : SubredditRemoteKeyDao
    }