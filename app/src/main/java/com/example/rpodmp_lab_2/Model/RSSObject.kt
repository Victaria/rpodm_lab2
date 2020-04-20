package com.example.rpodmp_lab_2.Model

data class RSSObject(
    val status: String,
    val feed: Feed,
    val items: List<Item>
)