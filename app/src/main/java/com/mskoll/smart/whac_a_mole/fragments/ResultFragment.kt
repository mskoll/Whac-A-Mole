package com.mskoll.smart.whac_a_mole.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mskoll.smart.whac_a_mole.R
import com.mskoll.smart.whac_a_mole.data.CurrentDataViewModel
import com.mskoll.smart.whac_a_mole.data.Score
import com.mskoll.smart.whac_a_mole.data.ScoreViewModel
import com.mskoll.smart.whac_a_mole.databinding.FragmentResultBinding


class ResultFragment : Fragment(R.layout.fragment_result) {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private lateinit var mScoreViewModel: ScoreViewModel
    private val currData: CurrentDataViewModel by activityViewModels()

    var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mScoreViewModel = ViewModelProvider(this).get(ScoreViewModel::class.java)

        currData.currState.value = false

        binding.btnPlayAgain.setOnClickListener {
            currData.currState.value = true
            currData.currScore.value = 0
            findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }

        binding.btnMenu.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }

        mScoreViewModel.getBest.observe(viewLifecycleOwner, { score ->
            binding.txtBestScore.text = score.score.toString()
        })

        currData.currScore.observe(viewLifecycleOwner, { score ->
            this.score = score
            binding.txtScore.text = this.score.toString()
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (score != 0) {
            mScoreViewModel.addScore(Score(0, score))
        }
        _binding = null
    }
}