package com.example.geographicatlas.ui.adapter

interface DelegateItem {

    fun content(): Any

    fun id(): Int

    fun compareToOther(other: DelegateItem): Boolean

    fun payload(other: DelegateItem): Payloadable = Payloadable.None

    interface Payloadable {
        object None : Payloadable
    }
}