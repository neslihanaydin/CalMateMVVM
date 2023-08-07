package com.example.calmatemvvm.ui.positive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.calmatemvvm.app.AppViewModel
import com.example.calmatemvvm.ui.common.BaseViewModel
import org.json.JSONArray
import org.json.JSONException

class PositiveViewModel(private val appViewModel: AppViewModel) : BaseViewModel() {


    private val _quote = MutableLiveData<String>()
    val quote: LiveData<String> = _quote

    private val _author = MutableLiveData<String>()
    val author: LiveData<String> = _author
    fun addFavoriteQuote(quote: String, author: String) :Boolean {
        // CURRENT USER
        val user = appViewModel.getUser()
        val userId = appViewModel.dbHelper.getUserIdByEmail(user!!.email)
        appViewModel.dbHelper.addFavoriteQuote(userId, quote, author)
        return true
    }

    fun fetchQuoteByMood(): JsonArrayRequest {
        val mood = appViewModel.getMood()
        var category = ""
        category = if (mood == "awful") {
            "Failure"
        } else if (mood == "bad") {
            "Anxiety"
        } else if (mood == "so so") {
            "Happiness"
        } else if (mood == "fine") {
            "Courage"
        } else if (mood == "amazing") {
            "Kindness"
        } else {
            "Inspiration"
        }
        val url = "https://zenquotes.io/api/quotes/keyword=" + category
        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response: JSONArray->
                try {
                    val jsonObject = response.getJSONObject(0)
                    val quote = jsonObject.getString("q")
                    val author = jsonObject.getString("a")
                    _quote.value = quote
                    _author.value = author
                    //_isLoading.value = false
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }) { error -> error.printStackTrace() }
        return request
    }
}