package com.example.calmatemvvm.ui.positive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.FragmentPositiveBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.example.calmatemvvm.volley.VolleySingleton
import org.json.JSONArray
import org.json.JSONException

class PositiveFragment : BaseFragment<FragmentPositiveBinding>() {

    private var requestQueue: RequestQueue? = null
    override val viewModel by viewModels {
        PositiveViewModel()
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPositiveBinding {
        requestQueue = VolleySingleton.getInstance(context).getRequestQueue()
        fetchQuoteByMood("fine")
        return FragmentPositiveBinding.inflate(inflater).also {
            it.viewModel = viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarVisibility(true)


    }

    private fun fetchDailyQuote() {
        val url = "https://zenquotes.io/api/today"
        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response: JSONArray ->
                try {
                    val jsonObject = response.getJSONObject(0)
                    val quote = jsonObject.getString("q")
                    binding.tvQuote.text = quote
                } catch (e: JSONException) {
                    throw RuntimeException(e)
                }
            }
        ) { error ->
            error.printStackTrace()
        }
        requestQueue?.add<JSONArray>(request)
    }

    private fun fetchQuoteByMood(mood: String) {
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
            { response: JSONArray ->
                try {
                    val jsonObject = response.getJSONObject(0)
                    val quote = jsonObject.getString("q")
                    val author = jsonObject.getString("a")
                    binding.tvQuote.text = quote
                    binding.tvAuthor.text = author
                    binding.tvCategory.text = "Category : " + category
                } catch (e: JSONException) {
                    throw RuntimeException(e)
                }
            }
        ) { error ->
            error.printStackTrace()
        }
        requestQueue?.add<JSONArray>(request)
    }




}