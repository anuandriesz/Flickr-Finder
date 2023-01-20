package com.assignment.flickerfinder.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.assignment.flickerfinder.R
import com.assignment.flickerfinder.network.responses.Photo

class AdapterPhotoList(
    private var flickerPhotoLists: List<Photo>?
) : RecyclerView.Adapter<AdapterPhotoList.ViewHolder>() {
    private var mListener: PhotoAdapterActionListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_photo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = flickerPhotoLists?.get(position)
        holder.txtPhotoName.text = item?.title
        holder.constraintLut.setOnClickListener {
            mListener?.photoOnClick(item)
        }
    }

    override fun getItemCount(): Int = flickerPhotoLists?.size!!

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtPhotoName: TextView = view.findViewById(R.id.photoName)
        val constraintLut: ConstraintLayout = view.findViewById(R.id.lutView)
        override fun toString(): String {
            return super.toString() + " ${txtPhotoName.text}"
        }
    }

    fun cardOnClickListener(listener: PhotoAdapterActionListener) {
        mListener = listener
    }

    interface PhotoAdapterActionListener {
        fun photoOnClick(item: Photo?)
    }
}