package com.example.calmatemvvm.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calmatemvvm.adapters.FavoritesAdapter
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.FragmentFavoritesBinding
import com.example.calmatemvvm.ui.common.BaseFragment

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {

    override val viewModel by viewModels{
        FavoritesViewModel(appViewModel)
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFavoritesBinding {
        return FragmentFavoritesBinding.inflate(inflater).also {
            it.viewModel = viewModel }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerFavoritesList
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.favoriQuotesList.observe(viewLifecycleOwner) {
            val favoritesAdapter = FavoritesAdapter(it)
            recyclerView.adapter = favoritesAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchFavoriteQuotes()
    }

}