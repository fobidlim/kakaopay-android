package com.fobidlim.kakaypay.ui.adapters

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.models.Media
import com.fobidlim.kakaypay.ui.viewholders.MediaViewHolder

class MediaAdapter(
    private val delegate: Delegate? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val medias = MutableList<Media>()

    interface Delegate : MediaViewHolder.Delegate

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        LayoutInflater.from(parent.context).let {
            DataBindingUtil.inflate<ItemMediaBinding>(it, R.layout.item_media, parent, false).apply {
                MediaViewHolder(this, delegate)
            }
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