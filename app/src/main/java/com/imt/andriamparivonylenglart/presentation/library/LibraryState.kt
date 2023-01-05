package com.imt.andriamparivonylenglart.presentation.library

import com.imt.andriamparivonylenglart.domain.Book

data class LibraryState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean
)