package com.example.calmatemvvm.ui.meditationlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calmatemvvm.R
import com.example.calmatemvvm.adapters.MeditationAdapter
import com.example.calmatemvvm.databinding.FragmentMeditationListBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.ui.meditation.MeditationFragmentArgs

class MeditationListFragment : BaseFragment<FragmentMeditationListBinding>() {

    override val viewModel by viewModels {
        MeditationListViewModel()
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentMeditationListBinding {
        return FragmentMeditationListBinding.inflate(inflater).also {
            it.viewModel = viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerMeditationList
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        viewModel.meditationList.observe(viewLifecycleOwner) {
            val meditationAdapter = MeditationAdapter(it)
            recyclerView.adapter = meditationAdapter
        }

        setToolbarVisibility(true)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.createMeditationList()
        viewModel.selectedIndex.observe(this) {
            it?.let {
                val item = viewModel.getSelectedItem(it)?.item
                item?.let { meditation ->
                    val bundle = MeditationFragmentArgs.Builder(meditation.imageUrl, meditation.audioUrl).build()
                    appViewModel.navigationUnit.navigate(R.id.meditationFragment, bundle.toBundle())
                }
            }
        }
    }
}