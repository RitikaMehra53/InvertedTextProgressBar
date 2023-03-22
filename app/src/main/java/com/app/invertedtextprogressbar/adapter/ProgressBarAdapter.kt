package com.app.invertedtextprogressbar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.invertedtextprogressbar.model.ProgressBarDataModel
import com.app.invertedtextprogressbar.R
import com.app.invertedtextprogressbar.databinding.ProgressBarItemBinding

class ProgressBarAdapter(
    progressData: ArrayList<ProgressBarDataModel>,
) :
    RecyclerView.Adapter<ProgressBarAdapter.ViewHolder>() {

    private var mProgressBarData = progressData
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ProgressBarItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.progress_bar_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mProgressBarData[position])
    }

    override fun getItemCount(): Int {
        return mProgressBarData.size
    }

    inner class ViewHolder(var itemRowBinding: ProgressBarItemBinding) :
        RecyclerView.ViewHolder(itemRowBinding.root) {
        fun bind(progressBarDataModel: ProgressBarDataModel) {
            itemRowBinding.executePendingBindings()
            itemRowBinding.tvTitle.text = progressBarDataModel.title
            itemRowBinding.tvAcceptedValue.text = progressBarDataModel.acceptedValue
            itemRowBinding.tvAcceptedValueWhite.text = progressBarDataModel.acceptedValue
            itemRowBinding.tvPrecentedValue.text = progressBarDataModel.presentedValue
            itemRowBinding.tvPrecentedValueWhite.text = progressBarDataModel.presentedValue

            itemRowBinding.tvPercentageValue.text = "${progressBarDataModel.percentage}%"

            itemRowBinding.progressBar.progress = progressBarDataModel.percentage

            itemRowBinding.progressBar.post {
                val width: Int = itemRowBinding.progressBar.width
                val bounds = (width * itemRowBinding.progressBar.progress) / 100
                val params: ConstraintLayout.LayoutParams =
                    itemRowBinding.cardTopHolder.layoutParams as ConstraintLayout.LayoutParams
                params.width = bounds
                itemRowBinding.cardTopHolder.layoutParams = params

                val boundsP = (width * (100 - itemRowBinding.progressBar.progress)) / 100
                val paramsP: ConstraintLayout.LayoutParams =
                    itemRowBinding.cardTopHolderP.layoutParams as ConstraintLayout.LayoutParams
                paramsP.width = itemRowBinding.tvPrecentedValue.width - boundsP
                itemRowBinding.cardTopHolderP.layoutParams = paramsP
            }
        }

    }
}