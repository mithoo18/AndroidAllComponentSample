package com.example.androidallcomponentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(),MainView {

    private val presenter by inject<MainPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)

        findViewById<Button>(R.id.hello_button).setOnClickListener {
            presenter().showMessage()
        }

    }

    override fun showError(error : String) {

    }

    override fun showMessage(heelo : String){
        findViewById<TextView>(R.id.textView).visibility = View.VISIBLE
        findViewById<TextView>(R.id.textView).text = "hello"
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }



}