package com.pedroagostini.appbooks.data

import com.pedroagostini.appbooks.data.model.Book

sealed class BooksResult {
    class Success(val books: List<Book>) : BooksResult()
}