package com.jrsoftware.tool.spending.management.ui.base
open class BaseState(val msg: String? = null) {
    fun getTitle(): String =
        if (msg != null) {
            val list = msg.split("|")
            if (list.size == 2) list[0] else ""
        } else ""

    fun getError(): String = if (msg != null) {
        val list = msg.split("|")
        if (list.size == 2) list[1] else ""
    } else ""
}