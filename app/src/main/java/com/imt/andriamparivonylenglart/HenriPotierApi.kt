package com.imt.andriamparivonylenglart

import retrofit2.http.GET

interface HenriPotierApi {
    @GET("/books")
    suspend fun  getBooks(): List<Book>
}