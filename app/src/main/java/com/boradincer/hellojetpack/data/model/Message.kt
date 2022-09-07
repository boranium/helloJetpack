package com.boradincer.hellojetpack.data.model

data class Message(
    val contact: String,
    val message: String,
    val time: String
) {
    fun fromMe(): Boolean {
        return contact == "Me"
    }
}
