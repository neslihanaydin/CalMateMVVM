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
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray
import org.json.JSONException

class PositiveFragment : BaseFragment<FragmentPositiveBinding>() {

    private var requestQueue: RequestQueue? = null
    override val viewModel by viewModels {
        PositiveViewModel(appViewModel)
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
        fetchQuoteByMood("fine")
        hideProgressBar()

        binding.btnNextQuote.setOnClickListener(View.OnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.addFav.visibility = View.GONE
            binding.btnNextQuote.visibility = View.GONE
            binding.addFav.isChecked = false
            fetchQuoteByMood("fine")
            hideProgressBar()
        })

        binding.addFav.setOnClickListener(View.OnClickListener {
            val quote = binding.tvQuote.text.toString()
            val author = binding.tvAuthor.text.toString()
            val result = viewModel.addFavoriteQuote(quote, author)
            if (result){
                showSnackbar("Quote added to favorites")
            }
        })

        viewModel.quote.observe(viewLifecycleOwner) {
            binding.tvQuote.text = it
        }

        viewModel.author.observe(viewLifecycleOwner) {
            binding.tvAuthor.text = it
        }
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
        val request = viewModel.fetchQuoteByMood(mood)
        requestQueue?.add<JSONArray>(request)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.addFav.visibility = View.VISIBLE
        binding.btnNextQuote.visibility = View.VISIBLE
    }


}