package com.fobidlim.kakaypay.ui.adapters

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.databinding.ItemMediaBinding
import com.fobidlim.kakaypay.models.Media
import com.fobidlim.kakaypay.ui.viewholders.MediaViewHolder

class MediaAdapter(
    private val delegate: Delegate? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val medias = ArrayList<Media>()

    interface Delegate : MediaViewHolder.Delegate

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        DataBindingUtil.inflate<ItemMediaBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_media,
            parent,
            false
        ).let {
            MediaViewHolder(it, delegate)
        }

    override fun getItemCount(): Int = medias.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as MediaViewHolder).bindData(medias[position])

    fun updateData(medias: MutableList<Media>) =
        this.medias.addAll(medias)
            .apply {
                notifyDataSetChanged()
            }
}