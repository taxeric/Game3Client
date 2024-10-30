package com.lanier.game3.client

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.lanier.game3.client.databinding.ActivityLoginBinding
import com.lanier.game3.client.ext.startAct
import com.lanier.game3.client.ext.toast

class LoginActivity : AppCompatActivity() {

    private val viewmodel by viewModels<LoginViewModel>()
    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.viewmodel = viewmodel
        binding.clickDelegate = Click()
    }

    inner class Click {

        fun onClickLogin() {
            viewmodel.login(
                onLoginSuccess = {
                    startAct<MainActivity>()
                    finish()
                },
                onLoginFailed = {
                    this@LoginActivity.toast(it)
                }
            )
        }
    }
}