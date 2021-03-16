package com.pedroagostini.appbooks.data

import com.pedroagostini.appbooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "VUSUda4pocGq0fZAbzmQWo3GEv4OyydF",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}