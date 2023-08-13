package com.example.calmatemvvm.ui.askmood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.CardAskMoodBinding
import com.example.calmatemvvm.ui.common.BaseFragment


class AskMoodFragment : BaseFragment<CardAskMoodBinding>() {

    override val viewModel by viewModels{
        AskMoodViewModel(appViewModel)
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): CardAskMoodBinding {
        return CardAskMoodBinding.inflate(inflater).also {
            it.viewModel = viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgSad.setOnClickListener {
            makeBackgroundInvisible()
            binding.fullSad.visibility = View.VISIBLE
        }
        binding.imgMood.setOnClickListener {
            makeBackgroundInvisible()
            binding.fullMood.visibility = View.VISIBLE
        }
        binding.imgCalm.setOnClickListener {
            makeBackgroundInvisible()
            binding.fullCalm.visibility = View.VISIBLE
        }
        binding.imgContent.setOnClickListener {
            makeBackgroundInvisible()
            binding.fullContent.visibility = View.VISIBLE
        }
        binding.imgExcited.setOnClickListener {
            makeBackgroundInvisible()
            binding.fullExcited.visibility = View.VISIBLE
        }
       binding.btnSendMood.setOnClickListener {
           val mood = when {
               binding.fullSad.visibility == View.VISIBLE -> "awful"
               binding.fullMood.visibility == View.VISIBLE -> "bad"
               binding.fullCalm.visibility == View.VISIBLE -> "so so"
               binding.fullContent.visibility == View.VISIBLE -> "fine"
               binding.fullExcited.visibility == View.VISIBLE -> "amazing"
               else -> "fine"
           }
           appViewModel.setMood(mood)
           appViewModel.navigationUnit.navigate(
               com.example.calmatemvvm.R.id.positiveFragment,
               null
           )
       }
    }

    private fun makeBackgroundInvisible(){
        binding.fullSad.visibility = View.INVISIBLE
        binding.fullMood.visibility = View.INVISIBLE
        binding.fullCalm.visibility = View.INVISIBLE
        binding.fullContent.visibility = View.INVISIBLE
        binding.fullExcited.visibility = View.INVISIBLE
    }

}