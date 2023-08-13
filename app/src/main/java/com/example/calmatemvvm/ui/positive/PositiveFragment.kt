package com.example.calmatemvvm.ui.positive

import android.os.Bundle
import android.os.Handler
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
    private val handler = Handler()
    private val progressDurationMillis = 1300L
    override val viewModel by viewModels {
        PositiveViewModel(appViewModel)
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPositiveBinding {
        requestQueue = VolleySingleton.getInstance(context).getRequestQueue()
        fetchQuoteByMood()

        return FragmentPositiveBinding.inflate(inflater).also {
            it.viewModel = viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarVisibility(true)
        showProgressBar()
        fetchQuoteByMood()
        handler.postDelayed({
            hideProgressBar()
        }, progressDurationMillis)
        binding.btnNextQuote.setOnClickListener(View.OnClickListener {
            showProgressBar()

            binding.addFav.isChecked = false
            fetchQuoteByMood()
            // Delay hiding the progress bar for 2 seconds
            handler.postDelayed({
                hideProgressBar()
            }, progressDurationMillis)
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

    private fun fetchQuoteByMood() {
        val request = viewModel.fetchQuoteByMood()
        requestQueue?.add<JSONArray>(request)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.addFav.visibility = View.GONE
        binding.btnNextQuote.visibility = View.GONE
        binding.tvQuote.visibility = View.GONE
        binding.tvAuthor.visibility = View.GONE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.addFav.visibility = View.VISIBLE
        binding.btnNextQuote.visibility = View.VISIBLE
        binding.tvQuote.visibility = View.VISIBLE
        binding.tvAuthor.visibility = View.VISIBLE
    }


}