package com.example.androidallcomponentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidallcomponentsample.databinding.ActivityMainBinding
import com.example.androidallcomponentsample.reddit.repository.inDb.DbRedditPostRepository
import com.example.androidallcomponentsample.reddit.ui.RedditActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.withDatabase.setOnClickListener{
            show(RedditPostRepository.Type.DB)
        }

        binding.networkOnly.setOnClickListener{
            show(RedditPostRepository.Type_IN_MEMORY_BY_ITEM)
        }

        binding.networkOnlyWithPageKeys.setOnClickListener{
            show(RedditPostRepository.Type_IN_MEMORY_BY_PAGE)
        }

    }

    private fun show(type : DbRedditPostRepository.Type){
        val Intent = RedditActivity.intentFor(this,type)
        startActivity(intent)
    }
}