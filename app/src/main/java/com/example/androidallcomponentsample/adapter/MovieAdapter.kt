package com.example.androidallcomponentsample.adapter

import com.example.androidallcomponentsample.model.Result
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidallcomponentsample.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class MovieAdapter(private val context: Context,
                   private val resultList: List<Result>?) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(resultList?.get(position))
    }

    override fun getItemCount(): Int {
        return resultList?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row,
            parent, false)
        return MyViewHolder(view)
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(result: Result?) {
            itemView.movie_title.text = result?.title
            val posterUri = Uri.parse("http://image.tmdb.org/t/p/w185/").buildUpon()
                .appendEncodedPath(result?.posterPath)
                .build()
            itemView.progress_bar.visibility = View.VISIBLE
            Picasso.with(itemView.context).load(posterUri.toString())
                .into(itemView.image_view_movie, object : Callback {

                    override fun onError() {
                        //Show Error here
                    }

                    override fun onSuccess() {
                        itemView.progress_bar.visibility = View.GONE
                    }

                })
        }
    }
}