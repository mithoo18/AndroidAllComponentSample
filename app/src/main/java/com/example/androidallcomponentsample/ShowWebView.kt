package com.example.androidallcomponentsample
import android.app.ProgressDialog
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity


class ShowWebView : AppCompatActivity() {
lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView1)

        startWebView("http://www.androidtoppers.com")

    }

    private fun startWebView(url: String) {

        webView.webViewClient = object : WebViewClient() {
            var progressDialog: ProgressDialog? = null

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onLoadResource(view: WebView, url: String) {
                if (progressDialog == null) {
                    progressDialog = ProgressDialog(this@ShowWebView)
                    progressDialog!!.setMessage("Loading...")
                    progressDialog!!.show()
                }
            }

            override fun onPageFinished(view: WebView, url: String) {
                try {
                    if (progressDialog!!.isShowing) {
                        progressDialog!!.dismiss()
                        progressDialog = null
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }
        }

        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
    }

    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        } else{

            super.onBackPressed()
        }

    }

}