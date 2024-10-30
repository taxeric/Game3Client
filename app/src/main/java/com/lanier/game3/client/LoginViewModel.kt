package com.lanier.game3.client

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanier.game3.client.cache.AppCacheData
import com.lanier.game3.client.model.LoginReqModel
import com.lanier.game3.client.net.APIRequester
import com.lanier.game3.client.net.handleAPIResp
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/30 22:48
 */
class LoginViewModel : ViewModel() {

    var inputAccount = ObservableField<String>("LaR10005")
    var inputPassword = ObservableField<String>("1248389474")

    private var loginJob: Job? = null

    fun login(
        onLoginSuccess: () -> Unit,
        onLoginFailed: (String) -> Unit,
    ) {
        val account = inputAccount.get()?: ""
        val password = inputPassword.get()?: ""
        if (account.isBlank() || password.isBlank()) {
            onLoginFailed.invoke("empty params")
            return
        }
        val model = LoginReqModel(
            account = account,
            password = password
        )
        val oldJob = loginJob
        loginJob = viewModelScope.launch {
            oldJob?.cancelAndJoin()
            runCatching {
                APIRequester.game3Api
                    .login(model)
                    .handleAPIResp()
            }.onSuccess {
                AppCacheData.respToken = it.token
                AppCacheData.loginUser = it.copy()
                onLoginSuccess.invoke()
            }.onFailure {
                onLoginFailed.invoke(it.message?: "Unknown message")
            }
            loginJob = null
        }
    }
}