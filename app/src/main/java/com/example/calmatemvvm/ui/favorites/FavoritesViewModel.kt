package com.example.calmatemvvm.ui.favorites


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.calmatemvvm.app.AppViewModel
import com.example.calmatemvvm.model.Quote
import com.example.calmatemvvm.ui.common.BaseViewModel

class FavoritesViewModel(private val appViewModel: AppViewModel) : BaseViewModel() {

    private val _favoriQuotesList = MutableLiveData<List<Quote>>()
    val favoriQuotesList: LiveData<List<Quote>> = _favoriQuotesList

    fun fetchFavoriteQuotes() {
        val user = appViewModel.getUser()
        val userId = appViewModel.dbHelper.getUserIdByEmail(user!!.email)

        val favoriteQoutes = appViewModel.dbHelper.getFavoritesQuotesByUserId(userId)
        _favoriQuotesList.value = favoriteQoutes
    }
}