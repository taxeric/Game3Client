package com.lanier.game3.client.widget.rv

interface OnItemClickListener<T> {

    fun onItemClick(t: T, position: Int)
}