package com.example.androidallcomponentsample

class MainPresenter {

    private var mainView : MainView? = null

    fun attachView(mainView: MainView?){
        this.mainView = mainView
    }

    fun detachView(){
        mainView = null
    }

    fun showMessage(){
        mainView
    }
}