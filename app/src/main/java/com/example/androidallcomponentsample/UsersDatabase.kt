package com.example.androidallcomponentsample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Note::class), version = 2, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    companion object{
        val MIGRATION_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase){
            }
        }

        fun createDatabase(appContext : Context) : UsersDatabase{
            return Room.databaseBuilder(
                appContext,
                UsersDatabase::class.java,
                "notes_db"
            ).addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}