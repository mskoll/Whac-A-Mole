package com.mskoll.smart.whac_a_mole

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mskoll.smart.whac_a_mole.data.CurrentDataViewModel
import com.mskoll.smart.whac_a_mole.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val currData: CurrentDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currData.currState.value = false

        currData.currState.observe(this, { state ->
            if (state) {
                binding.txtCurrentScore.visibility = View.VISIBLE
                binding.txtCurrentTime.visibility = View.VISIBLE
            } else {
                binding.txtCurrentScore.visibility = View.INVISIBLE
                binding.txtCurrentTime.visibility = View.INVISIBLE
            }
        })

        currData.currScore.observe(this, { score ->
            binding.txtCurrentScore.text = score.toString()
        })

        currData.currTime.observe(this, { time ->
            binding.txtCurrentTime.text = time.toString()
        })
    }

}