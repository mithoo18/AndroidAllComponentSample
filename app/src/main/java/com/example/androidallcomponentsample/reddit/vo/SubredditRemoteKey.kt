package com.example.androidallcomponentsample.reddit.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "remote_keys")
data class SubredditRemoteKey(
    @PrimaryKey
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val subreddit : String,
    val nextPageKey : String?
)

