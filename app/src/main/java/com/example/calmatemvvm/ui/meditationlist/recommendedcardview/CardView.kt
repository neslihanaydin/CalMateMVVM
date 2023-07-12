package com.example.calmatemvvm.ui.meditationlist.recommendedcardview

import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calmatemvvm.R
import com.example.calmatemvvm.adapters.MeditationAdapter
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.HomePageMeditationsBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.example.calmatemvvm.ui.meditation.MeditationFragmentArgs
import com.google.android.material.bottomnavigation.BottomNavigationView

class CardView : BaseFragment<HomePageMeditationsBinding>() {
    override val viewModel by viewModels {
        CardViewViewModel()
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): HomePageMeditationsBinding {
        return HomePageMeditationsBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnViewAll.setOnClickListener{
            appViewModel.navigationUnit.navigate(
                R.id.meditationListFragment,
                null
            )
            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNavigationView?.selectedItemId = R.id.page_meditation
        }

        val recyclerView: RecyclerView = binding.recyclerMeditations
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        viewModel.recommendedMeditationList.observe(viewLifecycleOwner) {
            val meditationAdapter = MeditationAdapter(it)
            recyclerView.adapter = meditationAdapter
        }
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