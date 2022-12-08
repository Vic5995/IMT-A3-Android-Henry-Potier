package com.imt.andriamparivonylenglart

data class LibraryState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean
)