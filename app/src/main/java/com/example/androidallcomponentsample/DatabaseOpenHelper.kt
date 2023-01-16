package com.example.androidallcomponentsample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
internal class DatabaseOpenHelper(private val mContext : Context)
    : SQLiteOpenHelper(mContext,NAME,null,VERSION){

        companion object{
            const val  TABLE_NAME = "artists"
            const val ARTIST_NAME = "name"
            private const val ID = "_id"
            val columns = arrayOf(ID, ARTIST_NAME)

            const val CREARE_CMD = (
                    "CREATE TABLE artists (" + ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ARTIST_NAME + " TEXT NOT NULL )")
            const val NAME = "artist_db"
            const val VERSION = 1

        }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREARE_CMD)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun deleteDatabase(){
        mContext.deleteDatabase(NAME)
    }





}