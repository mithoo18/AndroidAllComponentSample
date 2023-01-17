package com.example.androidallcomponentsample

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class QuotesFragment : Fragment() {

    companion object{
        private const val TAG = "QuotedFragment"
        private lateinit var mQuoteArray: Array<String>
    }

    private lateinit var mQuoteView : TextView
    private var mCurrIdx = ListView.INVALID_POSITION

    internal fun showQuoteAtIndex(index : Int){
        if(index >= 0 && index < mQuoteArray.size) {
            mQuoteView.text = mQuoteArray[index]
            mCurrIdx = index
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mQuoteView =requireActivity().findViewById(R.id.quoteView)
        mQuoteArray = resources.getStringArray(R.array.Quotes)
        showQuoteAtIndex(mCurrIdx)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.quote_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.detail_menu_item_main -> {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "This action provided by the QuoteFragment",
                    Toast.LENGTH_SHORT
                ).show()
                return true
            }

            R.id.detail_menu_item_secondary -> {
                Toast.makeText(
                   requireActivity().applicationContext,
                    "This action is also provided by the QuoteFragment",
                    Toast.LENGTH_SHORT
                ).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quotes, container, false)
    }


}