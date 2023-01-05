package com.imt.andriamparivonylenglart.presentation.detail

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imt.andriamparivonylenglart.repository.HenriPotierApi
import com.imt.andriamparivonylenglart.util.RetrofitHelper
import kotlinx.coroutines.launch

class BookViewModel(private val bookId : String) : ViewModel() {
    val state = MutableLiveData<BookState>()

    fun loadBook() {

        val service: HenriPotierApi = RetrofitHelper.getInstance().create(HenriPotierApi::class.java)


        viewModelScope.launch(context = Dispatchers.Main) {
            val books = withContext(Dispatchers.IO) {
                service.getBooks()
            }
            val book = books
                .filter { book -> book.isbn == bookId }[0]
            state.postValue(BookState(book))
        }
    }
}