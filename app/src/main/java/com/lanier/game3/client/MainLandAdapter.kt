package com.lanier.game3.client

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lanier.game3.client.databinding.ItemLandBinding
import com.lanier.game3.client.model.Land
import com.lanier.game3.client.widget.rv.BaseRvAdapter
import com.lanier.game3.client.widget.rv.BaseVH

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/26 01:18
 */
class MainLandAdapter : BaseRvAdapter<Land, MainLandViewHolder>() {
    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): MainLandViewHolder {
        val holder = MainLandViewHolder(
            ItemLandBinding.inflate(inflater)
        )
        return holder
    }
}

class MainLandViewHolder(
    private val binding: ItemLandBinding
) : BaseVH<Land>(binding) {
    override fun bind(data: Land) {
        binding.land = data
    }
}