package com.lanier.game3.client.widget.rv

interface OnItemSelectListener<T> {

    fun onItemSelected(data: T, position: Int, selected: Boolean)
}