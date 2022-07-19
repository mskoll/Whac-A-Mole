package com.mskoll.smart.whac_a_mole.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mskoll.smart.whac_a_mole.R
import com.mskoll.smart.whac_a_mole.data.Score
import com.mskoll.smart.whac_a_mole.databinding.ItemScoreListBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.Holder>() {

    private var scoreList = emptyList<Score>()

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_score_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val binding = ItemScoreListBinding.bind(holder.itemView)
        val currentItem = scoreList[position].score
        binding.txtRVScore.text = currentItem.toString()

    }

    override fun getItemCount(): Int {
        return scoreList.size
    }

    fun setData(score: List<Score>) {
        scoreList = score
        notifyDataSetChanged()
    }

}
