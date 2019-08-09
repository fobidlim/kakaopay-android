package com.fobidlim.kakaypay.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.fobidlim.kakaypay.databinding.ItemCommentBinding
import com.fobidlim.kakaypay.models.Comment

class CommentViewHolder(
    private val binding: ItemCommentBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: Comment) {
        binding.comment = data
    }
}