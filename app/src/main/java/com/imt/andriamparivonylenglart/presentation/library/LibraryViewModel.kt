package com.imt.andriamparivonylenglart.presentation.library


import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imt.andriamparivonylenglart.repository.HenriPotierApi
import com.imt.andriamparivonylenglart.util.RetrofitHelper
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