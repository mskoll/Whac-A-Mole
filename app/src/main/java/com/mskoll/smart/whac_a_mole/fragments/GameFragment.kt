package com.mskoll.smart.whac_a_mole.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mskoll.smart.whac_a_mole.R
import com.mskoll.smart.whac_a_mole.data.CurrentDataViewModel
import com.mskoll.smart.whac_a_mole.databinding.FragmentGameBinding

class GameFragment : Fragment(R.layout.fragment_game) {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private val currData: CurrentDataViewModel by activityViewModels()

    private var timer: CountDownTimer? = null

    var score = 0

    private val holes = arrayOf(
        R.id.imgHole0,
        R.id.imgHole1,
        R.id.imgHole2,
        R.id.imgHole3,
        R.id.imgHole4,
        R.id.imgHole5
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_gameFragment_to_scoreFragment)
        }

        binding.btnPlay.setOnClickListener {
            startGame()
            binding.btnPlay.visibility = View.INVISIBLE
            binding.floatingActionButton.visibility = View.INVISIBLE
        }

        binding.imgMole.setOnClickListener {
            score += 1
            currData.currScore.value = score
            binding.imgMole.visibility = View.INVISIBLE
        }

        currData.currState.observe(viewLifecycleOwner, Observer { state ->
            if (state == true) {
                //startGame()
            }
        })
    }

    private fun startGame() {
        currData.currState.value = true
        score = 0
        currData.currScore.value = score
        timer = object : CountDownTimer(31000, 500) {
            override fun onTick(millisUntilFinished: Long) {
                changeMolePosition()
                currData.currTime.value = millisUntilFinished.div(1000).toInt()
            }

            override fun onFinish() {
                findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
            }

        }.start()
    }

    private fun changeMolePosition() {
        binding.imgMole.visibility = View.VISIBLE
        val holeId = holes.random()
        val margin = binding.imgMole.marginBottom
        val layoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.bottomToBottom = holeId
        layoutParams.startToStart = holeId
        layoutParams.endToEnd = holeId
        layoutParams.bottomMargin = margin
        binding.imgMole.layoutParams = layoutParams
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}