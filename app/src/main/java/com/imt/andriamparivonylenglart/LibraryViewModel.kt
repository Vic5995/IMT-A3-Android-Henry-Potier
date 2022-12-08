package com.imt.andriamparivonylenglart


import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LibraryViewModel : ViewModel() {

    val state = MutableLiveData<LibraryState>()

    fun loadBooks() {

        val service: HenriPotierApi = RetrofitHelper.getInstance().create(HenriPotierApi::class.java)

        state.postValue(LibraryState(emptyList(), true))

        viewModelScope.launch(context = Dispatchers.Main) {
            val books = withContext(Dispatchers.IO) {
                service.getBooks()
            }
            state.postValue(LibraryState(books, false))
        }
    }
}