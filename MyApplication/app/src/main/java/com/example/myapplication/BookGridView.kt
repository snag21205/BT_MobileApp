package com.example.myapplication

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

class BookGridView (val activity: Activity,val listBook: List<BookModel>) : ArrayAdapter<BookModel>(activity, R.layout.layout_item, listBook) {
    override fun getCount(): Int {
        return listBook.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = activity.layoutInflater.inflate(R.layout.layout_item, parent, false)

        val imgBook = view.findViewById<ImageView>(R.id.imgBook)
        if (listBook[position].imgBook != 0) {
            imgBook.setImageResource(listBook[position].imgBook)
        } else {
            Glide.with(activity).load(listBook[position].urlBook).into(imgBook)
        }
        return view


    }


}