package com.lanier.game3.client.ext

import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

inline fun <reified T> Context.startAct(
    noinline block: (Intent.() -> Unit)? = null,
) {
    val intent = Intent(this, T::class.java).apply {
        block?.invoke(this)
    }
    startActivity(intent)
}
