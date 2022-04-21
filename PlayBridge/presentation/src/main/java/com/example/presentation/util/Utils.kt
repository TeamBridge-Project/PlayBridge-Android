package com.example.presentation.util

import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date

fun hash(msg: String, algorithm: String): String {
    val bytes = msg.toByteArray()
    val md = MessageDigest.getInstance(algorithm)
    val digest = md.digest(bytes)
    return digest.fold("", { str, it -> str + "%02x".format(it) })
}

fun String.sha256(): String {
    return hash(this, "SHA-256")
}

fun String.toDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.parse(this)
}
