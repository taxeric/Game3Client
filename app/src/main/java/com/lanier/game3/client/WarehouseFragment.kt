package com.lanier.game3.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lanier.game3.client.databinding.FragmentWarehouseBinding
import com.lanier.game3.client.model.ItemType

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/12/11 02:30
 */
class WarehouseFragment : Fragment() {

    private val binding by lazy {
        FragmentWarehouseBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<WarehouseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type = arguments?.getInt("item_type", ItemType.Seed.type) ?: ItemType.Seed.type
        viewModel.type = when (type) {
            ItemType.Seed.type -> ItemType.Seed
            ItemType.Crop.type -> ItemType.Crop
            else -> null
        }
    }
}