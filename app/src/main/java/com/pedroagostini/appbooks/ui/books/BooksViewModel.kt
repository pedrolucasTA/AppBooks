package com.pedroagostini.appbooks.ui.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedroagostini.appbooks.data.ApiService
import com.pedroagostini.appbooks.data.model.Book
import com.pedroagostini.appbooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks() {

//        booksLiveData.value = createFakeBooks()

        ApiService.service.getBooks().enqueue(object : Callback<BookBodyResponse> {

            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                if (response.isSuccessful) {
                    val books: MutableList<Book> = mutableListOf()

                    response.body()?.let { bookBodyResponse ->
                        for (result in bookBodyResponse.bookResults) {
                            val book = Book(
                                title = result.bookDetailResponses[0].title,
                                author = result.bookDetailResponses[0].author,
                                description = result.bookDetailResponses[0].description
                            )

                            books.add(book)
                        }
                    }

                    booksLiveData.value = books
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {

            }
        })

    }

//    fun createFakeBooks(): List<Book>{
//        return listOf(
//            Book("Sapiens", "Yuval Harari", "description"),
//            Book("1822", "Laurentino Gomes", "description"),
//            Book("O Senhor dos Anéis", "John Ronald Reuel Tolkien", "description")
//        )
//    }
}