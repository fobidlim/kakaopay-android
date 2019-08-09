package com.fobidlim.kakaypay.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.databinding.ItemCommentBinding
import com.fobidlim.kakaypay.models.Comment
import com.fobidlim.kakaypay.ui.viewholders.CommentViewHolder

class CommentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val comments = ArrayList<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        DataBindingUtil.inflate<ItemCommentBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_comment,
            parent,
            false
        ).run {
            CommentViewHolder(this)
        }

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as CommentViewHolder).bindData(comments[position])

    fun updateData(comments: MutableList<Comment>) =
        this.comments.addAll(comments)
            .apply {
                notifyDataSetChanged()
            }
}