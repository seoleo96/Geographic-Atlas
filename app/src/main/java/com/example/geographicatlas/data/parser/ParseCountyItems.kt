package com.example.geographicatlas.data.parser

interface ParseCountyItems {
    fun parseCapital(value: List<String>?): String
    fun parseContinent(value: List<String>?): String
    fun parseCurrency(value: Any?): String
}

class ParseCountyItemsImpl : ParseCountyItems {

    override fun parseCapital(value: List<String>?): String {
        return if (value != null && value.isNotEmpty()) {
            value[0]
        } else {
            "Not Found!"
        }
    }

    override fun parseContinent(value: List<String>?): String {
        return if (value != null && value.isNotEmpty()) {
            value[0]
        } else {
            "Not Found!"
        }
    }

    override fun parseCurrency(value: Any?): String {
        return if (value != null) {
            val currencies = value.toString()
            val currencyWithoutJson = currencies.substring(currencies.indexOf("=") + 1, currencies.lastIndexOf("}"))
            val contains = currencyWithoutJson.contains("symbol")
            val currency = currencies.substring(currencies.indexOf("{") + 1, currencies.indexOf("="))
            val name = currencyWithoutJson.substring(
                currencyWithoutJson.indexOf("=") + 1,
                currencyWithoutJson.indexOf(if (contains) "," else "}")
            )
            val symbol = if (contains) currencyWithoutJson.substring(
                currencyWithoutJson.indexOf("symbol=") + 7,
                currencyWithoutJson.indexOf("}")
            ) else ""
            "$name${if (symbol.isNotEmpty()) "($symbol)" else ""} ${if (currency.isNotEmpty()) "($currency)" else ""}"
        } else {
            "Not found!"
        }
    }
}