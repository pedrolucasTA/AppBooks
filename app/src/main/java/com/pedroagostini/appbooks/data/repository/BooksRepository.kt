package com.pedroagostini.appbooks.data.repository

import com.pedroagostini.appbooks.data.BooksResult

interface BooksRepository {

    fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
}