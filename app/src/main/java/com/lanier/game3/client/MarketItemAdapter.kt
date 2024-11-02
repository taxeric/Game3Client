package com.lanier.game3.client

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.lanier.game3.client.databinding.ItemMarketBinding
import com.lanier.game3.client.model.MarketModel
import com.lanier.game3.client.widget.rv.BaseRvAdapter
import com.lanier.game3.client.widget.rv.BaseVH

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/2 00:20
 */
class MarketItemAdapter : BaseRvAdapter<MarketModel, MarketItemHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): MarketItemHolder {
        return MarketItemHolder(
            ItemMarketBinding.inflate(inflater, parent, false)
        )
    }
}

class MarketItemHolder(
    private val binding: ItemMarketBinding
) : BaseVH<MarketModel>(binding) {

    override fun bind(data: MarketModel) {
        binding.model = data
        data.itemId?.let { mid ->
            val nid = mid.toString().replace("100728", "")
            val picUrl = "http://192.168.31.52:8080/resources/$nid/crop.png"
            binding.ivPic.load(picUrl)
        }
    }
}