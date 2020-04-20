package com.example.rpodmp_lab_2.Model

data class Item(
    val title: String,
    val pubDate: String,
    val link: String,
    val guid: String,
    val author: String,
    val thumbnail: String,
    val description: String,
    val content: String,
    val enclosure: Enclosure,
    val categories: List<String>
)

data class Enclosure(
    val link: String,
    val type: String,
    val length: Int
)