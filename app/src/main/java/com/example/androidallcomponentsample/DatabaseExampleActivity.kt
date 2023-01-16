package com.example.androidallcomponentsample

import android.app.ListActivity
import android.content.ContentValues
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import android.widget.Button
import android.widget.SimpleCursorAdapter
import android.widget.TextView

class DatabaseExampleActivity : ListActivity(){
    private lateinit var mDbHelper : DatabaseOpenHelper
    private lateinit var mAdapter : SimpleCursorAdapter
    private var mCursor : Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDbHelper = DatabaseOpenHelper(this)
        clearAll()
        insertArtists()

        mCursor = readArtists()

        mAdapter = SimpleCursorAdapter(
            this, R.layout.list_layout, mCursor,
            DatabaseOpenHelper.columns, intArrayOf(R.id._id, R.id.name),
            0
        )

        listAdapter = mAdapter
    }

    fun onClick(v : View){
        fix()
        mCursor = readArtists()
        mAdapter.changeCursor(mCursor)
    }


    private fun insertArtists(){
        val values = ContentValues()

        values.put(DatabaseOpenHelper.ARTIST_NAME, "Frank Sinatra")
        mDbHelper.writableDatabase.insert(DatabaseOpenHelper.TABLE_NAME,null,values)
        values.clear()

        values.put(DatabaseOpenHelper.ARTIST_NAME,"Lady Gaga")
        mDbHelper.writableDatabase.insert(DatabaseOpenHelper.TABLE_NAME,null,values)
        values.clear()

        values.put(DatabaseOpenHelper.ARTIST_NAME,"Jawnry Cash")
        mDbHelper.writableDatabase.insert(DatabaseOpenHelper.TABLE_NAME,null,values)
        values.clear()

        values.put(DatabaseOpenHelper.ARTIST_NAME,"Ludwig Van Beethoven")
        mDbHelper.writableDatabase.insert(DatabaseOpenHelper.TABLE_NAME,null,values)

    }
    private fun readArtists(): Cursor? {
        return mDbHelper.writableDatabase.query(
            DatabaseOpenHelper.TABLE_NAME,
            DatabaseOpenHelper.columns,null, arrayOf(),null,null,null
        )

    }

    private fun fix() {

        // Sorry Lady Gaga :-(
        mDbHelper.writableDatabase.delete(
            DatabaseOpenHelper.TABLE_NAME,
            DatabaseOpenHelper.ARTIST_NAME + "=?",
            arrayOf("Lady Gaga")
        )

        // fix the Man in Black
        val values = ContentValues()
        values.put(DatabaseOpenHelper.ARTIST_NAME, "Johnny Cash")

        mDbHelper.writableDatabase.update(
            DatabaseOpenHelper.TABLE_NAME, values,
            DatabaseOpenHelper.ARTIST_NAME + "=?",
            arrayOf("Jawny Cash")
        )
    }

    private fun clearAll() {
        mDbHelper.writableDatabase.delete(DatabaseOpenHelper.TABLE_NAME,null,null)
    }

    override fun onDestroy() {
        mDbHelper.writableDatabase.close()
        mDbHelper.deleteDatabase()
        super.onDestroy()
    }
}