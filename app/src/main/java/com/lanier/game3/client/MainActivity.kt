package com.lanier.game3.client

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.lanier.game3.client.databinding.ActivityMainBinding
import com.lanier.game3.client.ext.startAct
import com.lanier.game3.client.ext.toast
import com.lanier.game3.client.model.Land
import com.lanier.game3.client.widget.rv.OnItemClickListener

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val viewmodel by viewModels<MainViewModel>()

    private val adapter by lazy {
        MainLandAdapter().apply {
            onItemClickListener = object : OnItemClickListener<Land> {
                override fun onItemClick(t: Land, position: Int) {
                }
            }
        }
    }

    private val click = Click()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.recyclerview.adapter = adapter

        binding.click = click

        viewmodel.land.observe(this) {
            adapter.data = it
        }

        click.refreshLands()
    }

    inner class Click {

        fun refreshLands() {
            viewmodel.loadLands {
                toast(it)
            }
        }

        fun gotoMarket() {
            startAct<MarketActivity>()
        }
    }
}