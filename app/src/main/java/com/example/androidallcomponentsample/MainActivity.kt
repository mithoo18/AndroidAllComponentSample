package com.example.androidallcomponentsample
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.androidallcomponentsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var databaseHelper: DatabaseHelper
    lateinit var usersDatabase: UsersDatabase
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        usersDatabase = UsersDatabase.createDatabase(this)

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonShow = findViewById<Button>(R.id.buttonShow)

        buttonAdd.setOnClickListener {
            val note = usersDatabase.noteDao().insertAll(Note(null,"dfdfd"))
        }

        buttonShow.setOnClickListener {
            val note = usersDatabase.noteDao().getAll()
            Log.d("TAG", "notes:${notes.toString()}")
        }

    }


}
