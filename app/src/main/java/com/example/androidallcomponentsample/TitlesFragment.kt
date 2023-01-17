package com.example.androidallcomponentsample

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment


class TitlesFragment : ListFragment() {
    private var mListener : ListSelectionListener? = null
    private var mCurrIdx = ListView.INVALID_POSITION

    companion object {
        private const val TAG ="TitlesFragment"
    }

    fun unCheckSelection(){
        if(listView.checkedItemCount > 0){
            listView.setItemChecked(listView.checkedItemPosition, false)
        }
        mCurrIdx = ListView.INVALID_POSITION
    }


    override fun onListItemClick(l: ListView, v: View, pos: Int, id: Long) {
        if (mCurrIdx != pos) {
            mCurrIdx = pos
            // Indicates the selected item has been checked
            l.setItemChecked(mCurrIdx, true)
            mListener?.onListSelection(mCurrIdx)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as? ListSelectionListener
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        retainInstance = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listAdapter = ArrayAdapter(
            activity as Context,
            R.layout.title_item,resources.getStringArray(R.array.Titles)
        )
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
    }

    override fun onStart() {
        Log.i(TAG, "${javaClass.simpleName}: entered onStart()")
        super.onStart()
    }

    override fun onResume() {
        Log.i(TAG, "${javaClass.simpleName}: entered onResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.i(TAG, "${javaClass.simpleName}: entered onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.i(TAG, "${javaClass.simpleName}: entered onStop()")
        super.onStop()
    }

    override fun onDetach() {
        Log.i(TAG, "${javaClass.simpleName}: entered onDetach()")
        super.onDetach()
    }

    override fun onDestroy() {
        Log.i(TAG, "${javaClass.simpleName}: entered onDestroy()")
        super.onDestroy()
    }

    override fun onDestroyView() {
        Log.i(TAG, "${javaClass.simpleName}: entered onDestroyView()")
        super.onDestroyView()
    }

}