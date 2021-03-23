package com.pedroagostini.appbooks.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.pedroagostini.appbooks.data.ApiService
import com.pedroagostini.appbooks.data.BooksResult
import com.pedroagostini.appbooks.data.model.Book
import com.pedroagostini.appbooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksApiDataSource : BooksRepository {

    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        ApiService.service.getBooks().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { bookBodyResponse ->
                            for (result in bookBodyResponse.bookResults) {
                                val book = result.bookDetailResponses[0].getBookModel()
                                books.add(book)

//                                Log.i(TAG, "onResponse: Aqui, ${books[0]}")
                            }
                        }

                        booksResultCallback(BooksResult.Success(books))
                    }

                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}