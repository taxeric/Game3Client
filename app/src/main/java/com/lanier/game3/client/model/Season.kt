package com.lanier.game3.client.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/23 22:51
 */
sealed interface Season {

    val value: Int

    data object None : Season {
        override val value: Int
            get() = 0
    }

    data object Spring : Season {
        override val value: Int
            get() = 1
    }

    data object Summer : Season {
        override val value: Int
            get() = 2
    }

    data object Autumn : Season {
        override val value: Int
            get() = 3
    }

    data object Winter : Season {
        override val value: Int
            get() = 4
    }
}