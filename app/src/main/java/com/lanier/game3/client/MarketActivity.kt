package com.lanier.game3.client

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.lanier.game3.client.databinding.ActivityMarketBinding
import com.lanier.game3.client.model.MarketModel
import com.lanier.game3.client.model.MarketType
import com.lanier.game3.client.widget.rv.EqualDivider
import com.lanier.game3.client.widget.rv.OnItemClickListener
import com.lanier.game3.client.widget.rv.OnLoadMoreListener

class MarketActivity : AppCompatActivity() {

    private val viewmodel by viewModels<MarketViewModel>()

    private val adapter by lazy {
        MarketItemAdapter().apply {
            onItemClickListener = object : OnItemClickListener<MarketModel> {
                override fun onItemClick(t: MarketModel, position: Int) {
                }
            }

            onLoadMoreListener = object : OnLoadMoreListener {
                override fun onLoadMore() {
                    viewmodel.load(false)
                }
            }
        }
    }

    private val binding: ActivityMarketBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_market)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.recyclerview.adapter = adapter
        ContextCompat.getDrawable(this, R.drawable.equal_divider)?.let { drawable ->
            binding.recyclerview.addItemDecoration(
                EqualDivider(drawable, 3)
            )
        }

        viewmodel.marketItemsLiveData.observe(this) { triple ->
            if (triple.first == 1) {
                adapter.data = triple.second
            } else {
                adapter.addData(triple.second)
            }
            adapter.isEnd = triple.third
        }

        viewmodel.type = MarketType.Seed
        viewmodel.load(true)
    }
}