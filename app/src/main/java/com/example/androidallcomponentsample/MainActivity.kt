package com.example.androidallcomponentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.androidallcomponentsample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), MvpView {

    private lateinit var binding: ActivityMainBinding

    private val mvpPresenter: MvpPresenter by lazy { MvpPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding) {
            setContentView(root)

            btnSubmit.setOnClickListener {
                val email = edtxName.text.toString()
                val name = edtxEmail.text.toString()
                mvpPresenter.updateUserInfo(name, email)
            }
        }
    }

    override fun updateUserInfoTextView(info: String?) {
        binding.txtUserinfo.text = info
    }
}