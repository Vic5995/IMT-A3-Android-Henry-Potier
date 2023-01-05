package com.imt.andriamparivonylenglart.domain


data class Book(
    val isbn: String,
    val title: String,
    val price: String,
    val cover: String,
    val synopsis: List<String>
    )