package com.mskoll.smart.whac_a_mole.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mskoll.smart.whac_a_mole.R
import com.mskoll.smart.whac_a_mole.data.ScoreViewModel
import com.mskoll.smart.whac_a_mole.databinding.FragmentScoreBinding

class ScoreFragment : Fragment(R.layout.fragment_score) {

    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var mScoreViewModel: ScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScoreBinding.inflate(inflater, container, false)

        val recycleView: RecyclerView = binding.rvScore
        val adapter = ListAdapter()
        recycleView.adapter = adapter
        mScoreViewModel = ViewModelProvider(this).get(ScoreViewModel::class.java)
        mScoreViewModel.getScore.observe(viewLifecycleOwner, { score ->
            adapter.setData(score)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}