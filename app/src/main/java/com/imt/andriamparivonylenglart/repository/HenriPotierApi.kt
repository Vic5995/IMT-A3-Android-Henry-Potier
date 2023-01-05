package com.imt.andriamparivonylenglart.repository

import com.imt.andriamparivonylenglart.domain.Book
import retrofit2.http.GET

interface HenriPotierApi {
    @GET("/books")
    suspend fun  getBooks(): List<Book>
}