package com.example.androidallcomponentsample

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context : Context?) : SQLiteOpenHelper(
    context,DATABASE_NAME,null,DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Note.Companion.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS" + Note.Companion.TABLE_NAME)
        onCreate(db)
    }

    fun insertNote(note : String) : Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(Note.Companion.COLUMN_NOTE,note)
        val id = db.insert(Note.Companion.TABLE_NAME,null,values)
        db.close()
        return id
    }

    fun getNote(
        id : Long
    ) : Note {
        val db = this.readableDatabase
        val cursor = db.query(
            Note.Companion.TABLE_NAME,
            arrayOf(
                Note.Companion.COLUMN_ID,
                Note.Companion.COLUMN_NOTE,
            ),
            Note.Companion.COLUMN_ID + "=?",
            arrayOf(id.toString()),
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        val note = Note(
            cursor!!.getInt(cursor.getColumnIndex(Note.Companion.COLUMN_ID)),
            cursor.getString(cursor.getColumnIndex(Note.Companion.COLUMN_NOTE))
        cursor.close()
        return note
        )

    }



}