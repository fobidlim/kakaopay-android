package com.fobidlim.kakaypay.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.fobidlim.kakaypay.databinding.ItemMediaBinding
import com.fobidlim.kakaypay.models.Media

class MediaViewHolder(
    private val binding: ItemMediaBinding,
    private val delegate: Delegate? = null
) : RecyclerView.ViewHolder(binding.root) {

    interface Delegate {
        fun mediaViewHolderItemClick(holder: MediaViewHolder, media: Media)
    }

    fun bindData(data: Media) {
        binding.media = data

        itemView.setOnClickListener {
            delegate?.mediaViewHolderItemClick(this, data)
        }
    }
}