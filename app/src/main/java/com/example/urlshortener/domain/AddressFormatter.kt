package com.example.urlshortener.domain

class AddressFormatter {
    fun format(address: String): String {
        var formattedAddress = address
        if (address.subSequence(0,3) != "http") {
            formattedAddress = "http://" + address
        }
        return formattedAddress
    }
}